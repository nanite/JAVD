package com.unrealdinnerbone.javd.data;

import com.unrealdinnerbone.javd.JAVDRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends FabricTagProvider.BlockTagProvider {


    public BlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        getOrCreateTagBuilder(JAVDRegistry.GENERATOR_BLOCKS)
                .add(Blocks.BLACK_CONCRETE)
                .add(Blocks.BLUE_CONCRETE)
                .add(Blocks.BROWN_CONCRETE)
                .add(Blocks.CYAN_CONCRETE)
                .add(Blocks.GRAY_CONCRETE)
                .add(Blocks.GREEN_CONCRETE)
                .add(Blocks.LIGHT_BLUE_CONCRETE)
                .add(Blocks.LIGHT_GRAY_CONCRETE)
                .add(Blocks.LIME_CONCRETE)
                .add(Blocks.MAGENTA_CONCRETE)
                .add(Blocks.ORANGE_CONCRETE)
                .add(Blocks.PINK_CONCRETE)
                .add(Blocks.PURPLE_CONCRETE)
                .add(Blocks.RED_CONCRETE)
                .add(Blocks.WHITE_CONCRETE)
                .add(Blocks.YELLOW_CONCRETE);
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(JAVDRegistry.PORTAL_BLOCK.get());
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(JAVDRegistry.PORTAL_BLOCK.get());
    }
}
