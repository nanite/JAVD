package com.unrealdinnerbone.javd.data;

import com.unrealdinnerbone.javd.JAVDRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class LootTableProvider extends FabricBlockLootTableProvider {

    protected LootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        dropSelf(JAVDRegistry.PORTAL_BLOCK.get());
    }
}
