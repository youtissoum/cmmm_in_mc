package me.youtissoum.cmmm;

import me.youtissoum.cmmm.blocks.BlocksManager;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModRegistries {
    public static void registerMod() {
        BlocksManager.registerModBlocks();
    }

    public static final ItemGroup CELL_GROUP = FabricItemGroupBuilder.build(new Identifier(Cmmm.MOD_ID, "cell_group"),
            () -> new ItemStack(BlocksManager.MOVER));
}
