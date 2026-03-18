package net.bauxite_ltk.tfc_trihydrate;

import net.bauxite_ltk.tfc_trihydrate.fluid.ModFluids;
import net.dries007.tfc.client.extensions.FluidRendererExtension;
import net.dries007.tfc.common.TFCTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.model.DynamicFluidContainerModel;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.Objects;


// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = TFCTrihydrate.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = TFCTrihydrate.MODID, value = Dist.CLIENT)
public class TFCTrihydrateClient {
    public static final ResourceLocation WATER_STILL = ResourceLocation.fromNamespaceAndPath("minecraft","block/water_still");
    public static final ResourceLocation BUBBLE_STILL = ResourceLocation.fromNamespaceAndPath("tfc_trihydrate","block/bubble_still");
    public static final ResourceLocation WATER_FLOW = ResourceLocation.fromNamespaceAndPath("minecraft","block/water_flow");
    public static final ResourceLocation WATER_OVERLAY = ResourceLocation.fromNamespaceAndPath("minecraft","block/water_overlay");
    public static final ResourceLocation UNDERWATER_LOCATION = ResourceLocation.fromNamespaceAndPath("minecraft","textures/misc/underwater.png");

    public static final ResourceLocation THICKY_WATER_STILL = ResourceLocation.fromNamespaceAndPath("tfc_trihydrate","block/thicky_water_still");
    public static final ResourceLocation THICKY_WATER_FLOW = ResourceLocation.fromNamespaceAndPath("tfc_trihydrate","block/thicky_water_flow");
    
    public static final ResourceLocation DARK_THICKY_WATER_STILL = ResourceLocation.fromNamespaceAndPath("tfc_trihydrate","block/dark_thicky_water_still");
    public static final ResourceLocation DARK_THICKY_WATER_FLOW = ResourceLocation.fromNamespaceAndPath("tfc_trihydrate","block/dark_thicky_water_flow");

    private static final ResourceLocation MOLTEN_STILL = ResourceLocation.fromNamespaceAndPath("tfc_trihydrate","block/molten_still");
    private static final ResourceLocation MOLTEN_FLOW = ResourceLocation.fromNamespaceAndPath("tfc_trihydrate","block/molten_flow");

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
        ItemBlockRenderTypes.setRenderLayer(ModFluids.SIMPLE_FLOTATION_SOLUTION.getSource(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.SIMPLE_FLOTATION_SOLUTION.getFlowing(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.SODIUM_ETHYL_XANTHATE.getSource(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.SODIUM_ETHYL_XANTHATE.getFlowing(), RenderType.translucent());
    }

//    @SubscribeEvent
//    public static void onClientTicking(ClientTickEvent.Post event){
//        CustomItemRender.aniTicks++;
//    }

//    @SubscribeEvent
//    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
//        event.registerItem(
//                // The only instance of our IClientItemExtensions, and as such, the only instance of our BEWLR.
//                new CustomItemExtensions(),
//                // A vararg list of items that use this BEWLR.
//                ModItems.LTK_TOOL.get()
//        );
//    }


    @SubscribeEvent
    public static void registerExtensions(RegisterClientExtensionsEvent event){
        event.registerFluidType(
                new FluidRendererExtension(0xFF93bcb2, BUBBLE_STILL, BUBBLE_STILL, null, null),
                ModFluids.SIMPLE_FLOTATION_SOLUTION.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFf4ad00, WATER_STILL, WATER_FLOW, null, null),
                ModFluids.SODIUM_ETHYL_XANTHATE.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFb4b3a9, THICKY_WATER_STILL, THICKY_WATER_FLOW, null, null),
                ModFluids.LIME_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFf18579, MOLTEN_STILL, MOLTEN_FLOW, null, null),
                ModFluids.HEMATITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFc1975c, MOLTEN_STILL, MOLTEN_FLOW, null, null),
                ModFluids.LIMONITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF979ba8, MOLTEN_STILL, MOLTEN_FLOW, null, null),
                ModFluids.MAGNETITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFe2b198, MOLTEN_STILL, MOLTEN_FLOW, null, null),
                ModFluids.NATIVE_COPPER_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFe4e0dc, MOLTEN_STILL, MOLTEN_FLOW, null, null),
                ModFluids.NATIVE_SILVER_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFf9f4a1, MOLTEN_STILL, MOLTEN_FLOW, null, null),
                ModFluids.NATIVE_GOLD_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF65a7a2, MOLTEN_STILL, MOLTEN_FLOW, null, null),
                ModFluids.MALACHITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF95948b, MOLTEN_STILL, MOLTEN_FLOW, null, null),
                ModFluids.TETRAHEDRITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFa1bd90, MOLTEN_STILL, MOLTEN_FLOW, null, null),
                ModFluids.GARNIERITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF8d8059, MOLTEN_STILL, MOLTEN_FLOW, null, null),
                ModFluids.CASSITERITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFc1d1db, MOLTEN_STILL, MOLTEN_FLOW, null, null),
                ModFluids.SPHALERITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF4f572e, MOLTEN_STILL, MOLTEN_FLOW, null, null),
                ModFluids.BISMUTHINITE_SLURRY.getType());



        event.registerFluidType(
                new FluidRendererExtension(0xFFf18579, WATER_STILL, WATER_FLOW, null, null),
                ModFluids.PROCESSED_HEMATITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFc1975c, WATER_STILL, WATER_FLOW, null, null),
                ModFluids.PROCESSED_LIMONITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF979ba8, WATER_STILL, WATER_FLOW, null, null),
                ModFluids.PROCESSED_MAGNETITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFe2b198, WATER_STILL, WATER_FLOW, null, null),
                ModFluids.PROCESSED_NATIVE_COPPER_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFe4e0dc, WATER_STILL, WATER_FLOW, null, null),
                ModFluids.PROCESSED_NATIVE_SILVER_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFf9f4a1, WATER_STILL, WATER_FLOW, null, null),
                ModFluids.PROCESSED_NATIVE_GOLD_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF65a7a2, WATER_STILL, WATER_FLOW, null, null),
                ModFluids.PROCESSED_MALACHITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF95948b, WATER_STILL, WATER_FLOW, null, null),
                ModFluids.PROCESSED_TETRAHEDRITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFa1bd90, WATER_STILL, WATER_FLOW, null, null),
                ModFluids.PROCESSED_GARNIERITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF8d8059, WATER_STILL, WATER_FLOW, null, null),
                ModFluids.PROCESSED_CASSITERITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFc1d1db, WATER_STILL, WATER_FLOW, null, null),
                ModFluids.PROCESSED_SPHALERITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF4f572e, WATER_STILL, WATER_FLOW, null, null),
                ModFluids.PROCESSED_BISMUTHINITE_SLURRY.getType());



        event.registerFluidType(
                new FluidRendererExtension(0xFFf18579, BUBBLE_STILL, BUBBLE_STILL, null, null),
                ModFluids.CONCENTRATE_HEMATITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFc1975c, BUBBLE_STILL, BUBBLE_STILL, null, null),
                ModFluids.CONCENTRATE_LIMONITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF979ba8, BUBBLE_STILL, BUBBLE_STILL, null, null),
                ModFluids.CONCENTRATE_MAGNETITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFe2b198, BUBBLE_STILL, BUBBLE_STILL, null, null),
                ModFluids.CONCENTRATE_NATIVE_COPPER_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFe4e0dc, BUBBLE_STILL, BUBBLE_STILL, null, null),
                ModFluids.CONCENTRATE_NATIVE_SILVER_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFf9f4a1, BUBBLE_STILL, BUBBLE_STILL, null, null),
                ModFluids.CONCENTRATE_NATIVE_GOLD_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF65a7a2, BUBBLE_STILL, BUBBLE_STILL, null, null),
                ModFluids.CONCENTRATE_MALACHITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF95948b, BUBBLE_STILL, BUBBLE_STILL, null, null),
                ModFluids.CONCENTRATE_TETRAHEDRITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFa1bd90, BUBBLE_STILL, BUBBLE_STILL, null, null),
                ModFluids.CONCENTRATE_GARNIERITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF8d8059, BUBBLE_STILL, BUBBLE_STILL, null, null),
                ModFluids.CONCENTRATE_CASSITERITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFc1d1db, BUBBLE_STILL, BUBBLE_STILL, null, null),
                ModFluids.CONCENTRATE_SPHALERITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF4f572e, BUBBLE_STILL, BUBBLE_STILL, null, null),
                ModFluids.CONCENTRATE_BISMUTHINITE_SLURRY.getType());


        event.registerFluidType(
                new FluidRendererExtension(0xFFf18579, THICKY_WATER_STILL, THICKY_WATER_FLOW, null, null),
                ModFluids.TAILING_HEMATITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFc1975c, THICKY_WATER_STILL, THICKY_WATER_FLOW, null, null),
                ModFluids.TAILING_LIMONITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF979ba8, THICKY_WATER_STILL, THICKY_WATER_FLOW, null, null),
                ModFluids.TAILING_MAGNETITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFe2b198, THICKY_WATER_STILL, THICKY_WATER_FLOW, null, null),
                ModFluids.TAILING_NATIVE_COPPER_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFe4e0dc, THICKY_WATER_STILL, THICKY_WATER_FLOW, null, null),
                ModFluids.TAILING_NATIVE_SILVER_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFf9f4a1, THICKY_WATER_STILL, THICKY_WATER_FLOW, null, null),
                ModFluids.TAILING_NATIVE_GOLD_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF65a7a2, THICKY_WATER_STILL, THICKY_WATER_FLOW, null, null),
                ModFluids.TAILING_MALACHITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF95948b, THICKY_WATER_STILL, THICKY_WATER_FLOW, null, null),
                ModFluids.TAILING_TETRAHEDRITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFa1bd90, THICKY_WATER_STILL, THICKY_WATER_FLOW, null, null),
                ModFluids.TAILING_GARNIERITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF8d8059, THICKY_WATER_STILL, THICKY_WATER_FLOW, null, null),
                ModFluids.TAILING_CASSITERITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFFc1d1db, THICKY_WATER_STILL, THICKY_WATER_FLOW, null, null),
                ModFluids.TAILING_SPHALERITE_SLURRY.getType());

        event.registerFluidType(
                new FluidRendererExtension(0xFF4f572e, THICKY_WATER_STILL, THICKY_WATER_FLOW, null, null),
                ModFluids.TAILING_BISMUTHINITE_SLURRY.getType());
    }
    
    

    @SubscribeEvent
    public static void registerColorHandlerItems(RegisterColorHandlersEvent.Item event){
        for (Fluid fluid : BuiltInRegistries.FLUID)
        {
            if (Objects.requireNonNull(BuiltInRegistries.FLUID.getKey(fluid)).getNamespace().equals(TFCTrihydrate.MODID))
            {
                event.register(new DynamicFluidContainerModel.Colors(), fluid.getBucket());
            }
        }
    }


//    @SubscribeEvent
//    public static void onModelBaked(ModelEvent.ModifyBakingResult event){
//        Map<ModelResourceLocation, BakedModel> modelRegistry = event.getModels();
//        ModelResourceLocation location = new ModelResourceLocation(BuiltInRegistries.ITEM.getKey(ModItems.LTK_TOOL.get()), "inventory");
//        BakedModel existingModel = modelRegistry.get(location);
//
//        ModelResourceLocation frontLocation = new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath("tfc_trihydrate", "ltk_tool_front"), "inventory");
//        ItemStack itemStack = new ItemStack(ModItems.LTK_TOOL.get());
//        itemStack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(1));
//
//
//        ModelResourceLocation backLocation = new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath("tfc_trihydrate", "ltk_tool_back"), "inventory");
//        ItemStack itemStack2 = new ItemStack(ModItems.LTK_TOOL.get());
//        itemStack2.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(2));
//
//
//        BakedModel testModel1 = existingModel.getOverrides().resolve(existingModel,itemStack,null,null,0);
//        BakedModel testModel2 = existingModel.getOverrides().resolve(existingModel,itemStack2,null,null,0);
//
//        System.out.println("===============================");
//        System.out.println(testModel1);
//        System.out.println(testModel2);
//        System.out.println(existingModel);
//        System.out.println("===============================");
//
//        if (existingModel == null) {
//            throw new RuntimeException("Did not find Item in registry");
//        } else if (existingModel instanceof CustomModel) {
//            throw new RuntimeException("Tried to replace Item twice");
//        } else {
//            CustomModel customModel = new CustomModel(existingModel);
//            event.getModels().put(location, customModel);
//
//            CustomModel front = new CustomModel(testModel1);
//            event.getModels().put(frontLocation, front);
//
//            CustomModel back = new CustomModel(testModel2);
//            event.getModels().put(backLocation,back);
//
//        }
//    }


    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        // 检查合成的物品是否是你想要的物品
        Player player = event.getEntity();
        ItemStack crafting = event.getCrafting();
        Container container = event.getInventory();
        AbstractContainerMenu menu = player.containerMenu;

        if(menu instanceof CraftingMenu craftingMenu){
            for(int i = 0; i < craftingMenu.slots.size(); i++){
                ItemStack itemStack = craftingMenu.slots.get(i).getItem();
                if(itemStack.getTags().anyMatch(tag -> tag.equals(TFCTags.Items.TOOLS_HAMMER))){
                    player.playSound(SoundEvents.UI_STONECUTTER_TAKE_RESULT);
                    break;
                }
            }
        }


    }


}
