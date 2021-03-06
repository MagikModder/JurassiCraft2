package org.jurassicraft.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.container.ContainerEmbryoCalcificationMachine;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.item.ItemSyringe;
import org.jurassicraft.common.item.JCItemRegistry;

public class TileEmbryoCalcificationMachine extends TileMachineBase
{
    private int[] inputs = new int[] { 0, 1 };
    private int[] outputs = new int[] { 2 };

    private ItemStack[] slots = new ItemStack[3];

    @Override
    protected int getProcess(int slot)
    {
        return 0;
    }

    @Override
    protected boolean canProcess(int process)
    {
        ItemStack input = slots[0];
        ItemStack egg = slots[1];

        if (input != null && input.getItem() instanceof ItemSyringe && egg != null && egg.getItem() == Items.egg)
        {
            Dinosaur dino = JCEntityRegistry.getDinosaurById(input.getItemDamage());

            if (!dino.isMammal())
            {
                ItemStack output = new ItemStack(JCItemRegistry.egg, 1, input.getItemDamage());
                output.setTagCompound(input.getTagCompound());

                return hasOutputSlot(output);
            }
        }

        return false;
    }

    @Override
    protected void processItem(int process)
    {
        if (this.canProcess(process))
        {
            ItemStack output = new ItemStack(JCItemRegistry.egg, 1, slots[0].getItemDamage());
            output.setTagCompound(slots[0].getTagCompound());

            mergeStack(2, output);
            decreaseStackSize(0);
            decreaseStackSize(1);
        }
    }

    @Override
    protected int getMainInput(int process)
    {
        return 0;
    }

    @Override
    protected int getMainOutput(int process)
    {
        return 1;
    }

    @Override
    protected int getStackProcessTime(ItemStack stack)
    {
        return 200;
    }

    @Override
    protected int getProcessCount()
    {
        return 1;
    }

    @Override
    protected int[] getInputs()
    {
        return inputs;
    }

    @Override
    protected int[] getOutputs()
    {
        return outputs;
    }

    @Override
    protected ItemStack[] getSlots()
    {
        return slots;
    }

    @Override
    protected void setSlots(ItemStack[] slots)
    {
        this.slots = slots;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerEmbryoCalcificationMachine(playerInventory, this);
    }

    @Override
    public String getGuiID()
    {
        return JurassiCraft.MODID + ":embryo_calcification_machine";
    }

    public String getName()
    {
        return hasCustomName() ? customName : "container.embryo_calcification_machine";
    }

    public String getCommandSenderName() // Forge Version compatibility, keep both getName and getCommandSenderName
    {
        return getName();
    }
}
