package com.osheaven.bushcraft.init;

import com.osheaven.bushcraft.Logger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Level;

public class Dictionary
{
    public static void initItems()
    {
       // register(HEMP_FABRIC_RF, "fabricHemp", "leather");
    }

    public static void initBlocks()
    {
        // register(HEMPSTONE_COBBLE, "cobblestone");
    }

    static void register(Item item, String... oreDicts)
    {
        for (String oreDict : oreDicts)
        {
            OreDictionary.registerOre(oreDict, item);
            Logger.log(Level.INFO, "OreDictionary: " + item.getRegistryName() + " -> oreDict:" + oreDict);
        }
    }

    static void register(Block block, String... oreDicts)
    {
        for (String oreDict : oreDicts)
        {
            OreDictionary.registerOre(oreDict, block);
            Logger.log(Level.INFO, "OreDictionary: " + block.getRegistryName() + " -> oreDict:" + oreDict);
        }
    }
}
