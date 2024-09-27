package com.x4mok.depths_of_ruin.block;

import com.x4mok.depths_of_ruin.Depths_of_ruin;
import com.x4mok.depths_of_ruin.block.custom.trees.MahoganyTree;
import com.x4mok.depths_of_ruin.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Depths_of_ruin.MODID);

    public static final RegistryObject<Block> MAGIC_ENDSTONE = registerBlock("magic_endstone",
            () -> new Block(AbstractBlock.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(5f)));
    public static final RegistryObject<Block> MAHOGANY_LOG = registerBlock("mahogany_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> MAHOGANY_WOOD = registerBlock("mahogany_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> STRIPPED_MAHOGANY_LOG = registerBlock("stripped_mahogany_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> STRIPPED_MAHOGANY_WOOD = registerBlock("stripped_mahogany_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> MAHOGANY_PLANKS = registerBlock("mahogany_planks",
            () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> MAHOGANY_LEAVES = registerBlock("mahogany_leaves",
            () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).harvestTool(ToolType.HOE).strength(0.2f).randomTicks().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> MAHOGANY_SAPLING = registerBlock("mahogany_sapling",
            () -> new SaplingBlock(new MahoganyTree(),AbstractBlock.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> NULL = registerBlockWithRarity("null",
            () -> new Block(AbstractBlock.Properties.of(Material.STONE).harvestLevel(9).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(50f)), Rarity.EPIC);

    private static  <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);

        registerBlockItem(name, toReturn);

        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithRarity(String name, Supplier<T> block, Rarity rarity) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);

        registerBlockItemWithRarity(name, toReturn, rarity);

        return toReturn;
    }

    private static <T extends  Block> void registerBlockItemWithRarity(String name, RegistryObject<T> block, Rarity rarity) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().rarity(rarity)));
    }

    private static <T extends  Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
