package dev.turtywurty.tutorialmod.screenhandler;

import dev.turtywurty.tutorialmod.entity.ExampleEntity;
import dev.turtywurty.tutorialmod.init.ScreenHandlerTypeInit;
import dev.turtywurty.tutorialmod.network.IntegerPayload;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class ExampleEntityScreenHandler extends ScreenHandler {
    private final SimpleInventory inventory;
    private final ExampleEntity entity;

    public ExampleEntityScreenHandler(int syncId, PlayerInventory playerInv, ExampleEntity entity) {
        super(ScreenHandlerTypeInit.EXAMPLE_ENTITY, syncId);
        this.inventory = entity.getInventory();
        this.entity = entity;

        checkSize(this.inventory, 18);
        this.inventory.onOpen(playerInv.player);

        if (!playerInv.player.getWorld().isClient) {
            playerInv.player.getWorld().sendEntityStatus(this.entity, ExampleEntity.OPEN_STATUS);
        }

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 6; column++) {
                addSlot(new Slot(this.inventory, column + row * 6, 62 + column * 18, 18 + row * 18));
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInv, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
            }
        }

        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInv, column, 8 + column * 18, 142));
        }
    }

    public ExampleEntityScreenHandler(int syncId, PlayerInventory playerInv, IntegerPayload payload) {
        this(syncId, playerInv, (ExampleEntity) playerInv.player.getWorld().getEntityById(payload.integer()));
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (slotIndex < this.inventory.size()) {
                if (!insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.entity.areInventoriesEqual(this.inventory)
                && this.inventory.canPlayerUse(player)
                && this.entity.isAlive()
                && player.canInteractWithEntity(this.entity, 4.0);
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.inventory.onClose(player);
        if (!player.getWorld().isClient) {
            player.getWorld().sendEntityStatus(this.entity, ExampleEntity.CLOSE_STATUS);
        }
    }

    public ExampleEntity getEntity() {
        return this.entity;
    }
}
