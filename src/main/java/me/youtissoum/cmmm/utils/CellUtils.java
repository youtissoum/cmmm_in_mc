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

    /**
     * A function to push a cell in a direction
     * @param direction The direction to push the cell in
     * @param pos The original position of the cell
     * @param world The world to push the cell in
     * @param blockToPush The BlockState of the block to push
     * @return only true if the movement was a success
     */
    public static boolean pushInDirection(Direction direction, BlockPos pos, World world, BlockState blockToPush) {
        BlockPos newPos = getLocWithDirection(pos, direction);

        BlockState block_at_new_location = world.getBlockState(newPos);

        if(block_at_new_location.getBlock() instanceof BlockWithEntity) {
            return false;
        }

        if(block_at_new_location.getBlock() instanceof Cell cell_at_new_location) {
            switch(cell_at_new_location.cellId) {
                case "trash":
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                case "immobile":
                    return true;
                case "enemy":
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                    world.setBlockState(newPos, Blocks.AIR.getDefaultState());
                    return true;
                case "slide":
                    if(direction != cell_at_new_location.direction && direction != cell_at_new_location.direction.getOpposite()) {
                        return false;
                    }
            }
        }

        if(block_at_new_location.getBlock() != null && block_at_new_location.getBlock() != Blocks.AIR) {
            if(!pushInDirection(direction, newPos, world, block_at_new_location)) {
                return false;
            }
        }

        world.setBlockState(newPos, blockToPush);
        world.setBlockState(pos, Blocks.AIR.getDefaultState());

        return true;
    }
}
