package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.animationapi.client.Animations;
import org.jurassicraft.common.entity.ai.animations.JCNonAutoAnimBase;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityHypsilophodon extends EntityDinosaurDefensiveHerbivore // implements IEntityAICreature, IHerbivore
{
    private static final String[] hurtSounds = new String[] { "hypsilophodon_hurt_1", "hypsilophodon_hurt_2" };
    private static final String[] livingSounds = new String[] { "hypsilophodon_living_1", "hypsilophodon_living_2", "hypsilophodon_living_3", "hypsilophodon_living_4" };

    public EntityHypsilophodon(World world)
    {
        super(world);
        tasks.addTask(2, new JCNonAutoAnimBase(this, 35, Animations.SCRATCHING.get(), 60)); // Scratch Animation
    }

    @Override
    public int getTailBoxCount()
    {
        return 6;
    }

    @Override
    public String getLivingSound()
    {
        return randomSound(livingSounds);
    }

    @Override
    public String getHurtSound()
    {
        return randomSound(hurtSounds);
    }

    @Override
    public String getDeathSound()
    {
        return randomSound(hurtSounds);
    }
}
