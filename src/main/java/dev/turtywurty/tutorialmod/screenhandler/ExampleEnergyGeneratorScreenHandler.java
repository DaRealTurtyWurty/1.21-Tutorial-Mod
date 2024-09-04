package dev.turtywurty.tutorialmod.screenhandler;

import dev.turtywurty.tutorialmod.block.entity.ExampleEnergyGeneratorBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockInit;
import dev.turtywurty.tutorialmod.init.ScreenHandlerTypeInit;
import dev.turtywurty.tutorialmod.network.BlockPosPayload;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.MathHelper;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class ExampleEnergyGeneratorScreenHandler extends ScreenHandler {
    private final ExampleEnergyGeneratorBlockEntity blockEntity;
    private final ScreenHandlerContext context;

    // Client Constructor
    public ExampleEnergyGeneratorScreenHandler(int syncId, PlayerInventory playerInventory, BlockPosPayload payload) {
        this(syncId, playerInventory, (ExampleEnergyGeneratorBlockEntity) playerInventory.player.getWorld().getBlockEntity(payload.pos()));
    }

    // Main Constructor - (Directly called from server)
    public ExampleEnergyGeneratorScreenHandler(int syncId, PlayerInventory playerInventory, ExampleEnergyGeneratorBlockEntity blockEntity) {
        super(ScreenHandlerTypeInit.EXAMPLE_ENERGY_GENERATOR, syncId);

        this.blockEntity = blockEntity;
        this.context = ScreenHandlerContext.create(this.blockEntity.getWorld(), this.blockEntity.getPos());

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
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
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, BlockInit.EXAMPLE_ENERGY_GENERATOR_BLOCK);
    }

    public ExampleEnergyGeneratorBlockEntity getBlockEntity() {
        return this.blockEntity;
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
