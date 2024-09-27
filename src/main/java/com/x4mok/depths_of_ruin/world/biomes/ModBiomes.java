package com.x4mok.depths_of_ruin.world.biomes;

import com.x4mok.depths_of_ruin.Depths_of_ruin;
import com.x4mok.depths_of_ruin.world.gen.ModConfiguredFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBiomes {
	public static final DeferredRegister<Biome> BIOMES =
			DeferredRegister.create(ForgeRegistries.BIOMES, Depths_of_ruin.MODID);

	public static final RegistryObject<Biome> MAHOGANY_BIOME = BIOMES.register("mahogany_biome",
			ModBiomes::mahoganyBiome);
	public static final RegistryObject<Biome> MAHOGANY_PLATEAU = BIOMES.register("mahogany_plateau",
			ModBiomes::mahoganyPlateauBiome);
	public static final RegistryObject<Biome> DIVINE_QUARRY = BIOMES.register("divine_quarry",
			ModBiomes::divineQuarryBiome);



	public static Biome divineQuarryBiome() {
		return makeDivineQuarry(0.125f, 0.05f, 0.5f, divineQuarryMobs());
	}

	private static MobSpawnInfo.Builder divineQuarryMobs() {
		MobSpawnInfo.Builder lvt_0_1_ = new MobSpawnInfo.Builder();
		return lvt_0_1_;
	}

	public static Biome mahoganyBiome() {
		MobSpawnInfo.Builder lvt_5_1_ = mahoganyMobs();
		return makeMahoganyBiome(0.125F, 0.05F, 1.2F, false, false, lvt_5_1_);
	}

	private static MobSpawnInfo.Builder mahoganyMobs() {
		MobSpawnInfo.Builder lvt_0_1_ = new MobSpawnInfo.Builder();
		DefaultBiomeFeatures.farmAnimals(lvt_0_1_);
		lvt_0_1_.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.HORSE, 1, 2, 6)).addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.DONKEY, 1, 1, 1));
		DefaultBiomeFeatures.commonSpawns(lvt_0_1_);
		return lvt_0_1_;
	}

	public static Biome mahoganyPlateauBiome() {
		MobSpawnInfo.Builder lvt_0_1_ = mahoganyMobs();
		lvt_0_1_.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.LLAMA, 8, 4, 4));
		return makeMahoganyBiome(1.5F, 0.025F, 1.0F, true, false, lvt_0_1_);
	}

	private static Biome makeDivineQuarry(float depth, float scale, float temperature, MobSpawnInfo.Builder mobSpawnBuilder) {
		// Define biome generation settings
		BiomeGenerationSettings.Builder biomeGenSettings = new BiomeGenerationSettings.Builder();

		// Use your custom Surface Builder (for now, just using a placeholder)
		biomeGenSettings.surfaceBuilder(ModConfiguredSurfaceBuilders.DIVINE_QUARRY_SURFACE);

		// Add lakes and caves
		DefaultBiomeFeatures.addDefaultCarvers(biomeGenSettings);
		DefaultBiomeFeatures.addDefaultLakes(biomeGenSettings);
		DefaultBiomeFeatures.addDefaultMonsterRoom(biomeGenSettings);
		DefaultBiomeFeatures.addDefaultUndergroundVariety(biomeGenSettings);
		DefaultBiomeFeatures.addDefaultOres(biomeGenSettings);
		DefaultBiomeFeatures.addDefaultSoftDisks(biomeGenSettings);

		// Set biome effects like grass color, fog, and sky color for the End
		BiomeAmbience.Builder biomeAmbience = new BiomeAmbience.Builder()
				.grassColorOverride(0xC0E481) // Custom grass color for your biome
				.fogColor(0xA080A0) // Typical End fog color
				.skyColor(0x8080FF) // Typical End sky color
				.waterColor(0x3F76E4) // Default water color
				.waterFogColor(0x050533); // Default water fog color

		// Build and return the biome with the specified parameters (e.g., depth, scale, temperature)
		return new Biome.Builder()
				.precipitation(Biome.RainType.RAIN) // Allow rain
				.biomeCategory(Biome.Category.THEEND) // End-themed biome
				.depth(depth) // Customizable depth
				.scale(scale) // Customizable scale
				.temperature(temperature) // Customizable temperature
				.downfall(0.5F) // Allows rain
				.specialEffects(biomeAmbience.build())
				.mobSpawnSettings(mobSpawnBuilder.build()) // Custom mob spawn settings
				.generationSettings(biomeGenSettings.build()) // Generation settings
				.build();
	}



	private static Biome makeMahoganyBiome(float depth, float scale, float temperature, boolean isMountain, boolean isShattered, MobSpawnInfo.Builder mobSpawnBuilder) {
		// Choose surface builder based on whether the savanna is shattered or not
		BiomeGenerationSettings.Builder biomeGenSettings =
				(new BiomeGenerationSettings.Builder())
						.surfaceBuilder(isShattered ? ConfiguredSurfaceBuilders.SHATTERED_SAVANNA : ConfiguredSurfaceBuilders.GRASS);

		// Add structures only if it's not a mountain and not a shattered savanna
		if (!isMountain && !isShattered) {
			biomeGenSettings
					.addStructureStart(StructureFeatures.VILLAGE_SAVANNA)
					.addStructureStart(StructureFeatures.PILLAGER_OUTPOST);
		}

		// Add default land structures to the biome
		DefaultBiomeFeatures.addDefaultOverworldLandStructures(biomeGenSettings);

		// Add the ruined portal structure, using a different one for mountains
		biomeGenSettings.addStructureStart(isMountain ? StructureFeatures.RUINED_PORTAL_MOUNTAIN : StructureFeatures.RUINED_PORTAL_STANDARD);

		// Add basic biome features like carvers, lakes, underground variety, etc.
		DefaultBiomeFeatures.addDefaultCarvers(biomeGenSettings);
		DefaultBiomeFeatures.addDefaultLakes(biomeGenSettings);
		DefaultBiomeFeatures.addDefaultMonsterRoom(biomeGenSettings);

		// If it's not a shattered savanna, add regular savanna grass
		if (!isShattered) {
			DefaultBiomeFeatures.addSavannaGrass(biomeGenSettings);
		}

		// Add features like ores, underground variety, soft disks (clay, sand, gravel)
		DefaultBiomeFeatures.addDefaultUndergroundVariety(biomeGenSettings);
		DefaultBiomeFeatures.addDefaultOres(biomeGenSettings);
		DefaultBiomeFeatures.addDefaultSoftDisks(biomeGenSettings);

		// Add mahogany trees
		biomeGenSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.MAHOGANY);

		// If it's a shattered savanna, add specific shattered grass
		if (isShattered) {
			DefaultBiomeFeatures.addDefaultFlowers(biomeGenSettings);
			DefaultBiomeFeatures.addShatteredSavannaGrass(biomeGenSettings);
		} else {  // Otherwise, add warm flowers, and extra grass
			DefaultBiomeFeatures.addWarmFlowers(biomeGenSettings);
			DefaultBiomeFeatures.addSavannaExtraGrass(biomeGenSettings);
		}

		// Add common biome features like mushrooms, extra vegetation, and springs
		DefaultBiomeFeatures.addDefaultMushrooms(biomeGenSettings);
		DefaultBiomeFeatures.addDefaultExtraVegetation(biomeGenSettings);
		DefaultBiomeFeatures.addDefaultSprings(biomeGenSettings);

		// Add surface freezing feature
		DefaultBiomeFeatures.addSurfaceFreezing(biomeGenSettings);

		// Build and return the biome with the specified parameters (e.g., depth, scale, temperature)
		return (new Biome.Builder())
				.precipitation(Biome.RainType.NONE)
				.biomeCategory(Biome.Category.SAVANNA)
				.depth(depth)
				.scale(scale)
				.temperature(temperature)
				.downfall(0.0F)
				.specialEffects((new BiomeAmbience.Builder())
						.waterColor(4159204)
						.waterFogColor(329011)
						.fogColor(12638463)
						.skyColor(calculateSkyColor(temperature))
						.ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)
						.build())
				.mobSpawnSettings(mobSpawnBuilder.build())
				.generationSettings(biomeGenSettings.build())
				.build();
	}

	private static int calculateSkyColor(float temperature) {
		// Normalize the temperature value for sky color calculation
		float normalizedTemperature = temperature;

		// Divide the temperature by 3.0 to scale it down
		normalizedTemperature /= 3.0F;

		// Clamp the temperature value between -1.0 and 1.0 to avoid extreme values
		normalizedTemperature = MathHelper.clamp(normalizedTemperature, -1.0F, 1.0F);

		// Convert the adjusted temperature to an RGB color using HSV to RGB conversion
		return MathHelper.hsvToRgb(
				0.62222224F - normalizedTemperature * 0.05F,  // Adjust hue based on temperature
				0.5F + normalizedTemperature * 0.1F,          // Adjust saturation based on temperature
				1.0F                                           // Maximum brightness
		);
	}




	public static void register(IEventBus eventBus) {
		BIOMES.register(eventBus);
	}
}
