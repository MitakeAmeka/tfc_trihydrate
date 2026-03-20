package net.bauxite_ltk.tfc_trihydrate.render;

import blusunrize.immersiveengineering.api.ApiUtils;
import net.bauxite_ltk.tfc_trihydrate.TFCTrihydrate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = TFCTrihydrate.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TFCTHDynamicModel
{
    private static final List<ResourceLocation> MODELS = new ArrayList<>();

    @SubscribeEvent
    public static void registerModels(ModelEvent.RegisterAdditional ev)
    {
        for(ResourceLocation model : MODELS)
            // TODO check if this works
            ev.register(model);
    }

    private final ResourceLocation name;

    public TFCTHDynamicModel(String desc)
    {
        // TODO does this work?
        this.name = ResourceLocation.fromNamespaceAndPath(TFCTrihydrate.MODID, "dynamic/" + desc);
        MODELS.add(this.name);
    }

    public BakedModel get()
    {
        final BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
        BakedModel model = blockRenderer.getBlockModelShaper().getModelManager().getModel(name);
        return model;
    }

    public List<BakedQuad> getNullQuads()
    {
        return getNullQuads(ModelData.EMPTY);
    }

    public List<BakedQuad> getNullQuads(ModelData data)
    {
        return get().getQuads(null, null, ApiUtils.RANDOM_SOURCE, data, null);
    }

    public ResourceLocation getName()
    {
        return name;
    }
}
