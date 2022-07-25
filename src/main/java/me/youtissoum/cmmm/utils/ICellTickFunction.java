package me.youtissoum.cmmm.utils;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public interface ICellTickFunction {
    void call(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, Direction direction);
}