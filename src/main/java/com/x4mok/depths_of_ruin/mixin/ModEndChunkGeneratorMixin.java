package com.x4mok.depths_of_ruin.mixin;

import com.x4mok.depths_of_ruin.world.gen.ModEndBiomeProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.EndBiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(ChunkGenerator.class)
public class ModEndChunkGeneratorMixin {

	@Inject(method = "<init>", at = @At("RETURN"))
	private void injectCustomEndBiomeProvider(Registry<Biome> biomeRegistry, long seed, BiomeProvider provider, CallbackInfo ci) {
		if (provider instanceof EndBiomeProvider) {
			ModEndBiomeProvider customProvider = new ModEndBiomeProvider(seed, biomeRegistry);

			// Use reflection to access the private biome provider field
			try {
				Field biomeProviderField = ChunkGenerator.class.getDeclaredField("biomeProvider");
				biomeProviderField.setAccessible(true);  // Allow access to private field
				biomeProviderField.set(this, customProvider);  // Set the custom biome provider
			} catch (NoSuchFieldException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}

