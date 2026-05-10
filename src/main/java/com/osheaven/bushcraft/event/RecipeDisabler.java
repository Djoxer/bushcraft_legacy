package com.osheaven.bushcraft.event;

import com.osheaven.bushcraft.Config;
import com.osheaven.bushcraft.Logger;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import org.apache.logging.log4j.Level;

import static com.osheaven.bushcraft.Reference.MODID;


@EventBusSubscriber(modid = MODID)
public class RecipeDisabler
{
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
    {
        disableRecipe(event, Config.recipeCattailTorch, "cattail_torch", MODID);
        disableRecipe(event, Config.recipeWorkbench, "modplanks_workbench", MODID);
        disableRecipe(event, Config.recipeSticks, "modplanks_stick", MODID);
        disableRecipe(event, Config.recipeChest, "modplanks_chest", MODID);
        disableRecipe(event, Config.recipeChest, "modplanks_bed", MODID);
    }

    public static void disableRecipe(RegistryEvent.Register<IRecipe> event, boolean isEnabled, String recipeName, String modId)
    {
        if (recipeName.equals("modplanks_bed") && !isEnabled)
        {
            for (int i = 0; i < 16; i++)
            {
                disableRecipe(event, recipeName + '_' + i, modId);
            }
        }
        else
        {
            if (isEnabled)
            {
                Logger.log(Level.INFO, "Recipe " + recipeName + " active");
            }
            else
            {
                disableRecipe(event, recipeName, modId);
            }
        }
    }

    public static void disableRecipe(RegistryEvent.Register<IRecipe> event, String recipeName, String modId)
    {
        ResourceLocation recipe = new ResourceLocation(modId + ":" + recipeName);
        IForgeRegistryModifiable recipeRegistry = (IForgeRegistryModifiable) event.getRegistry();
        recipeRegistry.remove(recipe);
        Logger.log(Level.INFO, "Recipe " + recipeName + " disabled");
    }
}