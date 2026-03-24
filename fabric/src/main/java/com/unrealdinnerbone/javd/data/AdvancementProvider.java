package com.unrealdinnerbone.javd.data;

import com.unrealdinnerbone.javd.JAVD;
import com.unrealdinnerbone.javd.JAVDRegistry;
import com.unrealdinnerbone.trenzalore.lib.IDUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.ChangeDimensionTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementProvider extends FabricAdvancementProvider {

    public static final String ADVANCEMENT_ID = "advancement." + JAVD.MOD_ID + ".enter_void_dimension";
    public static final String ADVANCEMENT_DESCRIPTION_KEY = ADVANCEMENT_ID + ".description";

    public AdvancementProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider lookup, Consumer<AdvancementHolder> consumer) {
        consumer.accept(Advancement.Builder.advancement()
                .parent(IDUtils.idFull("minecraft:story/mine_diamond"))
                .display(
                                JAVDRegistry.PORTAL_BLOCK_ITEM.getHolder().value(),
                                Component.translatable(ADVANCEMENT_ID),
                                Component.translatable(ADVANCEMENT_DESCRIPTION_KEY),
                                null,
                                AdvancementType.TASK,
                                true,
                                true,
                                true
                        )
                        .addCriterion("enter_dimension", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(JAVDRegistry.Keys.LEVEL))
                .build(JAVD.rl( "enter_mining_dimension")));
    }
}
