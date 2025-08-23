package dev.turtywurty.tutorialmod.util;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;

public class OutputSimpleInventory extends SimpleInventory {
    public OutputSimpleInventory(int size) {
        super(size);
    }

    public OutputSimpleInventory(ItemStack... items) {
        super(items);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    public boolean canAddStack(ItemStack stack) {
        return super.canInsert(stack);
    }
}
