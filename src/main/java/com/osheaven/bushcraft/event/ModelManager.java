package com.osheaven.bushcraft.event;

import com.osheaven.bushcraft.init.Content;
import com.osheaven.bushcraft.util.IVariant;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.HashSet;
import java.util.Set;
import java.util.function.ToIntFunction;

import static com.osheaven.bushcraft.Reference.MODID;

@EventBusSubscriber(value = Side.CLIENT, modid = MODID)
public class ModelManager
{
    public static final ModelManager INSTANCE = new ModelManager();
    private final Set<Item> itemsRegistered = new HashSet<>();
    private final StateMapperBase propertyStringMapper = new StateMapperBase()
    {
        @Override
        protected ModelResourceLocation getModelResourceLocation(IBlockState state)
        {
            return new ModelResourceLocation("minecraft:air");
        }
    };

    private ModelManager()
    {
    }

    @SubscribeEvent
    public static void registerAllModels(ModelRegistryEvent event)
    {
        INSTANCE.registerBlockModels();
        INSTANCE.registerItemModels();
    }

    private void registerBlockModels()
    {
        for (Block block : Content.blocks) registerBlockItemModel(block.getDefaultState());
        RegistrationHandler.ITEM_BLOCKS.stream().filter(item -> !itemsRegistered.contains(item)).forEach(this::registerItemModel);
    }

    private void registerItemModels()
    {
        for (Item item : Content.items) registerItemModel(item);
        RegistrationHandler.ITEMS.stream().filter(item -> !itemsRegistered.contains(item)).forEach(this::registerItemModel);
    }

    private void registerBlockItemModel(IBlockState state)
    {
        Block block = state.getBlock();
        Item item = Item.getItemFromBlock(block);

        if (item != Items.AIR)
        {
            registerItemModel(item, new ModelResourceLocation(block.getRegistryName(), propertyStringMapper.getPropertyString(state.getProperties())));
        }
    }

    private void registerBlockItemModelForMeta(IBlockState state, int metadata)
    {
        Item item = Item.getItemFromBlock(state.getBlock());

        if (item != Items.AIR)
        {
            registerItemModelForMeta(item, metadata, propertyStringMapper.getPropertyString(state.getProperties()));
        }
    }

    private <T extends Comparable<T>> void registerVariantBlockItemModels(IBlockState baseState, IProperty<T> property, ToIntFunction<T> getMeta)
    {
        property.getAllowedValues().forEach(value -> registerBlockItemModelForMeta(baseState.withProperty(property, value), getMeta.applyAsInt(value)));
    }


    private <T extends IVariant & Comparable<T>> void registerVariantBlockItemModels(IBlockState baseState, IProperty<T> property)
    {
        registerVariantBlockItemModels(baseState, property, IVariant::getMeta);
    }

    private void registerItemModel(Item item)
    {
        registerItemModel(item, item.getRegistryName().toString());
    }

    private void registerItemModel(Item item, String modelLocation)
    {
        ModelResourceLocation fullModelLocation = new ModelResourceLocation(modelLocation, "inventory");
        registerItemModel(item, fullModelLocation);
    }

    private void registerItemModel(Item item, ModelResourceLocation fullModelLocation)
    {
        ModelBakery.registerItemVariants(item, fullModelLocation); // Ensure the custom model is loaded and prevent the default model from being loaded
        registerItemModel(item, stack -> fullModelLocation);
    }

    private void registerItemModel(Item item, ItemMeshDefinition meshDefinition)
    {
        itemsRegistered.add(item);
        ModelLoader.setCustomMeshDefinition(item, meshDefinition);
    }

    private <T extends IVariant> void registerVariantItemModels(Item item, String variantName, T[] values)
    {
        for (T value : values)
        {
            registerItemModelForMeta(item, value.getMeta(), variantName + "=" + value.getName());
        }
    }

    private void registerItemModelForMeta(Item item, int metadata, String variant)
    {
        registerItemModelForMeta(item, metadata, new ModelResourceLocation(item.getRegistryName(), variant));
    }

    private void registerItemModelForMeta(Item item, int metadata, ModelResourceLocation modelResourceLocation)
    {
        itemsRegistered.add(item);
        ModelLoader.setCustomModelResourceLocation(item, metadata, modelResourceLocation);
    }
}
