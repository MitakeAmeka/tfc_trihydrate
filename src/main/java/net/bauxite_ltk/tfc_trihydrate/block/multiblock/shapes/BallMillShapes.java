package net.bauxite_ltk.tfc_trihydrate.block.multiblock.shapes;


import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Function;

public class BallMillShapes implements Function<BlockPos, VoxelShape> {

    public static final Function<BlockPos, VoxelShape> SHAPE_GETTER = new BallMillShapes();

    private BallMillShapes()
    {
    }

    @Override
    public VoxelShape apply(BlockPos blockPos) {
        return Shapes.block();
    }
}
