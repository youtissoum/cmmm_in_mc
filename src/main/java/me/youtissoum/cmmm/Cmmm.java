package me.youtissoum.cmmm;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cmmm implements ModInitializer {
    public static final String MOD_ID = "cmmm";
    public static final Logger LOGGER = LoggerFactory.getLogger("cmmm");

    @Override
    public void onInitialize() {
        ModRegistries.registerMod();
    }
}
