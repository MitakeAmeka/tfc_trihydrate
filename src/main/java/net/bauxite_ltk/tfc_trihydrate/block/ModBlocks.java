package net.bauxite_ltk.tfc_trihydrate.block;

import net.bauxite_ltk.tfc_trihydrate.fluid.ModFluids;
import net.bauxite_ltk.tfc_trihydrate.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.bauxite_ltk.tfc_trihydrate.TFCTrihydrate.MODID;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    public static final DeferredBlock<LiquidBlock> SIMPLE_FLOTATION_SOLUTION = registerNoItem("fluid/simple_flotation_solution", () -> new LiquidBlock(ModFluids.SIMPLE_FLOTATION_SOLUTION.getFlowing(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredBlock<LiquidBlock> LIME_SLURRY = registerNoItem("fluid/lime_slurry", () -> new LiquidBlock(ModFluids.LIME_SLURRY.getFlowing(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));

    public static final DeferredBlock<LiquidBlock> HEMATITE_SLURRY = registerNoItem("fluid/hematite_slurry", () -> new LiquidBlock(ModFluids.HEMATITE_SLURRY.getFlowing(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredBlock<LiquidBlock> LIMONITE_SLURRY = registerNoItem("fluid/limonite_slurry", () -> new LiquidBlock(ModFluids.LIMONITE_SLURRY.getFlowing(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredBlock<LiquidBlock> MAGNETITE_SLURRY = registerNoItem("fluid/magnetite_slurry", () -> new LiquidBlock(ModFluids.MAGNETITE_SLURRY.getFlowing(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredBlock<LiquidBlock> NATIVE_COPPER_SLURRY = registerNoItem("fluid/native_copper_slurry", () -> new LiquidBlock(ModFluids.NATIVE_COPPER_SLURRY.getFlowing(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredBlock<LiquidBlock> NATIVE_SILVER_SLURRY = registerNoItem("fluid/native_silver_slurry", () -> new LiquidBlock(ModFluids.NATIVE_SILVER_SLURRY.getFlowing(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredBlock<LiquidBlock> NATIVE_GOLD_SLURRY = registerNoItem("fluid/native_gold_slurry", () -> new LiquidBlock(ModFluids.NATIVE_GOLD_SLURRY.getFlowing(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredBlock<LiquidBlock> MALACHITE_SLURRY = registerNoItem("fluid/malachite_slurry", () -> new LiquidBlock(ModFluids.MALACHITE_SLURRY.getFlowing(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredBlock<LiquidBlock> TETRAHEDRITE_SLURRY = registerNoItem("fluid/tetrahedrite_slurry", () -> new LiquidBlock(ModFluids.TETRAHEDRITE_SLURRY.getFlowing(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredBlock<LiquidBlock> GARNIERITE_SLURRY = registerNoItem("fluid/garnierite_slurry", () -> new LiquidBlock(ModFluids.GARNIERITE_SLURRY.getFlowing(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredBlock<LiquidBlock> CASSITERITE_SLURRY = registerNoItem("fluid/cassiterite_slurry", () -> new LiquidBlock(ModFluids.CASSITERITE_SLURRY.getFlowing(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredBlock<LiquidBlock> SPHALERITE_SLURRY = registerNoItem("fluid/sphalerite_slurry", () -> new LiquidBlock(ModFluids.SPHALERITE_SLURRY.getFlowing(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredBlock<LiquidBlock> BISMUTHINITE_SLURRY = registerNoItem("fluid/bismuthinite_slurry", () -> new LiquidBlock(ModFluids.BISMUTHINITE_SLURRY.getFlowing(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));



    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> DeferredBlock<T> registerNoItem(String name, Supplier<T> block)
    {
        return BLOCKS.register(name, block);
    }

    public static void init(IEventBus modEventBus){
        BLOCKS.register(modEventBus);
    }
}
