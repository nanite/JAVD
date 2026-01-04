package com.unrealdinnerbone.javd;

import com.unrealdinnerbone.trenzalore.lib.IDUtils;
import net.minecraft.resources.Identifier;

public class JAVD {
    public static final String MOD_ID = "javd";


    public static Identifier rl(String value) {
        return IDUtils.id(MOD_ID, value);
    }

}