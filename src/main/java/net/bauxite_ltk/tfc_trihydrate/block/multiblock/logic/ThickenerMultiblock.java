package net.bauxite_ltk.tfc_trihydrate.block.multiblock.logic;

import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import net.bauxite_ltk.tfc_trihydrate.block.multiblock.TFCTHMultiblockLogic;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class ThickenerMultiblock extends IETemplateMultiblock {
    public ThickenerMultiblock() {
        super(ResourceLocation.fromNamespaceAndPath("tfc_trihydrate","multiblocks/thickener"),
                ThickenerLogic.MASTER_OFFSET, new BlockPos(4, 1, 8), new BlockPos(9, 8, 9),
                TFCTHMultiblockLogic.THICKENER);
    }

    @Override
    public float getManualScale() {
        return 12;
    }
}
