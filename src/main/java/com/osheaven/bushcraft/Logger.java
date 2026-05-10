package com.osheaven.bushcraft;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;

public class Logger
{
    public static org.apache.logging.log4j.Logger logger;

    public static boolean isEnabled;

    public static void isEnabled(FMLPreInitializationEvent event, boolean enabled)
    {
        logger = event.getModLog();
        isEnabled = enabled;
    }

    public static void log(Level level, String message)
    {
        if (isEnabled) logger.log(level, "*** Bushkraft *** " + message);
    }

    public static void log(Level level, String message, Exception e)
    {
        if (isEnabled) logger.log(level, "*** Bushkraft *** " + message, e);
    }
}
