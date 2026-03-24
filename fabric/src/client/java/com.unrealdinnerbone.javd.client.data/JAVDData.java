package com.unrealdinnerbone.javd.client.data;

import com.unrealdinnerbone.javd.JAVDRegistry;
import com.unrealdinnerbone.javd.data.AdvancementProvider;
import com.unrealdinnerbone.javd.data.BlockTagProvider;
import com.unrealdinnerbone.javd.data.DRP;
import com.unrealdinnerbone.javd.data.LangProvider;
import com.unrealdinnerbone.javd.data.LootTableProvider;
import com.unrealdinnerbone.javd.data.RecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TimelineTags;
import net.minecraft.util.ARGB;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.attribute.AmbientSounds;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.timeline.Timeline;

import java.util.Optional;
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

    private void bootstrapBiome(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> configuredWorldCarvers = context.lookup(Registries.CONFIGURED_CARVER);
        context.register(JAVDRegistry.Keys.BIOME, new Biome.BiomeBuilder()
                .temperature(1)
                .downfall(0.4f)
                .hasPrecipitation(false)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .setAttribute(EnvironmentAttributes.SKY_COLOR, 8103167)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 12638463)
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 270131)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(4445678)
                        .build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, configuredWorldCarvers).build())
                .build());
    }



    private void bootstrapDimensionType(BootstrapContext<DimensionType> context) {
        HolderGetter<Timeline> holdergetter = context.lookup(Registries.TIMELINE);
        HolderGetter<WorldClock> clocks = context.lookup(Registries.WORLD_CLOCK);
        EnvironmentAttributeMap environmentattributemap = EnvironmentAttributeMap.builder()
//                .set(EnvironmentAttributes.FOG_COLOR, -4138753)
//                .set(EnvironmentAttributes.CLOUD_COLOR, ARGB.white(0.8F))
//                .set(EnvironmentAttributes.CLOUD_HEIGHT, 192.33F)
//                .set(EnvironmentAttributes.BACKGROUND_MUSIC, BackgroundMusic.OVERWORLD)
//                .set(EnvironmentAttributes.BED_RULE, BedRule.CAN_SLEEP_WHEN_DARK)
//                .set(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, false)
//                .set(EnvironmentAttributes.NETHER_PORTAL_SPAWNS_PIGLINS, true)
//                .set(EnvironmentAttributes.AMBIENT_SOUNDS, AmbientSounds.LEGACY_CAVE_SETTINGS)
                .build();
        context.register(JAVDRegistry.Keys.DIMENSION_TYPE, new DimensionType(
                true,
                true,
                false,
                false,
                1.0f,
                -64,
                384,
                384,
                BlockTags.INFINIBURN_OVERWORLD,
                0.25F,
                new DimensionType.MonsterSettings(UniformInt.of(0, 7), 0),
                DimensionType.Skybox.OVERWORLD,
                CardinalLighting.Type.DEFAULT,
                environmentattributemap,
                holdergetter.getOrThrow(TimelineTags.UNIVERSAL),
                Optional.of(clocks.getOrThrow(WorldClocks.OVERWORLD)))
        );
    }
}
