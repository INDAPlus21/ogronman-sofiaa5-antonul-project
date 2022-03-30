package com.lavamod.lava;


import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lavamod.lava.setup.*;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(LavaMod.MODID)
public class LavaMod
{
    // Directly reference a slf4j logger
	private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "lavamod";
    

    public LavaMod()
    {
        // Register the deferred registry
        Registration.init();

        // Register the setup method for modloading
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register 'ModSetup::init' to be called at mod setup time (server and client)
        modbus.addListener(ModSetup::init);
        // Register 'ClientSetup::init' to be called at mod setup time (client only)
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));
    }

}
