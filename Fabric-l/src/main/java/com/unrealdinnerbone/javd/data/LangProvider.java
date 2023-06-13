package com.unrealdinnerbone.javd.data;

import com.unrealdinnerbone.javd.JAVDRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class LangProvider extends FabricLanguageProvider {

    protected LangProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(JAVDRegistry.PORTAL_BLOCK.get(), "Void Portal");
        translationBuilder.add("javd.invalid.world", "Unable to find world '%s'");
        translationBuilder.add("javd.invalid.pos", "Unable to find valid portal location");
        translationBuilder.add("biome.javd.void", "The Void");
    }
}
