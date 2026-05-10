package com.osheaven.bushcraft.item;

import com.osheaven.bushcraft.init.Content;
import net.minecraft.item.Item;

import static com.osheaven.bushcraft.Reference.MODID;
import static net.minecraftforge.common.util.EnumHelper.addToolMaterial;

public class ItemBase extends Item
{
    public String name;

    public ItemBase(String name)
    {
        this.name = name;
        setRegistryName(MODID, name);
        setUnlocalizedName(getRegistryName().toString());
        Content.items.add(this);
    }

    /**
     * ToolMaterials
     *      Harvest    max  effic        enchant
     *        Level   uses  iency   dmg  ability
     * WOOD     0,      59,  2.0F, 0.0F,   15
     * STONE    1,     131,  4.0F, 1.0F,    5
     * IRON     2,     250,  6.0F, 2.0F,   14
     * DIAMOND  3,    1561,  8.0F, 3.0F,   10
     * GOLD     0,      32, 12.0F, 0.0F,   22
     */
    public static class ToolMaterials
    {
        public static final ToolMaterial FLINT = addToolMaterial(MODID + "_flint", 1, 250, 5.0F, 1.0F, 5);
    }
}
