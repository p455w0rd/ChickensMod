package com.setycz.chickens;

import com.setycz.chickens.chicken.EntityChickensChicken;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by setyc on 21.03.2016.
 */
public class ChickenTeachHanhler {

    @SubscribeEvent
    public void handleInteraction(EntityInteractEvent event) {
        ItemStack item = event.entityPlayer.getCurrentEquippedItem();
        if (item == null || item.getItem() != Items.book) {
            return;
        }
        if (!(event.target.getClass() == EntityChicken.class)) {
            return;
        }

        World worldObj = event.entityPlayer.worldObj;
        if (worldObj.isRemote) {
            return;
        }

        ChickensRegistryItem smartChickenDescription = ChickensRegistry.getSmartChicken();
        if (smartChickenDescription == null || !smartChickenDescription.isEnabled()) {
            return;
        }

        EntityChicken chicken = (EntityChicken) event.target;
        EntityChickensChicken smartChicken = convertToSmart(chicken, worldObj, smartChickenDescription);

        worldObj.removeEntity(chicken);
        worldObj.spawnEntityInWorld(smartChicken);
        smartChicken.spawnExplosionParticle();

        event.setCanceled(true);
    }

    private EntityChickensChicken convertToSmart(EntityChicken chicken, World worldObj, ChickensRegistryItem smartChickenDescription) {
        EntityChickensChicken smartChicken = new EntityChickensChicken(worldObj);
        smartChicken.setPositionAndRotation(chicken.posX, chicken.posY, chicken.posZ, chicken.rotationYaw, chicken.rotationPitch);
        smartChicken.onInitialSpawn(worldObj.getDifficultyForLocation(chicken.getPosition()), null);
        smartChicken.setChickenType(smartChickenDescription.getId());
        if (chicken.hasCustomName()) {
            smartChicken.setCustomNameTag(chicken.getCustomNameTag());
        }
        smartChicken.setGrowingAge(chicken.getGrowingAge());
        return smartChicken;
    }
}
