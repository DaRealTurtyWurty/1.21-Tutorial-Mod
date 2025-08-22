package dev.turtywurty.tutorialmod.block.entity;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.init.BlockEntityTypeInit;
import dev.turtywurty.tutorialmod.network.BlockPosPayload;
import dev.turtywurty.tutorialmod.screenhandler.ExampleRecipeScreenHandler;
import dev.turtywurty.tutorialmod.util.TickableBlockEntity;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
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
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class ExampleRecipeBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPosPayload>, TickableBlockEntity {
    public static final Text TITLE = Text.translatable("container." + TutorialMod.MOD_ID + ".example_recipe");

    private final SimpleInventory inputInventory = new SimpleInventory(2) {
        @Override
        public void markDirty() {
            super.markDirty();
            update();
        }
    };

    private final SimpleInventory outputInventory = new SimpleInventory(1) {
        @Override
        public void markDirty() {
            super.markDirty();
            update();
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return false;
        }
    };

    private final InventoryStorage inputInventoryStorage = InventoryStorage.of(inputInventory, Direction.UP);
    private final InventoryStorage outputInventoryStorage = InventoryStorage.of(outputInventory, Direction.DOWN);

    private final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(10_000, 100, 0) {
        @Override
        protected void onFinalCommit() {
            super.onFinalCommit();
            update();
        }
    };

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> progress;
                case 1 -> maxProgress;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> progress = value;
                case 1 -> maxProgress = value;
            }
        }

        @Override
        public int size() {
            return 2;
        }
    };

    private int progress = 0, maxProgress = 0;

    public ExampleRecipeBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypeInit.EXAMPLE_RECIPE_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void tick() {
        if(this.world == null || this.world.isClient) return;

        this.maxProgress = 100;
        if(this.progress++ >= this.maxProgress) {
            this.progress = 0;
            ((ServerWorld)this.world).spawnParticles(
                    ParticleTypes.END_ROD,
                    this.pos.getX() + 0.5,
                    this.pos.getY() + 1.0,
                    this.pos.getZ() + 0.5,
                    10,
                    0.25,
                    0.25,
                    0.25,
                    0.02
            );
        }

        update();
    }

    @Override
    public BlockPosPayload getScreenOpeningData(ServerPlayerEntity player) {
        return new BlockPosPayload(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return TITLE;
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ExampleRecipeScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        if(nbt.contains("InputInventory", NbtElement.COMPOUND_TYPE))
            Inventories.readNbt(nbt.getCompound("InputInventory"), this.inputInventory.getHeldStacks(), registryLookup);
        if (nbt.contains("OutputInventory", NbtElement.COMPOUND_TYPE))
            Inventories.readNbt(nbt.getCompound("OutputInventory"), this.outputInventory.getHeldStacks(), registryLookup);

        if (nbt.contains("Energy", NbtElement.LONG_TYPE)) {
            this.energyStorage.amount = nbt.getLong("Energy");
        }

        this.progress = nbt.getInt("Progress");
        this.maxProgress = nbt.getInt("MaxProgress");
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.put("InputInventory", Inventories.writeNbt(new NbtCompound(), this.inputInventory.getHeldStacks(), registryLookup));
        nbt.put("OutputInventory", Inventories.writeNbt(new NbtCompound(), this.outputInventory.getHeldStacks(), registryLookup));

        nbt.putLong("Energy", this.energyStorage.amount);
        nbt.putInt("Progress", this.progress);
        nbt.putInt("MaxProgress", this.maxProgress);
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

    public InventoryStorage getInventoryProvider(Direction direction) {
        return switch (direction) {
            case UP -> inputInventoryStorage;
            case DOWN -> outputInventoryStorage;
            case null, default -> null;
        };
    }

    public SimpleInventory getInputInventory() {
        return inputInventory;
    }

    public SimpleInventory getOutputInventory() {
        return outputInventory;
    }

    public EnergyStorage getEnergyProvider(Direction direction) {
        return this.energyStorage;
    }

    public SimpleEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    public int getProgress() {
        return progress;
    }

    public int getMaxProgress() {
        return maxProgress;
    }
}
