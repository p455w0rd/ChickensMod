package com.setycz.chickens.jei.breeding;

import com.setycz.chickens.ChickensMod;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * Created by setyc on 21.02.2016.
 */
public class BreedingRecipeCategory implements IRecipeCategory {
    public static final String UID = "chickens.Breeding";
    private final String title;
    private final IDrawableStatic background;
    private final IDrawableAnimated arrow;

    public BreedingRecipeCategory(IGuiHelper guiHelper) {
        title = Translator.translateToLocal("gui.breeding");

        ResourceLocation location = new ResourceLocation(ChickensMod.MODID, "textures/gui/breeding.png");
        background = guiHelper.createDrawable(location, 0, 0, 82, 54);


        IDrawableStatic arrowDrawable = guiHelper.createDrawable(location, 82, 0, 7, 7);
        arrow = guiHelper.createAnimatedDrawable(arrowDrawable, 200, IDrawableAnimated.StartDirection.BOTTOM, false);
    }

    @Nonnull
    @Override
    public String getUid() {
        return UID;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return title;
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {

    }

    @Override
    public void drawAnimations(Minecraft minecraft) {
        arrow.draw(minecraft, 37, 5);
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        int parent1Slot = 0;
        guiItemStacks.init(parent1Slot, true, 10, 15);
        guiItemStacks.setFromRecipe(parent1Slot, recipeWrapper.getInputs().get(0));

        int parent2Slot = 1;
        guiItemStacks.init(parent2Slot, true, 53, 15);
        guiItemStacks.setFromRecipe(parent2Slot, recipeWrapper.getInputs().get(1));

        int childrenSlot = 2;
        guiItemStacks.init(childrenSlot, false, 33, 30);
        guiItemStacks.setFromRecipe(childrenSlot, recipeWrapper.getOutputs());
    }
}
