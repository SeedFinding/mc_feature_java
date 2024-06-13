package com.seedfinding.mcfeature.loot.function;

import com.seedfinding.mccore.util.data.Pair;
import com.seedfinding.mcfeature.loot.LootContext;
import com.seedfinding.mcfeature.loot.enchantment.Enchantment;
import com.seedfinding.mcfeature.loot.enchantment.Enchantments;
import com.seedfinding.mcfeature.loot.item.Item;
import com.seedfinding.mcfeature.loot.item.ItemStack;
import com.seedfinding.mcfeature.loot.item.Items;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.seedfinding.mcfeature.loot.enchantment.Enchantments.ARMOR;

public class EnchantRandomlyFunction extends EnchantmentFunction {
	private List<Enchantment> applicableEnchantments = new ArrayList<>();

	public EnchantRandomlyFunction(Item item) {
		super(item);
	}

	public EnchantRandomlyFunction(Item item, boolean isTreasure) {
		super(item, isTreasure);
	}

	public EnchantRandomlyFunction(Item item, boolean isTreasure, boolean isDiscoverable) {
		super(item, isTreasure, isDiscoverable);
	}


	public EnchantmentFunction applyEnchantment(List<Enchantment> enchantments) {
		this.applicableEnchantments = enchantments.stream()
			.filter(Enchantment::isDiscoverable)
			.collect(Collectors.toList());
		return this;
	}
	private static final HashSet<String> DAMAGEABLE_ITEMS=new HashSet<String>(){{
		add(Items.FLINT_AND_STEEL.getName());
		add(Items.BOW.getName());
		add(Items.FISHING_ROD.getName());
		add(Items.SHEARS.getName());
		add(Items.CARROT_ON_A_STICK.getName());
		add(Items.SHIELD.getName());
		add(Items.ELYTRA.getName());
		add(Items.TRIDENT.getName());
		add(Items.CROSSBOW.getName());
	}};

	private static final HashSet<String> DAMAGE_ENCHANTS=new HashSet<String>(){{
		add("sharpness");
		add("smite");
		add("bane_of_arthropods");
	}};
	/**
	 * Process the overrides, there is currently only 4 of them
	 *  - THORNS: apply to all armor instead of just chestplate
	 *  - DAMAGE: apply to axe item
	 *  - EFFICIENCY: apply to shears
	 *  - UNBREAKING: apply to all damageable items
	 *
	 * @param enchantment the enchantment to consider
	 * @param item the item to consider
	 * @return a boolean indicating if this enchantment should be kept for this item
	 */
	private boolean processOverrides(Enchantment enchantment, Item item) {
		if(enchantment.getName().equals("thorns")) {
			// thorns allow either ARMOR_CHEST in the normal type or ARMOR in the override,
			// since ARMOR_CHEST is a subset this is simplified
			return ARMOR.contains(item.getName().toUpperCase(Locale.ROOT));
		}
		if (DAMAGE_ENCHANTS.contains(enchantment.getName())){
			if (item.getName().endsWith("_axe")){
				return true;
			}
		}
		if (enchantment.getName().equals("efficiency")){
			if (item.equalsName(Items.SHEARS)){
				return true;
			}
		}
		if (enchantment.getName().equals("unbreaking")){
			if (DAMAGEABLE_ITEMS.contains(item.getName())){
				return true;
			}
		}
		// default case, this is the standard function
		return Enchantments.getCategories(new ItemStack(item)).contains(enchantment.getCategory());
	}

	@Override
	public ItemStack process(ItemStack baseStack, LootContext context) {
		Item newItem = baseStack.getItem();
		List<Enchantment> enchantments = this.applicableEnchantments.stream()
			.filter(enchantment -> newItem.equalsName(Items.BOOK) || processOverrides(enchantment,newItem))
			.collect(Collectors.toList());
		if(enchantments.isEmpty()) return baseStack;
		int enchantNr = context.nextInt(enchantments.size());
		Enchantment enchantment = enchantments.get(enchantNr);
		int level = 1;
		if(!(Enchantments.SingleEnchants.contains(enchantment.getName()))) {
			level = context.nextInt(enchantment.getMaxLevel()) + 1;
		}
		newItem.addEnchantment(new Pair<>(enchantment.getName(), level));
		return new ItemStack(newItem, baseStack.getCount());
	}
}
