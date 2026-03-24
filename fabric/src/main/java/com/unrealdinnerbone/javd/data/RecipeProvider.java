package com.unrealdinnerbone.javd.data;

import com.unrealdinnerbone.javd.JAVD;
import com.unrealdinnerbone.javd.JAVDRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {

    public RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    protected net.minecraft.data.recipes.RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new net.minecraft.data.recipes.RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.TRANSPORTATION, JAVDRegistry.PORTAL_BLOCK_ITEM.getHolder().value())
                        .pattern("OOO")
                        .pattern("OEO")
                        .pattern("OOO")
                        .define('O', Blocks.OBSIDIAN)
                        .define('E', Items.ENDER_PEARL)
                        .unlockedBy("has_ender_pearl", has(Items.ENDER_PEARL))
                        .save(output, ResourceKey.create(Registries.RECIPE, JAVD.rl("portal_block")));
            }
        };
    }

    @Override
    public String getName() {
        return "";
    }
}
