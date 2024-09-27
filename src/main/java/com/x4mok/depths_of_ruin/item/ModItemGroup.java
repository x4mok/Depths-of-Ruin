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

            // Mod namespace for filtering
            String modNamespace = "depths_of_ruin";

            // Add items with specific words in their name
            addItemToTab("dragon", items, modNamespace, addedItems);
            addItemToTab("mahogany", items, modNamespace, addedItems);

            // Now add regular items in alphabetical order
            addRegularItemsInOrder(items, modNamespace, addedItems);

            // Add tools in specific order
            addToolsInOrder(items, modNamespace, addedItems);

            // Add armor in specific order
            addArmorInOrder(items, modNamespace, addedItems);

            // Add blocks in alphabetical order
            addBlocksInOrder(items, modNamespace, addedItems);

            // Finally, add regular items again in alphabetical order
            addRegularItemsInOrder(items, modNamespace, addedItems);
        }

        private void addRegularItemsInOrder(NonNullList<ItemStack> items, String namespace, Set<Item> addedItems) {
            // Get regular items in alphabetical order
            ForgeRegistries.ITEMS.getValues().stream()
                    .filter(item -> item.getRegistryName() != null && item.getRegistryName().getNamespace().equals(namespace))
                    .filter(item -> !(item instanceof ToolItem || item instanceof ArmorItem || item instanceof BlockItem))
                    .filter(addedItems::add) // Only add if not already present
                    .sorted((item1, item2) -> item1.getDescriptionId().compareTo(item2.getDescriptionId())) // Sort A-Z
                    .forEach(item -> items.add(new ItemStack(item)));
        }

        private void addToolsInOrder(NonNullList<ItemStack> items, String namespace, Set<Item> addedItems) {
            String[] toolOrder = {"sword", "axe", "pickaxe", "shovel", "hoe"};

            for (String tool : toolOrder) {
                ForgeRegistries.ITEMS.getValues().stream()
                        .filter(item -> item instanceof ToolItem)
                        .filter(item -> item.getDescriptionId().toLowerCase().contains(tool))
                        .filter(item -> item.getRegistryName() != null && item.getRegistryName().getNamespace().equals(namespace))
                        .filter(addedItems::add) // Only add if not already present
                        .forEach(item -> items.add(new ItemStack(item)));
            }
        }

        private void addArmorInOrder(NonNullList<ItemStack> items, String namespace, Set<Item> addedItems) {
            String[] armorOrder = {"helmet", "chestplate", "leggings", "boots"};

            for (String armor : armorOrder) {
                ForgeRegistries.ITEMS.getValues().stream()
                        .filter(item -> item instanceof ArmorItem)
                        .filter(item -> item.getDescriptionId().toLowerCase().contains(armor))
                        .filter(item -> item.getRegistryName() != null && item.getRegistryName().getNamespace().equals(namespace))
                        .filter(addedItems::add) // Only add if not already present
                        .forEach(item -> items.add(new ItemStack(item)));
            }
        }

        private void addBlocksInOrder(NonNullList<ItemStack> items, String namespace, Set<Item> addedItems) {
            // Get block items in alphabetical order
            ForgeRegistries.ITEMS.getValues().stream()
                    .filter(item -> item instanceof BlockItem)
                    .filter(item -> item.getRegistryName() != null && item.getRegistryName().getNamespace().equals(namespace))
                    .filter(addedItems::add) // Only add if not already present
                    .sorted((item1, item2) -> item1.getDescriptionId().compareTo(item2.getDescriptionId())) // Sort A-Z
                    .forEach(item -> items.add(new ItemStack(item)));
        }

        public void addItemToTab(String specificWord, NonNullList<ItemStack> items, String namespace, Set<Item> addedItems) {
            // 1. Add items that contain the specific word in their name (excluding tools, armor, and blocks)
            ForgeRegistries.ITEMS.getValues().stream()
                    .filter(item -> item.getDescriptionId().toLowerCase().contains(specificWord))
                    .filter(item -> item.getRegistryName() != null && item.getRegistryName().getNamespace().equals(namespace))
                    .filter(item -> !(item instanceof ToolItem || item instanceof ArmorItem || item instanceof BlockItem)) // Exclude tools, armor, and blocks
                    .filter(addedItems::add) // Only add if not already present
                    .forEach(item -> items.add(new ItemStack(item)));

            // 2. Add tools that contain the specific word in their name in specified order
            String[] toolOrder = {"sword", "axe", "pickaxe", "shovel", "hoe"};
            for (String tool : toolOrder) {
                ForgeRegistries.ITEMS.getValues().stream()
                        .filter(item -> item instanceof ToolItem)
                        .filter(item -> item.getDescriptionId().toLowerCase().contains(tool))
                        .filter(item -> item.getRegistryName() != null && item.getRegistryName().getNamespace().equals(namespace))
                        .filter(addedItems::add) // Only add if not already present
                        .forEach(item -> items.add(new ItemStack(item)));
            }

            // 3. Add armor that contains the specific word in their name in specified order
            String[] armorOrder = {"helmet", "chestplate", "leggings", "boots"};
            for (String armor : armorOrder) {
                ForgeRegistries.ITEMS.getValues().stream()
                        .filter(item -> item instanceof ArmorItem)
                        .filter(item -> item.getDescriptionId().toLowerCase().contains(armor))
                        .filter(item -> item.getRegistryName() != null && item.getRegistryName().getNamespace().equals(namespace))
                        .filter(addedItems::add) // Only add if not already present
                        .forEach(item -> items.add(new ItemStack(item)));
            }

            // 4. Add blocks that contain the specific word in their name
            ForgeRegistries.ITEMS.getValues().stream()
                    .filter(item -> item instanceof BlockItem)
                    .filter(item -> item.getDescriptionId().toLowerCase().contains(specificWord))
                    .filter(item -> item.getRegistryName() != null && item.getRegistryName().getNamespace().equals(namespace))
                    .filter(addedItems::add) // Only add if not already present
                    .forEach(item -> items.add(new ItemStack(item)));
        }
    };
}
