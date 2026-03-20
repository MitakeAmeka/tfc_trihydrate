package net.bauxite_ltk.tfc_trihydrate.crafting;

import blusunrize.immersiveengineering.api.crafting.*;
import blusunrize.immersiveengineering.api.crafting.cache.CachedRecipeList;
import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryObject;

public class FlotationCellRecipe extends MultiblockRecipe {
    public static RegistryObject<IERecipeSerializer<FlotationCellRecipe>> SERIALIZER;
    public static final CachedRecipeList<FlotationCellRecipe> RECIPES = new CachedRecipeList<>(TFCTHRecipeType.FLOTATION_CELL);

    public final FluidStack outputConcentrate;
    public final FluidStack outputTailing;
    public final FluidTagInput inputOre;
    public final FluidTagInput inputAdd;

    protected <T extends Recipe<?>> FlotationCellRecipe(ResourceLocation id,
                                                        FluidStack outputConcentrate,
                                                        FluidStack outputTailing,
                                                        FluidTagInput inputOre,
                                                        FluidTagInput inputAdd,
                                                        int baseTime, int energy) {
        super(LAZY_EMPTY, TFCTHRecipeType.FLOTATION_CELL, id);
        this.outputConcentrate = outputConcentrate;
        this.outputTailing = outputTailing;
        this.inputOre = inputOre;
        this.inputAdd = inputAdd;
        this.fluidInputList = Lists.newArrayList(this.inputOre, this.inputAdd);
        this.fluidOutputList = Lists.newArrayList(this.outputConcentrate, this.outputTailing);
    }

    @Override
    protected IERecipeSerializer<?> getIESerializer()
    {
        return SERIALIZER.get();
    }

    public static FlotationCellRecipe findRecipe(Level level, FluidStack inputOre, FluidStack inputAdd)
    {
        if(inputOre.isEmpty() || inputAdd.isEmpty())
            return null;
        for(FlotationCellRecipe recipe : RECIPES.getRecipes(level)){
            if (recipe.inputOre != null && recipe.inputOre.test(inputOre)) {
                if (recipe.inputAdd != null && recipe.inputAdd.test(inputAdd)) {
                    return recipe;
                }
            }
        }
        return null;
    }

    @Override
    public int getMultipleProcessTicks() {
        return 0;
    }
}
