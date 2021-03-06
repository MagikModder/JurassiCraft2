package org.jurassicraft.common.plant;

import net.minecraft.block.Block;
import org.jurassicraft.common.block.JCBlockRegistry;

public class PlantSmallChainFern extends Plant
{
    @Override
    public EnumPlantType getPlantType()
    {
        return EnumPlantType.FERN;
    }

    @Override
    public String getName()
    {
        return "Small Chain Fern";
    }

    @Override
    public Block getBlock()
    {
        return JCBlockRegistry.small_chain_fern;
    }
}
