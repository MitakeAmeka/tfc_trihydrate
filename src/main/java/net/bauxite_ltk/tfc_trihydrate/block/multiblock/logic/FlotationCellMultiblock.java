package net.bauxite_ltk.tfc_trihydrate.block.multiblock.logic;

import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import net.bauxite_ltk.tfc_trihydrate.block.multiblock.TFCTHMultiblockLogic;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class FlotationCellMultiblock extends IETemplateMultiblock {
    public FlotationCellMultiblock() {
        super(ResourceLocation.fromNamespaceAndPath("tfc_trihydrate","multiblocks/flotation_cell"),
                FlotationCellLogic.MASTER_OFFSET, new BlockPos(2, 1, 3), new BlockPos(5, 5, 4),
                TFCTHMultiblockLogic.FLOTATION_CELL);
    }

    @Override
    public float getManualScale() {
        return 12;
    }
}
