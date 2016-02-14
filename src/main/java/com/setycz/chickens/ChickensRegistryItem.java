package com.setycz.chickens;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * Created by setyc on 12.02.2016.
 */
public class ChickensRegistryItem {
    private final String entityName;
    private final ItemStack layItem;
    private final int bgColor;
    private final int fgColor;
    private final ResourceLocation texture;
    private final ChickensRegistryItem parent1;
    private final ChickensRegistryItem parent2;

    public ChickensRegistryItem(String entityName, ResourceLocation texture, ItemStack layItem, int bgColor, int fgColor) {
        this(entityName, texture, layItem, bgColor, fgColor, null, null);
    }

    public ChickensRegistryItem(String entityName, ResourceLocation texture, ItemStack layItem, int bgColor, int fgColor, ChickensRegistryItem parent1, ChickensRegistryItem parent2) {
        this.entityName = entityName;
        this.layItem = layItem;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.texture = texture;
        this.parent1 = parent1;
        this.parent2 = parent2;
    }

    public String getEntityName() {
        return entityName;
    }

    public int getBgColor() {
        return bgColor;
    }

    public int getFgColor() {
        return fgColor;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public ItemStack createLayItem() {
        return layItem.copy();
    }

    public int getTier() {
        if (parent1 == null || parent2 == null) {
            return 0;
        }
        return Math.max(parent1.getTier(), parent2.getTier()) + 1;
    }

    public boolean isChildOf(ChickensRegistryItem parent1, ChickensRegistryItem parent2) {
        return this.parent1 == parent1 && this.parent2 == parent2 || this.parent1 == parent2 && this.parent2 == parent1;
    }

    public boolean isDye() {
        return layItem.getItem() == Items.dye;
    }

    public boolean isDye(int dyeMetadata) {
        return layItem.getItem() == Items.dye && layItem.getMetadata() == dyeMetadata;
    }

    public int getDyeMetadata() {
        return layItem.getMetadata();
    }

    public boolean canSpawn() {
        return getTier() == 0 && !isDye();
    }
}