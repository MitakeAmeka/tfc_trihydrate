package net.bauxite_ltk.tfc_trihydrate.effect;

import net.bauxite_ltk.tfc_trihydrate.TFCTrihydrate;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

import java.util.Random;

public class FearEffect extends MobEffect {
    protected FearEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if(livingEntity instanceof Player player){
            addFearRate(player, amplifier);
            playSound(player, amplifier);
            player.getAttribute(Attributes.BLOCK_BREAK_SPEED).addOrUpdateTransientModifier(
                    new AttributeModifier(
                            ResourceLocation.fromNamespaceAndPath(TFCTrihydrate.MODID, "fear"),
                            Math.min(0,((double)player.getOnPos().getY()-16)/80),
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
            );
        }
        return super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }


    private int fearRate = 0;

    private int tick = 0;
    private boolean addFearRate(Player player, int amplifier){
        if(tick < 4){
            tick++;
            return false;
        }
        tick = 0;
        Random random = new Random();
        int randX = random.nextInt(41)-20;
        int randZ = random.nextInt(41)-20;
        int randY = random.nextInt(41)-20;
        BlockPos testBlockPos = new BlockPos(
                randX + player.getOnPos().getX(),
                randY + player.getOnPos().getY(),
                randZ + player.getOnPos().getZ());
        Level level = player.level();
        int brightness = level.getBrightness(LightLayer.SKY,testBlockPos);
        if(brightness < 8){
            fearRate+=(8-brightness)*2*(amplifier+1);
        }
        else if(fearRate > 0){
            fearRate--;
        }

        //player.sendSystemMessage(Component.literal("相对你脚下坐标 (" + randX + "," + randY + "," + randZ + ") 处亮度为：" + brightness));
        return true;
    }

    private void playSound(Player player, int amplifier){
        if(fearRate > 100){
            player.playSound(SoundEvents.WARDEN_HEARTBEAT,1f,1f);
            if(amplifier>0){
                player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 200));
            }
            fearRate = 0;

        }
    }


}
