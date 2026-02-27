package net.bauxite_ltk.tfc_trihydrate.block.multiblock.logic;

import blusunrize.immersiveengineering.api.IEApi;
import blusunrize.immersiveengineering.api.multiblocks.blocks.MultiblockRegistration;
import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import blusunrize.immersiveengineering.common.blocks.multiblocks.logic.CrusherLogic;
import blusunrize.immersiveengineering.common.register.IEMultiblockLogic;
import net.bauxite_ltk.tfc_trihydrate.block.multiblock.TFCTHMultiblockLogic;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class BallMillMultiblock extends IETemplateMultiblock {
    public BallMillMultiblock() {
        super(ResourceLocation.fromNamespaceAndPath("tfc_trihydrate","multiblocks/ball_mill"),
                BallMillLogic.MASTER_OFFSET, new BlockPos(3, 1, 2), new BlockPos(7, 3, 3),
                TFCTHMultiblockLogic.BALL_MILL);
    }

    @Override
    public float getManualScale() {
        return 12;
    }
}
