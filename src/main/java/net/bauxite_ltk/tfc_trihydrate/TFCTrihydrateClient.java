package net.bauxite_ltk.tfc_trihydrate;

import net.bauxite_ltk.tfc_trihydrate.fluid.ModFluids;
import net.bauxite_ltk.tfc_trihydrate.util.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.model.DynamicFluidContainerModel;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = TFCTrihydrate.MODID, value = Dist.CLIENT)
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

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
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

        Player player = event.getEntity();
        ItemStack crafting = event.getCrafting();
        Container container = event.getInventory();
        AbstractContainerMenu menu = player.containerMenu;

        ItemStack itemStack = event.getCrafting();
        TFCTrihydrate.LOGGER.info(itemStack.toString());
        if(itemStack.getTags().anyMatch(tag -> tag.equals(ModTags.Items.CRYSTAL_CHUNKS))){
            player.playSound(SoundEvents.SHROOMLIGHT_BREAK);
            player.playSound(SoundEvents.AMETHYST_BLOCK_BREAK, 0.5f,1f);
            player.playSound(SoundEvents.AMETHYST_BLOCK_BREAK,0.5f,1f);
            player.playSound(SoundEvents.AMETHYST_BLOCK_BREAK,0.5f,1f);
        }
        else if(itemStack.getTags().anyMatch(tag -> tag.equals(ModTags.Items.ORE_CHUNKS))){
            player.playSound(SoundEvents.SHROOMLIGHT_BREAK);
        }
        else if(itemStack.getTags().anyMatch(tag -> tag.equals(ModTags.Items.POOR_ORE_CHUNKS))) {
            player.playSound(SoundEvents.NETHER_ORE_BREAK);
        }
    }
}
