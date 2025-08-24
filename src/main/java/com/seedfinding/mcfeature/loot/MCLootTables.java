package com.seedfinding.mcfeature.loot;

import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.condition.BiomeCondition;
import com.seedfinding.mcfeature.loot.condition.OpenWaterCondition;
import com.seedfinding.mcfeature.loot.effect.Effects;
import com.seedfinding.mcfeature.loot.entry.EmptyEntry;
import com.seedfinding.mcfeature.loot.entry.ItemEntry;
import com.seedfinding.mcfeature.loot.entry.TableEntry;
import com.seedfinding.mcfeature.loot.function.ApplyDamageFunction;
import com.seedfinding.mcfeature.loot.function.EffectFunction;
import com.seedfinding.mcfeature.loot.function.EnchantRandomlyFunction;
import com.seedfinding.mcfeature.loot.function.EnchantWithLevelsFunction;
import com.seedfinding.mcfeature.loot.function.SetCountFunction;
import com.seedfinding.mcfeature.loot.function.SetStewEffectFunction;
import com.seedfinding.mcfeature.loot.item.Items;
import com.seedfinding.mcfeature.loot.roll.ConstantRoll;
import com.seedfinding.mcfeature.loot.roll.UniformRoll;

import java.util.HashMap;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class MCLootTables {

	public static final Supplier<LootTable> NULL = LootTable::new;

	public static final Supplier<LootTable> ABANDONED_MINESHAFT_CHEST = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.GOLDEN_APPLE, 20),
			new ItemEntry(Items.ENCHANTED_GOLDEN_APPLE),
			new ItemEntry(Items.NAME_TAG, 30),
			new ItemEntry(Items.ENCHANTED_BOOK, 10).apply(version -> new EnchantRandomlyFunction(Items.ENCHANTED_BOOK).apply(version)),
			new ItemEntry(Items.IRON_PICKAXE, 5),
			new EmptyEntry(5)),
		new LootPool(new UniformRoll(2.0F, 4.0F),
			new ItemEntry(Items.IRON_INGOT, 10).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.GOLD_INGOT, 5).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.REDSTONE, 5).apply(version -> SetCountFunction.uniform(4.0F, 9.0F)),
			new ItemEntry(Items.LAPIS_LAZULI, 5).apply(version -> SetCountFunction.uniform(4.0F, 9.0F)),
			new ItemEntry(Items.DIAMOND, 3).apply(version -> SetCountFunction.uniform(1.0F, 2.0F)),
			new ItemEntry(Items.COAL, 10).apply(version -> SetCountFunction.uniform(3.0F, 8.0F)),
			new ItemEntry(Items.BREAD, 15).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.GLOW_BERRIES, 15).apply(version -> SetCountFunction.uniform(3.0F, 6.0F))
				.introducedVersion(MCVersion.v1_17),
			new ItemEntry(Items.MELON_SEEDS, 10).apply(version -> SetCountFunction.uniform(2.0F, 4.0F)),
			new ItemEntry(Items.PUMPKIN_SEEDS, 10).apply(version -> SetCountFunction.uniform(2.0F, 4.0F)),
			new ItemEntry(Items.BEETROOT_SEEDS, 10).apply(version -> SetCountFunction.uniform(2.0F, 4.0F))),
		new LootPool(new ConstantRoll(3),
			new ItemEntry(Items.RAIL, 20).apply(version -> SetCountFunction.uniform(4.0F, 8.0F)),
			new ItemEntry(Items.POWERED_RAIL, 5).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.DETECTOR_RAIL, 5).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.ACTIVATOR_RAIL, 5).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.TORCH, 15).apply(version -> SetCountFunction.uniform(1.0F, 16.0F)))
	);

	public static final Supplier<LootTable> BASTION_BRIDGE_CHEST = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.LODESTONE).apply(version -> SetCountFunction.constant(1))),
		new LootPool(new UniformRoll(1.0F, 2.0F),
			new ItemEntry(Items.CROSSBOW).apply(version -> new ApplyDamageFunction(), version -> new EnchantRandomlyFunction(Items.CROSSBOW).apply(version)),
			new ItemEntry(Items.SPECTRAL_ARROW).apply(version -> SetCountFunction.uniform(2.0F, 12.0F)),
			new ItemEntry(Items.GILDED_BLACKSTONE).apply(version -> SetCountFunction.uniform(5.0F, 8.0F)),
			new ItemEntry(Items.CRYING_OBSIDIAN).apply(version -> SetCountFunction.uniform(3.0F, 8.0F)),
			new ItemEntry(Items.GOLD_BLOCK).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.GOLD_INGOT).apply(version -> SetCountFunction.uniform(2.0F, 8.0F)),
			new ItemEntry(Items.IRON_INGOT).apply(version -> SetCountFunction.uniform(2.0F, 8.0F)),
			new ItemEntry(Items.GOLDEN_SWORD).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.GOLDEN_CHESTPLATE).apply(version -> SetCountFunction.constant(1)).apply(version -> new EnchantRandomlyFunction(Items.GOLDEN_CHESTPLATE).apply(version)),
			new ItemEntry(Items.GOLDEN_HELMET).apply(version -> SetCountFunction.constant(1)).apply(version -> new EnchantRandomlyFunction(Items.GOLDEN_HELMET).apply(version)),
			new ItemEntry(Items.GOLDEN_LEGGINGS).apply(version -> SetCountFunction.constant(1)).apply(version -> new EnchantRandomlyFunction(Items.GOLDEN_LEGGINGS).apply(version)),
			new ItemEntry(Items.GOLDEN_BOOTS).apply(version -> SetCountFunction.constant(1)).apply(version -> new EnchantRandomlyFunction(Items.GOLDEN_BOOTS).apply(version))),
		new LootPool(new UniformRoll(2.0F, 4.0F),
			new ItemEntry(Items.STRING).apply(version -> SetCountFunction.uniform(1.0F, 6.0F)),
			new ItemEntry(Items.LEATHER).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.ARROW).apply(version -> SetCountFunction.uniform(5.0F, 17.0F)),
			new ItemEntry(Items.IRON_NUGGET).apply(version -> SetCountFunction.uniform(2.0F, 6.0F)),
			new ItemEntry(Items.GOLD_NUGGET).apply(version -> SetCountFunction.uniform(2.0F, 6.0F)))
	);

	public static final Supplier<LootTable> BASTION_HOGLIN_STABLE_CHEST = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.DIAMOND_SHOVEL, 5).apply(version -> new ApplyDamageFunction(), version -> new EnchantRandomlyFunction(Items.DIAMOND_SHOVEL).apply(version)),
			new ItemEntry(Items.NETHERITE_SCRAP, 2).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.ANCIENT_DEBRIS, 3).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.SADDLE, 10).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.GOLD_BLOCK, 25).apply(version -> SetCountFunction.uniform(2.0F, 4.0F)),
			new ItemEntry(Items.GOLDEN_HOE, 15).apply(version -> SetCountFunction.constant(1)).apply(version -> new EnchantRandomlyFunction(Items.GOLDEN_HOE).apply(version)),
			new EmptyEntry(45)),
		new LootPool(new UniformRoll(3.0F, 4.0F),
			new ItemEntry(Items.GLOWSTONE).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.GILDED_BLACKSTONE).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.SOUL_SAND).apply(version -> SetCountFunction.uniform(2.0F, 7.0F)),
			new ItemEntry(Items.CRIMSON_NYLIUM).apply(version -> SetCountFunction.uniform(2.0F, 7.0F)),
			new ItemEntry(Items.GOLD_NUGGET).apply(version -> SetCountFunction.uniform(2.0F, 8.0F)),
			new ItemEntry(Items.LEATHER).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.ARROW).apply(version -> SetCountFunction.uniform(5.0F, 17.0F)),
			new ItemEntry(Items.STRING).apply(version -> SetCountFunction.uniform(3.0F, 8.0F)),
			new ItemEntry(Items.PORKCHOP).apply(version -> SetCountFunction.uniform(2.0F, 5.0F)),
			new ItemEntry(Items.COOKED_PORKCHOP).apply(version -> SetCountFunction.uniform(2.0F, 5.0F)),
			new ItemEntry(Items.CRIMSON_FUNGUS).apply(version -> SetCountFunction.uniform(2.0F, 7.0F)),
			new ItemEntry(Items.CRIMSON_ROOTS).apply(version -> SetCountFunction.uniform(2.0F, 7.0F)))
	);

	public static final Supplier<LootTable> BASTION_OTHER_CHEST = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.CROSSBOW, 12).apply(version -> new ApplyDamageFunction(), version -> new EnchantRandomlyFunction(Items.CROSSBOW).apply(version)),
			new ItemEntry(Items.ANCIENT_DEBRIS, 2).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.NETHERITE_SCRAP, 2).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.SPECTRAL_ARROW, 16).apply(version -> SetCountFunction.uniform(2.0F, 15.0F)),
			new ItemEntry(Items.PIGLIN_BANNER_PATTERN, 5).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.MUSIC_DISC_PIGSTEP, 3).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.ENCHANTED_BOOK, 10).apply(version -> new EnchantRandomlyFunction(Items.ENCHANTED_BOOK, true, false).apply(version)),
			new EmptyEntry(50)),
		new LootPool(new ConstantRoll(2),
			new ItemEntry(Items.GOLDEN_BOOTS).apply(version -> SetCountFunction.constant(1)).apply(version -> new EnchantRandomlyFunction(Items.GOLDEN_BOOTS, true, false).apply(version)),
			new ItemEntry(Items.GOLD_BLOCK).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.CROSSBOW).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.GOLD_INGOT).apply(version -> SetCountFunction.uniform(1.0F, 6.0F)),
			new ItemEntry(Items.IRON_INGOT).apply(version -> SetCountFunction.uniform(1.0F, 6.0F)),
			new ItemEntry(Items.GOLDEN_SWORD).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.GOLDEN_CHESTPLATE).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.GOLDEN_HELMET).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.GOLDEN_LEGGINGS).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.GOLDEN_BOOTS).apply(version -> SetCountFunction.constant(1)),
			new EmptyEntry(2)),
		new LootPool(new UniformRoll(3.0F, 5.0F),
			new ItemEntry(Items.CRYING_OBSIDIAN).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.GILDED_BLACKSTONE).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.CHAIN).apply(version -> SetCountFunction.uniform(2.0F, 10.0F)),
			new ItemEntry(Items.MAGMA_CREAM).apply(version -> SetCountFunction.uniform(2.0F, 6.0F)),
			new ItemEntry(Items.BONE_BLOCK).apply(version -> SetCountFunction.uniform(3.0F, 6.0F)),
			new ItemEntry(Items.IRON_NUGGET).apply(version -> SetCountFunction.uniform(2.0F, 8.0F)),
			new ItemEntry(Items.OBSIDIAN).apply(version -> SetCountFunction.uniform(4.0F, 6.0F)),
			new ItemEntry(Items.GOLD_NUGGET).apply(version -> SetCountFunction.uniform(2.0F, 8.0F)),
			new ItemEntry(Items.STRING).apply(version -> SetCountFunction.uniform(4.0F, 6.0F)),
			new ItemEntry(Items.ARROW, 2).apply(version -> SetCountFunction.uniform(5.0F, 17.0F)))
	);

	public static final Supplier<LootTable> BASTION_TREASURE_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(1.0F, 2.0F),
			new ItemEntry(Items.NETHERITE_INGOT, 10).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.ANCIENT_DEBRIS, 14).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.NETHERITE_SCRAP, 10).apply(version -> SetCountFunction.constant(1)),
			new ItemEntry(Items.ANCIENT_DEBRIS).apply(version -> SetCountFunction.constant(2)),
			new ItemEntry(Items.DIAMOND_SWORD, 10).apply(version -> new ApplyDamageFunction(), version -> new EnchantRandomlyFunction(Items.DIAMOND_SWORD).apply(version)),
			new ItemEntry(Items.DIAMOND_CHESTPLATE, 6).apply(version -> new ApplyDamageFunction(), version -> new EnchantRandomlyFunction(Items.DIAMOND_CHESTPLATE).apply(version)),
			new ItemEntry(Items.DIAMOND_HELMET, 6).apply(version -> new ApplyDamageFunction(), version -> new EnchantRandomlyFunction(Items.DIAMOND_HELMET).apply(version)),
			new ItemEntry(Items.DIAMOND_LEGGINGS, 6).apply(version -> new ApplyDamageFunction(), version -> new EnchantRandomlyFunction(Items.DIAMOND_LEGGINGS).apply(version)),
			new ItemEntry(Items.DIAMOND_BOOTS, 6).apply(version -> new ApplyDamageFunction(), version -> new EnchantRandomlyFunction(Items.DIAMOND_BOOTS).apply(version)),
			new ItemEntry(Items.DIAMOND_SWORD, 6).apply(version -> new ApplyDamageFunction()),
			new ItemEntry(Items.DIAMOND_CHESTPLATE, 5).apply(version -> new ApplyDamageFunction()),
			new ItemEntry(Items.DIAMOND_HELMET, 5).apply(version -> new ApplyDamageFunction()),
			new ItemEntry(Items.DIAMOND_BOOTS, 5).apply(version -> new ApplyDamageFunction()),
			new ItemEntry(Items.DIAMOND_LEGGINGS, 5).apply(version -> new ApplyDamageFunction()),
			new ItemEntry(Items.DIAMOND, 5).apply(version -> SetCountFunction.uniform(1.0F, 3.0F))),
		new LootPool(new UniformRoll(2.0F, 4.0F),
			new ItemEntry(Items.SPECTRAL_ARROW).apply(version -> SetCountFunction.uniform(5.0F, 21.0F)),
			new ItemEntry(Items.GOLD_BLOCK).apply(version -> SetCountFunction.uniform(2.0F, 5.0F)),
			new ItemEntry(Items.GOLD_INGOT).apply(version -> SetCountFunction.uniform(3.0F, 9.0F)),
			new ItemEntry(Items.IRON_INGOT).apply(version -> SetCountFunction.uniform(3.0F, 9.0F)),
			new ItemEntry(Items.CRYING_OBSIDIAN).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.QUARTZ).apply(version -> SetCountFunction.uniform(8.0F, 23.0F)),
			new ItemEntry(Items.GILDED_BLACKSTONE).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.MAGMA_CREAM).apply(version -> SetCountFunction.uniform(2.0F, 8.0F)),
			new ItemEntry(Items.IRON_NUGGET).apply(version -> SetCountFunction.uniform(8.0F, 16.0F)))
	);

	public static final Supplier<LootTable> BURIED_TREASURE_CHEST = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.HEART_OF_THE_SEA)),
		new LootPool(new UniformRoll(5.0F, 8.0F),
			new ItemEntry(Items.IRON_INGOT, 20).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.GOLD_INGOT, 10).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.TNT, 5).apply(version -> SetCountFunction.uniform(1.0F, 2.0F))),
		new LootPool(new UniformRoll(1.0F, 3.0F),
			new ItemEntry(Items.EMERALD, 5).apply(version -> SetCountFunction.uniform(4.0F, 8.0F)),
			new ItemEntry(Items.DIAMOND, 5).apply(version -> SetCountFunction.uniform(1.0F, 2.0F)),
			new ItemEntry(Items.PRISMARINE_CRYSTALS, 5).apply(version -> SetCountFunction.uniform(1.0F, 5.0F))),
		new LootPool(new UniformRoll(0.0F, 1.0F),
			new ItemEntry(Items.LEATHER_CHESTPLATE),
			new ItemEntry(Items.IRON_SWORD)),
		new LootPool(new ConstantRoll(2),
			new ItemEntry(Items.COOKED_COD).apply(version -> SetCountFunction.uniform(2.0F, 4.0F)),
			new ItemEntry(Items.COOKED_SALMON).apply(version -> SetCountFunction.uniform(2.0F, 4.0F)))
	);

	public static final Supplier<LootTable> DESERT_PYRAMID_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(2.0F, 4.0F),
			new ItemEntry(Items.DIAMOND, 5).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.IRON_INGOT, 15).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.GOLD_INGOT, 15).apply(version -> SetCountFunction.uniform(2.0F, 7.0F)),
			new ItemEntry(Items.EMERALD, 15).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.BONE, 25).apply(version -> SetCountFunction.uniform(4.0F, 6.0F)),
			new ItemEntry(Items.SPIDER_EYE, 25).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.ROTTEN_FLESH, 25).apply(version -> SetCountFunction.uniform(3.0F, 7.0F)),
			new ItemEntry(Items.SADDLE, 20),
			new ItemEntry(Items.IRON_HORSE_ARMOR, 15),
			new ItemEntry(Items.GOLDEN_HORSE_ARMOR, 10),
			new ItemEntry(Items.DIAMOND_HORSE_ARMOR, 5),
			new ItemEntry(Items.ENCHANTED_BOOK, 20).apply(version -> new EnchantRandomlyFunction(Items.ENCHANTED_BOOK).apply(version)),
			new ItemEntry(Items.GOLDEN_APPLE, 20),
			new ItemEntry(Items.ENCHANTED_GOLDEN_APPLE, 2),
			new EmptyEntry(15)),
		new LootPool(new ConstantRoll(4),
			new ItemEntry(Items.BONE, 10).apply(version -> SetCountFunction.uniform(1.0F, 8.0F)),
			new ItemEntry(Items.GUNPOWDER, 10).apply(version -> SetCountFunction.uniform(1.0F, 8.0F)),
			new ItemEntry(Items.ROTTEN_FLESH, 10).apply(version -> SetCountFunction.uniform(1.0F, 8.0F)),
			new ItemEntry(Items.STRING, 10).apply(version -> SetCountFunction.uniform(1.0F, 8.0F)),
			new ItemEntry(Items.SAND, 10).apply(version -> SetCountFunction.uniform(1.0F, 8.0F)))
	);

	public static final Supplier<LootTable> END_CITY_TREASURE_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(2.0F, 6.0F),
			new ItemEntry(Items.DIAMOND, 5).apply(version -> SetCountFunction.uniform(2.0F, 7.0F)),
			new ItemEntry(Items.IRON_INGOT, 10).apply(version -> SetCountFunction.uniform(4.0F, 8.0F)),
			new ItemEntry(Items.GOLD_INGOT, 15).apply(version -> SetCountFunction.uniform(2.0F, 7.0F)),
			new ItemEntry(Items.EMERALD, 2).apply(version -> SetCountFunction.uniform(2.0F, 6.0F)),
			new ItemEntry(Items.BEETROOT_SEEDS, 5).apply(version -> SetCountFunction.uniform(1.0F, 10.0F)),
			new ItemEntry(Items.SADDLE, 3),
			new ItemEntry(Items.IRON_HORSE_ARMOR),
			new ItemEntry(Items.GOLDEN_HORSE_ARMOR),
			new ItemEntry(Items.DIAMOND_HORSE_ARMOR),
			new ItemEntry(Items.DIAMOND_SWORD, 3).apply(version -> new EnchantWithLevelsFunction(Items.DIAMOND_SWORD, 20, 39, true).apply(version)),
			new ItemEntry(Items.DIAMOND_BOOTS, 3).apply(version -> new EnchantWithLevelsFunction(Items.DIAMOND_BOOTS, 20, 39, true).apply(version)),
			new ItemEntry(Items.DIAMOND_CHESTPLATE, 3).apply(version -> new EnchantWithLevelsFunction(Items.DIAMOND_CHESTPLATE, 20, 39, true).apply(version)),
			new ItemEntry(Items.DIAMOND_LEGGINGS, 3).apply(version -> new EnchantWithLevelsFunction(Items.DIAMOND_LEGGINGS, 20, 39, true).apply(version)),
			new ItemEntry(Items.DIAMOND_HELMET, 3).apply(version -> new EnchantWithLevelsFunction(Items.DIAMOND_HELMET, 20, 39, true).apply(version)),
			new ItemEntry(Items.DIAMOND_PICKAXE, 3).apply(version -> new EnchantWithLevelsFunction(Items.DIAMOND_PICKAXE, 20, 39, true).apply(version)),
			new ItemEntry(Items.DIAMOND_SHOVEL, 3).apply(version -> new EnchantWithLevelsFunction(Items.DIAMOND_SHOVEL, 20, 39, true).apply(version)),
			new ItemEntry(Items.IRON_SWORD, 3).apply(version -> new EnchantWithLevelsFunction(Items.IRON_SWORD, 20, 39, true).apply(version)),
			new ItemEntry(Items.IRON_BOOTS, 3).apply(version -> new EnchantWithLevelsFunction(Items.IRON_BOOTS, 20, 39, true).apply(version)),
			new ItemEntry(Items.IRON_CHESTPLATE, 3).apply(version -> new EnchantWithLevelsFunction(Items.IRON_CHESTPLATE, 20, 39, true).apply(version)),
			new ItemEntry(Items.IRON_LEGGINGS, 3).apply(version -> new EnchantWithLevelsFunction(Items.IRON_LEGGINGS, 20, 39, true).apply(version)),
			new ItemEntry(Items.IRON_HELMET, 3).apply(version -> new EnchantWithLevelsFunction(Items.IRON_HELMET, 20, 39, true).apply(version)),
			new ItemEntry(Items.IRON_PICKAXE, 3).apply(version -> new EnchantWithLevelsFunction(Items.IRON_PICKAXE, 20, 39, true).apply(version)),
			new ItemEntry(Items.IRON_SHOVEL, 3).apply(version -> new EnchantWithLevelsFunction(Items.IRON_SHOVEL, 20, 39, true).apply(version))
		)
	);

	public static final Supplier<LootTable> IGLOO_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(2.0F, 8.0F),
			new ItemEntry(Items.APPLE, 15).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.COAL, 15).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.GOLD_NUGGET, 10).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.STONE_AXE, 2),
			new ItemEntry(Items.ROTTEN_FLESH, 10),
			new ItemEntry(Items.EMERALD),
			new ItemEntry(Items.WHEAT, 10).apply(version -> SetCountFunction.uniform(2.0F, 3.0F))
		),
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.GOLDEN_APPLE)
		)
	);

	public static final Supplier<LootTable> JUNGLE_TEMPLE_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(2.0F, 6.0F),
			new ItemEntry(Items.DIAMOND, 3).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.IRON_INGOT, 10).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.GOLD_INGOT, 15).apply(version -> SetCountFunction.uniform(2.0F, 7.0F)),
			new ItemEntry(Items.BAMBOO, 15).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.EMERALD, 2).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.BONE, 20).apply(version -> SetCountFunction.uniform(4.0F, 6.0F)),
			new ItemEntry(Items.ROTTEN_FLESH, 16).apply(version -> SetCountFunction.uniform(3.0F, 7.0F)),
			new ItemEntry(Items.SADDLE, 3),
			new ItemEntry(Items.IRON_HORSE_ARMOR),
			new ItemEntry(Items.GOLDEN_HORSE_ARMOR),
			new ItemEntry(Items.DIAMOND_HORSE_ARMOR),
			new ItemEntry(Items.BOOK).apply(version -> new EnchantWithLevelsFunction(Items.BOOK, 30, 30, true).apply(version)))
	);

	public static final Supplier<LootTable> JUNGLE_TEMPLE_DISPENSER_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(1.0F, 2.0F),
			new ItemEntry(Items.ARROW, 30).apply(version -> SetCountFunction.uniform(2.0F, 7.0F)))
	);

	public static final Supplier<LootTable> NETHER_BRIDGE_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(2.0F, 4.0F),
			new ItemEntry(Items.DIAMOND, 5).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.IRON_INGOT, 5).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.GOLD_INGOT, 15).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.GOLDEN_SWORD, 5),
			new ItemEntry(Items.GOLDEN_CHESTPLATE, 5),
			new ItemEntry(Items.FLINT_AND_STEEL, 5),
			new ItemEntry(Items.NETHER_WART, 5).apply(version -> SetCountFunction.uniform(3.0F, 7.0F)),
			new ItemEntry(Items.SADDLE, 10),
			new ItemEntry(Items.GOLDEN_HORSE_ARMOR, 8),
			new ItemEntry(Items.IRON_HORSE_ARMOR, 5),
			new ItemEntry(Items.DIAMOND_HORSE_ARMOR, 3),
			new ItemEntry(Items.OBSIDIAN, 2).apply(version -> SetCountFunction.uniform(2.0F, 4.0F)))
	);

	public static final Supplier<LootTable> PILLAGER_OUTPOST_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(0.0F, 1.0F),
			new ItemEntry(Items.CROSSBOW)),
		new LootPool(new UniformRoll(2.0F, 3.0F),
			new ItemEntry(Items.WHEAT, 7).apply(version -> SetCountFunction.uniform(3.0F, 5.0F)),
			new ItemEntry(Items.POTATO, 5).apply(version -> SetCountFunction.uniform(2.0F, 5.0F)),
			new ItemEntry(Items.CARROT, 5).apply(version -> SetCountFunction.uniform(3.0F, 5.0F))),
		new LootPool(new UniformRoll(1.0F, 3.0F),
			new ItemEntry(Items.DARK_OAK_LOG).apply(version -> SetCountFunction.uniform(2.0F, 3.0F))),
		new LootPool(new UniformRoll(2.0F, 3.0F),
			new ItemEntry(Items.EXPERIENCE_BOTTLE, 7),
			new ItemEntry(Items.STRING, 4).apply(version -> SetCountFunction.uniform(1.0F, 6.0F)),
			new ItemEntry(Items.ARROW, 4).apply(version -> SetCountFunction.uniform(2.0F, 7.0F)),
			new ItemEntry(Items.TRIPWIRE_HOOK, 3).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.IRON_INGOT, 3).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.ENCHANTED_BOOK).apply(version -> new EnchantRandomlyFunction(Items.ENCHANTED_BOOK).apply(version)))
	);

	public static final Supplier<LootTable> RUINED_PORTAL_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(4.0F, 8.0F),
			new ItemEntry(Items.OBSIDIAN, 40).apply(version -> SetCountFunction.uniform(1.0F, 2.0F)),
			new ItemEntry(Items.FLINT, 40).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.IRON_NUGGET, 40).apply(version -> SetCountFunction.uniform(9.0F, 18.0F)),
			new ItemEntry(Items.FLINT_AND_STEEL, 40),
			new ItemEntry(Items.FIRE_CHARGE, 40),
			new ItemEntry(Items.GOLDEN_APPLE, 15),
			new ItemEntry(Items.GOLD_NUGGET, 15).apply(version -> SetCountFunction.uniform(4.0F, 24.0F)),
			new ItemEntry(Items.GOLDEN_SWORD, 15).apply(version -> new EnchantRandomlyFunction(Items.GOLDEN_SWORD, true).apply(version)),
			new ItemEntry(Items.GOLDEN_AXE, 15).apply(version -> new EnchantRandomlyFunction(Items.GOLDEN_AXE, true).apply(version)),
			new ItemEntry(Items.GOLDEN_HOE, 15).apply(version -> new EnchantRandomlyFunction(Items.GOLDEN_HOE, true).apply(version)),
			new ItemEntry(Items.GOLDEN_SHOVEL, 15).apply(version -> new EnchantRandomlyFunction(Items.GOLDEN_SHOVEL, true).apply(version)),
			new ItemEntry(Items.GOLDEN_PICKAXE, 15).apply(version -> new EnchantRandomlyFunction(Items.GOLDEN_PICKAXE, true).apply(version)),
			new ItemEntry(Items.GOLDEN_BOOTS, 15).apply(version -> new EnchantRandomlyFunction(Items.GOLDEN_BOOTS, true).apply(version)),
			new ItemEntry(Items.GOLDEN_CHESTPLATE, 15).apply(version -> new EnchantRandomlyFunction(Items.GOLDEN_CHESTPLATE, true).apply(version)),
			new ItemEntry(Items.GOLDEN_HELMET, 15).apply(version -> new EnchantRandomlyFunction(Items.GOLDEN_HELMET, true).apply(version)),
			new ItemEntry(Items.GOLDEN_LEGGINGS, 15).apply(version -> new EnchantRandomlyFunction(Items.GOLDEN_LEGGINGS, true).apply(version)),
			new ItemEntry(Items.GLISTERING_MELON_SLICE, 5).apply(version -> SetCountFunction.uniform(4.0F, 12.0F)),
			new ItemEntry(Items.GOLDEN_HORSE_ARMOR, 5),
			new ItemEntry(Items.LIGHT_WEIGHTED_PRESSURE_PLATE, 5),
			new ItemEntry(Items.GOLDEN_CARROT, 5).apply(version -> SetCountFunction.uniform(4.0F, 12.0F)),
			new ItemEntry(Items.CLOCK, 5),
			new ItemEntry(Items.GOLD_INGOT, 5).apply(version -> SetCountFunction.uniform(2.0F, 8.0F)),
			new ItemEntry(Items.BELL),
			new ItemEntry(Items.ENCHANTED_GOLDEN_APPLE),
			new ItemEntry(Items.GOLD_BLOCK).apply(version -> SetCountFunction.uniform(1.0F, 2.0F)))
	);

	public static final Supplier<LootTable> SHIPWRECK_MAP_CHEST = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.FILLED_MAP).introducedVersion(MCVersion.v1_16), new ItemEntry(Items.MAP).introducedVersion(MCVersion.v1_14).deprecatedVersion(MCVersion.v1_16)),
		new LootPool(new ConstantRoll(3),
			new ItemEntry(Items.COMPASS),
			new ItemEntry(Items.MAP),
			new ItemEntry(Items.CLOCK),
			new ItemEntry(Items.PAPER, 20).apply(version -> SetCountFunction.uniform(1.0F, 10.0F)),
			new ItemEntry(Items.FEATHER, 10).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.BOOK, 5).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)))
	);

	public static final Supplier<LootTable> SHIPWRECK_SUPPLY_CHEST = () -> new LootTable(
		new LootPool(
			new UniformRoll(3.0F, 10.0F),
			new ItemEntry(Items.PAPER, 8).apply(version -> SetCountFunction.uniform(1.0F, 12.0F)),
			new ItemEntry(Items.POTATO, 7).apply(version -> SetCountFunction.uniform(2.0F, 6.0F)),
			new ItemEntry(Items.MOSS_BLOCK, 7).apply(version -> SetCountFunction.uniform(1.0F, 4.0F))
				.introducedVersion(MCVersion.v1_17),
			new ItemEntry(Items.POISONOUS_POTATO, 7).apply(version -> SetCountFunction.uniform(2.0F, 6.0F)),
			new ItemEntry(Items.CARROT, 7).apply(version -> SetCountFunction.uniform(4.0F, 8.0F)),
			new ItemEntry(Items.WHEAT, 7).apply(version -> SetCountFunction.uniform(8.0F, 21.0F)),
			new ItemEntry(Items.SUSPICIOUS_STEW, 10).apply(version ->
				// order matters, this was obtained through a fabric mod, albeit it will be completely platform dependant
				EffectFunction.builder()
					.apply(Effects.BLINDNESS, 5.0F, 7.0F)
					.apply(Effects.SATURATION, 7.0F, 10.0F)
					.apply(Effects.NIGHT_VISION, 7.0F, 10.0F)
					.apply(Effects.JUMP, 7.0F, 10.0F)
					.apply(Effects.POISON, 10.0F, 20.0F)
					.apply(Effects.WEAKNESS, 6.0F, 8.0F)
			),
			new ItemEntry(Items.COAL, 6).apply(version -> SetCountFunction.uniform(2.0F, 8.0F)),
			new ItemEntry(Items.ROTTEN_FLESH, 5).apply(version -> SetCountFunction.uniform(5.0F, 24.0F)),
			new ItemEntry(Items.PUMPKIN, 2).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.BAMBOO, 2).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.GUNPOWDER, 3).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.TNT).apply(version -> SetCountFunction.uniform(1.0F, 2.0F)),
			new ItemEntry(Items.LEATHER_HELMET, 3).apply(version -> new EnchantRandomlyFunction(Items.LEATHER_HELMET).apply(version)),
			new ItemEntry(Items.LEATHER_CHESTPLATE, 3).apply(version -> new EnchantRandomlyFunction(Items.LEATHER_CHESTPLATE).apply(version)),
			new ItemEntry(Items.LEATHER_LEGGINGS, 3).apply(version -> new EnchantRandomlyFunction(Items.LEATHER_LEGGINGS).apply(version)),
			new ItemEntry(Items.LEATHER_BOOTS, 3).apply(version -> new EnchantRandomlyFunction(Items.LEATHER_BOOTS).apply(version)))
	);

	public static final Supplier<LootTable> SHIPWRECK_TREASURE_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(3.0F, 6.0F),
			new ItemEntry(Items.IRON_INGOT, 90).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.GOLD_INGOT, 10).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.EMERALD, 40).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.DIAMOND, 5),
			new ItemEntry(Items.EXPERIENCE_BOTTLE, 5)),
		new LootPool(new UniformRoll(2.0F, 5.0F),
			new ItemEntry(Items.IRON_NUGGET, 50).apply(version -> SetCountFunction.uniform(1.0F, 10.0F)),
			new ItemEntry(Items.GOLD_NUGGET, 10).apply(version -> SetCountFunction.uniform(1.0F, 10.0F)),
			new ItemEntry(Items.LAPIS_LAZULI, 20).apply(version -> SetCountFunction.uniform(1.0F, 10.0F)))
	);

	public static final Supplier<LootTable> SIMPLE_DUNGEON_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(1.0F, 3.0F),
			new ItemEntry(Items.SADDLE, 20),
			new ItemEntry(Items.GOLDEN_APPLE, 15),
			new ItemEntry(Items.ENCHANTED_GOLDEN_APPLE, 2),
			new ItemEntry(Items.MUSIC_DISC_13, 15),
			new ItemEntry(Items.MUSIC_DISC_CAT, 15),
			new ItemEntry(Items.NAME_TAG, 20),
			new ItemEntry(Items.GOLDEN_HORSE_ARMOR, 10),
			new ItemEntry(Items.IRON_HORSE_ARMOR, 15),
			new ItemEntry(Items.DIAMOND_HORSE_ARMOR, 5),
			new ItemEntry(Items.ENCHANTED_BOOK, 10).apply(version -> new EnchantRandomlyFunction(Items.ENCHANTED_BOOK).apply(version))),
		new LootPool(new UniformRoll(1.0F, 4.0F),
			new ItemEntry(Items.IRON_INGOT, 10).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.GOLD_INGOT, 5).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.BREAD, 20),
			new ItemEntry(Items.WHEAT, 20).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.BUCKET, 10),
			new ItemEntry(Items.REDSTONE, 15).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.COAL, 15).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.MELON_SEEDS, 10).apply(version -> SetCountFunction.uniform(2.0F, 4.0F)),
			new ItemEntry(Items.PUMPKIN_SEEDS, 10).apply(version -> SetCountFunction.uniform(2.0F, 4.0F)),
			new ItemEntry(Items.BEETROOT_SEEDS, 10).apply(version -> SetCountFunction.uniform(2.0F, 4.0F))),
		new LootPool(new ConstantRoll(3),
			new ItemEntry(Items.BONE, 10).apply(version -> SetCountFunction.uniform(1.0F, 8.0F)),
			new ItemEntry(Items.GUNPOWDER, 10).apply(version -> SetCountFunction.uniform(1.0F, 8.0F)),
			new ItemEntry(Items.ROTTEN_FLESH, 10).apply(version -> SetCountFunction.uniform(1.0F, 8.0F)),
			new ItemEntry(Items.STRING, 10).apply(version -> SetCountFunction.uniform(1.0F, 8.0F)))
	);

	public static final Supplier<LootTable> SPAWN_BONUS_CHEST_CHEST = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.STONE_AXE),
			new ItemEntry(Items.WOODEN_AXE, 3)),
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.STONE_PICKAXE),
			new ItemEntry(Items.WOODEN_PICKAXE, 3)),
		new LootPool(new ConstantRoll(3),
			new ItemEntry(Items.APPLE, 5).apply(version -> SetCountFunction.uniform(1.0F, 2.0F)),
			new ItemEntry(Items.BREAD, 3).apply(version -> SetCountFunction.uniform(1.0F, 2.0F)),
			new ItemEntry(Items.SALMON, 3).apply(version -> SetCountFunction.uniform(1.0F, 2.0F))),
		new LootPool(new ConstantRoll(4),
			new ItemEntry(Items.STICK, 10).apply(version -> SetCountFunction.uniform(1.0F, 12.0F)),
			new ItemEntry(Items.OAK_PLANKS, 10).apply(version -> SetCountFunction.uniform(1.0F, 12.0F)),
			new ItemEntry(Items.OAK_LOG, 3).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.SPRUCE_LOG, 3).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.BIRCH_LOG, 3).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.JUNGLE_LOG, 3).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.ACACIA_LOG, 3).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.DARK_OAK_LOG, 3).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)))
	);

	public static final Supplier<LootTable> STRONGHOLD_CORRIDOR_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(2.0F, 3.0F),
			new ItemEntry(Items.ENDER_PEARL, 10),
			new ItemEntry(Items.DIAMOND, 3).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.IRON_INGOT, 10).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.GOLD_INGOT, 5).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.REDSTONE, 5).apply(version -> SetCountFunction.uniform(4.0F, 9.0F)),
			new ItemEntry(Items.BREAD, 15).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.APPLE, 15).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.IRON_PICKAXE, 5),
			new ItemEntry(Items.IRON_SWORD, 5),
			new ItemEntry(Items.IRON_CHESTPLATE, 5),
			new ItemEntry(Items.IRON_HELMET, 5),
			new ItemEntry(Items.IRON_LEGGINGS, 5),
			new ItemEntry(Items.IRON_BOOTS, 5),
			new ItemEntry(Items.GOLDEN_APPLE),
			new ItemEntry(Items.SADDLE),
			new ItemEntry(Items.IRON_HORSE_ARMOR),
			new ItemEntry(Items.GOLDEN_HORSE_ARMOR),
			new ItemEntry(Items.DIAMOND_HORSE_ARMOR),
			new ItemEntry(Items.BOOK).apply(version -> new EnchantWithLevelsFunction(Items.BOOK, 30, 30, true).apply(version)))
	);

	public static final Supplier<LootTable> STRONGHOLD_CROSSING_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(1.0F, 4.0F),
			new ItemEntry(Items.IRON_INGOT, 10).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.GOLD_INGOT, 5).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.REDSTONE, 5).apply(version -> SetCountFunction.uniform(4.0F, 9.0F)),
			new ItemEntry(Items.COAL, 10).apply(version -> SetCountFunction.uniform(3.0F, 8.0F)),
			new ItemEntry(Items.BREAD, 15).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.APPLE, 15).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.IRON_PICKAXE),
			new ItemEntry(Items.BOOK).apply(version -> new EnchantWithLevelsFunction(Items.BOOK, 30, 30, true).apply(version)))
	);

	public static final Supplier<LootTable> STRONGHOLD_LIBRARY_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(2.0F, 10.0F),
			new ItemEntry(Items.BOOK, 20).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.PAPER, 20).apply(version -> SetCountFunction.uniform(2.0F, 7.0F)),
			new ItemEntry(Items.MAP),
			new ItemEntry(Items.COMPASS),
			new ItemEntry(Items.BOOK, 10).apply(version -> new EnchantWithLevelsFunction(Items.BOOK, 30, 30, true).apply(version)))
	);

	public static final Supplier<LootTable> UNDERWATER_RUIN_BIG_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(2.0F, 8.0F),
			new ItemEntry(Items.COAL, 10).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.GOLD_NUGGET, 10).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.EMERALD),
			new ItemEntry(Items.WHEAT, 10).apply(version -> SetCountFunction.uniform(2.0F, 3.0F))),
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.GOLDEN_APPLE),
			new ItemEntry(Items.ENCHANTED_BOOK, 5).apply(version -> new EnchantRandomlyFunction(Items.ENCHANTED_BOOK).apply(version)),
			new ItemEntry(Items.LEATHER_CHESTPLATE),
			new ItemEntry(Items.GOLDEN_HELMET),
			new ItemEntry(Items.FISHING_ROD, 5).apply(version -> new EnchantRandomlyFunction(Items.FISHING_ROD).apply(version)),
			new ItemEntry(Items.MAP, 10))
	);

	public static final Supplier<LootTable> UNDERWATER_RUIN_SMALL_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(2.0F, 8.0F),
			new ItemEntry(Items.COAL, 10).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.STONE_AXE, 2),
			new ItemEntry(Items.ROTTEN_FLESH, 5),
			new ItemEntry(Items.EMERALD),
			new ItemEntry(Items.WHEAT, 10).apply(version -> SetCountFunction.uniform(2.0F, 3.0F))),
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.LEATHER_CHESTPLATE),
			new ItemEntry(Items.GOLDEN_HELMET),
			new ItemEntry(Items.FISHING_ROD, 5).apply(version -> new EnchantRandomlyFunction(Items.FISHING_ROD).apply(version)),
			new ItemEntry(Items.MAP, 5))
	);

	public static final Supplier<LootTable> VILLAGE_ARMORER_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(1.0F, 5.0F),
			new ItemEntry(Items.IRON_INGOT, 2).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.BREAD, 4).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.IRON_HELMET),
			new ItemEntry(Items.EMERALD))
	);

	public static final Supplier<LootTable> VILLAGE_BUTCHER_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(1.0F, 5.0F),
			new ItemEntry(Items.EMERALD),
			new ItemEntry(Items.PORKCHOP, 6).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.WHEAT, 6).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.BEEF, 6).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.MUTTON, 6).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.COAL, 3).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)))
	);

	public static final Supplier<LootTable> VILLAGE_CARTOGRAPHER_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(1.0F, 5.0F),
			new ItemEntry(Items.MAP, 10).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.PAPER, 15).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.COMPASS, 5),
			new ItemEntry(Items.BREAD, 15).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.STICK, 5).apply(version -> SetCountFunction.uniform(1.0F, 2.0F)))
	);

	public static final Supplier<LootTable> VILLAGE_DESERT_HOUSE_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(3.0F, 8.0F),
			new ItemEntry(Items.CLAY_BALL),
			new ItemEntry(Items.GREEN_DYE),
			new ItemEntry(Items.CACTUS, 10).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.WHEAT, 10).apply(version -> SetCountFunction.uniform(1.0F, 7.0F)),
			new ItemEntry(Items.BREAD, 10).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.BOOK),
			new ItemEntry(Items.DEAD_BUSH, 2).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.EMERALD).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)))
	);

	public static final Supplier<LootTable> VILLAGE_FISHER_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(1.0F, 5.0F),
			new ItemEntry(Items.EMERALD),
			new ItemEntry(Items.COD, 2).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.SALMON).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.WATER_BUCKET).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.BARREL).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.WHEAT_SEEDS, 3).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.COAL, 2).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)))
	);

	public static final Supplier<LootTable> VILLAGE_FLETCHER_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(1.0F, 5.0F),
			new ItemEntry(Items.EMERALD),
			new ItemEntry(Items.ARROW, 2).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.FEATHER, 6).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.EGG, 2).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.FLINT, 6).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.STICK, 6).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)))
	);

	public static final Supplier<LootTable> VILLAGE_MASON_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(1.0F, 5.0F),
			new ItemEntry(Items.CLAY_BALL).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.FLOWER_POT),
			new ItemEntry(Items.STONE, 2),
			new ItemEntry(Items.STONE_BRICKS, 2),
			new ItemEntry(Items.BREAD, 4).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.YELLOW_DYE),
			new ItemEntry(Items.SMOOTH_STONE),
			new ItemEntry(Items.EMERALD))
	);

	public static final Supplier<LootTable> VILLAGE_PLAINS_HOUSE_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(3.0F, 8.0F),
			new ItemEntry(Items.GOLD_NUGGET).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.DANDELION, 2),
			new ItemEntry(Items.POPPY),
			new ItemEntry(Items.POTATO, 10).apply(version -> SetCountFunction.uniform(1.0F, 7.0F)),
			new ItemEntry(Items.BREAD, 10).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.APPLE, 10).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.BOOK),
			new ItemEntry(Items.FEATHER),
			new ItemEntry(Items.EMERALD, 2).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.OAK_SAPLING, 5).apply(version -> SetCountFunction.uniform(1.0F, 2.0F)))
	);

	public static final Supplier<LootTable> VILLAGE_SAVANNA_HOUSE_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(3.0F, 8.0F),
			new ItemEntry(Items.GOLD_NUGGET).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.SHORT_GRASS, 5),
			new ItemEntry(Items.TALL_GRASS, 5),
			new ItemEntry(Items.BREAD, 10).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.WHEAT_SEEDS, 10).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.EMERALD, 2).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.ACACIA_SAPLING, 10).apply(version -> SetCountFunction.uniform(1.0F, 2.0F)),
			new ItemEntry(Items.SADDLE),
			new ItemEntry(Items.TORCH).apply(version -> SetCountFunction.uniform(1.0F, 2.0F)),
			new ItemEntry(Items.BUCKET))
	);

	public static final Supplier<LootTable> VILLAGE_SHEPHERD_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(1.0F, 5.0F),
			new ItemEntry(Items.WHITE_WOOL, 6).apply(version -> SetCountFunction.uniform(1.0F, 8.0F)),
			new ItemEntry(Items.BLACK_WOOL, 3).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.GRAY_WOOL, 2).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.BROWN_WOOL, 2).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.LIGHT_GRAY_WOOL, 2).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.EMERALD),
			new ItemEntry(Items.SHEARS),
			new ItemEntry(Items.WHEAT, 6).apply(version -> SetCountFunction.uniform(1.0F, 6.0F)))
	);

	public static final Supplier<LootTable> VILLAGE_SNOWY_HOUSE_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(3.0F, 8.0F),
			new ItemEntry(Items.BLUE_ICE),
			new ItemEntry(Items.SNOW_BLOCK, 4),
			new ItemEntry(Items.POTATO, 10).apply(version -> SetCountFunction.uniform(1.0F, 7.0F)),
			new ItemEntry(Items.BREAD, 10).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.BEETROOT_SEEDS, 10).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.BEETROOT_SOUP),
			new ItemEntry(Items.FURNACE),
			new ItemEntry(Items.EMERALD).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.SNOWBALL, 10).apply(version -> SetCountFunction.uniform(1.0F, 7.0F)),
			new ItemEntry(Items.COAL, 5).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)))
	);

	public static final Supplier<LootTable> VILLAGE_TAIGA_HOUSE_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(3.0F, 8.0F),
			new ItemEntry(Items.IRON_NUGGET).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.FERN, 2),
			new ItemEntry(Items.LARGE_FERN, 2),
			new ItemEntry(Items.POTATO, 10).apply(version -> SetCountFunction.uniform(1.0F, 7.0F)),
			new ItemEntry(Items.SWEET_BERRIES, 5).apply(version -> SetCountFunction.uniform(1.0F, 7.0F)),
			new ItemEntry(Items.BREAD, 10).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.PUMPKIN_SEEDS, 5).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.PUMPKIN_PIE),
			new ItemEntry(Items.EMERALD, 2).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.SPRUCE_SAPLING, 5).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.SPRUCE_SIGN),
			new ItemEntry(Items.SPRUCE_LOG, 10).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)))
	);

	public static final Supplier<LootTable> VILLAGE_TANNERY_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(1.0F, 5.0F),
			new ItemEntry(Items.LEATHER).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.LEATHER_CHESTPLATE, 2),
			new ItemEntry(Items.LEATHER_BOOTS, 2),
			new ItemEntry(Items.LEATHER_HELMET, 2),
			new ItemEntry(Items.BREAD, 5).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.LEATHER_LEGGINGS, 2),
			new ItemEntry(Items.SADDLE),
			new ItemEntry(Items.EMERALD).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)))
	);

	public static final Supplier<LootTable> VILLAGE_TEMPLE_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(3.0F, 8.0F),
			new ItemEntry(Items.REDSTONE, 2).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.BREAD, 7).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.ROTTEN_FLESH, 7).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.LAPIS_LAZULI).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.GOLD_INGOT).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.EMERALD).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)))
	);

	public static final Supplier<LootTable> VILLAGE_TOOLSMITH_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(3.0F, 8.0F),
			new ItemEntry(Items.DIAMOND).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.IRON_INGOT, 5).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.GOLD_INGOT).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.BREAD, 15).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.IRON_PICKAXE, 5),
			new ItemEntry(Items.COAL).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.STICK, 20).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.IRON_SHOVEL, 5))
	);

	public static final Supplier<LootTable> VILLAGE_WEAPONSMITH_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(3.0F, 8.0F),
			new ItemEntry(Items.DIAMOND, 3).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.IRON_INGOT, 10).apply(version -> SetCountFunction.uniform(1.0F, 5.0F)),
			new ItemEntry(Items.GOLD_INGOT, 5).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.BREAD, 15).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.APPLE, 15).apply(version -> SetCountFunction.uniform(1.0F, 3.0F)),
			new ItemEntry(Items.IRON_PICKAXE, 5),
			new ItemEntry(Items.IRON_SWORD, 5),
			new ItemEntry(Items.IRON_CHESTPLATE, 5),
			new ItemEntry(Items.IRON_HELMET, 5),
			new ItemEntry(Items.IRON_LEGGINGS, 5),
			new ItemEntry(Items.IRON_BOOTS, 5),
			new ItemEntry(Items.OBSIDIAN, 5).apply(version -> SetCountFunction.uniform(3.0F, 7.0F)),
			new ItemEntry(Items.OAK_SAPLING, 5).apply(version -> SetCountFunction.uniform(3.0F, 7.0F)),
			new ItemEntry(Items.SADDLE, 3),
			new ItemEntry(Items.IRON_HORSE_ARMOR),
			new ItemEntry(Items.GOLDEN_HORSE_ARMOR),
			new ItemEntry(Items.DIAMOND_HORSE_ARMOR))
	);

	public static final Supplier<LootTable> WOODLAND_MANSION_CHEST = () -> new LootTable(
		new LootPool(new UniformRoll(1.0F, 3.0F),
			new ItemEntry(Items.LEAD, 20),
			new ItemEntry(Items.GOLDEN_APPLE, 15),
			new ItemEntry(Items.ENCHANTED_GOLDEN_APPLE, 2),
			new ItemEntry(Items.MUSIC_DISC_13, 15),
			new ItemEntry(Items.MUSIC_DISC_CAT, 15),
			new ItemEntry(Items.NAME_TAG, 20),
			new ItemEntry(Items.CHAINMAIL_CHESTPLATE, 10),
			new ItemEntry(Items.DIAMOND_HOE, 15),
			new ItemEntry(Items.DIAMOND_CHESTPLATE, 5),
			new ItemEntry(Items.ENCHANTED_BOOK, 10).apply(version -> new EnchantRandomlyFunction(Items.ENCHANTED_BOOK).apply(version))),
		new LootPool(new UniformRoll(1.0F, 4.0F),
			new ItemEntry(Items.IRON_INGOT, 10).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.GOLD_INGOT, 5).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.BREAD, 20),
			new ItemEntry(Items.WHEAT, 20).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.BUCKET, 10),
			new ItemEntry(Items.REDSTONE, 15).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.COAL, 15).apply(version -> SetCountFunction.uniform(1.0F, 4.0F)),
			new ItemEntry(Items.MELON_SEEDS, 10).apply(version -> SetCountFunction.uniform(2.0F, 4.0F)),
			new ItemEntry(Items.PUMPKIN_SEEDS, 10).apply(version -> SetCountFunction.uniform(2.0F, 4.0F)),
			new ItemEntry(Items.BEETROOT_SEEDS, 10).apply(version -> SetCountFunction.uniform(2.0F, 4.0F))),
		new LootPool(new ConstantRoll(3),
			new ItemEntry(Items.BONE, 10).apply(version -> SetCountFunction.uniform(1.0F, 8.0F)),
			new ItemEntry(Items.GUNPOWDER, 10).apply(version -> SetCountFunction.uniform(1.0F, 8.0F)),
			new ItemEntry(Items.ROTTEN_FLESH, 10).apply(version -> SetCountFunction.uniform(1.0F, 8.0F)),
			new ItemEntry(Items.STRING, 10).apply(version -> SetCountFunction.uniform(1.0F, 8.0F)))
	);

	public static final Supplier<LootTable> FISHING_FISH = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.COD, 60),
			new ItemEntry(Items.SALMON, 25),
			new ItemEntry(Items.TROPICAL_FISH, 2),
			new ItemEntry(Items.PUFFERFISH, 13)
		)
	);

	public static final Supplier<LootTable> FISHING_JUNK = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.LILY_PAD, 17),
			new ItemEntry(Items.LEATHER_BOOTS, 10).apply(version -> new ApplyDamageFunction()),
			new ItemEntry(Items.LEATHER, 10),
			new ItemEntry(Items.BONE, 10),
			new ItemEntry(Items.POTION, 10), // potion function is not random
			new ItemEntry(Items.STRING, 5),
			new ItemEntry(Items.FISHING_ROD, 2).apply(version -> new ApplyDamageFunction()),
			new ItemEntry(Items.BOWL, 10),
			new ItemEntry(Items.STICK, 5),
			new ItemEntry(Items.INK_SAC, 1).apply(version -> SetCountFunction.constant(10)),
			new ItemEntry(Items.TRIPWIRE_HOOK, 10),
			new ItemEntry(Items.ROTTEN_FLESH, 10),
			new ItemEntry(Items.BAMBOO, 10).when(version -> {
					if(version.isNewerOrEqualTo(MCVersion.v1_18)) {
						// TODO add sparse jungle for 1.18+
						return new BiomeCondition(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE);
					} else {
						return new BiomeCondition(Biomes.JUNGLE, Biomes.JUNGLE_HILLS,
							Biomes.JUNGLE_EDGE, Biomes.BAMBOO_JUNGLE, Biomes.MODIFIED_JUNGLE,
							Biomes.MODIFIED_JUNGLE_EDGE, Biomes.BAMBOO_JUNGLE_HILLS
						);
					}
				}
			)
		)
	);

	public static final Supplier<LootTable> FISHING_TREASURE = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.NAME_TAG),
			new ItemEntry(Items.SADDLE),
			new ItemEntry(Items.BOW)
				.apply(version -> new ApplyDamageFunction(), version -> new EnchantWithLevelsFunction(Items.BOW, 30, 30, true).apply(version)),
			new ItemEntry(Items.FISHING_ROD)
				.apply(version -> new ApplyDamageFunction(), version -> new EnchantWithLevelsFunction(Items.FISHING_ROD, 30, 30, true).apply(version)),
			new ItemEntry(Items.BOOK)
				.apply(version -> new EnchantWithLevelsFunction(Items.BOOK, 30, 30, true).apply(version)),
			new ItemEntry(Items.NAUTILUS_SHELL)
		)
	);

	public static final Supplier<LootTable> FISHING = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new TableEntry(FISHING_JUNK, 10, -2),
			new TableEntry(FISHING_TREASURE, 5, 2)
				.when(version -> new OpenWaterCondition(true)),
			new TableEntry(FISHING_FISH, 85, -1)
		)
	);

	public static final Supplier<LootTable> DESERT_WELL_ARCHAEOLOGY = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.ARMS_UP_POTTERY_SHERD, 2),
			new ItemEntry(Items.BREWER_POTTERY_SHERD, 2),
			new ItemEntry(Items.BRICK),
			new ItemEntry(Items.EMERALD),
			new ItemEntry(Items.STICK),
			new ItemEntry(Items.SUSPICIOUS_STEW)
				.apply(version -> new SetStewEffectFunction(
					new SetStewEffectFunction.EffectEntry(Effects.NIGHT_VISION, new UniformRoll(7.0F, 10.0F)),
					new SetStewEffectFunction.EffectEntry(Effects.JUMP, new UniformRoll(7.0F, 10.0F)),
					new SetStewEffectFunction.EffectEntry(Effects.WEAKNESS, new UniformRoll(6.0F, 8.0F)),
					new SetStewEffectFunction.EffectEntry(Effects.BLINDNESS, new UniformRoll(5.0F, 7.0F)),
					new SetStewEffectFunction.EffectEntry(Effects.POISON, new UniformRoll(10.0F, 20.0F)),
					new SetStewEffectFunction.EffectEntry(Effects.SATURATION, new UniformRoll(7.0F, 10.0F))
				))
		)
	);

	public static final Supplier<LootTable> DESERT_PYRAMID_ARCHAEOLOGY = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.ARCHER_POTTERY_SHERD),
			new ItemEntry(Items.MINER_POTTERY_SHERD),
			new ItemEntry(Items.PRIZE_POTTERY_SHERD),
			new ItemEntry(Items.SKULL_POTTERY_SHERD),
			new ItemEntry(Items.DIAMOND),
			new ItemEntry(Items.TNT),
			new ItemEntry(Items.GUNPOWDER),
			new ItemEntry(Items.EMERALD)
		)
	);

	public static final Supplier<LootTable> TRAIL_RUINS_ARCHAEOLOGY_COMMON = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.EMERALD, 2),
			new ItemEntry(Items.WHEAT, 2),
			new ItemEntry(Items.WOODEN_HOE, 2),
			new ItemEntry(Items.CLAY, 2),
			new ItemEntry(Items.BRICK, 2),
			new ItemEntry(Items.YELLOW_DYE, 2),
			new ItemEntry(Items.BLUE_DYE, 2),
			new ItemEntry(Items.LIGHT_BLUE_DYE, 2),
			new ItemEntry(Items.WHITE_DYE, 2),
			new ItemEntry(Items.ORANGE_DYE, 2),
			new ItemEntry(Items.RED_CANDLE, 2),
			new ItemEntry(Items.GREEN_CANDLE, 2),
			new ItemEntry(Items.PURPLE_CANDLE, 2),
			new ItemEntry(Items.BROWN_CANDLE, 2),
			new ItemEntry(Items.MAGENTA_STAINED_GLASS_PANE),
			new ItemEntry(Items.PINK_STAINED_GLASS_PANE),
			new ItemEntry(Items.BLUE_STAINED_GLASS_PANE),
			new ItemEntry(Items.LIGHT_BLUE_STAINED_GLASS_PANE),
			new ItemEntry(Items.RED_STAINED_GLASS_PANE),
			new ItemEntry(Items.YELLOW_STAINED_GLASS_PANE),
			new ItemEntry(Items.PURPLE_STAINED_GLASS_PANE),
			new ItemEntry(Items.SPRUCE_HANGING_SIGN),
			new ItemEntry(Items.OAK_HANGING_SIGN),
			new ItemEntry(Items.GOLD_NUGGET),
			new ItemEntry(Items.COAL),
			new ItemEntry(Items.WHEAT_SEEDS),
			new ItemEntry(Items.BEETROOT_SEEDS),
			new ItemEntry(Items.DEAD_BUSH),
			new ItemEntry(Items.FLOWER_POT),
			new ItemEntry(Items.STRING),
			new ItemEntry(Items.LEAD)
		)
	);

	public static final Supplier<LootTable> TRAIL_RUINS_ARCHAEOLOGY_RARE = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.BURN_POTTERY_SHERD),
			new ItemEntry(Items.DANGER_POTTERY_SHERD),
			new ItemEntry(Items.FRIEND_POTTERY_SHERD),
			new ItemEntry(Items.HEART_POTTERY_SHERD),
			new ItemEntry(Items.HEARTBREAK_POTTERY_SHERD),
			new ItemEntry(Items.HOWL_POTTERY_SHERD),
			new ItemEntry(Items.SHEAF_POTTERY_SHERD),
			new ItemEntry(Items.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE),
			new ItemEntry(Items.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE),
			new ItemEntry(Items.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE),
			new ItemEntry(Items.HOST_ARMOR_TRIM_SMITHING_TEMPLATE),
			new ItemEntry(Items.MUSIC_DISC_RELIC)
		)
	);

	public static final Supplier<LootTable> OCEAN_RUIN_WARM_ARCHAEOLOGY = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.ANGLER_POTTERY_SHERD),
			new ItemEntry(Items.SHELTER_POTTERY_SHERD),
			new ItemEntry(Items.SNORT_POTTERY_SHERD),
			new ItemEntry(Items.SNIFFER_EGG),
			new ItemEntry(Items.IRON_AXE),
			new ItemEntry(Items.EMERALD, 2),
			new ItemEntry(Items.WHEAT, 2),
			new ItemEntry(Items.WOODEN_HOE, 2),
			new ItemEntry(Items.COAL, 2),
			new ItemEntry(Items.GOLD_NUGGET, 2)
		)
	);

	public static final Supplier<LootTable> OCEAN_RUIN_COLD_ARCHAEOLOGY = () -> new LootTable(
		new LootPool(new ConstantRoll(1),
			new ItemEntry(Items.BLADE_POTTERY_SHERD),
			new ItemEntry(Items.EXPLORER_POTTERY_SHERD),
			new ItemEntry(Items.MOURNER_POTTERY_SHERD),
			new ItemEntry(Items.PLENTY_POTTERY_SHERD),
			new ItemEntry(Items.IRON_AXE),
			new ItemEntry(Items.EMERALD, 2),
			new ItemEntry(Items.WHEAT, 2),
			new ItemEntry(Items.WOODEN_HOE, 2),
			new ItemEntry(Items.COAL, 2),
			new ItemEntry(Items.GOLD_NUGGET, 2)
		)
	);

	public static final HashMap<String, Supplier<LootTable>> ALL_LOOT_TABLE = new HashMap<String, Supplier<LootTable>>() {{
		put("gameplay/fishing", FISHING);
		put("gameplay/fishing/fish", FISHING_FISH);
		put("gameplay/fishing/junk", FISHING_JUNK);
		put("gameplay/fishing/treasure", FISHING_TREASURE);
		put("chests/abandoned_mineshaft", ABANDONED_MINESHAFT_CHEST);
		put("chests/buried_treasure", BURIED_TREASURE_CHEST);
		put("chests/desert_pyramid", DESERT_PYRAMID_CHEST);
		put("chests/end_city_treasure", END_CITY_TREASURE_CHEST);
		put("chests/igloo_chest", IGLOO_CHEST);
		put("chests/jungle_temple", JUNGLE_TEMPLE_CHEST);
		put("chests/jungle_temple_dispenser", JUNGLE_TEMPLE_DISPENSER_CHEST);
		put("chests/nether_bridge", NETHER_BRIDGE_CHEST);
		put("chests/pillager_outpost", PILLAGER_OUTPOST_CHEST);
		put("chests/shipwreck_map", SHIPWRECK_MAP_CHEST);
		put("chests/shipwreck_supply", SHIPWRECK_SUPPLY_CHEST);
		put("chests/shipwreck_treasure", SHIPWRECK_TREASURE_CHEST);
		put("chests/simple_dungeon", SIMPLE_DUNGEON_CHEST);
		put("chests/spawn_bonus_chest", SPAWN_BONUS_CHEST_CHEST);
		put("chests/stronghold_corridor", STRONGHOLD_CORRIDOR_CHEST);
		put("chests/stronghold_crossing", STRONGHOLD_CROSSING_CHEST);
		put("chests/stronghold_library", STRONGHOLD_LIBRARY_CHEST);
		put("chests/underwater_ruin_big", UNDERWATER_RUIN_BIG_CHEST);
		put("chests/underwater_ruin_small", UNDERWATER_RUIN_SMALL_CHEST);
		put("chests/village/village_weaponsmith", VILLAGE_WEAPONSMITH_CHEST);
		put("chests/village/village_toolsmith", VILLAGE_TOOLSMITH_CHEST);
		put("chests/village/village_cartographer", VILLAGE_CARTOGRAPHER_CHEST);
		put("chests/village/village_mason", VILLAGE_MASON_CHEST);
		put("chests/village/village_armorer", VILLAGE_ARMORER_CHEST);
		put("chests/village/village_shepherd", VILLAGE_SHEPHERD_CHEST);
		put("chests/village/village_butcher", VILLAGE_BUTCHER_CHEST);
		put("chests/village/village_fletcher", VILLAGE_FLETCHER_CHEST);
		put("chests/village/village_fisher", VILLAGE_FISHER_CHEST);
		put("chests/village/village_tannery", VILLAGE_TANNERY_CHEST);
		put("chests/village/village_temple", VILLAGE_TEMPLE_CHEST);
		put("chests/village/village_plains_house", VILLAGE_PLAINS_HOUSE_CHEST);
		put("chests/village/village_taiga_house", VILLAGE_TAIGA_HOUSE_CHEST);
		put("chests/village/village_savanna_house", VILLAGE_SAVANNA_HOUSE_CHEST);
		put("chests/village/village_snowy_house", VILLAGE_SNOWY_HOUSE_CHEST);
		put("chests/village/village_desert_house", VILLAGE_DESERT_HOUSE_CHEST);
		put("archaeology/desert_well", DESERT_WELL_ARCHAEOLOGY);
		put("archaeology/desert_pyramid", DESERT_PYRAMID_ARCHAEOLOGY);
		put("archaeology/trail_ruins_common", TRAIL_RUINS_ARCHAEOLOGY_COMMON);
		put("archaeology/trail_ruins_rare", TRAIL_RUINS_ARCHAEOLOGY_RARE);
		put("archaeology/ocean_ruin_warm", OCEAN_RUIN_WARM_ARCHAEOLOGY);
		put("archaeology/ocean_ruin_cold", OCEAN_RUIN_COLD_ARCHAEOLOGY);
	}};

}
