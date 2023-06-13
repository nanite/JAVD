package com.unrealdinnerbone.javd.data;

import com.unrealdinnerbone.javd.JAVDRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

public class ModelProvider extends FabricModelProvider {

    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        generator.createTrivialCube(JAVDRegistry.PORTAL_BLOCK.get());
    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
//        itemModelGenerator.generateFlatItem(JAGSRegistry.GRASS_SEED.get(), ModelTemplates.FLAT_ITEM);
    }
}
