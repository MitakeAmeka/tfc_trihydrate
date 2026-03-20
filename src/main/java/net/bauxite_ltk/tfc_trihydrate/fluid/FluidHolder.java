package net.bauxite_ltk.tfc_trihydrate.fluid;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.RegistryObject;

public record FluidHolder<F extends FlowingFluid>(
        RegistryObject<FluidType> type,
        RegistryObject<F> flowing,
        RegistryObject<F> source
) {
    public F getFlowing()
    {
        return flowing.get();
    }

    public F getSource()
    {
        return source.get();
    }

    public FluidType getType()
    {
        return type.get();
    }

    public BlockState createSourceBlock()
    {
        return source.get().defaultFluidState().createLegacyBlock();
    }
}
