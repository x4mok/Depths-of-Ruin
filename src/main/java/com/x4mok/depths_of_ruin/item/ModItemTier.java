package com.x4mok.depths_of_ruin.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum ModItemTier implements IItemTier {

    DRAGON(5, 2850, 10.5f, 5.0f, 15, () -> Ingredient.of(ModItems.DRAGON_SCALE.get()));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyValue<Ingredient> repairIngredient;

    ModItemTier(int level, int uses, float speed,
                float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }


    @Override
    public int getUses() {
        return uses; // 300
    }

    @Override
    public float getSpeed() {
        return speed; // 5.0f
    }

    @Override
    public float getAttackDamageBonus() {
        return damage; // 2.0f
    }

    @Override
    public int getLevel() {
        return level; // 2
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue; // 14
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get(); //copper ingot
    }
}
