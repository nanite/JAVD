package com.unrealdinnerbone.javd.forge;

import com.unrealdinnerbone.javd.JAVD;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(JAVD.MOD_ID)
public class JAVDForge {
    public JAVDForge() {
        EventBuses.registerModEventBus(JAVD.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(DataEvent::onData);
        JAVD.init();
    }
}
