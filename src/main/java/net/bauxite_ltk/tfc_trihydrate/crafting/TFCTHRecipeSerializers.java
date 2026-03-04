package net.bauxite_ltk.tfc_trihydrate.crafting;

import blusunrize.immersiveengineering.ImmersiveEngineering;
import blusunrize.immersiveengineering.api.crafting.SqueezerRecipe;
import blusunrize.immersiveengineering.common.crafting.serializers.SqueezerRecipeSerializer;
import net.bauxite_ltk.tfc_trihydrate.TFCTrihydrate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TFCTHRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(
            BuiltInRegistries.RECIPE_SERIALIZER, TFCTrihydrate.MODID
    );

    static {
        BallMillRecipe.SERIALIZER = RECIPE_SERIALIZERS.register(
                "ball_mill", BallMillRecipeSerializer::new
        );
    }

    public static void init(IEventBus modEventBus){
        RECIPE_SERIALIZERS.register(modEventBus);
    }
}
