package com.example.examplemod.bakedModel;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomModel implements BakedModel {
    private BakedModel existModel;

    public CustomModel(BakedModel existModel){
        this.existModel = existModel;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState blockState, @Nullable Direction direction, RandomSource randomSource) {
        return this.existModel.getQuads(blockState, direction, randomSource);
    }

    @Override
    public boolean useAmbientOcclusion() {
        return this.existModel.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return this.existModel.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return this.existModel.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return true;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return this.existModel.getParticleIcon();
    }

    @Override
    public ItemOverrides getOverrides() {
        return this.existModel.getOverrides();
    }

    @Override
    public BakedModel applyTransform(ItemDisplayContext transformType, PoseStack poseStack, boolean applyLeftHandTransform) {
        if (transformType == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND || transformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
            return this;
        }
        else if(
                transformType == ItemDisplayContext.THIRD_PERSON_LEFT_HAND ||
                transformType == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND ||
                transformType == ItemDisplayContext.GUI ||
                transformType == ItemDisplayContext.GROUND ||
                transformType == ItemDisplayContext.FIXED
        ){
            return this;
        }
        //return this;
        return this.existModel.applyTransform(transformType,poseStack,applyLeftHandTransform);
    }
}
