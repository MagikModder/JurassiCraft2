package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityPachycephalosaurus extends EntityDinosaurDefensiveHerbivore
{
    public EntityPachycephalosaurus(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 7;
    }
}
