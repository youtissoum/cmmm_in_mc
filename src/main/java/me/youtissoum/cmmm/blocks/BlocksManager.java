package me.youtissoum.cmmm.blocks;

import me.youtissoum.cmmm.Cmmm;
import me.youtissoum.cmmm.utils.CellUtils;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.HashMap;

public class BlocksManager {

    /*
    public static final Cell GENERATOR = (Cell) registerBlock("generator",
            new CellBuilder()
                    .subtickId(0)
                    .onTick((BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, Direction direction) -> {

                    }), ItemGroup.MISC);
     */

    public static final Cell MOVER = (Cell) registerBlock("mover",
            new CellBuilder()
                    .subtickId(3)
                    .onTick((BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, Direction direction) ->
                            CellUtils.moveInDirection(direction, pos, world, state)
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