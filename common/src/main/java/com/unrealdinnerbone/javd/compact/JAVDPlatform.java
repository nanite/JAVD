package com.unrealdinnerbone.javd.compact;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.PortalInfo;
import org.jetbrains.annotations.Nullable;

public class JAVDPlatform {

    @ExpectPlatform
    public static @Nullable <T extends Entity> Entity teleport(T entity, ServerLevel level, PortalInfo portalInfo) {
        throw new AssertionError();
    }
}
