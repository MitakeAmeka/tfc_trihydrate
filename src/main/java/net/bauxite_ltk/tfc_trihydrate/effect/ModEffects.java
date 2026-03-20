package net.bauxite_ltk.tfc_trihydrate.effect;

import net.bauxite_ltk.tfc_trihydrate.TFCTrihydrate;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.UUID;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TFCTrihydrate.MODID);

    public static final RegistryObject<MobEffect> FEAR_EFFECT = MOB_EFFECTS.register("fear",
            () -> new FearEffect(MobEffectCategory.HARMFUL, 0xa157df)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE,
                            UUID.nameUUIDFromBytes("tfc_trihydrate:fear".getBytes()).toString(),
                            -0.5f,
                            AttributeModifier.Operation.MULTIPLY_TOTAL));


    public static void init(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
