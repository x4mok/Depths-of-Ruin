package com.x4mok.depths_of_ruin.world;

import com.x4mok.depths_of_ruin.Depths_of_ruin;
import com.x4mok.depths_of_ruin.world.gen.ModTreeGeneration;
import com.x4mok.depths_of_ruin.world.biomes.ModBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.EndBiomeProvider;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Depths_of_ruin.MODID)
public class ModWorldEvents {

	// This method handles biome loading events
	@SubscribeEvent
	public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
		// Handle tree generation (existing functionality)
		ModTreeGeneration.generateTrees(event);

		// Inject Divine Quarry biome into the End generation
		injectDivineQuarryBiome(event);
	}

	// Inject custom Divine Quarry biome into End biome generation
	private static void injectDivineQuarryBiome(BiomeLoadingEvent event) {
		// Check if the biome belongs to the End dimension
		if (event.getCategory() == Biome.Category.THEEND) {
			// Use ResourceLocation to reference the custom Divine Quarry biome
			RegistryKey<Biome> divineQuarryKey = RegistryKey.create(ForgeRegistries.Keys.BIOMES,
					new ResourceLocation("depths_of_ruin", "divine_quarry"));

			BiomeManager.addBiome(BiomeManager.BiomeType.COOL,
					new BiomeManager.BiomeEntry(divineQuarryKey, 20));

		}
	}
}
