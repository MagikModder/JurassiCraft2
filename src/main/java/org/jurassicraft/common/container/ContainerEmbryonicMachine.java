package org.jurassicraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.jurassicraft.common.container.slot.SlotCustom;
import org.jurassicraft.common.container.slot.SlotPetriDish;
import org.jurassicraft.common.container.slot.SlotTestTube;
import org.jurassicraft.common.item.JCItemRegistry;
import org.jurassicraft.common.tileentity.TileEmbryonicMachine;

public class ContainerEmbryonicMachine extends Container
{
    private TileEmbryonicMachine embryonicMachine;

    public ContainerEmbryonicMachine(InventoryPlayer playerInventory, TileEntity tileEntity)
    {
        this.embryonicMachine = (TileEmbryonicMachine) tileEntity;
        this.addSlotToContainer(new SlotTestTube(embryonicMachine, 0, 24, 49));
        this.addSlotToContainer(new SlotPetriDish(embryonicMachine, 1, 50, 49));
        this.addSlotToContainer(new SlotCustom(embryonicMachine, 2, 50, 13, JCItemRegistry.empty_syringe));

        int i;

        for (i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                this.addSlotToContainer(new Slot(embryonicMachine, i + (j * 2) + 3, i * 18 + 119, j * 18 + 26));
            }
        }

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);
        if (!player.worldObj.isRemote)
        {
            embryonicMachine.closeInventory(player);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return embryonicMachine.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i)
    {
        return null;
    }
}
