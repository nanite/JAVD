package com.unrealdinnerbone.javd;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class JAVD
{
    public static final String MOD_ID = "javd";

    public static final ResourceLocation DIM_ID = new ResourceLocation(MOD_ID, "void");

    public static final ResourceKey<Level> MINING_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY, DIM_ID);

    public static void init() {
        JAVDRegistry.REGISTRIES.forEach(DeferredRegister::register);
    }
}
