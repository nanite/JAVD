package com.unrealdinnerbone.javd;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.common.Mod;

@Mod(JAVD.MOD_ID)
public class JAVDNeo {
    
    public JAVDNeo() {
        Player player;
        player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).setBaseValue(20.0D);
    }

}