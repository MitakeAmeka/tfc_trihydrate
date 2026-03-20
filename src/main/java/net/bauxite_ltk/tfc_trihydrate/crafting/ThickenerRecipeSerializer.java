package net.bauxite_ltk.tfc_trihydrate.crafting;

import blusunrize.immersiveengineering.api.ApiUtils;
import blusunrize.immersiveengineering.api.crafting.FluidTagInput;
import blusunrize.immersiveengineering.api.crafting.IERecipeSerializer;
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

public class ThickenerRecipeSerializer extends IERecipeSerializer<ThickenerRecipe> {
    @Override
    public ThickenerRecipe readFromJson(ResourceLocation recipeId, JsonObject json, IContext context) {
        FluidStack outputFluid = ApiUtils.jsonDeserializeFluidStack(GsonHelper.getAsJsonObject(json, "result_fluid"));
        Lazy<ItemStack> outputItem = readOutput(json.get("result_item"));
        FluidTagInput inputFluid = FluidTagInput.deserialize(GsonHelper.getAsJsonObject(json, "input_fluid"));

        int energy = GsonHelper.getAsInt(json, "energy");

        return new ThickenerRecipe(recipeId, outputFluid, outputItem.get(), inputFluid, energy);
    }

    @Override
    public ThickenerRecipe fromNetwork(@Nonnull ResourceLocation recipeId, @Nonnull FriendlyByteBuf buffer) {
        FluidStack outputFluid = FluidStack.readFromPacket(buffer);
        ItemStack outputItem = buffer.readItem();
        FluidTagInput inputFluid = FluidTagInput.read(buffer);

        int energy = buffer.readInt();

        return new ThickenerRecipe(recipeId, outputFluid, outputItem, inputFluid, energy);
    }

    @Override
    public void toNetwork(@Nonnull FriendlyByteBuf buffer, ThickenerRecipe recipe) {
        recipe.outputFluid.writeToPacket(buffer);
        buffer.writeItem(recipe.outputItem.get());
        recipe.inputFluid.write(buffer);

        buffer.writeInt(recipe.getTotalProcessEnergy());
    }

    @Override
    public ItemStack getIcon() {
        return TFCTHMultiblockLogic.THICKENER.iconStack();
    }
}
