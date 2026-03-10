package net.bauxite_ltk.tfc_trihydrate.crafting;

import blusunrize.immersiveengineering.api.crafting.*;
import blusunrize.immersiveengineering.api.crafting.cache.CachedRecipeList;
import com.google.common.collect.Lists;
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

public class FlotationCellRecipe extends MultiblockRecipe {
    public static DeferredHolder<RecipeSerializer<?>, IERecipeSerializer<FlotationCellRecipe>> SERIALIZER;
    public static final CachedRecipeList<FlotationCellRecipe> RECIPES = new CachedRecipeList<>(TFCTHRecipeType.FLOTATION_CELL);
    //TODO Add Multiplier Config TO Ball Mill
    public static final Supplier<RecipeMultiplier> MULTIPLIERS = () -> new RecipeMultiplier(()->1.0, ()->1.0);


    public final FluidStack outputConcentrate;
    public final FluidStack outputTailing;

    public final SizedFluidIngredient inputOre;
    public final SizedFluidIngredient inputAdd;



    protected <T extends Recipe<?>> FlotationCellRecipe(FluidStack outputConcentrate, FluidStack outputTailing,
                                                        SizedFluidIngredient inputOre, SizedFluidIngredient inputAdd,
                                                        int energy) {

        super(TagOutput.EMPTY, TFCTHRecipeType.FLOTATION_CELL, 10, energy, MULTIPLIERS);
        this.outputConcentrate = outputConcentrate;
        this.outputTailing = outputTailing;
        this.inputOre = inputOre;
        this.inputAdd = inputAdd;


        this.fluidInputList = Lists.newArrayList(this.inputOre);
        this.fluidInputList.add(this.inputAdd);

        this.fluidOutputList = Lists.newArrayList(this.outputConcentrate);
        this.fluidOutputList.add(this.outputTailing);

    }

    @Override
    protected IERecipeSerializer<?> getIESerializer() {
        return SERIALIZER.get();
    }


    public static RecipeHolder<FlotationCellRecipe> findRecipe(Level level, FluidStack inputOre, FluidStack inputAdd)
    {
        if(inputOre.isEmpty() || inputAdd.isEmpty())
            return null;
        for(RecipeHolder<FlotationCellRecipe> recipe : RECIPES.getRecipes(level)){
            if(recipe.value().inputOre.test(inputOre)){
                if(recipe.value().inputAdd.test(inputAdd))
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
