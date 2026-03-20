package net.bauxite_ltk.tfc_trihydrate.crafting;

import blusunrize.immersiveengineering.api.ApiUtils;
import blusunrize.immersiveengineering.api.crafting.FluidTagInput;
import blusunrize.immersiveengineering.api.crafting.IERecipeSerializer;
import blusunrize.immersiveengineering.api.crafting.IngredientWithSize;
import com.google.gson.JsonObject;
import net.bauxite_ltk.tfc_trihydrate.block.multiblock.TFCTHMultiblockLogic;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.conditions.ICondition.IContext;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class BallMillRecipeSerializer extends IERecipeSerializer<BallMillRecipe> {
    @Override
    public BallMillRecipe readFromJson(ResourceLocation recipeId, JsonObject json, IContext context) {
        Lazy<ItemStack> outputItem = readOutput(json.get("result_item"));
        FluidStack outputFluid = json.has("result_fluid")
                ? ApiUtils.jsonDeserializeFluidStack(GsonHelper.getAsJsonObject(json, "result_fluid"))
                : FluidStack.EMPTY;

        IngredientWithSize inputItem = IngredientWithSize.deserialize(GsonHelper.getAsJsonObject(json, "input_item"));
        FluidTagInput inputFluid = json.has("input_fluid")
                ? FluidTagInput.deserialize(GsonHelper.getAsJsonObject(json, "input_fluid"))
                : null;

        int time = GsonHelper.getAsInt(json, "time");
        int energy = GsonHelper.getAsInt(json, "energy");

        return new BallMillRecipe(recipeId, outputFluid, outputItem.get(), inputFluid, inputItem, time, energy);
    }

    @Override
    public BallMillRecipe fromNetwork(@Nonnull ResourceLocation recipeId, @Nonnull FriendlyByteBuf buffer) {
        ItemStack outputItem = buffer.readItem();
        FluidStack outputFluid = FluidStack.readFromPacket(buffer);

        IngredientWithSize inputItem = IngredientWithSize.read(buffer);
        boolean hasInputFluid = buffer.readBoolean();
        FluidTagInput inputFluid = hasInputFluid ? FluidTagInput.read(buffer) : null;

        int time = buffer.readInt();
        int energy = buffer.readInt();

        return new BallMillRecipe(recipeId, outputFluid, outputItem, inputFluid, inputItem, time, energy);
    }

    @Override
    public void toNetwork(@Nonnull FriendlyByteBuf buffer, BallMillRecipe recipe) {
        buffer.writeItem(recipe.outputItem.get());
        recipe.outputFluid.writeToPacket(buffer);

        recipe.inputItem.write(buffer);
        buffer.writeBoolean(recipe.inputFluid != null);
        if (recipe.inputFluid != null)
            recipe.inputFluid.write(buffer);

        buffer.writeInt(recipe.getTotalProcessTime());
        buffer.writeInt(recipe.getTotalProcessEnergy());
    }

    @Override
    public ItemStack getIcon()
    {
        return TFCTHMultiblockLogic.BALL_MILL.iconStack();
    }
}
