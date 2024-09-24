package dev.turtywurty.tutorialmod.screenhandler;

import dev.turtywurty.tutorialmod.block.entity.ExampleFluidTankBlockEntity;
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

public class ExampleFluidTankScreenHandler extends ScreenHandler {
    private final ExampleFluidTankBlockEntity blockEntity;
    private final ScreenHandlerContext context;

    @SuppressWarnings("DataFlowIssue")
    public ExampleFluidTankScreenHandler(int syncId, PlayerInventory playerInventory, BlockPosPayload payload) {
        this(syncId, playerInventory, (ExampleFluidTankBlockEntity) playerInventory.player.getWorld().getBlockEntity(payload.pos()));
    }

    public ExampleFluidTankScreenHandler(int syncId, PlayerInventory playerInventory, ExampleFluidTankBlockEntity blockEntity) {
        super(ScreenHandlerTypeInit.EXAMPLE_FLUID_TANK, syncId);

        this.blockEntity = blockEntity;
        this.context = ScreenHandlerContext.create(blockEntity.getWorld(), blockEntity.getPos());

        SimpleInventory inventory = blockEntity.getInventory();
        checkSize(inventory, 1);
        inventory.onOpen(playerInventory.player);

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        addSlot(new Slot(inventory, 0, 80, 33) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return inventory.isValid(0, stack);
            }
        });
    }

    private void addPlayerInventory(PlayerInventory playerInv) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInv, 9 + (column + (row * 9)), 8 + (column * 18), 84 + (row * 18)));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInv) {
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInv, column, 8 + (column * 18), 142));
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
        if (slot != null && slot.hasStack()) {
            ItemStack inSlot = slot.getStack();
            newStack = inSlot.copy();

            if (slotIndex == 0) {
                if (!insertItem(inSlot, 0, this.slots.size(), false))
                    return ItemStack.EMPTY;
            } else if (!insertItem(inSlot, 0, 0, true))
                return ItemStack.EMPTY;

            if (inSlot.isEmpty())
                slot.setStack(ItemStack.EMPTY);
            else
                slot.markDirty();
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, BlockInit.EXAMPLE_FLUID_TANK);
    }

    public ExampleFluidTankBlockEntity getBlockEntity() {
        return this.blockEntity;
    }
}
