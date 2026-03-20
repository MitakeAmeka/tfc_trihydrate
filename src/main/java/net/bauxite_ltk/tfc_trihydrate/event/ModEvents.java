package net.bauxite_ltk.tfc_trihydrate.event;

import net.bauxite_ltk.tfc_trihydrate.TFCTrihydrate;
import net.bauxite_ltk.tfc_trihydrate.block.multiblock.TFCTHMultiblockLogic;
import net.bauxite_ltk.tfc_trihydrate.effect.ModEffects;
import net.bauxite_ltk.tfc_trihydrate.gui.*;
import net.bauxite_ltk.tfc_trihydrate.render.BallMillRender;
import net.bauxite_ltk.tfc_trihydrate.render.FlotationCellRender;
import net.bauxite_ltk.tfc_trihydrate.render.TFCTHDynamicModel;
import net.bauxite_ltk.tfc_trihydrate.render.ThickenerRender;
import net.minecraft.advancements.Advancement;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Objects;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = TFCTrihydrate.MODID, value = Dist.CLIENT)
public class ModEvents {
    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        MobEffectInstance effect = player.getEffect(ModEffects.FEAR_EFFECT.get());
        if (effect != null) {
            double multiplier = Math.min(0, ((double) player.getOnPos().getY() - 46) / 80);
            event.setNewSpeed((float) (event.getNewSpeed() * (1 + multiplier)));
        }
    }

    @SubscribeEvent
    public static void addFearEvent(TickEvent.PlayerTickEvent playerTickEvent){
        Player player = playerTickEvent.player;
        int y = playerTickEvent.player.getOnPos().getY();
        boolean isInIronAge;
        if(player instanceof ServerPlayer serverPlayer){
            Advancement iron_age_advancement = Objects.requireNonNull(player.getServer()).getAdvancements().getAdvancement(
                    ResourceLocation.fromNamespaceAndPath("tfc","story/iron_age")
            );
            if(iron_age_advancement!=null){
                isInIronAge = serverPlayer.getAdvancements().getOrStartProgress(iron_age_advancement).isDone();
            }
            else{
                throw new RuntimeException("advancement not exist");
            }
            if(y < -10){
                if(!isInIronAge){
                    player.addEffect(new MobEffectInstance(ModEffects.FEAR_EFFECT.get(),200,1));
                    player.displayClientMessage(Component.translatable("tfc_trihydrate.message.fear2"),true);
                }
                else {
                    player.displayClientMessage(Component.translatable("tfc_trihydrate.message.fear"),true);
                }
            }
            else if(y < 30){
                if(!isInIronAge){
                    player.addEffect(new MobEffectInstance(ModEffects.FEAR_EFFECT.get(),200, 0));
                    player.displayClientMessage(Component.translatable("tfc_trihydrate.message.fear"),true);
                }
                else{
                    //player.displayClientMessage(Component.translatable("tfc_trihydrate.message.fearless"),true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void detectIronAgeAdvancement(AdvancementEvent.AdvancementEarnEvent event){
        Advancement advancement = event.getAdvancement();
        if(advancement.getId().equals(ResourceLocation.fromNamespaceAndPath("tfc","story/iron_age"))){
            event.getEntity().sendSystemMessage(Component.translatable("tfc_trihydrate.message.fearless"));
        }
    }

    @SubscribeEvent
    public static void registerRenders(EntityRenderersEvent.RegisterRenderers event)
    {
        registerBERenderNoContext(event, TFCTHMultiblockLogic.BALL_MILL.masterBE(), BallMillRender::new);
        registerBERenderNoContext(event, TFCTHMultiblockLogic.FLOTATION_CELL.masterBE(), FlotationCellRender::new);
        registerBERenderNoContext(event, TFCTHMultiblockLogic.THICKENER.masterBE(), ThickenerRender::new);
    }

    private static <T extends BlockEntity>
    void registerBERenderNoContext(
            EntityRenderersEvent.RegisterRenderers event, Supplier<BlockEntityType<? extends T>> type, Supplier<BlockEntityRenderer<T>> render
    )
    {
        ModEvents.registerBERenderNoContext(event, type.get(), render);
    }

    private static <T extends BlockEntity>
    void registerBERenderNoContext(
            EntityRenderersEvent.RegisterRenderers event, BlockEntityType<? extends T> type, Supplier<BlockEntityRenderer<T>> render
    )
    {
        event.registerBlockEntityRenderer(type, $ -> render.get());
    }

    @SubscribeEvent
    public static void registerModelLoaders(ModelEvent.RegisterGeometryLoaders event)
    {
        BallMillRender.BARREL = new TFCTHDynamicModel(BallMillRender.NAME);
        FlotationCellRender.BLADE = new TFCTHDynamicModel(FlotationCellRender.NAME);
        ThickenerRender.AGITATOR = new TFCTHDynamicModel(ThickenerRender.NAME);
    }

    @SubscribeEvent
    public static void onModelBake(ModelEvent.BakingCompleted event)
    {
        BallMillRender.reset();
    }

    @SubscribeEvent
    public static void registerContainersAndScreens(FMLClientSetupEvent event)
    {
        MenuScreens.register(TFCTHMenuTypes.BALL_MILL.getType(), BallMillScreen::new);
        MenuScreens.register(TFCTHMenuTypes.FLOTATION_CELL.getType(), FlotationCellScreen::new);
        MenuScreens.register(TFCTHMenuTypes.HYDROCYCLONE.getType(), HydrocycloneScreen::new);
        MenuScreens.register(TFCTHMenuTypes.THICKENER.getType(), ThickenerScreen::new);
    }
}
