package net.bauxite_ltk.tfc_trihydrate.render;

import blusunrize.immersiveengineering.api.client.IVertexBufferHolder;
import blusunrize.immersiveengineering.api.multiblocks.blocks.env.IMultiblockContext;
import blusunrize.immersiveengineering.api.multiblocks.blocks.registry.MultiblockBlockEntityMaster;
import blusunrize.immersiveengineering.api.multiblocks.blocks.util.MultiblockOrientation;
import blusunrize.immersiveengineering.client.render.tile.BERenderUtils;
import blusunrize.immersiveengineering.client.render.tile.IEBlockEntityRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.bauxite_ltk.tfc_trihydrate.block.multiblock.logic.BallMillLogic;
import net.bauxite_ltk.tfc_trihydrate.util.Helper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.NotNull;

public class BallMillRender extends IEBlockEntityRenderer<MultiblockBlockEntityMaster<BallMillLogic.State>> {
    public static final String NAME = "ball_mill_barrel";
    public static TFCTHDynamicModel BARREL;
    private static final IVertexBufferHolder BARREL_BUFFER = IVertexBufferHolder.create(() -> BARREL.getNullQuads());

    @Override
    public void render(@NotNull MultiblockBlockEntityMaster<BallMillLogic.State> be, float partialTicks,
                       @NotNull PoseStack matrixStack, @NotNull MultiBufferSource bufferIn,
                       int combinedLightIn, int combinedOverlayIn) {

        final IMultiblockContext<BallMillLogic.State> ctx = be.getHelper().getContext();
        final BallMillLogic.State state = ctx.getState();
        final MultiblockOrientation orientation = ctx.getLevel().getOrientation();
        Direction facing = orientation.front();

        boolean active = state.shouldRenderActive();
        float barrelAngle = state.getBarrelAngle() + (active ? 9 * partialTicks : 0);
        bufferIn = BERenderUtils.mirror(orientation, matrixStack, bufferIn);

        matrixStack.pushPose();

        rotateForFacing(matrixStack, facing);
        Helper.applyRotationX(14, 0, barrelAngle, matrixStack);

        BARREL_BUFFER.render(RenderType.solid(), combinedLightIn, combinedOverlayIn,
                bufferIn, matrixStack, orientation.mirrored());

        matrixStack.popPose();
    }

    public static void reset() {
        BARREL_BUFFER.reset();
    }
}
