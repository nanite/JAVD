package com.unrealdinnerbone.javd;

import com.unrealdinnerbone.javd.block.PortalBlock;
import com.unrealdinnerbone.javd.block.PortalTileEntity;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
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

    private static final DeferredRegister<Block> BLOCKS = register(Registry.BLOCK_REGISTRY);
    private static final DeferredRegister<Item> ITEMS = register(Registry.ITEM_REGISTRY);
    private static final DeferredRegister<BlockEntityType<?>> TILES = register(Registry.BLOCK_ENTITY_TYPE_REGISTRY);
    private static final DeferredRegister<Biome> BIOMES = register(Registry.BIOME_REGISTRY);
    private static final DeferredRegister<DimensionType> DIMENSION_TYPE = register(Registry.DIMENSION_TYPE_REGISTRY);

    public static final RegistrySupplier<PortalBlock> PORTAL_BLOCK = BLOCKS.register("portal_block", PortalBlock::new);
    public static final RegistrySupplier<Item> PORTAL_BLOCK_ITEM = ITEMS.register("portal_block", () -> new BlockItem(PORTAL_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_TRANSPORTATION)));
    public static final RegistrySupplier<BlockEntityType<PortalTileEntity>> PORTAL = TILES.register("portal", () -> BlockEntityType.Builder.of(PortalTileEntity::new, PORTAL_BLOCK.get()).build(null));

    public static final TagKey<Block> GENERATOR_BLOCKS = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(JAVD.MOD_ID, "generator"));


    public static final RegistrySupplier<Biome> BIOME = BIOMES.register(JAVD.DIM_ID.getPath(), () -> new Biome.BiomeBuilder()
            .temperature(1)
            .downfall(0.4f)
            .precipitation(Biome.Precipitation.NONE)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .specialEffects(new BiomeSpecialEffects.Builder()
                    .skyColor(8103167)
                    .fogColor(12638463)
                    .waterColor(4445678)
                    .waterFogColor(270131)
                    .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build());


    public static final RegistrySupplier<DimensionType> TYPE = DIMENSION_TYPE.register(JAVD.DIM_ID.getPath(), () -> new DimensionType(OptionalLong.of(6000),
            true,
            false,
            false,
            true,
            1.0D,
            true,
            false,
            -64,
            384,
            384,
            BlockTags.INFINIBURN_OVERWORLD,
            BuiltinDimensionTypes.OVERWORLD_EFFECTS,
            1.0F,
            new DimensionType.MonsterSettings(false,
                    false,
                    UniformInt.of(0, 7), 0)));

    private static <T> DeferredRegister<T> register(ResourceKey<Registry<T>> registry) {
        DeferredRegister<T> deferredRegister = DeferredRegister.create(JAVD.MOD_ID, registry);
        REGISTRIES.add(deferredRegister);
        return deferredRegister;
    }

}
