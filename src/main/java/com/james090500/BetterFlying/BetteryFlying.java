package com.james090500.BetterFlying;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetteryFlying implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("betterflying");

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");
    }
}
