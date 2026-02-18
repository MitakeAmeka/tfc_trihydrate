package net.bauxite_ltk.tfc_trihydrate.event;

import net.bauxite_ltk.tfc_trihydrate.TFCTrihydrate;
import net.bauxite_ltk.tfc_trihydrate.effect.ModEffects;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = TFCTrihydrate.MODID, value = Dist.CLIENT)
public class ModEvents {

    @SubscribeEvent
    public static void addFearEvent(PlayerTickEvent.Post playerTickEvent){
        Player player = playerTickEvent.getEntity();
        int y = playerTickEvent.getEntity().getOnPos().getY();
        boolean isInIronAge = false;
        if(player instanceof ServerPlayer serverPlayer){
            AdvancementHolder iron_age_advancement = player.getServer().getAdvancements().get(
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
                    player.addEffect(new MobEffectInstance(ModEffects.FEAR_EFFECT,200,1));
                    player.displayClientMessage(Component.translatable("tfc_trihydrate.message.fear2"),true);
                }
                else {
                    player.displayClientMessage(Component.translatable("tfc_trihydrate.message.fear"),true);
                }
            }
            else if(y < 30){
                if(!isInIronAge){
                    player.addEffect(new MobEffectInstance(ModEffects.FEAR_EFFECT,200, 0));
                    player.displayClientMessage(Component.translatable("tfc_trihydrate.message.fear"),true);
                }
                else{
                    //player.displayClientMessage(Component.translatable("tfc_trihydrate.message.fearless"),true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void resetJavalinAttackRange(PlayerTickEvent.Post playerTickEvent){
        Player player = playerTickEvent.getEntity();
        ItemStack holdingItem = player.getMainHandItem();
        if(holdingItem.is(Tags.Items.TOOLS_SPEAR)){
            player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).addOrUpdateTransientModifier(
                    new AttributeModifier(
                            ResourceLocation.fromNamespaceAndPath(TFCTrihydrate.MODID,"spear_range"),
                            0.5,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
            );
        }
        else{
            player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).removeModifier(ResourceLocation.fromNamespaceAndPath(TFCTrihydrate.MODID,"spear_range"));
        }
    }

    @SubscribeEvent
    public static void detectIronAgeAdvancement(AdvancementEvent.AdvancementEarnEvent event){
        AdvancementHolder advancement = event.getAdvancement();
        if(advancement.id().equals(ResourceLocation.fromNamespaceAndPath("tfc","story/iron_age"))){
            event.getEntity().sendSystemMessage(Component.translatable("tfc_trihydrate.message.fearless"));
        }
    }
}
