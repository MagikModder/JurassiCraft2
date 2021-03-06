package org.jurassicraft.common.item.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import org.jurassicraft.common.block.BlockFossil;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.lang.AdvLang;
import org.jurassicraft.common.period.EnumTimePeriod;

public class ItemFossilBlock extends ItemBlock
{
    public ItemFossilBlock(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("fossil_block");
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        Dinosaur dinosaur = ((BlockFossil) block).getDinosaur(stack.getMetadata());

        if (dinosaur == null)
        {
            dinosaur = JCEntityRegistry.getDinosaurById(0);
        }

        return new AdvLang("tile.fossil_block.name").withProperty("period", "period." + dinosaur.getPeriod().getName() + ".name").build();
    }

    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        EnumTimePeriod timePeriod = JCEntityRegistry.getDinosaurById(stack.getMetadata()).getPeriod();
        return super.getUnlocalizedName() + "." + timePeriod.getName();
    }
}
