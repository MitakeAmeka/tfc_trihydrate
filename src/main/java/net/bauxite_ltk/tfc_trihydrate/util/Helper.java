package net.bauxite_ltk.tfc_trihydrate.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

public class Helper {
    public static void applyRotationX(double pivotY, double pivotZ, double degree, PoseStack poseStack){
        double arcDegree = -degree/180 * Math.PI;
        //transY, transZ are the Y and Z position of the pivot after *directly* applying the rotation.
        double transY = Math.cos(arcDegree)*pivotY + Math.sin(arcDegree)*pivotZ;
        double transZ = -Math.sin(arcDegree)*pivotY + Math.cos(arcDegree)*pivotZ;
        //System.out.println("(" + transY + "," + transZ + ")");
        poseStack.translate(0, (pivotY-transY)/16, (pivotZ-transZ)/16);

        poseStack.mulPose(Axis.XP.rotationDegrees((float) degree));
    }

    public static void applyRotationZ(double pivotX, double pivotY, double degree, PoseStack poseStack){
        double arcDegree = -degree/180 * Math.PI;
        //transY, transZ are the Y and Z position of the pivot after *directly* applying the rotation.
        double transX = Math.cos(arcDegree)*pivotX + Math.sin(arcDegree)*pivotY;
        double transY = -Math.sin(arcDegree)*pivotX + Math.cos(arcDegree)*pivotY;
        //System.out.println("(" + transY + "," + transZ + ")");
        poseStack.translate(0, (pivotX-transX)/16, (pivotY-transY)/16);

        poseStack.mulPose(Axis.ZP.rotationDegrees((float) degree));
    }
}
