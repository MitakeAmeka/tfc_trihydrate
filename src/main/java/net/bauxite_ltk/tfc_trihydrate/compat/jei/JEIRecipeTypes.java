package net.bauxite_ltk.tfc_trihydrate.compat.jei;

import blusunrize.immersiveengineering.api.crafting.IERecipeTypes;
import blusunrize.immersiveengineering.api.crafting.MixerRecipe;
import mezz.jei.api.recipe.RecipeType;
import net.bauxite_ltk.tfc_trihydrate.crafting.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

public class JEIRecipeTypes {
    public static final RecipeType<RecipeHolder<BallMillRecipe>> BALL_MILL = create(TFCTHRecipeType.BALL_MILL);
    public static final RecipeType<RecipeHolder<FlotationCellRecipe>> FLOTATION_CELL = create(TFCTHRecipeType.FLOTATION_CELL);
    public static final RecipeType<RecipeHolder<HydrocycloneRecipe>> HYDROCYCLONE = create(TFCTHRecipeType.HYDROCYCLONE);
    public static final RecipeType<RecipeHolder<ThickenerRecipe>> THICKENER = create(TFCTHRecipeType.THICKENER);


    private static <T extends Recipe<?>>
    RecipeType<RecipeHolder<T>> create(IERecipeTypes.TypeWithClass<T> type)
    {
        return RecipeType.createFromVanilla(type.get());
    }

    private static <T extends Recipe<?>>
    RecipeType<RecipeHolder<T>> createManual(ResourceLocation uid)
    {
        Class<? extends RecipeHolder<T>> holderClass = (Class)RecipeHolder.class;
        return new RecipeType<>(uid, holderClass);
    }
}
