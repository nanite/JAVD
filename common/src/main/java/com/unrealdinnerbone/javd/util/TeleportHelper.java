package com.unrealdinnerbone.javd.util;

import com.unrealdinnerbone.javd.compact.JAVDPlatform;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;

public class TeleportHelper {

    public static <T extends Entity> Entity teleport(T entity, ServerLevel level, double x, double y, double z) {
        return JAVDPlatform.teleport(entity, level, new PortalInfo(new Vec3(x, y, z), Vec3.ZERO, 0, 90));
    }
}
