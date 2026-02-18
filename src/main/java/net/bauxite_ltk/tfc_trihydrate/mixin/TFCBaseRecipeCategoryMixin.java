package net.bauxite_ltk.tfc_trihydrate.mixin;


import net.dries007.tfc.common.component.food.FoodCapability;
import net.dries007.tfc.common.items.TFCMaceItem;
import net.dries007.tfc.common.recipes.outputs.ItemStackModifier;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.dries007.tfc.compat.jei.category.BaseRecipeCategory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BaseRecipeCategory.class)
public class TFCBaseRecipeCategoryMixin {

    @Inject(
            method = "(Ljava/util/List;Lnet/dries007/tfc/common/recipes/outputs/ItemStackProvider;)Ljava/util/List;",
            at = @At(value = "RETURN"),
            cancellable = true)
    private static void collapse(List<ItemStack> inputs, ItemStackProvider output,
                                 CallbackInfoReturnable<List<ItemStack>> cir) {
        cir.setReturnValue(inputs.stream()
                .map(output::getSingleStack)
                .map(FoodCapability::setTransientNonDecaying) // Avoid decaying in JEI views
                .toList());
    }
}
