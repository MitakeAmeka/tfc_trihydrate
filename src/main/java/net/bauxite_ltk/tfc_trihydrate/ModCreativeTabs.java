package net.bauxite_ltk.tfc_trihydrate;

import net.bauxite_ltk.tfc_trihydrate.item.ModItems;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.items.TFCItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.bauxite_ltk.tfc_trihydrate.item.ModItems.*;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TFCTrihydrate.MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tfc_trihydrate")) //The language key for the title of your CreativeModeTab
            .icon(() -> TFCItems.GRADED_ORES.get(Ore.HEMATITE).get(Ore.Grade.RICH).asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {

                output.accept(TFCItems.GRADED_ORES.get(Ore.HEMATITE).get(Ore.Grade.RICH));
                output.accept(TFCItems.GRADED_ORES.get(Ore.HEMATITE).get(Ore.Grade.NORMAL));
                output.accept(TFCItems.GRADED_ORES.get(Ore.HEMATITE).get(Ore.Grade.POOR));
                output.accept(HEMATITE_CRYSTAL_CHUNK.get());
                output.accept(HEMATITE_ORE_CHUNK.get());
                output.accept(HEMATITE_POOR_ORE_CHUNK.get());
                output.accept(HEMATITE_PURE_COARSE_POWDER.get());
                output.accept(HEMATITE_COARSE_POWDER.get());
                output.accept(HEMATITE_DIRTY_COARSE_POWDER.get());


                output.accept(TFCItems.GRADED_ORES.get(Ore.LIMONITE).get(Ore.Grade.RICH));
                output.accept(TFCItems.GRADED_ORES.get(Ore.LIMONITE).get(Ore.Grade.NORMAL));
                output.accept(TFCItems.GRADED_ORES.get(Ore.LIMONITE).get(Ore.Grade.POOR));
                output.accept(LIMONITE_CRYSTAL_CHUNK.get());
                output.accept(LIMONITE_ORE_CHUNK.get());
                output.accept(LIMONITE_POOR_ORE_CHUNK.get());
                output.accept(LIMONITE_PURE_COARSE_POWDER.get());
                output.accept(LIMONITE_COARSE_POWDER.get());
                output.accept(LIMONITE_DIRTY_COARSE_POWDER.get());


                output.accept(TFCItems.GRADED_ORES.get(Ore.MAGNETITE).get(Ore.Grade.RICH));
                output.accept(TFCItems.GRADED_ORES.get(Ore.MAGNETITE).get(Ore.Grade.NORMAL));
                output.accept(TFCItems.GRADED_ORES.get(Ore.MAGNETITE).get(Ore.Grade.POOR));
                output.accept(MAGNETITE_CRYSTAL_CHUNK.get());
                output.accept(MAGNETITE_ORE_CHUNK.get());
                output.accept(MAGNETITE_POOR_ORE_CHUNK.get());
                output.accept(MAGNETITE_PURE_COARSE_POWDER.get());
                output.accept(MAGNETITE_COARSE_POWDER.get());
                output.accept(MAGNETITE_DIRTY_COARSE_POWDER.get());


                output.accept(TFCItems.GRADED_ORES.get(Ore.NATIVE_COPPER).get(Ore.Grade.RICH));
                output.accept(TFCItems.GRADED_ORES.get(Ore.NATIVE_COPPER).get(Ore.Grade.NORMAL));
                output.accept(TFCItems.GRADED_ORES.get(Ore.NATIVE_COPPER).get(Ore.Grade.POOR));
                output.accept(NATIVE_COPPER_CRYSTAL_CHUNK.get());
                output.accept(NATIVE_COPPER_ORE_CHUNK.get());
                output.accept(NATIVE_COPPER_POOR_ORE_CHUNK.get());
                output.accept(NATIVE_COPPER_PURE_COARSE_POWDER.get());
                output.accept(NATIVE_COPPER_COARSE_POWDER.get());
                output.accept(NATIVE_COPPER_DIRTY_COARSE_POWDER.get());


                output.accept(TFCItems.GRADED_ORES.get(Ore.NATIVE_SILVER).get(Ore.Grade.RICH));
                output.accept(TFCItems.GRADED_ORES.get(Ore.NATIVE_SILVER).get(Ore.Grade.NORMAL));
                output.accept(TFCItems.GRADED_ORES.get(Ore.NATIVE_SILVER).get(Ore.Grade.POOR));
                output.accept(NATIVE_SILVER_CRYSTAL_CHUNK.get());
                output.accept(NATIVE_SILVER_ORE_CHUNK.get());
                output.accept(NATIVE_SILVER_POOR_ORE_CHUNK.get());
                output.accept(NATIVE_SILVER_PURE_COARSE_POWDER.get());
                output.accept(NATIVE_SILVER_COARSE_POWDER.get());
                output.accept(NATIVE_SILVER_DIRTY_COARSE_POWDER.get());


                output.accept(TFCItems.GRADED_ORES.get(Ore.NATIVE_GOLD).get(Ore.Grade.RICH));
                output.accept(TFCItems.GRADED_ORES.get(Ore.NATIVE_GOLD).get(Ore.Grade.NORMAL));
                output.accept(TFCItems.GRADED_ORES.get(Ore.NATIVE_GOLD).get(Ore.Grade.POOR));
                output.accept(NATIVE_GOLD_CRYSTAL_CHUNK.get());
                output.accept(NATIVE_GOLD_ORE_CHUNK.get());
                output.accept(NATIVE_GOLD_POOR_ORE_CHUNK.get());
                output.accept(NATIVE_GOLD_PURE_COARSE_POWDER.get());
                output.accept(NATIVE_GOLD_COARSE_POWDER.get());
                output.accept(NATIVE_GOLD_DIRTY_COARSE_POWDER.get());
                

                output.accept(HEMATITE_CONCENTRATE.get());
                output.accept(LIMONITE_CONCENTRATE.get());
                output.accept(MAGNETITE_CONCENTRATE.get());
                output.accept(NATIVE_COPPER_CONCENTRATE.get());
                output.accept(NATIVE_SILVER_CONCENTRATE.get());
                output.accept(NATIVE_GOLD_CONCENTRATE.get());

                output.accept(SIMPLE_FLOTATION_SOLUTION_BUCKET);
                output.accept(LIME_SLURRY_BUCKET);
                //output.accept(LTK_TOOL.get());// Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());

    public static void init(IEventBus modEventBus){
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
