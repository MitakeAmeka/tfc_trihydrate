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
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class FlotationCellRecipeSerializer extends IERecipeSerializer<FlotationCellRecipe> {
    @Override
    public FlotationCellRecipe readFromJson(ResourceLocation recipeId, JsonObject json, IContext context) {
        FluidStack outputConcentrate = ApiUtils.jsonDeserializeFluidStack(GsonHelper.getAsJsonObject(json, "result_concentrate"));
        FluidStack outputTailing = json.has("result_tailing")
                ? ApiUtils.jsonDeserializeFluidStack(GsonHelper.getAsJsonObject(json, "result_tailing"))
                : FluidStack.EMPTY;

        FluidTagInput inputOre = FluidTagInput.deserialize(GsonHelper.getAsJsonObject(json, "input_ore"));
        FluidTagInput inputAdd = FluidTagInput.deserialize(GsonHelper.getAsJsonObject(json, "input_add"));

        int time = GsonHelper.getAsInt(json, "time");
        int energy = GsonHelper.getAsInt(json, "energy");

        return new FlotationCellRecipe(recipeId, outputConcentrate, outputTailing, inputOre, inputAdd, time, energy);
    }

    @Override
    public FlotationCellRecipe fromNetwork(@Nonnull ResourceLocation recipeId, @Nonnull FriendlyByteBuf buffer) {
        FluidStack outputConcentrate = FluidStack.readFromPacket(buffer);
        FluidStack outputTailing = FluidStack.readFromPacket(buffer);

        FluidTagInput inputOre = FluidTagInput.read(buffer);
        FluidTagInput inputAdd = FluidTagInput.read(buffer);

        int time = buffer.readInt();
        int energy = buffer.readInt();

        return new FlotationCellRecipe(recipeId, outputConcentrate, outputTailing, inputOre, inputAdd, time, energy);
    }

    @Override
    public void toNetwork(@Nonnull FriendlyByteBuf buffer, FlotationCellRecipe recipe) {
        recipe.outputConcentrate.writeToPacket(buffer);
        recipe.outputTailing.writeToPacket(buffer);

        recipe.inputOre.write(buffer);
        recipe.inputAdd.write(buffer);

        buffer.writeInt(recipe.getTotalProcessTime());
        buffer.writeInt(recipe.getTotalProcessEnergy());
    }

    @Override
    public ItemStack getIcon() {
        return TFCTHMultiblockLogic.FLOTATION_CELL.iconStack();
    }
}
