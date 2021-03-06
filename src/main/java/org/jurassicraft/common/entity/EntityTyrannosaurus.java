package org.jurassicraft.common.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.animationapi.client.Animations;
import org.jurassicraft.common.animation.ControlledParam;
import org.jurassicraft.common.entity.ai.animations.JCNonAutoAnimBase;
import org.jurassicraft.common.entity.ai.animations.JCNonAutoAnimSoundBase;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;

import java.util.Random;

public class EntityTyrannosaurus extends EntityDinosaurAggressive // , IEntityAICreature,
// ICarnivore
{
    private static final String[] hurtSounds = new String[] { "tyrannosaurus_hurt_1", "tyrannosaurus_hurt_2" };
    private static final String[] deathSounds = new String[] { "tyrannosaurus_death_1" };
    private static final String[] roarSounds = new String[] { "tyrannosaurus_roar_1" };
    private static final String[] breathSounds = new String[] { "tyrannosaurus_breath_1" };

    private static final Class[] targets = { EntityCompsognathus.class, EntityAnkylosaurus.class, EntityPlayer.class, EntityDilophosaurus.class, EntityDimorphodon.class, EntityDodo.class, EntityLeaellynasaura.class, EntityLudodactylus.class, EntityHypsilophodon.class, EntityGallimimus.class, EntitySegisaurus.class, EntityProtoceratops.class, EntityParasaurolophus.class, EntityOthnielia.class, EntityMicroceratus.class, EntityTriceratops.class, EntityStegosaurus.class, EntityBrachiosaurus.class, EntityApatosaurus.class, EntityRugops.class, EntityHerrerasaurus.class, EntityVelociraptor.class, EntitySpinosaurus.class, EntityAchillobator.class, EntityCarnotaurus.class, EntityTherizinosaurus.class, EntityIndominus.class };

    private int stepCount = 0;

    public ControlledParam roarCount = new ControlledParam(0F, 0F, 0.5F, 0F);
    public ControlledParam roarTiltDegree = new ControlledParam(0F, 0F, 1F, 0F);

    public EntityTyrannosaurus(World world)
    {
        super(world);

        tasks.addTask(2, new JCNonAutoAnimSoundBase(this, 75, Animations.IDLE.get(), 750, "jurassicraft:" + roarSounds[0], 1.5F));
        tasks.addTask(2, new JCNonAutoAnimBase(this, 75, Animations.INJURED.get(), 750));

        for (Class target : targets)
        {
            this.addAIForAttackTargets(target, new Random().nextInt(3) + 1);
        }
    }

    @Override
    public int getTailBoxCount()
    {
        return 6;
    }

    @Override
    public String getLivingSound()
    {
        return randomSound(roarSounds);
    }

    @Override
    public String getHurtSound()
    {
        return randomSound(hurtSounds);
    }

    @Override
    public String getDeathSound()
    {
        return randomSound(deathSounds);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        this.roarCount.update();
        this.roarTiltDegree.update();

        if (this.ticksExisted % 62 == 0)
        {
            this.playSound(randomSound(breathSounds), this.getSoundVolume(), this.getSoundPitch());
        }

        /** Step Sound */
        if (this.moveForward > 0 && this.stepCount <= 0)
        {
            this.playSound("jurassicraft:stomp", (float) transitionFromAge(0.1F, 1.0F), this.getSoundPitch());
            stepCount = 65;
        }

        this.stepCount -= this.moveForward * 9.5;
    }
}
