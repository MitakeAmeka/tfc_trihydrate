package com.example.examplemod.item.customSword;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public enum CustomTiers implements Tier {
    LTK(2000,16f, 8.0f, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 25, Ingredient.of(Items.GOLDEN_APPLE));

    private final int uses;
    private final float speed;
    private final float attackDamageBonus;
    private final TagKey<Block> incorrectBlocksForDrops;
    private final int enchantmentValue;
    private final Ingredient repairIngredient;

    CustomTiers(int uses, float speed, float attackDamageBonus, TagKey<Block> incorrectBlocksForDrops, int enchantmentValue, Ingredient repairIngredient) {
        this.uses = uses;
        this.speed = speed;
        this.attackDamageBonus = attackDamageBonus;
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamageBonus;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return incorrectBlocksForDrops;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }
}
