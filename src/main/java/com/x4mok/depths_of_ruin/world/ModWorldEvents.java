package com.x4mok.depths_of_ruin.world;

import com.x4mok.depths_of_ruin.Depths_of_ruin;
import com.x4mok.depths_of_ruin.world.gen.ModTreeGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Depths_of_ruin.MODID)
public class ModWorldEvents {

	@SubscribeEvent
	public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
		ModTreeGeneration.generateTrees(event);
	}

}
