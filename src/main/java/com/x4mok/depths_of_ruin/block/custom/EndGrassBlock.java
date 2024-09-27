package com.x4mok.depths_of_ruin.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class EndGrassBlock extends Block {
	public EndGrassBlock() {
		super(Properties.of(Material.GRASS).strength(0.6f));
	}

	@Override
	public void randomTick(BlockState state, ServerWorld level, BlockPos pos, Random random) {
		// Spread to end stone if adjacent
		if (random.nextInt(100) < 20) { // 20% chance to spread per tick
			spreadToEndStone(level, pos);
		}
		if (random.nextInt(100) < 20) {
			setToEndstone(level, pos);
		}
	}


	private void setToEndstone(ServerWorld level, BlockPos pos) {
		BlockState adjacentState = level.getBlockState(pos.above());
		// Check if the adjacent block is end stone and is air above it
		if (!adjacentState.is(Blocks.AIR)) {
			level.setBlock(pos, Blocks.END_STONE.defaultBlockState(), 3);
		}

	}

	private void spreadToEndStone(ServerWorld level, BlockPos pos) {
		BlockPos[] adjacentPositions = {
				pos.north(), pos.east(), pos.south(), pos.west()
		};

		for (BlockPos adjacentPos : adjacentPositions) {
			BlockState adjacentState = level.getBlockState(adjacentPos);
			// Check if the adjacent block is end stone and is air above it
			if (adjacentState.is(Blocks.END_STONE) && level.getBlockState(adjacentPos.above()).is(Blocks.AIR)) {
				level.setBlock(adjacentPos, this.defaultBlockState(), 3);
				break; // Only spread to one adjacent block
			}
		}
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		// Additional state definitions can be added here if needed
	}
}
