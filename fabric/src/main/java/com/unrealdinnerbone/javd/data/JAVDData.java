package com.unrealdinnerbone.javd.data;

import com.unrealdinnerbone.javd.JAVDRegistry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.OptionalLong;

public class JAVDData implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(DRP::new);
        pack.addProvider(BlockTagProvider::new);
        pack.addProvider(LangProvider::new);
        pack.addProvider(ModelProvider::new);
        pack.addProvider(RecipeProvider::new);
        pack.addProvider(LootTableProvider::new);
        pack.addProvider(AdvancementProvider::new);

    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.DIMENSION_TYPE, this::bootstrapDimensionType);
        registryBuilder.add(Registries.BIOME, this::bootstrapBiome);
    }

    private void bootstrapBiome(BootstapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> configuredWorldCarvers = context.lookup(Registries.CONFIGURED_CARVER);
        context.register(JAVDRegistry.Keys.BIOME, new Biome.BiomeBuilder()
                .temperature(1)
                .downfall(0.4f)
                .hasPrecipitation(false)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .skyColor(8103167)
                        .fogColor(12638463)
                        .waterColor(4445678)
                        .waterFogColor(270131)
                        .build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, configuredWorldCarvers).build())
                .build());
    }

    private void bootstrapDimensionType(BootstapContext<DimensionType> context) {
        context.register(JAVDRegistry.Keys.DIMENSION_TYPE, new DimensionType(OptionalLong.of(6000),
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
    }
}
