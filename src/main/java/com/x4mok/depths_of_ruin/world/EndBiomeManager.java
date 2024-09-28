package com.x4mok.depths_of_ruin.world;

import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.biome.IBiomeMagnifier;

public class EndBiomeManager extends BiomeManager {
    public EndBiomeManager(IBiomeReader pNoiseBiomeSource, long pBiomeZoomSeed, IBiomeMagnifier pZoomer) {
        super(pNoiseBiomeSource, pBiomeZoomSeed, pZoomer);
    }
}
