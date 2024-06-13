package com.unrealdinnerbone.javd;

import com.unrealdinnerbone.trenzalore.lib.RLUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class JAVD {
    public static final String MOD_ID = "javd";


    public static ResourceLocation rl(String value) {
        return RLUtils.rl(MOD_ID, value);
    }

}