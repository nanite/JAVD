package com.unrealdinnerbone.javd;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.impl.object.builder.FabricEntityType;

public class JAVDFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        JAVD.init();
    }
}
