package me.youtissoum.cmmm.utils;

import me.youtissoum.cmmm.blocks.Cell;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Objects;

public class CellUtils {
    public static BlockPos getLocWithDirection(BlockPos origPos, Direction direction, boolean reversed, int exponent) {
        if(reversed) {
            return new BlockPos(origPos.getX() - direction.getOffsetX()*exponent, origPos.getY() - direction.getOffsetY()*exponent, origPos.getZ() - direction.getOffsetZ()*exponent);
        } else {
            return new BlockPos(origPos.getX() + direction.getOffsetX()*exponent, origPos.getY() + direction.getOffsetY()*exponent, origPos.getZ() + direction.getOffsetZ()*exponent);
        }
    }

    public static BlockPos getLocWithDirection(BlockPos origPos, Direction direction, boolean reversed) {
        return getLocWithDirection(origPos, direction, reversed, 1);
    }

    public static BlockPos getLocWithDirection(BlockPos origPos, Direction direction, int exponent) {
        return getLocWithDirection(origPos, direction, false, exponent);
    }

    public static BlockPos getLocWithDirection(BlockPos origPos, Direction direction) {
        return getLocWithDirection(origPos, direction, false, 1);
    }

    public static boolean pushInDirection(Direction direction, BlockPos pos, World world, BlockState block_to_push) {
        BlockPos newPos = getLocWithDirection(pos, direction);

        BlockState block_at_new_location = world.getBlockState(newPos);

        if(block_at_new_location.getBlock() instanceof BlockWithEntity) {
            return false;
        }

        if(block_at_new_location.getBlock() instanceof Cell cell_at_new_location) {
            if(Objects.equals(cell_at_new_location.cellId, "trash")) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
                return true;
            } else if(Objects.equals(cell_at_new_location.cellId, "enemy")) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
                world.setBlockState(newPos, Blocks.AIR.getDefaultState());
                return true;
            }
        }

        if(block_at_new_location.getBlock() != null && block_at_new_location.getBlock() != Blocks.AIR) {
            if(!pushInDirection(direction, newPos, world, block_at_new_location)) {
                return false;
            }
        }

        world.setBlockState(newPos, block_to_push);
        world.setBlockState(pos, Blocks.AIR.getDefaultState());

        return true;
    }
}
