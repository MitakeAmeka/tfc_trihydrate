package net.bauxite_ltk.tfc_trihydrate.bakedModel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec2;

public class CustomItemRender extends BlockEntityWithoutLevelRenderer {
    ModelResourceLocation frontLocation = new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath("tfc_trihydrate", "ltk_tool_front"), "inventory");
    ModelResourceLocation backLocation = new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath("tfc_trihydrate", "ltk_tool_back"), "inventory");
    private static float degree = 0;
    //TickThread tickThread;



    // We need some boilerplate in the constructor, telling the superclass where to find the central block entity and entity renderers.
    public CustomItemRender() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());

    }


    public static float aniTicks = 0;
    public static float aniDuration = 40;



    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transform, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        // Do the rendering here

        boolean isFirstPerson = transform.getSerializedName().equals(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND.getSerializedName())
                || transform.getSerializedName().equals(ItemDisplayContext.FIRST_PERSON_LEFT_HAND.getSerializedName());
//        if(tickThread == null){
//            tickThread = new TickThread();
//        }
//        if(!tickThread.isAlive()) {
//            tickThread.start();
//        }

        Player player = Minecraft.getInstance().player;
        if(player!=null){
            if (Minecraft.getInstance().level != null) {
                if(Minecraft.getInstance().level.getBlockState(player.getOnPos()).equals(Blocks.DIAMOND_BLOCK.defaultBlockState())){
                    aniDuration = 10;
                }
                else{
                    aniDuration = 40;
                }
            }
        }

        //计算当前动画的时间戳
        float aniRenderTicks = aniTicks;
        aniRenderTicks = (float) (aniRenderTicks - Math.floor(aniRenderTicks / aniDuration)* aniDuration);

        //计算动画补间
        double degree = 0;
        if(aniRenderTicks<=aniDuration/2){
            degree = smoothStep(aniRenderTicks / (aniDuration/2) )*45;
        }
        else{
            degree = smoothStep(1 - (aniRenderTicks-(aniDuration/2)) / (aniDuration/2) )*45;
        }




        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        BakedModel bakedModel = itemRenderer.getModel(stack,null,null,0);
        BakedModel frontModel = Minecraft.getInstance().getModelManager().getModel(frontLocation);
        BakedModel backModel = Minecraft.getInstance().getModelManager().getModel(backLocation);



        poseStack.pushPose();

        //基础的位移
        poseStack.translate(0.5, 0.5, 0.3);
        if(!isFirstPerson){
            poseStack.mulPose(Axis.YP.rotationDegrees(90));
            poseStack.mulPose(Axis.XP.rotationDegrees((float) (degree/2-90)));
            poseStack.mulPose(Axis.ZP.rotationDegrees((float) (30)));

        }
        poseStack.mulPose(Axis.XP.rotationDegrees(30));





//            //绕(0,2,1)旋转的translate变换
//            double arcDegree = degree/180 * Math.PI;
//            double transY = Math.cos(arcDegree)*2 + Math.sin(arcDegree)*1;
//            double transZ = -Math.sin(arcDegree)*2 + Math.cos(arcDegree)*1;
//            //System.out.println("(" + transY + "," + transZ + ")");
//            poseStack.translate(0, (2-transY)/16, (1-transZ)/16);
//
//            poseStack.mulPose(Axis.XN.rotationDegrees((float) degree));

        applyRotationX(2,1,-degree,poseStack);

        poseStack.scale(1.28f,1.28f,1.28f);


        //itemRenderer.render(stack, ItemDisplayContext.NONE, false, poseStack, bufferSource, packedLight, packedOverlay, bakedModel);
        itemRenderer.render(stack, ItemDisplayContext.NONE, false, poseStack, bufferSource, packedLight, packedOverlay, frontModel);

        poseStack.popPose();



        poseStack.pushPose();
        poseStack.translate(0.5f, 0.5f, 0.3f);
        if(!isFirstPerson){
            poseStack.mulPose(Axis.YP.rotationDegrees(90));
            poseStack.mulPose(Axis.XP.rotationDegrees((float) (degree/2-90)));
            poseStack.mulPose(Axis.ZP.rotationDegrees((float) (30)));
        }
        poseStack.mulPose(Axis.XP.rotationDegrees(30));
        poseStack.scale(1.28f,1.28f,1.28f);
        itemRenderer.render(stack, ItemDisplayContext.NONE, false, poseStack, bufferSource, packedLight, packedOverlay, backModel);
        poseStack.popPose();




        //更新时间戳
//            float deltaTicks = Minecraft.getInstance().getTimer().getRealtimeDeltaTicks();
//            aniTicks += deltaTicks;


    }

    private double smoothStep(double t){
        if(t>=1) return 1;
        if(t<=0) return 0;
        return t*t*(3-2*t);
    }

    private void applyRotationX(double pivotY, double pivotZ, double degree, PoseStack poseStack){
        double arcDegree = -degree/180 * Math.PI;
        double transY = Math.cos(arcDegree)*pivotY + Math.sin(arcDegree)*pivotZ;
        double transZ = -Math.sin(arcDegree)*pivotY + Math.cos(arcDegree)*pivotZ;
        //System.out.println("(" + transY + "," + transZ + ")");
        poseStack.translate(0, (pivotY-transY)/16, (pivotZ-transZ)/16);

        poseStack.mulPose(Axis.XP.rotationDegrees((float) degree));
    }


    class TickThread extends Thread{
        @Override
        public void run() {
            while(Minecraft.getInstance().level!=null){
                aniTicks = (float) (aniTicks - Math.floor(aniTicks / aniDuration)* aniDuration);
                float deltaTicks = 20f/60f;
                aniTicks += deltaTicks;
                try {
                    sleep(1000/60);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
