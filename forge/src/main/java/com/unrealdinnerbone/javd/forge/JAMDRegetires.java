package com.unrealdinnerbone.javd.forge;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.data.worldgen.*;
import net.minecraft.data.worldgen.biome.Biomes;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.network.chat.ChatType;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorPresets;
import net.minecraft.world.level.levelgen.presets.WorldPresets;

public class JAMDRegetires
{

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
//            .add(Registries.DIMENSION_TYPE, DimensionTypes::bootstrap)
//            .add(Registries.CONFIGURED_CARVER, Carvers::bootstrap)
//            .add(Registries.CONFIGURED_FEATURE, FeatureUtils::bootstrap)
//            .add(Registries.PLACED_FEATURE, PlacementUtils::bootstrap)
//            .add(Registries.STRUCTURE, Structures::bootstrap)
//            .add(Registries.STRUCTURE_SET, StructureSets::bootstrap)
//            .add(Registries.PROCESSOR_LIST, ProcessorLists::bootstrap)
//            .add(Registries.TEMPLATE_POOL, Pools::bootstrap)
            .add(Registries.BIOME, JAMDBiomes::bootstrap)
//            .add(Registries.NOISE, NoiseData::bootstrap)
//            .add(Registries.DENSITY_FUNCTION, NoiseRouterData::bootstrap)
//            .add(Registries.NOISE_SETTINGS, NoiseGeneratorSettings::bootstrap)
//            .add(Registries.WORLD_PRESET, WorldPresets::bootstrap)
//            .add(Registries.FLAT_LEVEL_GENERATOR_PRESET, FlatLevelGeneratorPresets::bootstrap)
//            .add(Registries.CHAT_TYPE, ChatType::bootstrap)
            ;

    public static HolderLookup.Provider createLookup() {
        return BUILDER.build(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY));
    }
}
