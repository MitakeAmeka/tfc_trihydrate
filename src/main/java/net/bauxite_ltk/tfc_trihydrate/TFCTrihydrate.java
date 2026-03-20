package net.bauxite_ltk.tfc_trihydrate;

import net.bauxite_ltk.tfc_trihydrate.block.ModBlocks;
import net.bauxite_ltk.tfc_trihydrate.block.multiblock.TFCTHMultiblockLogic;
import net.bauxite_ltk.tfc_trihydrate.block.multiblock.TFCTHMultiblocks;
import net.bauxite_ltk.tfc_trihydrate.block.multiblock.logic.TFCTHMultiblockBuilder;
import net.bauxite_ltk.tfc_trihydrate.crafting.TFCTHRecipeSerializers;
import net.bauxite_ltk.tfc_trihydrate.crafting.TFCTHRecipeType;
import net.bauxite_ltk.tfc_trihydrate.effect.ModEffects;
import net.bauxite_ltk.tfc_trihydrate.fluid.ModFluids;
import net.bauxite_ltk.tfc_trihydrate.gui.TFCTHMenuTypes;
import net.bauxite_ltk.tfc_trihydrate.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

@Mod(TFCTrihydrate.MODID)
public class TFCTrihydrate {
    public static final String MODID = "tfc_trihydrate";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TFCTrihydrate() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        TFCTHRecipeSerializers.init(modEventBus);
        ModBlocks.init(modEventBus);
        ModItems.init(modEventBus);
        ModEffects.init(modEventBus);
        ModFluids.init(modEventBus);
        ModCreativeTabs.init(modEventBus);

        TFCTHMultiblocks.init();
        TFCTHMultiblockLogic.init(modEventBus);
        TFCTHMultiblockBuilder.handleModBusRegistrations(modEventBus);

        TFCTHRecipeType.init(modEventBus);
        TFCTHMenuTypes.init(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM TFC:TH COMMON SETUP");
    }
}
