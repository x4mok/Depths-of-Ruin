package com.x4mok.depths_of_ruin.world.gen;

import com.x4mok.depths_of_ruin.world.biomes.ModBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ModBiomeGeneration {
	public static void generateBiomes() {
		addBiome(ModBiomes.MAHOGANY_BIOME.get(), BiomeManager.BiomeType.WARM, 20, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.PLAINS);
		addBiome(ModBiomes.MAHOGANY_PLATEAU.get(), BiomeManager.BiomeType.WARM, 20, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.PLATEAU);
	}

	private static void addBiome(Biome biome, BiomeManager.BiomeType type, int weight, BiomeDictionary.Type... types) {
		RegistryKey<Biome> key = RegistryKey.create(ForgeRegistries.Keys.BIOMES,
				Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome)));

		BiomeDictionary.addTypes(key, types);
		BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(key, weight));
	}
}
