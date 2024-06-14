package com.seedfinding.mcfeature.loot;


import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.item.ItemStack;
import com.seedfinding.mcfeature.loot.item.Items;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test against standard loot obtained from minecraft
 * Warning those tests are super intensive !!!
 */
public class LootTestStandard {

	public static boolean simpleEquals(ItemStack a, ItemStack b) {
		return a.getCount() == b.getCount() && a.getItem().equalsName(b.getItem());
	}

	@Test
	public void test1_14() throws IOException, ParseException {
		ZipFile zipFile = new ZipFile(Objects.requireNonNull(getClass().getClassLoader().getResource("loot/1.14-small.zip")).getFile());
		ZipEntry zipEntry = zipFile.getEntry("1.14.json");
		BufferedInputStream reader = new BufferedInputStream(zipFile.getInputStream(zipEntry));
		JSONArray root = (JSONArray)new JSONParser().parse(new InputStreamReader(reader));
		for(Object o : root) {
			JSONObject current = (JSONObject)o;
			long seed = (long)current.get("seed");
			JSONObject loots = (JSONObject)current.get("loots");
			MCLootTables.ALL_LOOT_TABLE.forEach((name, lootTable) -> {
				JSONArray result = (JSONArray)loots.get(name);
				List<ItemStack> goldenItems = new ArrayList<>();
				for(Object x : result) {
					JSONObject loot = (JSONObject)x;
					String itemName = (String)loot.get("item");
					long count = (long)loot.get("count");
					JSONArray enchantments = (JSONArray)loot.get("enchantments");
					goldenItems.add(new ItemStack(Items.getItems().values().stream().filter(item -> Objects.equals(item.getName(), itemName)).findFirst().get(), (int)count));
				}
				HashMap<String, List<ItemStack>> goldenItemsMap = new HashMap<>();
				goldenItems.forEach(itemStack -> goldenItemsMap.computeIfAbsent(itemStack.getItem().getName(), v -> new ArrayList<>()).add(itemStack));
				List<ItemStack> items = lootTable.get().apply(MCVersion.v1_14).generate(new LootContext(seed));
				goldenItemsMap.forEach((itemName, itemStacks) -> {
					List<ItemStack> match = items.stream().filter(x -> x.getItem().equalsName(itemName)).collect(Collectors.toList());
					int goldenCount = itemStacks.stream().mapToInt(ItemStack::getCount).sum();
					int matchCount = match.stream().mapToInt(ItemStack::getCount).sum();
					boolean goldenEnchanted = !itemStacks.stream().allMatch(x -> x.getItem().getEnchantments().isEmpty());
					if(goldenCount == matchCount) {
						fail(
							"Missing item: " + itemName + " ,count: " + goldenCount + " with enchants:" + goldenEnchanted
								+ " in loot table: " + name + " generated for seed: " + seed +
								"\nObjects available " + items.stream().map(w -> "Item: " + w.getItem().getName() + ", Count: " + w.getCount()).collect(Collectors.toList()) + "\n"
						);
					}
				});

			});
		}
	}

}
