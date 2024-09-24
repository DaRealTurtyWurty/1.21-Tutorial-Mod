package dev.turtywurty.tutorialmod.block.entity;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.init.BlockEntityTypeInit;
import dev.turtywurty.tutorialmod.network.BlockPosPayload;
import dev.turtywurty.tutorialmod.screenhandler.ExampleFluidTankScreenHandler;
import dev.turtywurty.tutorialmod.util.TickableBlockEntity;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class ExampleFluidTankBlockEntity extends BlockEntity implements TickableBlockEntity, ExtendedScreenHandlerFactory<BlockPosPayload> {
    public static final Text TITLE = Text.translatable("container." + TutorialMod.MOD_ID + ".example_fluid_tank");

    private final SimpleInventory inventory = new SimpleInventory(1) {
        @Override
        public void markDirty() {
            super.markDirty();
            update();
        }

        @Override
        public boolean isValid(int slot, ItemStack stack) {
            return ExampleFluidTankBlockEntity.this.isValid(stack, slot);
        }
    };

    private final InventoryStorage inventoryStorage = InventoryStorage.of(inventory, null);

    private final ContainerItemContext fluidItemContext = ContainerItemContext.ofSingleSlot(this.inventoryStorage.getSlot(0));

    private final SingleFluidStorage fluidStorage = SingleFluidStorage.withFixedCapacity(
            FluidConstants.BUCKET * 100,
            this::update);

    public ExampleFluidTankBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypeInit.EXAMPLE_FLUID_TANK, pos, state);
    }

    @Override
    public void tick() {
        if(this.world == null || this.world.isClient)
            return;

        if(this.inventory.isEmpty() || !isValid(this.inventory.getStack(0), 0))
            return;

        Storage<FluidVariant> itemFluidStorage = this.fluidItemContext.find(FluidStorage.ITEM);
        if(itemFluidStorage == null)
            return;

        FluidVariant match = null;
        for (StorageView<FluidVariant> storageView : itemFluidStorage.nonEmptyViews()) {
            if(storageView.isResourceBlank())
                continue;

            try(Transaction transaction = Transaction.openOuter()) {
                if(this.fluidStorage.insert(storageView.getResource(), FluidConstants.BUCKET, transaction) > 0) {
                    match = storageView.getResource();
                    break;
                }
            }
        }

        if(match == null || match.isBlank())
            return;

        try(Transaction transaction = Transaction.openOuter()) {
            long inserted = this.fluidStorage.insert(match, FluidConstants.BUCKET, transaction);
            long extracted = itemFluidStorage.extract(match, inserted, transaction);
            if(extracted < FluidConstants.BUCKET) {
                long extra = FluidConstants.BUCKET - extracted;
                this.fluidStorage.extract(match, extra, transaction); // Take any extra fluid
            }

            transaction.commit();
        }

        TutorialMod.LOGGER.info("Fluid: {}, Amount: {}", this.fluidStorage.getResource().getRegistryEntry().getIdAsString(), this.fluidStorage.getAmount());
    }

    @Override
    public BlockPosPayload getScreenOpeningData(ServerPlayerEntity player) {
        return new BlockPosPayload(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return TITLE;
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ExampleFluidTankScreenHandler(syncId, playerInventory, this);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);

        if(nbt.contains("Inventory", NbtElement.COMPOUND_TYPE)) {
            Inventories.readNbt(nbt.getCompound("Inventory"), this.inventory.getHeldStacks(), registryLookup);
        }

        if(nbt.contains("FluidTank", NbtElement.COMPOUND_TYPE)) {
            this.fluidStorage.readNbt(nbt.getCompound("FluidTank"), registryLookup);
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);

        var inventoryNbt = new NbtCompound();
        Inventories.writeNbt(inventoryNbt, this.inventory.getHeldStacks(), registryLookup);
        nbt.put("Inventory", inventoryNbt);

        var fluidNbt = new NbtCompound();
        this.fluidStorage.writeNbt(fluidNbt, registryLookup);
        nbt.put("FluidTank", fluidNbt);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        var nbt = super.toInitialChunkDataNbt(registryLookup);
        writeNbt(nbt, registryLookup);
        return nbt;
    }

    private void update() {
        markDirty();
        if(world != null)
            world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
    }

    public boolean isValid(ItemStack stack, int slot) {
        if(stack.isEmpty()) return true;
        if(slot != 0) return false;

        Storage<FluidVariant> storage = ContainerItemContext.withConstant(stack).find(FluidStorage.ITEM);
        return storage != null;
    }

    public InventoryStorage getInventoryProvider(Direction direction) {
        return inventoryStorage;
    }

    public SingleFluidStorage getFluidTankProvider(Direction direction) {
        return this.fluidStorage;
    }

    public SimpleInventory getInventory() {
        return this.inventory;
    }
}
