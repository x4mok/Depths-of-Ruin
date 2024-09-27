package com.x4mok.depths_of_ruin.item;

import com.x4mok.depths_of_ruin.Depths_of_ruin;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

	public static final DeferredRegister<Item> ITEMS =
			DeferredRegister.create(ForgeRegistries.ITEMS, Depths_of_ruin.MODID);

	public static final RegistryObject<Item> DRAGON_BLOOD = ITEMS.register("dragon_blood",
			() -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).tab(ModItemGroup.DEPTHS_OF_RUIN_TAB)));
	public static final RegistryObject<Item> DRAGON_SCALE = ITEMS.register("dragon_scale",
			() -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).tab(ModItemGroup.DEPTHS_OF_RUIN_TAB)));
	public static final RegistryObject<Item> TRUE_DRAGON_SCALE = ITEMS.register("true_dragon_scale",
			() -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON).tab(ModItemGroup.DEPTHS_OF_RUIN_TAB)));
	public static final RegistryObject<Item> DRAGON_SWORD = ITEMS.register("dragon_sword",
			() -> new SwordItem(ModItemTier.DRAGON, 3, -2.4F, (new Item.Properties().rarity(Rarity.COMMON))));
	public static final RegistryObject<Item> DRAGON_AXE = ITEMS.register("dragon_axe",
			() -> new AxeItem(ModItemTier.DRAGON, 6.5f, -3.0F, (new Item.Properties().rarity(Rarity.COMMON))));
	public static final RegistryObject<Item> DRAGON_PICKAXE = ITEMS.register("dragon_pickaxe",
			() -> new PickaxeItem(ModItemTier.DRAGON, 1, -2.8F, (new Item.Properties().rarity(Rarity.COMMON))));
	public static final RegistryObject<Item> DRAGON_SHOVEL = ITEMS.register("dragon_shovel",
			() -> new ShovelItem(ModItemTier.DRAGON, 1.5f, -3.0F, (new Item.Properties().rarity(Rarity.COMMON))));
	public static final RegistryObject<Item> DRAGON_HOE = ITEMS.register("dragon_hoe",
			() -> new HoeItem(ModItemTier.DRAGON, 0, -2.0F, (new Item.Properties().rarity(Rarity.COMMON))));

	public static final RegistryObject<Item> DRAGON_HELMET = ITEMS.register("dragon_helmet",
			() -> new ArmorItem(ModArmourMaterial.DRAGON, EquipmentSlotType.HEAD, (new Item.Properties().rarity(Rarity.COMMON))));
	public static final RegistryObject<Item> DRAGON_CHESTPLATE = ITEMS.register("dragon_chestplate",
			() -> new ArmorItem(ModArmourMaterial.DRAGON, EquipmentSlotType.CHEST, (new Item.Properties().rarity(Rarity.COMMON))));
	public static final RegistryObject<Item> DRAGON_LEGGINGS = ITEMS.register("dragon_leggings",
			() -> new ArmorItem(ModArmourMaterial.DRAGON, EquipmentSlotType.LEGS, (new Item.Properties().rarity(Rarity.COMMON))));
	public static final RegistryObject<Item> DRAGON_BOOTS = ITEMS.register("dragon_boots",
			() -> new ArmorItem(ModArmourMaterial.DRAGON, EquipmentSlotType.FEET, (new Item.Properties().rarity(Rarity.COMMON))));



	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}