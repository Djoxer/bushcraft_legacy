package com.osheaven.bushcraft.proxy;

import com.osheaven.bushcraft.Config;
import com.osheaven.bushcraft.Logger;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;

@EventBusSubscriber
public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        Config.load(event);

        Logger.isEnabled(event, Config.logIsEnabled);

        Logger.log(Level.INFO, "Mod is loading...");
    }

    public void init(FMLInitializationEvent event)
    {
    }

    public void postInit(FMLPostInitializationEvent event)
    {
        Config.refresh();
        Logger.log(Level.INFO, "Loading complete");
    }
}
