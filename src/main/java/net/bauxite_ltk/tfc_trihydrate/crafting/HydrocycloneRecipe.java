package net.bauxite_ltk.tfc_trihydrate.crafting;

import blusunrize.immersiveengineering.api.crafting.*;
import blusunrize.immersiveengineering.api.crafting.cache.CachedRecipeList;
import com.google.common.collect.Lists;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;

public class HydrocycloneRecipe extends MultiblockRecipe {
    public static RegistryObject<IERecipeSerializer<HydrocycloneRecipe>> SERIALIZER;
    public static final CachedRecipeList<HydrocycloneRecipe> RECIPES = new CachedRecipeList<>(TFCTHRecipeType.HYDROCYCLONE);

    public final FluidStack outputFluid;
    @Nonnull
    public final Lazy<ItemStack> outputItem;
    public final FluidTagInput inputFluid;

    protected <T extends Recipe<?>> HydrocycloneRecipe(ResourceLocation id,
                                                       FluidStack outputFluid,
                                                       @Nonnull ItemStack outputItem,
                                                       FluidTagInput inputFluid,
                                                       int energy) {
        super(LAZY_EMPTY, TFCTHRecipeType.HYDROCYCLONE, id);
        this.outputFluid = outputFluid;
        this.inputFluid = inputFluid;
        this.outputItem = Lazy.of(() -> outputItem);
        this.outputList = Lazy.of(() -> NonNullList.of(ItemStack.EMPTY, this.outputItem.get()));
        this.fluidInputList = Lists.newArrayList(this.inputFluid);
        this.fluidOutputList = Lists.newArrayList(this.outputFluid);
    }

    @Override
    protected IERecipeSerializer<?> getIESerializer() {
        return SERIALIZER.get();
    }

    public static HydrocycloneRecipe findRecipe(Level level, FluidStack inputFluid)
    {
        if(inputFluid.isEmpty())
            return null;
        for(HydrocycloneRecipe recipe : RECIPES.getRecipes(level)){
            if(recipe.inputFluid != null && recipe.inputFluid.test(inputFluid)){
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
