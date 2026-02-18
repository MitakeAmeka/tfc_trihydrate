package net.bauxite_ltk.tfc_trihydrate.bakedModel;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class CustomItemExtensions implements IClientItemExtensions {
    private final CustomItemRender myBEWLR = new CustomItemRender();

    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return myBEWLR;
    }
}
