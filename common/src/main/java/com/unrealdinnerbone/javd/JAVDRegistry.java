package com.unrealdinnerbone.javd;

import com.unrealdinnerbone.javd.block.PortalBlock;
import com.unrealdinnerbone.javd.block.PortalTileEntity;
import com.unrealdinnerbone.trenzalore.api.platform.Services;
import com.unrealdinnerbone.trenzalore.api.platform.services.IRegistry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryFactory;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.List;

public class JAVDRegistry implements IRegistry {


    private static final RegistryObjects<Block> BLOCKS = RegistryFactory.create(Registries.BLOCK);
    private static final RegistryObjects<Item> ITEMS = RegistryFactory.create(Registries.ITEM);

    private static final RegistryObjects<BlockEntityType<?>> TILES = RegistryFactory.create(Registries.BLOCK_ENTITY_TYPE);

    public static final RegistryEntry<PortalBlock> PORTAL_BLOCK = BLOCKS.register("portal_block", PortalBlock::new);

    public static final RegistryEntry<BlockItem> PORTAL_BLOCK_ITEM = ITEMS.register("portal_block", () -> new BlockItem(PORTAL_BLOCK.get(), new Item.Properties()));

    public static final RegistryEntry<BlockEntityType<?>> PORTAL = TILES.register("portal", () -> Services.PLATFORM.createBlockEntityType(PortalTileEntity::new, PORTAL_BLOCK.get()));

    public static final TagKey<Block> GENERATOR_BLOCKS = TagKey.create(Registries.BLOCK, new ResourceLocation(JAVD.MOD_ID, "generator"));


    public static class Keys {
        public static final ResourceKey<DimensionType> DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, new ResourceLocation(JAVD.MOD_ID, "void"));
        public static final ResourceKey<Biome> BIOME = ResourceKey.create(Registries.BIOME, new ResourceLocation(JAVD.MOD_ID, "void"));

        public static final ResourceKey<Level> LEVEL = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(JAVD.MOD_ID, "void"));
    }

    @Override
    public void afterRegistered() {
        BuiltInRegistries.CREATIVE_MODE_TAB.registryKeySet().stream()
                .filter(tabResourceKey -> tabResourceKey.location().equals(new ResourceLocation("tools_and_utilities")))
                .findFirst()
                .ifPresent(tabResourceKey -> RegistryFactory.registerCreativeTabItems(tabResourceKey, List.of(PORTAL_BLOCK_ITEM)));
    }

    @Override
    public List<RegistryObjects<?>> getRegistryObjects() {
        return List.of(BLOCKS, ITEMS, TILES);
    }

    @Override
    public String getModID() {
        return JAVD.MOD_ID;
    }
}
