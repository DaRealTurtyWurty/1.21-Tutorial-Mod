package dev.turtywurty.tutorialmod.screenhandler;

import dev.turtywurty.tutorialmod.block.entity.ExampleRecipeBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockInit;
import dev.turtywurty.tutorialmod.init.ScreenHandlerTypeInit;
import dev.turtywurty.tutorialmod.network.BlockPosPayload;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.MathHelper;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class ExampleRecipeScreenHandler extends ScreenHandler {
    private final ExampleRecipeBlockEntity blockEntity;
    private final ScreenHandlerContext context;
    private final PropertyDelegate propertyDelegate;

    // Client Constructor
    public ExampleRecipeScreenHandler(int syncId, PlayerInventory playerInventory, BlockPosPayload payload) {
        this(syncId, playerInventory, (ExampleRecipeBlockEntity) playerInventory.player.getWorld().getBlockEntity(payload.pos()), new ArrayPropertyDelegate(2));
    }

    // Main Constructor - (Directly called from server)
    public ExampleRecipeScreenHandler(int syncId, PlayerInventory playerInventory, ExampleRecipeBlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ScreenHandlerTypeInit.EXAMPLE_RECIPE_SCREEN_HANDLER, syncId);

        this.blockEntity = blockEntity;
        this.context = ScreenHandlerContext.create(this.blockEntity.getWorld(), this.blockEntity.getPos());

        SimpleInventory inputInventory = this.blockEntity.getInputInventory();
        checkSize(inputInventory, 2);
        inputInventory.onOpen(playerInventory.player);

        SimpleInventory outputInventory = this.blockEntity.getOutputInventory();
        checkSize(outputInventory, 1);
        outputInventory.onOpen(playerInventory.player);

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        addBlockInventory(inputInventory, outputInventory);

        checkDataCount(propertyDelegate, 2);
        addProperties(propertyDelegate);
        this.propertyDelegate = propertyDelegate;
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

    private void addBlockInventory(SimpleInventory inputInventory, SimpleInventory outputInventory) {
        this.addSlot(new Slot(inputInventory, 0, 56, 35));
        this.addSlot(new Slot(inputInventory, 1, 79, 35));
        this.addSlot(new Slot(outputInventory, 0, 134, 35) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.blockEntity.getInputInventory().onClose(player);
        this.blockEntity.getOutputInventory().onClose(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = getSlot(slotIndex);
        if (slot != null && slot.hasStack()) {
            ItemStack inSlot = slot.getStack();
            newStack = inSlot.copy();

            if (slotIndex < 36) {
                if (!insertItem(inSlot, 36, this.slots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!insertItem(inSlot, 0, 36, false))
                return ItemStack.EMPTY;

            if (inSlot.isEmpty())
                slot.setStack(ItemStack.EMPTY);
            else slot.markDirty();
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, BlockInit.EXAMPLE_RECIPE_BLOCK);
    }

    public int getProgress() {
        return this.propertyDelegate.get(0);
    }

    public int getMaxProgress() {
        return this.propertyDelegate.get(1);
    }

    public float getProgressScaled(int size) {
        float progress = getProgress();
        float maxProgress = getMaxProgress();
        if(progress == 0 || maxProgress == 0) return 0;

        float percent = progress / maxProgress;
        return MathHelper.clamp(percent * size, 0, size);
    }

    public long getEnergy() {
        return this.blockEntity.getEnergyStorage().getAmount();
    }

    public long getMaxEnergy() {
        return this.blockEntity.getEnergyStorage().getCapacity();
    }

    public float getEnergyPercent() {
        SimpleEnergyStorage energyStorage = this.blockEntity.getEnergyStorage();
        long energy = energyStorage.getAmount();
        long maxEnergy = energyStorage.getCapacity();
        if (maxEnergy == 0 || energy == 0)
            return 0.0F;

        return MathHelper.clamp((float) energy / (float) maxEnergy, 0.0F, 1.0F);
    }
}
