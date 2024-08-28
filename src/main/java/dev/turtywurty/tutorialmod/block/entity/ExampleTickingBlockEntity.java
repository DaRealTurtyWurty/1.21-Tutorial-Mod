package dev.turtywurty.tutorialmod.block.entity;

import dev.turtywurty.tutorialmod.init.BlockEntityTypeInit;
import dev.turtywurty.tutorialmod.util.TickableBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

public class ExampleTickingBlockEntity extends BlockEntity implements TickableBlockEntity {
    private int ticks = 0;
    private BlockPos miningPos = this.pos.down();

    public ExampleTickingBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypeInit.EXAMPLE_TICKING_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void tick() {
        if(this.world == null || this.world.isClient)
            return;

        if(this.ticks++ % 20 == 0) {
            if(this.miningPos.getY() <= this.world.getBottomY()) {
                this.miningPos = this.pos.down();
            }

            BlockState state = this.world.getBlockState(this.miningPos);
            if(state.isAir() || state.getHardness(this.world, this.miningPos) < 0) {
                this.miningPos = this.miningPos.down();
                return;
            }

            List<ItemStack> drops = new ArrayList<>(state.getDroppedStacks(
                    new LootContextParameterSet.Builder((ServerWorld) this.world)
                            .add(LootContextParameters.TOOL, Items.DIAMOND_PICKAXE.getDefaultStack())
                            .add(LootContextParameters.ORIGIN, this.miningPos.toCenterPos())
                            .addOptional(LootContextParameters.BLOCK_ENTITY, this)));

            this.world.breakBlock(this.miningPos, false);

            Storage<ItemVariant> aboveStorage = findItemStorage((ServerWorld) this.world, this.pos.up());
            if(aboveStorage != null && aboveStorage.supportsInsertion()) {
                insertDrops(drops, aboveStorage);
            }

            if(!drops.isEmpty()) {
                spawnDrops(drops, (ServerWorld) this.world, this.pos);
            }

            this.miningPos = this.miningPos.down();
        }
    }

    private static Storage<ItemVariant> findItemStorage(ServerWorld world, BlockPos pos) {
        return ItemStorage.SIDED.find(world, pos, Direction.DOWN);
    }

    private static void insertDrops(List<ItemStack> drops, Storage<ItemVariant> aboveStorage) {
        for (ItemStack drop : drops) {
            try(Transaction transaction = Transaction.openOuter()) {
                long inserted = aboveStorage.insert(ItemVariant.of(drop), drop.getCount(), transaction);
                if(inserted > 0) {
                    drop.decrement((int) inserted);
                    transaction.commit();
                }
            }
        }

        drops.removeIf(ItemStack::isEmpty);
    }

    private static void spawnDrops(List<ItemStack> drops, ServerWorld world, BlockPos pos) {
        for (ItemStack drop : drops) {
            ItemScatterer.spawn(world, pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 1.0D, drop);
        }
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.ticks = nbt.getInt("Ticks");
        this.miningPos = BlockPos.fromLong(nbt.getLong("MiningPos"));
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putInt("Ticks", this.ticks);
        nbt.putLong("MiningPos", this.miningPos.asLong());
    }

    public BlockPos getMiningPos() {
        return this.miningPos;
    }
}
