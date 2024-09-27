package com.x4mok.depths_of_ruin.world.gen;

import com.x4mok.depths_of_ruin.block.ModBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.Mod;

public class ModConfiguredFeatures {

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MAHOGANY =
			register("mahogany",
					Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.MAHOGANY_LOG.get().defaultBlockState()),
							new SimpleBlockStateProvider(ModBlocks.MAHOGANY_LEAVES.get().defaultBlockState()),
							new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0)),
							new ForkyTrunkPlacer(4, 4, 0),
							new TwoLayerFeature(3, 0, 0))).ignoreVines().build()));

	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String p_243968_0_, ConfiguredFeature<FC, ?> p_243968_1_) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, p_243968_0_, p_243968_1_);
	}
}
