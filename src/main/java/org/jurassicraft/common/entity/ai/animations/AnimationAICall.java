package org.jurassicraft.common.entity.ai.animations;

import net.ilexiconn.llibrary.common.animation.Animation;
import net.ilexiconn.llibrary.common.animation.IAnimated;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import net.timeless.animationapi.client.Animations;
import org.jurassicraft.common.entity.base.EntityDinosaur;

import java.util.List;

public class AnimationAICall extends EntityAIBase
{
    protected EntityDinosaur animatingEntity;

    public AnimationAICall(IAnimated entity)
    {
        super();
        animatingEntity = (EntityDinosaur) entity;
    }

    public List<Entity> getEntitiesWithinDistance(Entity e, double xz, double y)
    {
        return e.worldObj.getEntitiesWithinAABBExcludingEntity(e, AxisAlignedBB.fromBounds(e.posX - xz, e.posY - y, e.posZ - xz, e.posX + xz, e.posY + y, e.posZ + xz));
    }

    @Override
    public boolean shouldExecute()
    {
        if (animatingEntity.getRNG().nextDouble() < 0.003)
        {
            List<Entity> entities = getEntitiesWithinDistance(animatingEntity, 50, 10);

            for (Entity entity : entities)
            {
                if (animatingEntity.getClass().isInstance(entity))
                {
                    animatingEntity.playSound(animatingEntity.getCallSound(), animatingEntity.getSoundVolume() + 1.25F, animatingEntity.getSoundPitch());
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void startExecuting()
    {
        super.startExecuting();
        Animation.sendAnimationPacket(animatingEntity, Animations.CALLING.get());
        animatingEntity.getNavigator().clearPathEntity();
    }

    @Override
    public void resetTask()
    {
        super.resetTask();
        animatingEntity.currentAnim = null;
    }
}
