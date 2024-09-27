package com.x4mok.depths_of_ruin.world.gen;

import com.x4mok.depths_of_ruin.block.custom.trees.MahoganyTree;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class ModTreeGeneration {

	public static void generateTrees(final BiomeLoadingEvent event) {

		RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());

		Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

		// Check if the biome name matches "depths_of_ruin:mahogany_biome" or "depths_of_ruin:mahogany_plateau"
		if (key.equals(RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("depths_of_ruin:mahogany_biome"))) ||
				key.equals(RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("depths_of_ruin:mahogany_plateau")))) {

			List<Supplier<ConfiguredFeature<?, ?>>> base =
					event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

			base.add(() -> ModConfiguredFeatures.MAHOGANY
					.decorated(Features.Placements.HEIGHTMAP_SQUARE
							.decorated(Placement.COUNT_EXTRA.configured(
									new AtSurfaceWithExtraConfig(2, 0.5f, 2)))));
		}

	}


}
