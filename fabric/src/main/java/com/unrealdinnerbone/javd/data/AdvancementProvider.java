package com.unrealdinnerbone.javd.data;

import com.unrealdinnerbone.javd.JAVD;
import com.unrealdinnerbone.javd.JAVDRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.ChangeDimensionTrigger;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class AdvancementProvider extends FabricAdvancementProvider {

    public static final String ADVANCEMENT_ID = "advancement." + JAVD.MOD_ID + ".enter_void_dimension";
    public static final String ADVANCEMENT_DESCRIPTION_KEY = ADVANCEMENT_ID + ".description";
    protected AdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<AdvancementHolder> consumer) {
        consumer.accept(Advancement.Builder.advancement()
                        .display(
                                JAVDRegistry.PORTAL_BLOCK_ITEM.get(),
                                Component.translatable(ADVANCEMENT_ID),
                                Component.translatable(ADVANCEMENT_DESCRIPTION_KEY),
                                null,
                                AdvancementType.TASK,
                                true,
                                true,
                                false
                        )
                        .addCriterion("enter_dimension", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(JAVDRegistry.Keys.LEVEL))
                .build(new ResourceLocation(JAVD.MOD_ID, "enter_mining_dimension")));
    }
}
