package com.unrealdinnerbone.javd.util;

import com.unrealdinnerbone.javd.JAVD;
import com.unrealdinnerbone.javd.JAVDRegistry;
import com.unrealdinnerbone.javd.block.PortalTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Collection;
import java.util.Optional;

public class TelerportUtils {


    public static void teleport(Player playerEntity, ResourceKey<Level> toWorldKey, BlockPos blockPos, boolean spawnPlatform) {
        ServerLevel toWorld = playerEntity.getServer().getLevel(toWorldKey);
        if (toWorld != null) {
            findPortalLocation(toWorld, blockPos).ifPresentOrElse(portalLocation -> {
                        if (toWorld.getBlockState(portalLocation).isAir()) {
                            toWorld.setBlockAndUpdate(portalLocation, JAVDRegistry.PORTAL_BLOCK.get().defaultBlockState());
                            Block block = Registry.BLOCK.getTag(JAVDRegistry.GENERATOR_BLOCKS)
                                    .map(named -> named.getRandomElement(toWorld.getRandom()))
                                    .filter(Optional::isPresent)
                                    .map(Optional::get)
                                    .map(Holder::value)
                                    .orElse(Blocks.STONE);
                            int range = 3;
                            BlockPos.betweenClosedStream(portalLocation.offset(range, 0, range), portalLocation.offset(-range, 0, -range)).forEach(blockPos1 -> {
                                if (toWorld.getBlockState(blockPos1).isAir()) {
                                    toWorld.setBlockAndUpdate(blockPos1, block.defaultBlockState());
                                }
                            });

                        }
                        TeleportHelper.teleport(playerEntity, toWorld, portalLocation.getX() + 0.5, portalLocation.getY() + 1, portalLocation.getZ() + 0.5);
                    },
                    () -> playerEntity.displayClientMessage(Component.translatable(JAVD.MOD_ID + ".invalid.pos"), true));

        } else {
            playerEntity.displayClientMessage(Component.translatable(JAVD.MOD_ID + ".invalid.world", toWorldKey.location().toString()), true);
        }
    }


    private static Optional<BlockPos> findPortalLocation(Level worldTo, BlockPos fromPos) {
        if (worldTo.getBlockState(fromPos).getBlock() == JAVDRegistry.PORTAL_BLOCK.get() && isSafeSpawnLocation(worldTo, fromPos)) {
            return Optional.of(fromPos.above());
        }

        int range = 5;
        return Optional.ofNullable(ChunkPos.rangeClosed(worldTo.getChunkAt(fromPos).getPos(), range)
                .map(chunkPos -> worldTo.getChunk(chunkPos.x, chunkPos.z).getBlockEntitiesPos())
                .flatMap(Collection::stream).toList().stream()
                .filter(pos -> worldTo.getBlockEntity(pos) instanceof PortalTileEntity)
                .findFirst()
                .orElseGet(() -> {
                    BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(0, 0, 0);
                    int minY = worldTo.getMinBuildHeight();
                    int maxY = worldTo.getMaxBuildHeight();
                    int start = (minY + maxY);
                    if (start != 0) {
                        start = start / 2;
                    }
                    for (int y = start - 1; y > minY; y--) {
                        if (forLocationAround(worldTo, mutableBlockPos, fromPos.getX(), fromPos.getZ(), y)) {
                            return mutableBlockPos;
                        }
                    }
                    for (int y = start; y < maxY; y++) {
                        if (forLocationAround(worldTo, mutableBlockPos, fromPos.getX(), fromPos.getZ(), y)) {
                            return mutableBlockPos;
                        }
                    }

                    return null;
                }));

    }

    private static boolean forLocationAround(Level levelTo, BlockPos.MutableBlockPos blockPos, int fromX, int fromZ, int y) {
        for (int x = fromX - 6; x < fromX + 6; x++) {
            for (int z = fromZ - 6; z < fromZ + 6; z++) {
                blockPos.set(x, y, z);
                if(isSaveLocation(levelTo, blockPos)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isSaveLocation(Level levelTo, BlockPos.MutableBlockPos blockPos) {
        BlockState blockState = levelTo.getBlockState(blockPos);
        return blockState.isAir() && isSafeSpawnLocation(levelTo, blockPos.above());
    }


    private static boolean isSafeSpawnLocation(Level world, BlockPos blockPos) {
        return world.getBlockState(blockPos).isAir() && world.getBlockState(blockPos.above()).isAir();
    }


}
