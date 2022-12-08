package com.unrealdinnerbone.javd;

import com.unrealdinnerbone.javd.block.PortalBlock;
import com.unrealdinnerbone.javd.block.PortalTileEntity;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

public class JAVDRegistry {

    public static final List<DeferredRegister<?>> REGISTRIES = new ArrayList<>();

    private static final DeferredRegister<Block> BLOCKS = register(Registries.BLOCK);
    private static final DeferredRegister<Item> ITEMS = register(Registries.ITEM);
    private static final DeferredRegister<BlockEntityType<?>> TILES = register(Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<PortalBlock> PORTAL_BLOCK = BLOCKS.register("portal_block", PortalBlock::new);
    public static final RegistrySupplier<Item> PORTAL_BLOCK_ITEM = ITEMS.register("portal_block", () -> new BlockItem(PORTAL_BLOCK.get(), new Item.Properties()));
    public static final RegistrySupplier<BlockEntityType<PortalTileEntity>> PORTAL = TILES.register("portal", () -> BlockEntityType.Builder.of(PortalTileEntity::new, PORTAL_BLOCK.get()).build(null));

    public static final TagKey<Block> GENERATOR_BLOCKS = TagKey.create(Registries.BLOCK, new ResourceLocation(JAVD.MOD_ID, "generator"));



    private static <T> DeferredRegister<T> register(ResourceKey<Registry<T>> registry) {
        DeferredRegister<T> deferredRegister = DeferredRegister.create(JAVD.MOD_ID, registry);
        REGISTRIES.add(deferredRegister);
        return deferredRegister;
    }

}
