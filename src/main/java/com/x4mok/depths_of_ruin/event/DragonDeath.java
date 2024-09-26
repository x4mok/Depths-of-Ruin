package com.x4mok.depths_of_ruin.event;

import com.x4mok.depths_of_ruin.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber(modid = "depths_of_ruin")
public class DragonDeath {
	public static void sendPlayerMessage(PlayerEntity player, String message) {
		// Create a text component from the message string
		StringTextComponent textComponent = new StringTextComponent(TextFormatting.GREEN + message);

		// Send the message to the player
		player.sendMessage(textComponent, player.getUUID());
	}

	public static void dropItemInWorld(World world, PlayerEntity player, ItemStack itemStack) {
		if (!world.isClientSide) { // Ensure you're on the server side
			ItemEntity itemEntity = new ItemEntity(world, player.position().x, player.position().y, player.position().z, itemStack);
			world.addFreshEntity(itemEntity);
		}
	}

	@SubscribeEvent
	public static void onEnderDragonDeath(LivingDeathEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof EnderDragonEntity) {
			EnderDragonEntity enderDragon = (EnderDragonEntity) entity;
			PlayerEntity player = (PlayerEntity) enderDragon.getCombatTracker().getKiller();
			if (player != null) {
				// Give custom items to the player who dealt the final blow
				ItemStack dragonscale = new ItemStack(ModItems.DRAGON_SCALE.get());
				ItemStack dragonblood = new ItemStack(ModItems.DRAGON_BLOOD.get());

				System.out.println("got itemstacks");

				int randomInt = ThreadLocalRandom.current().nextInt(3, 9 + 1);

				for (int i = 1; i <= randomInt; i++) {
					System.out.println("Iteration " + i);
					DragonDeath.dropItemInWorld(player.getEntity().level, player, new ItemStack(ModItems.DRAGON_SCALE.get()));
					System.out.println("DROPPED");
				}

				randomInt = ThreadLocalRandom.current().nextInt(3, 9 + 1);

				for (int i = 1; i <= randomInt; i++) {
					System.out.println("Iteration " + i);
					DragonDeath.dropItemInWorld(player.getEntity().level, player, new ItemStack(ModItems.DRAGON_BLOOD.get()));
					System.out.println("DROPPED");
				}

				sendPlayerMessage(player, "A Dragon's Blood Dropped Near You!");
				sendPlayerMessage(player, "A Dragon's Scale Dropped Near You!");

			}
		}
	}
}
