package net.bauxite_ltk.tfc_trihydrate;

import blusunrize.immersiveengineering.api.crafting.*;
import blusunrize.immersiveengineering.common.config.IEServerConfig;
import net.bauxite_ltk.tfc_trihydrate.crafting.BallMillRecipe;
import net.bauxite_ltk.tfc_trihydrate.crafting.FlotationCellRecipe;
import net.bauxite_ltk.tfc_trihydrate.crafting.HydrocycloneRecipe;
import net.bauxite_ltk.tfc_trihydrate.crafting.ThickenerRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final Machines MACHINES = new Machines(BUILDER);

    public static class Machines
    {
        public final IEServerConfig.Machines.MachineRecipeConfig<BallMillRecipe> ballMillConfig;
        public final IEServerConfig.Machines.MachineRecipeConfig<HydrocycloneRecipe> hydrocycloneConfig;
        public final IEServerConfig.Machines.MachineRecipeConfig<FlotationCellRecipe> flotationCellConfig;
        public final IEServerConfig.Machines.MachineRecipeConfig<ThickenerRecipe> thickenerConfig;

        Machines(ForgeConfigSpec.Builder builder)
        {
            builder.push("machines");

            ballMillConfig = addMachineEnergyTimeModifiers(builder, "ball mill");
            hydrocycloneConfig = addMachineEnergyTimeModifiers(builder, "hydrocyclone");
            flotationCellConfig = addMachineEnergyTimeModifiers(builder, "flotation cell");
            thickenerConfig = addMachineEnergyTimeModifiers(builder, "thickener");

            builder.pop();
        }

        private <T extends MultiblockRecipe> IEServerConfig.Machines.MachineRecipeConfig<T> addMachineEnergyTimeModifiers(ForgeConfigSpec.Builder builder, String machine)
        {
            builder.push(machine.replace(' ', '_'));
            ForgeConfigSpec.DoubleValue energy = builder
                    .comment("A modifier to apply to the energy costs of every "+machine+" recipe")
                    .worldRestart()
                    .defineInRange("energyModifier", 1, 1e-3, 1e3);
            ForgeConfigSpec.DoubleValue time = builder
                    .comment("A modifier to apply to the time of every "+machine+" recipe")
                    .defineInRange("timeModifier", 1, 1e-3, 1e3);
                builder.pop();
            return new IEServerConfig.Machines.MachineRecipeConfig<>(energy, time);
        }
    }

    static final ForgeConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}
