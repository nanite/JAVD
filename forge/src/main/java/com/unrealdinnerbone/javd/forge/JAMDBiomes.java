package com.unrealdinnerbone.javd.forge;

import com.unrealdinnerbone.javd.JAVD;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.OptionalLong;

public class JAMDBiomes {

    public static void bootstrapDimTypes(BootstapContext<DimensionType> context) {
        context.register(ResourceKey.create(Registries.DIMENSION_TYPE, new ResourceLocation(JAVD.MOD_ID, "void")), createMiningDimType());

    }

    private static DimensionType createMiningDimType() {
        return new DimensionType(OptionalLong.of(6000),
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
                        UniformInt.of(0, 7), 0));
    }

    public static void bootstrapBiomes(BootstapContext<Biome> bootstapContext) {
        HolderGetter<PlacedFeature> features = bootstapContext.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers = bootstapContext.lookup(Registries.CONFIGURED_CARVER);
        bootstapContext.register(createKey("void"), createVoidBiome(features, carvers));
    }


    private static Biome createVoidBiome(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        BiomeGenerationSettings.Builder settings = new BiomeGenerationSettings.Builder(features, carvers);
        BiomeDefaultFeatures.addDefaultCrystalFormations(settings);
        BiomeDefaultFeatures.addDefaultOres(settings);
        BiomeDefaultFeatures.addExtraEmeralds(settings);
        settings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_TUFF);
        return new Biome.BiomeBuilder()
                .temperature(1)
                .downfall(0.4f)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .skyColor(8103167)
                        .fogColor(12638463)
                        .waterColor(4445678)
                        .waterFogColor(270131)
                        .build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .generationSettings(settings.build())
                .build();
    }

    private static ResourceKey<Biome> createKey(String string) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(JAVD.MOD_ID, string));
    }
}
