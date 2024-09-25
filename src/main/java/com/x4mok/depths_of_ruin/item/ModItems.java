package com.x4mok.depths_of_ruin.item;

import com.x4mok.depths_of_ruin.Depths_of_ruin;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Depths_of_ruin.MODID);

    public static final RegistryObject<Item> DRAGON_BLOOD = ITEMS.register("dragon_blood",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS).stacksTo(64).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> DRAGON_SCALE = ITEMS.register("dragon_scale",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS).stacksTo(64).rarity(Rarity.COMMON)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
