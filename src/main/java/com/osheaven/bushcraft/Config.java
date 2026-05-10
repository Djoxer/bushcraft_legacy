package com.osheaven.bushcraft;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;

import java.io.File;

import static com.osheaven.bushcraft.Reference.MODID;

public class Config
{
    public static Configuration config;
    public static String path;

    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_RECIPES = "recipes";
    public static final String CATEGORY_DROPS   = "drops";

    public static boolean   logIsEnabled        = false;

    public static boolean   recipeCattailTorch  = false;
    public static boolean   recipeWorkbench     = false;
    public static boolean   recipeSticks        = false;
    public static boolean   recipeChest         = false;
    public static boolean   recipeBeds          = false;

    public Config(FMLPreInitializationEvent event)
    {
    }

    public static void load(FMLPreInitializationEvent event)
    {
        path = event.getModConfigurationDirectory().getPath();
        config = new Configuration(new File(path, File.separator + MODID + ".cfg"));
        read();
    }

    public static void read()
    {
        try
        {
            config.load();
            initGeneralConfig(config);
            initRecipesConfig(config);
            Logger.log(Level.INFO, "[Configuration]: " + config + " loading complete");
        }
        catch (Exception e)
        {
            Logger.log(Level.ERROR, "[Configuration]: Problem loading config file!", e);
        }
        finally
        {
            refresh();
        }
    }

    public static void refresh()
    {
        if (config.hasChanged())
        {
            config.save();
        }
    }

    private static void initGeneralConfig(Configuration cfg)
    {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        logIsEnabled = cfg.getBoolean("enableLog", CATEGORY_GENERAL, logIsEnabled, "Show log in console");
   }

    private static void initRecipesConfig(Configuration cfg)
    {
        cfg.addCustomCategoryComment(CATEGORY_RECIPES, "Recipes configuration");
        recipeCattailTorch = cfg.getBoolean("recipeCattailTorch", CATEGORY_RECIPES, recipeCattailTorch, "Torches from cattail plants (BiomesOPlenty etc)");
        recipeWorkbench = cfg.getBoolean("recipeWorkbench", CATEGORY_RECIPES, recipeWorkbench, "Workbench from oreDictionary: planksWood");
        recipeSticks = cfg.getBoolean("recipeSticks", CATEGORY_RECIPES, recipeSticks, "Sticks from oreDictionary: planksWood");
        recipeChest = cfg.getBoolean("recipeChest", CATEGORY_RECIPES, recipeChest, "Chest from oreDictionary: planksWood");
        recipeBeds = cfg.getBoolean("recipeBeds", CATEGORY_RECIPES, recipeBeds, "Beds from oreDictionary: planksWood");
    }
}
