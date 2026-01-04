package com.unrealdinnerbone.javd.data;

import com.unrealdinnerbone.javd.JAVDRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class LangProvider extends FabricLanguageProvider {

    public LangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        translationBuilder.add(JAVDRegistry.PORTAL_BLOCK.value(), "Void Portal");
        translationBuilder.add(JAVDRegistry.PORTAL_BLOCK_ITEM.value(), "Void Portal");
        translationBuilder.add("javd.invalid.world", "Unable to find world '%s'");
        translationBuilder.add("javd.invalid.pos", "Unable to find valid portal location");
        translationBuilder.add("biome.javd.void", "The Void");
        translationBuilder.add("dimension.javd.void", "The Void");
        translationBuilder.add(AdvancementProvider.ADVANCEMENT_ID, "To Infinity and Nothing");
        translationBuilder.add(AdvancementProvider.ADVANCEMENT_DESCRIPTION_KEY, "Enter the Void Dimension");
    }
}
