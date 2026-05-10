package com.osheaven.bushcraft.event;

import com.google.common.base.Preconditions;
import com.osheaven.bushcraft.init.Content;
import com.osheaven.bushcraft.init.Dictionary;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

import static com.osheaven.bushcraft.Reference.MODID;


@Mod.EventBusSubscriber(modid = MODID)
public class RegistrationHandler
{
    static final Set<Item> ITEMS = new HashSet<>();
    static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> registry = event.getRegistry();

        for (Block block : Content.blocks)
        {
            registry.register(block);
            String name = block.getRegistryName().toString();
        }
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();

        for (Block block : Content.blocks)
        {
            ItemBlock item = new ItemBlock(block);
            ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
            registry.register(item.setRegistryName(registryName));
            ITEM_BLOCKS.add(item);
        }

        Dictionary.initBlocks();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();

        for (Item item : Content.items)
        {
            registry.register(item);

            ITEMS.add(item);
        }

        Dictionary.initItems();
    }
}