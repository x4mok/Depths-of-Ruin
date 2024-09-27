package com.x4mok.depths_of_ruin.world.biomes;

import com.x4mok.depths_of_ruin.Depths_of_ruin;
import com.x4mok.depths_of_ruin.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ModConfiguredSurfaceBuilders {

	// and example because mahogany will be the minecraft grass one
	public static ConfiguredSurfaceBuilder<?> EXAMPLE_SURFACE = register("example_surface",
			SurfaceBuilder.DEFAULT.configured(new SurfaceBuilderConfig(
					Blocks.OAK_LOG.defaultBlockState(), // TOP MATERIAL (E.G GRASS BLOCKS)
					Blocks.OAK_LEAVES.defaultBlockState(), // UNDER TOP MATERIAL (E.G DIRT)
					Blocks.TNT.defaultBlockState() // UNDER WATER MATERIAL (E.G STONE)
			)));
	public static ConfiguredSurfaceBuilder<?> DIVINE_QUARRY_SURFACE = register("divine_quarry_surface",
			SurfaceBuilder.DEFAULT.configured(new SurfaceBuilderConfig(
					ModBlocks.END_GRASS.get().defaultBlockState(), // TOP MATERIAL (E.G GRASS BLOCKS)
					Blocks.END_STONE.defaultBlockState(), // UNDER TOP MATERIAL (E.G DIRT)
					Blocks.END_STONE.defaultBlockState() // UNDER WATER MATERIAL (E.G STONE)
			)));


	private static <SC extends ISurfaceBuilderConfig>ConfiguredSurfaceBuilder<SC> register(String name,
																						   ConfiguredSurfaceBuilder<SC> csb) {
		return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER,
				new ResourceLocation(Depths_of_ruin.MODID, name), csb);
	}
}
