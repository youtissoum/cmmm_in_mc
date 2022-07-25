package me.youtissoum.cmmm.blocks;

import me.youtissoum.cmmm.Cmmm;
import me.youtissoum.cmmm.utils.CellUtils;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class BlocksManager {

    public static final Cell GENERATOR = (Cell) registerBlock("generator",
            new CellBuilder()
                    .subtickId(0)
                    .onTick((BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, Direction direction) -> {
                        BlockPos behind_loc = CellUtils.getLocWithDirection(pos, direction, true);
                        BlockPos front_loc = CellUtils.getLocWithDirection(pos, direction);

                        BlockState cell_to_copy = world.getBlockState(behind_loc);

                        if(cell_to_copy.getBlock() == Blocks.AIR || cell_to_copy.getBlock() instanceof BlockWithEntity) {
                            return;
                        }

                        BlockState front_block = world.getBlockState(front_loc);

                        if(front_block.getBlock() != Blocks.AIR) {
                            CellUtils.pushInDirection(direction, front_loc, world, front_block);
                        }

                        world.setBlockState(front_loc, cell_to_copy);
                    }), ItemGroup.MISC);

    public static final Cell MOVER = (Cell) registerBlock("mover",
            new CellBuilder()
                    .subtickId(3)
                    .onTick((BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, Direction direction) ->
                            CellUtils.pushInDirection(direction, pos, world, state)
                    ), ItemGroup.MISC);


    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(Cmmm.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(Cmmm.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void registerModBlocks() {
    }

}