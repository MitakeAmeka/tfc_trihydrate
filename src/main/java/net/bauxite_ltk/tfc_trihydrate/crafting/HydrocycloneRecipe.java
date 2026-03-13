package net.bauxite_ltk.tfc_trihydrate.crafting;

import blusunrize.immersiveengineering.api.crafting.*;
import blusunrize.immersiveengineering.api.crafting.cache.CachedRecipeList;
import com.google.common.collect.Lists;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.neoforged.neoforge.registries.DeferredHolder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Supplier;

public class HydrocycloneRecipe extends MultiblockRecipe {
    public static DeferredHolder<RecipeSerializer<?>, IERecipeSerializer<HydrocycloneRecipe>> SERIALIZER;
    public static final CachedRecipeList<HydrocycloneRecipe> RECIPES = new CachedRecipeList<>(TFCTHRecipeType.HYDROCYCLONE);
    //TODO Add Multiplier Config TO Ball Mill
    public static final Supplier<RecipeMultiplier> MULTIPLIERS = () -> new RecipeMultiplier(()->1.0, ()->1.0);


    public final FluidStack outputFluid;
    @Nonnull
    public TagOutput outputItem;

    public final SizedFluidIngredient inputFluid;



    protected <T extends Recipe<?>> HydrocycloneRecipe(FluidStack outputFluid, @Nonnull TagOutput outputItem,
                                                        SizedFluidIngredient inputFluid, int energy) {

        super(TagOutput.EMPTY, TFCTHRecipeType.HYDROCYCLONE, 10, energy, MULTIPLIERS);
        this.outputFluid = outputFluid;
        this.inputFluid = inputFluid;
        this.outputItem = outputItem;

        this.outputList = new TagOutputList(this.outputItem);
        this.fluidInputList = Lists.newArrayList(this.inputFluid);
        this.fluidOutputList = Lists.newArrayList(this.outputFluid);

    }

    @Override
    protected IERecipeSerializer<?> getIESerializer() {
        return SERIALIZER.get();
    }


    public static RecipeHolder<HydrocycloneRecipe> findRecipe(Level level, FluidStack inputFluid)
    {
        if(inputFluid.isEmpty())
            return null;
        for(RecipeHolder<HydrocycloneRecipe> recipe : RECIPES.getRecipes(level)){
            if(recipe.value().inputFluid.test(inputFluid)){
                return recipe;
            }
        }
        return null;
    }

    @Override
    public int getMultipleProcessTicks() {
        return 0;
    }
}
