package net.bauxite_ltk.tfc_trihydrate.fluid;

import com.google.common.base.Suppliers;
import net.bauxite_ltk.tfc_trihydrate.block.ModBlocks;
import net.bauxite_ltk.tfc_trihydrate.item.ModItems;
import net.dries007.tfc.common.fluids.MixingFluid;
import net.dries007.tfc.common.fluids.MoltenFluid;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.bauxite_ltk.tfc_trihydrate.TFCTrihydrate.MODID;


public class ModFluids {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, MODID);

    public static final FluidHolder<BaseFlowingFluid> SIMPLE_FLOTATION_SOLUTION = register(
            "simple_flotation_solution",
            properties -> properties
                    .block(ModBlocks.SIMPLE_FLOTATION_SOLUTION)
                    .bucket(ModItems.SIMPLE_FLOTATION_SOLUTION_BUCKET),
            waterLike()
                    .descriptionId("fluid.tfc_trihydrate.simple_flotation_solution"),
            BaseFlowingFluid.Source::new,
            BaseFlowingFluid.Flowing::new
    );

    public static final FluidHolder<BaseFlowingFluid> LIME_SLURRY = register(
            "lime_slurry",
            properties -> properties
                    .block(ModBlocks.LIME_SLURRY)
                    .bucket(ModItems.LIME_SLURRY_BUCKET),
            waterLike()
                    .descriptionId("fluid.tfc_trihydrate.lime_slurry"),
            MixingFluid.Source::new,
            MixingFluid.Flowing::new
    );

    public static final FluidHolder<BaseFlowingFluid> HEMATITE_SLURRY = register(
            "hematite_slurry",
            properties -> properties
                    .block(ModBlocks.HEMATITE_SLURRY)
                    .bucket(ModItems.HEMATITE_SLURRY_BUCKET),
            waterLike()
                    .descriptionId("fluid.tfc_trihydrate.hematite_slurry"),
            MixingFluid.Source::new,
            MixingFluid.Flowing::new
    );

    public static final FluidHolder<BaseFlowingFluid> LIMONITE_SLURRY = register(
            "limonite_slurry",
            properties -> properties
                    .block(ModBlocks.LIMONITE_SLURRY)
                    .bucket(ModItems.LIMONITE_SLURRY_BUCKET),
            waterLike()
                    .descriptionId("fluid.tfc_trihydrate.limonite_slurry"),
            MixingFluid.Source::new,
            MixingFluid.Flowing::new
    );

    public static final FluidHolder<BaseFlowingFluid> MAGNETITE_SLURRY = register(
            "magnetite_slurry",
            properties -> properties
                    .block(ModBlocks.MAGNETITE_SLURRY)
                    .bucket(ModItems.MAGNETITE_SLURRY_BUCKET),
            waterLike()
                    .descriptionId("fluid.tfc_trihydrate.magnetite_slurry"),
            MixingFluid.Source::new,
            MixingFluid.Flowing::new
    );

    public static final FluidHolder<BaseFlowingFluid> NATIVE_COPPER_SLURRY = register(
            "native_copper_slurry",
            properties -> properties
                    .block(ModBlocks.NATIVE_COPPER_SLURRY)
                    .bucket(ModItems.NATIVE_COPPER_SLURRY_BUCKET),
            waterLike()
                    .descriptionId("fluid.tfc_trihydrate.native_copper_slurry"),
            MixingFluid.Source::new,
            MixingFluid.Flowing::new
    );

    public static final FluidHolder<BaseFlowingFluid> NATIVE_SILVER_SLURRY = register(
            "native_silver_slurry",
            properties -> properties
                    .block(ModBlocks.NATIVE_SILVER_SLURRY)
                    .bucket(ModItems.NATIVE_SILVER_SLURRY_BUCKET),
            waterLike()
                    .descriptionId("fluid.tfc_trihydrate.native_silver_slurry"),
            MixingFluid.Source::new,
            MixingFluid.Flowing::new
    );

    public static final FluidHolder<BaseFlowingFluid> NATIVE_GOLD_SLURRY = register(
            "native_gold_slurry",
            properties -> properties
                    .block(ModBlocks.NATIVE_GOLD_SLURRY)
                    .bucket(ModItems.NATIVE_GOLD_SLURRY_BUCKET),
            waterLike()
                    .descriptionId("fluid.tfc_trihydrate.native_gold_slurry"),
            MixingFluid.Source::new,
            MixingFluid.Flowing::new
    );

    public static final FluidHolder<BaseFlowingFluid> MALACHITE_SLURRY = register(
            "malachite_slurry",
            properties -> properties
                    .block(ModBlocks.MALACHITE_SLURRY)
                    .bucket(ModItems.MALACHITE_SLURRY_BUCKET),
            waterLike()
                    .descriptionId("fluid.tfc_trihydrate.malachite_slurry"),
            MixingFluid.Source::new,
            MixingFluid.Flowing::new
    );

    public static final FluidHolder<BaseFlowingFluid> TETRAHEDRITE_SLURRY = register(
            "tetrahedrite_slurry",
            properties -> properties
                    .block(ModBlocks.TETRAHEDRITE_SLURRY)
                    .bucket(ModItems.TETRAHEDRITE_SLURRY_BUCKET),
            waterLike()
                    .descriptionId("fluid.tfc_trihydrate.tetrahedrite_slurry"),
            MixingFluid.Source::new,
            MixingFluid.Flowing::new
    );

    public static final FluidHolder<BaseFlowingFluid> GARNIERITE_SLURRY = register(
            "garnierite_slurry",
            properties -> properties
                    .block(ModBlocks.GARNIERITE_SLURRY)
                    .bucket(ModItems.GARNIERITE_SLURRY_BUCKET),
            waterLike()
                    .descriptionId("fluid.tfc_trihydrate.garnierite_slurry"),
            MixingFluid.Source::new,
            MixingFluid.Flowing::new
    );

    public static final FluidHolder<BaseFlowingFluid> CASSITERITE_SLURRY = register(
            "cassiterite_slurry",
            properties -> properties
                    .block(ModBlocks.CASSITERITE_SLURRY)
                    .bucket(ModItems.CASSITERITE_SLURRY_BUCKET),
            waterLike()
                    .descriptionId("fluid.tfc_trihydrate.cassiterite_slurry"),
            MixingFluid.Source::new,
            MixingFluid.Flowing::new
    );

    public static final FluidHolder<BaseFlowingFluid> SPHALERITE_SLURRY = register(
            "sphalerite_slurry",
            properties -> properties
                    .block(ModBlocks.SPHALERITE_SLURRY)
                    .bucket(ModItems.SPHALERITE_SLURRY_BUCKET),
            waterLike()
                    .descriptionId("fluid.tfc_trihydrate.sphalerite_slurry"),
            MixingFluid.Source::new,
            MixingFluid.Flowing::new
    );

    public static final FluidHolder<BaseFlowingFluid> BISMUTHINITE_SLURRY = register(
            "bismuthinite_slurry",
            properties -> properties
                    .block(ModBlocks.BISMUTHINITE_SLURRY)
                    .bucket(ModItems.BISMUTHINITE_SLURRY_BUCKET),
            waterLike()
                    .descriptionId("fluid.tfc_trihydrate.bismuthinite_slurry"),
            MixingFluid.Source::new,
            MixingFluid.Flowing::new
    );

    private static FluidType.Properties waterLike()
    {
        return FluidType.Properties.create()
                .adjacentPathType(PathType.WATER)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .canConvertToSource(true)
                .canDrown(true)
                .canExtinguish(true)
                .canHydrate(true)
                .canPushEntity(true)
                .canSwim(true)
                .supportsBoating(true);
    }

    private static FluidType.Properties lavaLike()
    {
        return FluidType.Properties.create()
                .adjacentPathType(PathType.LAVA)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                .lightLevel(15)
                .density(3000)
                .viscosity(6000)
                .temperature(1300)
                .canConvertToSource(false)
                .canDrown(false)
                .canExtinguish(false)
                .canHydrate(false)
                .canPushEntity(false)
                .canSwim(false)
                .supportsBoating(false);
    }


    private static <F extends FlowingFluid> FluidHolder<F> register(String name, Consumer<BaseFlowingFluid.Properties> builder, FluidType.Properties typeProperties, Function<BaseFlowingFluid.Properties, F> sourceFactory, Function<BaseFlowingFluid.Properties, F> flowingFactory)
    {
        // Names `metal/foo` to `metal/flowing_foo`
        final int index = name.lastIndexOf('/');
        final String flowingName = index == -1 ? "flowing_" + name : name.substring(0, index) + "/flowing_" + name.substring(index + 1);

        return registerFluid(FLUID_TYPES, FLUIDS, name, name, flowingName, builder, () -> new FluidType(typeProperties), sourceFactory, flowingFactory);
    }

    public static <F extends FlowingFluid> FluidHolder<F> registerFluid(
            DeferredRegister<FluidType> fluidTypes,
            DeferredRegister<Fluid> fluids,
            String typeName,
            String sourceName,
            String flowingName,
            Consumer<BaseFlowingFluid.Properties> builder,
            Supplier<FluidType> typeFactory,
            Function<BaseFlowingFluid.Properties, F> sourceFactory,
            Function<BaseFlowingFluid.Properties, F> flowingFactory)
    {
        // The type need a reference to both source and flowing
        // In addition, the properties' builder cannot be invoked statically, as it has hard references to registry objects, which may not be populated based on class load order - it must be invoked at registration time.
        // So, first we prepare the source and flowing registry objects, referring to the properties box (which will be opened during registration, which is ok)
        // Then, we populate the properties box lazily, (since it's a mutable lazy), so the properties inside are only constructed when the box is opened (again, during registration)
        final Mutable<Supplier<BaseFlowingFluid.Properties>> typeBox = new MutableObject<>();
        final DeferredHolder<Fluid, F> source = fluids.register(sourceName, () -> sourceFactory.apply(typeBox.getValue().get()));
        final DeferredHolder<Fluid, F> flowing = fluids.register(flowingName, () -> flowingFactory.apply(typeBox.getValue().get()));

        final DeferredHolder<FluidType, FluidType> fluidType = fluidTypes.register(typeName, typeFactory);

        typeBox.setValue(Suppliers.memoize(() -> {
            final BaseFlowingFluid.Properties lazyProperties = new BaseFlowingFluid.Properties(fluidType, source, flowing);
            builder.accept(lazyProperties);
            return lazyProperties;
        }));

        return new FluidHolder<>(fluidType, flowing, source);
    }

    public static void init(IEventBus modEventBus){
        FLUIDS.register(modEventBus);
        FLUID_TYPES.register(modEventBus);
    }

}
