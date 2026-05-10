package com.osheaven.bushcraft.init;

import com.osheaven.bushcraft.item.ItemClub;
import com.osheaven.bushcraft.item.ItemKnife;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Content
{
    public static List<Block> blocks = new ArrayList<>();
    public static List<Item> items = new ArrayList<>();

    public static Item FLINT_KNIFE = new ItemKnife();
    public static Item WOODEN_CLUB = new ItemClub();

}
