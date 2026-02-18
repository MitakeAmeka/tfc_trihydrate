package net.bauxite_ltk.tfc_trihydrate.effect;

import net.bauxite_ltk.tfc_trihydrate.TFCTrihydrate;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, TFCTrihydrate.MODID);

    public static final Holder<MobEffect> FEAR_EFFECT = MOB_EFFECTS.register("fear",
            () -> new FearEffect(MobEffectCategory.HARMFUL, 0xa157df)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE,
                            ResourceLocation.fromNamespaceAndPath(TFCTrihydrate.MODID, "fear"),
                            -0.5f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));


    public static void init(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
