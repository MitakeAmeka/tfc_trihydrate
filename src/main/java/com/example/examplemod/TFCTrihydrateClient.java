package com.example.examplemod;

import com.example.examplemod.bakedModel.CustomItemExtensions;
import com.example.examplemod.bakedModel.CustomModel;
import com.example.examplemod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomModelData;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import java.util.Map;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = TFCTrihydrate.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = TFCTrihydrate.MODID, value = Dist.CLIENT)
public class TFCTrihydrateClient {
    public TFCTrihydrateClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        TFCTrihydrate.LOGGER.info("HELLO FROM CLIENT SETUP");
        TFCTrihydrate.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(
                // The only instance of our IClientItemExtensions, and as such, the only instance of our BEWLR.
                new CustomItemExtensions(),
                // A vararg list of items that use this BEWLR.
                ModItems.LTK_TOOL.get()
        );
    }




    @SubscribeEvent
    public static void onModelBaked(ModelEvent.ModifyBakingResult event){
        // wrench item model
        Map<ModelResourceLocation, BakedModel> modelRegistry = event.getModels();
        ModelResourceLocation location = new ModelResourceLocation(BuiltInRegistries.ITEM.getKey(ModItems.LTK_TOOL.get()), "inventory");
        BakedModel existingModel = modelRegistry.get(location);

        ModelResourceLocation frontLocation = new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath("tfc_trihydrate", "ltk_tool_front"), "inventory");
        ItemStack itemStack = new ItemStack(ModItems.LTK_TOOL.get());
        itemStack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(1));


        ModelResourceLocation backLocation = new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath("tfc_trihydrate", "ltk_tool_back"), "inventory");
        ItemStack itemStack2 = new ItemStack(ModItems.LTK_TOOL.get());
        itemStack2.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(2));


        BakedModel testModel1 = existingModel.getOverrides().resolve(existingModel,itemStack,null,null,0);
        BakedModel testModel2 = existingModel.getOverrides().resolve(existingModel,itemStack2,null,null,0);

        System.out.println("===============================");
        System.out.println(testModel1);
        System.out.println(testModel2);
        System.out.println(existingModel);
        System.out.println("===============================");

        if (existingModel == null) {
            throw new RuntimeException("Did not find Item in registry");
        } else if (existingModel instanceof CustomModel) {
            throw new RuntimeException("Tried to replace Item twice");
        } else {
            CustomModel customModel = new CustomModel(existingModel);
            event.getModels().put(location, customModel);

            CustomModel front = new CustomModel(testModel1);
            event.getModels().put(frontLocation, front);

            CustomModel back = new CustomModel(testModel2);
            event.getModels().put(backLocation,back);

        }
    }


}
