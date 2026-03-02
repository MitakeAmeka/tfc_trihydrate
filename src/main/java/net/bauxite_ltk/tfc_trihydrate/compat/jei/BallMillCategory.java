package net.bauxite_ltk.tfc_trihydrate.compat.jei;

import blusunrize.immersiveengineering.api.IEApi;
import blusunrize.immersiveengineering.api.crafting.SqueezerRecipe;
import blusunrize.immersiveengineering.common.register.IEMultiblockLogic;
import blusunrize.immersiveengineering.common.util.compat.jei.IERecipeCategory;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.bauxite_ltk.tfc_trihydrate.TFCTrihydrate;
import net.bauxite_ltk.tfc_trihydrate.block.multiblock.TFCTHMultiblockLogic;
import net.bauxite_ltk.tfc_trihydrate.crafting.BallMillRecipe;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.Arrays;

public class BallMillCategory extends IERecipeCategory<BallMillRecipe> {

    private final IDrawableStatic tankOverlay;

    public BallMillCategory(IGuiHelper helper) {
        super(helper, JEIRecipeTypes.BALL_MILL, "block.tfc_trihydrate.ball_mill");
        ResourceLocation background = ResourceLocation.fromNamespaceAndPath(TFCTrihydrate.MODID,"textures/gui/ball_mill.png");
        setBackground(helper.createDrawable(background, 6, 9, 155-5, 73-8));
        setIcon(TFCTHMultiblockLogic.BALL_MILL.iconStack());
        tankOverlay = helper.createDrawable(background, 179, 33, 16, 47);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BallMillRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 31, 10)
                .addItemStacks(Arrays.asList(recipe.inputItem.getMatchingStacks()));
        IRecipeSlotBuilder outputBuilder = builder.addSlot(RecipeIngredientRole.OUTPUT, 132, 44);
        if(recipe.inputFluid!=null)
        {
            int tankSize = Math.max(FluidType.BUCKET_VOLUME, recipe.inputFluid.amount());
            builder.addSlot(RecipeIngredientRole.INPUT, 4, 3)
                    .setFluidRenderer(tankSize, false, 16, 47)
                    .setOverlay(tankOverlay, 0, 0)
                    .addIngredients(NeoForgeTypes.FLUID_STACK, Arrays.asList(recipe.inputFluid.getFluids()))
                    .addRichTooltipCallback(JEIHelper.fluidTooltipCallback);
        }

        if(!recipe.outputItem.get().isEmpty())
            outputBuilder.addItemStack(recipe.outputItem.get());
        if(recipe.outputFluid!=null&&!recipe.outputFluid.isEmpty())
        {
            int tankSize = Math.max(FluidType.BUCKET_VOLUME, recipe.outputFluid.getAmount());
            builder.addSlot(RecipeIngredientRole.OUTPUT, 112, 3)
                    .setFluidRenderer(tankSize, false, 16, 47)
                    .setOverlay(tankOverlay, 0, 0)
                    .addIngredient(NeoForgeTypes.FLUID_STACK, recipe.outputFluid)
                    .addRichTooltipCallback(JEIHelper.fluidTooltipCallback);
        }
    }
}
