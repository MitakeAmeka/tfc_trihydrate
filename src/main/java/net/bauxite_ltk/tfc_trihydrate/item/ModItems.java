package net.bauxite_ltk.tfc_trihydrate.item;

import net.bauxite_ltk.tfc_trihydrate.fluid.ModFluids;
import net.bauxite_ltk.tfc_trihydrate.item.customSword.CustomSwordItem;
import net.bauxite_ltk.tfc_trihydrate.item.customSword.CustomTiers;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.bauxite_ltk.tfc_trihydrate.TFCTrihydrate.MODID;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

//    public static final DeferredItem<Item> LTK_TOOL = ITEMS.register("ltk_tool",
//            () -> new CustomSwordItem(
//                    CustomTiers.LTK,
//                    new Item.Properties()
//                            .rarity(Rarity.UNCOMMON)
//                            .stacksTo(1)
//                            .attributes(
//                                    SwordItem.createAttributes(CustomTiers.LTK, 3, -2.4f)
//                            )
//            )
//    );

    public static final DeferredItem<Item> HEMATITE_CRYSTAL_CHUNK = ITEMS.registerSimpleItem("crystal_chunk/hematite");
    public static final DeferredItem<Item> HEMATITE_CONCENTRATE = ITEMS.registerSimpleItem("concentrate/hematite");
    public static final DeferredItem<Item> HEMATITE_PURE_COARSE_POWDER = ITEMS.registerSimpleItem("pure_coarse_powder/hematite");
    public static final DeferredItem<Item> HEMATITE_ORE_CHUNK = ITEMS.registerSimpleItem("ore_chunk/hematite");
    public static final DeferredItem<Item> HEMATITE_COARSE_POWDER = ITEMS.registerSimpleItem("coarse_powder/hematite");
    public static final DeferredItem<Item> HEMATITE_POOR_ORE_CHUNK = ITEMS.registerSimpleItem("poor_ore_chunk/hematite");
    public static final DeferredItem<Item> HEMATITE_DIRTY_COARSE_POWDER = ITEMS.registerSimpleItem("dirty_coarse_powder/hematite");


    public static final DeferredItem<Item> MAGNETITE_CRYSTAL_CHUNK = ITEMS.registerSimpleItem("crystal_chunk/magnetite");
    public static final DeferredItem<Item> MAGNETITE_CONCENTRATE = ITEMS.registerSimpleItem("concentrate/magnetite");
    public static final DeferredItem<Item> MAGNETITE_PURE_COARSE_POWDER = ITEMS.registerSimpleItem("pure_coarse_powder/magnetite");
    public static final DeferredItem<Item> MAGNETITE_ORE_CHUNK = ITEMS.registerSimpleItem("ore_chunk/magnetite");
    public static final DeferredItem<Item> MAGNETITE_COARSE_POWDER = ITEMS.registerSimpleItem("coarse_powder/magnetite");
    public static final DeferredItem<Item> MAGNETITE_POOR_ORE_CHUNK = ITEMS.registerSimpleItem("poor_ore_chunk/magnetite");
    public static final DeferredItem<Item> MAGNETITE_DIRTY_COARSE_POWDER = ITEMS.registerSimpleItem("dirty_coarse_powder/magnetite");

    public static final DeferredItem<Item> LIMONITE_CRYSTAL_CHUNK = ITEMS.registerSimpleItem("crystal_chunk/limonite");
    public static final DeferredItem<Item> LIMONITE_CONCENTRATE = ITEMS.registerSimpleItem("concentrate/limonite");
    public static final DeferredItem<Item> LIMONITE_PURE_COARSE_POWDER = ITEMS.registerSimpleItem("pure_coarse_powder/limonite");
    public static final DeferredItem<Item> LIMONITE_ORE_CHUNK = ITEMS.registerSimpleItem("ore_chunk/limonite");
    public static final DeferredItem<Item> LIMONITE_COARSE_POWDER = ITEMS.registerSimpleItem("coarse_powder/limonite");
    public static final DeferredItem<Item> LIMONITE_POOR_ORE_CHUNK = ITEMS.registerSimpleItem("poor_ore_chunk/limonite");
    public static final DeferredItem<Item> LIMONITE_DIRTY_COARSE_POWDER = ITEMS.registerSimpleItem("dirty_coarse_powder/limonite");


    public static final DeferredItem<Item> NATIVE_COPPER_CRYSTAL_CHUNK = ITEMS.registerSimpleItem("crystal_chunk/native_copper");
    public static final DeferredItem<Item> NATIVE_COPPER_CONCENTRATE = ITEMS.registerSimpleItem("concentrate/native_copper");
    public static final DeferredItem<Item> NATIVE_COPPER_PURE_COARSE_POWDER = ITEMS.registerSimpleItem("pure_coarse_powder/native_copper");
    public static final DeferredItem<Item> NATIVE_COPPER_ORE_CHUNK = ITEMS.registerSimpleItem("ore_chunk/native_copper");
    public static final DeferredItem<Item> NATIVE_COPPER_COARSE_POWDER = ITEMS.registerSimpleItem("coarse_powder/native_copper");
    public static final DeferredItem<Item> NATIVE_COPPER_POOR_ORE_CHUNK = ITEMS.registerSimpleItem("poor_ore_chunk/native_copper");
    public static final DeferredItem<Item> NATIVE_COPPER_DIRTY_COARSE_POWDER = ITEMS.registerSimpleItem("dirty_coarse_powder/native_copper");


    public static final DeferredItem<Item> NATIVE_SILVER_CRYSTAL_CHUNK = ITEMS.registerSimpleItem("crystal_chunk/native_silver");
    public static final DeferredItem<Item> NATIVE_SILVER_CONCENTRATE = ITEMS.registerSimpleItem("concentrate/native_silver");
    public static final DeferredItem<Item> NATIVE_SILVER_PURE_COARSE_POWDER = ITEMS.registerSimpleItem("pure_coarse_powder/native_silver");
    public static final DeferredItem<Item> NATIVE_SILVER_ORE_CHUNK = ITEMS.registerSimpleItem("ore_chunk/native_silver");
    public static final DeferredItem<Item> NATIVE_SILVER_COARSE_POWDER = ITEMS.registerSimpleItem("coarse_powder/native_silver");
    public static final DeferredItem<Item> NATIVE_SILVER_POOR_ORE_CHUNK = ITEMS.registerSimpleItem("poor_ore_chunk/native_silver");
    public static final DeferredItem<Item> NATIVE_SILVER_DIRTY_COARSE_POWDER = ITEMS.registerSimpleItem("dirty_coarse_powder/native_silver");

    
    public static final DeferredItem<Item> NATIVE_GOLD_CRYSTAL_CHUNK = ITEMS.registerSimpleItem("crystal_chunk/native_gold");
    public static final DeferredItem<Item> NATIVE_GOLD_CONCENTRATE = ITEMS.registerSimpleItem("concentrate/native_gold");
    public static final DeferredItem<Item> NATIVE_GOLD_PURE_COARSE_POWDER = ITEMS.registerSimpleItem("pure_coarse_powder/native_gold");
    public static final DeferredItem<Item> NATIVE_GOLD_ORE_CHUNK = ITEMS.registerSimpleItem("ore_chunk/native_gold");
    public static final DeferredItem<Item> NATIVE_GOLD_COARSE_POWDER = ITEMS.registerSimpleItem("coarse_powder/native_gold");
    public static final DeferredItem<Item> NATIVE_GOLD_POOR_ORE_CHUNK = ITEMS.registerSimpleItem("poor_ore_chunk/native_gold");
    public static final DeferredItem<Item> NATIVE_GOLD_DIRTY_COARSE_POWDER = ITEMS.registerSimpleItem("dirty_coarse_powder/native_gold");



    public static final DeferredItem<Item> SIMPLE_FLOTATION_SOLUTION_BUCKET =
            ITEMS.register("bucket/simple_flotation_solution",
                    () -> new BucketItem(
                            ModFluids.SIMPLE_FLOTATION_SOLUTION.getSource(),
                            new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)
                    )
            );

    public static final DeferredItem<Item> LIME_SLURRY_BUCKET =
            ITEMS.register("bucket/lime_slurry",
                    () -> new BucketItem(
                            ModFluids.LIME_SLURRY.getSource(),
                            new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)
                    )
            );

    public static void init(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }



}
