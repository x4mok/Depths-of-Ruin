package com.x4mok.depths_of_ruin.item;

import com.x4mok.depths_of_ruin.Depths_of_ruin;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ModArmourMaterial implements IArmorMaterial {

    DRAGON("dragon", 45, new int[]{4,7,9,4}, 25, SoundEvents.ARMOR_EQUIP_TURTLE, 3.0f, 0.2f, () -> Ingredient.of(ModItems.DRAGON_SCALE.get()));

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairIngredient;

    private ModArmourMaterial(String pName, int pDurabilityMultiplier, int[] pSlotProtections,
                              int pEnchantmentValue, SoundEvent pSound, float pToughness,
                              float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.slotProtections = pSlotProtections;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = new LazyValue<>(pRepairIngredient);
    }



    public int getDurabilityForSlot(EquipmentSlotType pSlot) {
        return HEALTH_PER_SLOT[pSlot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlotType pSlot) {
        return this.slotProtections[pSlot.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return Depths_of_ruin.MODID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }


    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
