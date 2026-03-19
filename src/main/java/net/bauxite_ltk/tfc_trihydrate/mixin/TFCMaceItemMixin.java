package net.bauxite_ltk.tfc_trihydrate.mixin;

import net.dries007.tfc.common.items.TFCMaceItem;
import net.dries007.tfc.common.items.ToolItem;
import net.dries007.tfc.util.registry.RegistryMetal;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;
import java.util.List;

import static net.minecraft.world.item.Item.BASE_ATTACK_DAMAGE_ID;
import static net.minecraft.world.item.Item.BASE_ATTACK_SPEED_ID;

@Mixin(TFCMaceItem.class)
public class TFCMaceItemMixin {

    @Inject(method = "<init>", at = @At("HEAD"))
    private static void TFCMaceItem(Item.Properties properties, CallbackInfo ci){

        double attackDamage = 0;
        try{
            Class<?> propsClass = properties.getClass();
            Field componentsField = propsClass.getDeclaredField("components");
            componentsField.setAccessible(true);

            DataComponentMap.Builder components = (DataComponentMap.Builder) componentsField.get(properties);

            List<ItemAttributeModifiers.Entry> modifiers = components.build().get(DataComponents.ATTRIBUTE_MODIFIERS).modifiers();
            for(ItemAttributeModifiers.Entry modifier : modifiers){
                if(modifier.attribute() == Attributes.ATTACK_DAMAGE){
                    attackDamage = modifier.modifier().amount();
                }
            }

            //components.set(DataComponents.ATTRIBUTE_MODIFIERS,attackDamage)

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }


        properties.component(DataComponents.ATTRIBUTE_MODIFIERS,
                ItemAttributeModifiers.builder().add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID,
                                attackDamage*1.5,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                ).add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                BASE_ATTACK_SPEED_ID,
                                -3.4,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                ).build()
        );
    }

    @Inject(method = "getAttackDamageBonus", at = @At("HEAD"), cancellable = true)
    private void getAttackDamageBonus(Entity target, float damage, DamageSource damageSource,
                                      CallbackInfoReturnable<Float> cir){
        Entity attacker = damageSource.getDirectEntity();
        if (attacker instanceof LivingEntity livingentity){
            if (!MaceItem.canSmashAttack(livingentity)){
                cir.setReturnValue(0f);
            }
            else{
                float lowCase = 3.0F;
                float mediumCase = 8.0F;
                float fallDistance = livingentity.fallDistance;
                float baseDamage;
                if (fallDistance <= lowCase) {
                    baseDamage = 4.0F * fallDistance - 6;
                } else if (fallDistance <= mediumCase) {
                    baseDamage = 6.0F + 2.0F * (fallDistance - lowCase);
                } else {
                    baseDamage = 16.0F + fallDistance - mediumCase;
                }

                Level level = livingentity.level();
                float actualDamage;
                if (level instanceof ServerLevel) {
                    ServerLevel serverlevel = (ServerLevel)level;
                    actualDamage = baseDamage + EnchantmentHelper.modifyFallBasedDamage(serverlevel, livingentity.getWeaponItem(), target, damageSource, 0.0F) * fallDistance;
                } else {
                    actualDamage = baseDamage;
                }
                cir.setReturnValue(actualDamage);
            }
        } else {
            cir.setReturnValue(0f);
        }
    }
}
