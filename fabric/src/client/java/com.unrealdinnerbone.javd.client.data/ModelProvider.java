package com.unrealdinnerbone.javd.client.data;

import com.unrealdinnerbone.javd.JAVDRegistry;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;

public class ModelProvider extends FabricModelProvider {

    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        generator.createTrivialCube(JAVDRegistry.PORTAL_BLOCK.getHolder().value());
    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {

    }
}
