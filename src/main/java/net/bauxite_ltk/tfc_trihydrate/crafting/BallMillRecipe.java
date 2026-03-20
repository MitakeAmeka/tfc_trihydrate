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
import javax.annotation.Nullable;

public class BallMillRecipe extends MultiblockRecipe {
    public static RegistryObject<IERecipeSerializer<BallMillRecipe>> SERIALIZER;
    public static final CachedRecipeList<BallMillRecipe> RECIPES = new CachedRecipeList<>(TFCTHRecipeType.BALL_MILL);

    public final FluidStack outputFluid;
    @Nonnull
    public final Lazy<ItemStack> outputItem;
    @Nullable
    public final FluidTagInput inputFluid;
    public IngredientWithSize inputItem;

    protected <T extends Recipe<?>> BallMillRecipe(ResourceLocation id,
                                                   FluidStack outputFluid,
                                                   ItemStack outputItem,
                                                   @Nullable FluidTagInput inputFluid,
                                                   IngredientWithSize inputItem,
                                                   int time, int energy){
        super(LAZY_EMPTY, TFCTHRecipeType.BALL_MILL, id);
        this.outputFluid = outputFluid;
        this.inputFluid = inputFluid;
        this.inputItem = inputItem;
        this.outputItem = Lazy.of(() -> outputItem);
        setInputListWithSizes(Lists.newArrayList(this.inputItem));
        this.outputList = Lazy.of(() -> NonNullList.of(ItemStack.EMPTY, this.outputItem.get()));
        if(this.inputFluid!=null){
            this.fluidInputList = Lists.newArrayList(this.inputFluid);
        }
        this.fluidOutputList = Lists.newArrayList(this.outputFluid);
    }

    @Override
    protected IERecipeSerializer<?> getIESerializer() {
        return SERIALIZER.get();
    }

    public BallMillRecipe setInputSize(int size)
    {
        this.inputItem = this.inputItem.withSize(size);
        return this;
    }

    public static BallMillRecipe findRecipe(Level level, ItemStack inputItem, FluidStack inputFluid)
    {
        if(inputItem.isEmpty())
            return null;
        for(BallMillRecipe recipe : RECIPES.getRecipes(level)){
            if(!inputFluid.isEmpty()){
                if(recipe.inputFluid != null && recipe.inputFluid.test(inputFluid)){
                    if(recipe.inputItem.test(inputItem))
                        return recipe;
                }
            }
            else{
                if(recipe.inputFluid == null && recipe.inputItem.test(inputItem)){
                    return recipe;
                }
            }
        }
        return null;
    }

    @Override
    public int getMultipleProcessTicks() {
        return 4;
    }
}
