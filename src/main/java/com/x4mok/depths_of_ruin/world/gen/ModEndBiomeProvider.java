package com.x4mok.depths_of_ruin.world.gen;

import com.x4mok.depths_of_ruin.world.biomes.ModBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.EndBiomeProvider;
import net.minecraft.world.gen.SimplexNoiseGenerator;

import java.util.Random;

public class ModEndBiomeProvider extends EndBiomeProvider {

	private final SimplexNoiseGenerator noiseGenerator;

	// Constructor (you can pass in the seed and noise generator here)
	public ModEndBiomeProvider(long seed, Registry<Biome> biomeRegistry) {
		super(biomeRegistry, seed);
		Random random = new Random(seed);
		this.noiseGenerator = new SimplexNoiseGenerator(random);
	}

	@Override
	public Biome getNoiseBiome(int x, int y, int z) {
		// Custom logic to inject your Divine Quarry biome
		// You can control where and when this biome appears based on noise or coordinates

		// Example: Replace a section of End Highlands with Divine Quarry biome
		if (noiseGenerator.getValue(x * 0.01D, z * 0.01D) > 0.5D) {
			return ModBiomes.DIVINE_QUARRY.get();  // Your custom biome
		}

		// Fallback to the default End biome generation (super method call)
		return super.getNoiseBiome(x, y, z);
	}
}
