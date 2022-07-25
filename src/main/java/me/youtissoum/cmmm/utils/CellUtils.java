package me.youtissoum.cmmm.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class CellUtils {
    public static boolean moveInDirection(Direction direction, BlockPos pos, World world, BlockState caller) {
        BlockPos newPos = new BlockPos(
                pos.getX() + direction.getOffsetX(),
                pos.getY() + direction.getOffsetY(),
                pos.getZ() + direction.getOffsetZ()
        );

        BlockState block_at_new_location = world.getBlockState(newPos);

        if(block_at_new_location.getBlock() instanceof BlockWithEntity) {
            return false;
        }

        if(block_at_new_location.getBlock() != null && block_at_new_location.getBlock() != Blocks.AIR) {
            if(!moveInDirection(direction, newPos, world, block_at_new_location)) {
                return false;
            }
        }

        world.setBlockState(newPos, caller);
        world.setBlockState(pos, Blocks.AIR.getDefaultState());

        return true;
    }
}
