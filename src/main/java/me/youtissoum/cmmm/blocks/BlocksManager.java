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

import java.util.Objects;

public class BlocksManager {

    public static final Cell GENERATOR = (Cell) registerBlock("generator",
            new CellBuilder()
                    .cellId("generator")
                    .subtickId(0)
                    .onTick((state, world, pos, player, hand, hit, direction) -> {
                        BlockPos behind_loc = CellUtils.getLocWithDirection(pos, direction, true);
                        BlockPos front_loc = CellUtils.getLocWithDirection(pos, direction);

                        BlockState cell_to_copy = world.getBlockState(behind_loc);

                        if(cell_to_copy.getBlock() == Blocks.AIR || cell_to_copy.getBlock() instanceof BlockWithEntity) {
                            return;
                        }

                        BlockState front_block = world.getBlockState(front_loc);

                        if(front_block.getBlock() instanceof Cell front_cell) {
                            if(Objects.equals(front_cell.cellId, "enemy")) {
                                world.setBlockState(front_loc, Blocks.AIR.getDefaultState());
                            }
                            return;
                        }

                        if(front_block.getBlock() != Blocks.AIR) {
                            CellUtils.pushInDirection(direction, front_loc, world, front_block);
                        }

                        world.setBlockState(front_loc, cell_to_copy);
                    }), ItemGroup.MISC);

    public static final Cell MOVER = (Cell) registerBlock("mover",
            new CellBuilder()
                    .cellId("mover")
                    .subtickId(3)
                    .onTick((state, world, pos, player, hand, hit, direction) -> CellUtils.pushInDirection(direction, pos, world, state)), ItemGroup.MISC);

    public static final Cell PUSH = (Cell) registerBlock("push",
            new CellBuilder()
                    .cellId("push")
                    .subtickId(-1)
                    .onTick(((state, world, pos, player, hand, hit, direction) -> {})), ItemGroup.MISC);

    public static final Cell ENEMY = (Cell) registerBlock("enemy",
            new CellBuilder()
                    .cellId("enemy")
                    .subtickId(-1)
                    .onTick((state, world, pos, player, hand, hit, direction) -> {}), ItemGroup.MISC);

    public static final Cell TRASH = (Cell) registerBlock("trash",
            new CellBuilder()
                    .cellId("trash")
                    .subtickId(-1)
                    .onTick((state, world, pos, player, hand, hit, direction) -> {}), ItemGroup.MISC);


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