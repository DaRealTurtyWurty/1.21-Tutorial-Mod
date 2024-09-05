package dev.turtywurty.tutorialmod.screenhandler;

import dev.turtywurty.tutorialmod.block.entity.ExampleInventoryBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockInit;
import dev.turtywurty.tutorialmod.init.ScreenHandlerTypeInit;
import dev.turtywurty.tutorialmod.network.BlockPosPayload;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;

public class ExampleInventoryScreenHandler extends ScreenHandler {
    private final ExampleInventoryBlockEntity blockEntity;
    private final ScreenHandlerContext context;

    // Client Constructor
    public ExampleInventoryScreenHandler(int syncId, PlayerInventory playerInventory, BlockPosPayload payload) {
        this(syncId, playerInventory, (ExampleInventoryBlockEntity) playerInventory.player.getWorld().getBlockEntity(payload.pos()));
    }

    // Main Constructor - (Directly called from server)
    public ExampleInventoryScreenHandler(int syncId, PlayerInventory playerInventory, ExampleInventoryBlockEntity blockEntity) {
        super(ScreenHandlerTypeInit.EXAMPLE_INVENTORY_SCREEN_HANDLER, syncId);

        this.blockEntity = blockEntity;
        this.context = ScreenHandlerContext.create(this.blockEntity.getWorld(), this.blockEntity.getPos());

        SimpleInventory inventory = this.blockEntity.getInventory();
        checkSize(inventory, 36);
        inventory.onOpen(playerInventory.player);

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        addBlockInventory(inventory);
    }

    private void addPlayerInventory(PlayerInventory playerInv) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInv, 9 + (column + (row * 9)), 8 + (column * 18), 102 + (row * 18)));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInv) {
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInv, column, 8 + (column * 18), 160));
        }
    }

    private void addBlockInventory(SimpleInventory inventory) {
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(inventory, column + (row * 9), 8 + (column * 18), 18 + (row * 18)));
            }
        }
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.blockEntity.getInventory().onClose(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = getSlot(slotIndex);
        if(slot != null && slot.hasStack()) {
            ItemStack inSlot = slot.getStack();
            newStack = inSlot.copy();

            if(slotIndex < 36) {
                if(!insertItem(inSlot, 36, this.slots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!insertItem(inSlot, 0, 36, false))
                return ItemStack.EMPTY;

            if(inSlot.isEmpty())
                slot.setStack(ItemStack.EMPTY);
            else slot.markDirty();
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, BlockInit.EXAMPLE_INVENTORY_BLOCK);
    }
}
