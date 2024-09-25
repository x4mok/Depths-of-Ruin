package com.x4mok.depths_of_ruin.item;

import net.minecraft.item.*;
import net.minecraft.util.NonNullList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashSet;
import java.util.Set;

public class ModItemGroup {



    public static final ItemGroup DEPTHS_OF_RUIN_TAB = new ItemGroup("depths_of_ruin_tab") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.DRAGON_BLOOD.get());
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> items) {
            // Set to track added items to avoid duplicates
            Set<Item> addedItems = new HashSet<>();

            // Sort items with specific words in their name first
            String modNamespace = "depths_of_ruin";

            // Add items with "dragon" in the name
            addItemToTab("dragon", items, modNamespace, addedItems);

            // Add non-tool, non-armor, non-block items from your mod
            ForgeRegistries.ITEMS.getValues().stream()
                    .filter(item -> item.getRegistryName() != null && item.getRegistryName().getNamespace().equals(modNamespace)) // Ensure item is from your mod
                    .filter(item -> !(item instanceof ToolItem || item instanceof ArmorItem || item instanceof BlockItem)) // Exclude tools, armor, and blocks
                    .filter(item -> addedItems.add(item)) // Only add if not already present
                    .forEach(item -> items.add(new ItemStack(item)));

            // Add tools from your mod
            ForgeRegistries.ITEMS.getValues().stream()
                    .filter(item -> item.getRegistryName() != null && item.getRegistryName().getNamespace().equals(modNamespace)) // Ensure item is from your mod
                    .filter(item -> item instanceof ToolItem) // Include only tools
                    .filter(item -> addedItems.add(item)) // Only add if not already present
                    .forEach(item -> items.add(new ItemStack(item)));

            // Add armor from your mod
            ForgeRegistries.ITEMS.getValues().stream()
                    .filter(item -> item.getRegistryName() != null && item.getRegistryName().getNamespace().equals(modNamespace)) // Ensure item is from your mod
                    .filter(item -> item instanceof ArmorItem) // Include only armor
                    .filter(item -> addedItems.add(item)) // Only add if not already present
                    .forEach(item -> items.add(new ItemStack(item)));

            // Add blocks from your mod
            ForgeRegistries.ITEMS.getValues().stream()
                    .filter(item -> item.getRegistryName() != null && item.getRegistryName().getNamespace().equals(modNamespace)) // Ensure item is from your mod
                    .filter(item -> item instanceof BlockItem) // Include only block items
                    .filter(item -> addedItems.add(item)) // Only add if not already present
                    .forEach(item -> items.add(new ItemStack(item)));
        }

        public void addItemToTab(String specificWord, NonNullList<ItemStack> items, String namespace, Set<Item> addedItems) {
            // 1. Add items that contain the specific word in their name (excluding tools, armor, and blocks)
            ForgeRegistries.ITEMS.getValues().stream()
                    .filter(item -> item.getDescriptionId().toLowerCase().contains(specificWord))
                    .filter(item -> item.getRegistryName() != null && item.getRegistryName().getNamespace().equals(namespace))
                    .filter(item -> !(item instanceof ToolItem || item instanceof ArmorItem || item instanceof BlockItem)) // Exclude tools, armor, and blocks
                    .filter(item -> addedItems.add(item)) // Only add if not already present
                    .forEach(item -> items.add(new ItemStack(item)));

            // 2. Add tools that contain the specific word in their name
            ForgeRegistries.ITEMS.getValues().stream()
                    .filter(item -> item instanceof ToolItem)
                    .filter(item -> item.getDescriptionId().toLowerCase().contains(specificWord))
                    .filter(item -> item.getRegistryName() != null && item.getRegistryName().getNamespace().equals(namespace))
                    .filter(item -> addedItems.add(item)) // Only add if not already present
                    .forEach(item -> items.add(new ItemStack(item)));

            // 3. Add armor that contains the specific word in their name
            ForgeRegistries.ITEMS.getValues().stream()
                    .filter(item -> item instanceof ArmorItem)
                    .filter(item -> item.getDescriptionId().toLowerCase().contains(specificWord))
                    .filter(item -> item.getRegistryName() != null && item.getRegistryName().getNamespace().equals(namespace))
                    .filter(item -> addedItems.add(item)) // Only add if not already present
                    .forEach(item -> items.add(new ItemStack(item)));

            // 4. Add blocks that contain the specific word in their name
            ForgeRegistries.ITEMS.getValues().stream()
                    .filter(item -> item instanceof BlockItem)
                    .filter(item -> item.getDescriptionId().toLowerCase().contains(specificWord))
                    .filter(item -> item.getRegistryName() != null && item.getRegistryName().getNamespace().equals(namespace))
                    .filter(item -> addedItems.add(item)) // Only add if not already present
                    .forEach(item -> items.add(new ItemStack(item)));
        }
    };


}
