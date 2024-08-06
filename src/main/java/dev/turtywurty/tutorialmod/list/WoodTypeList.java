package dev.turtywurty.tutorialmod.list;

import dev.turtywurty.tutorialmod.TutorialMod;
import net.minecraft.block.WoodType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

public class WoodTypeList {
    public static final WoodType EXAMPLE = new WoodType(
            TutorialMod.id("example").toString(),
            BlockSetTypeList.EXAMPLE,
            BlockSoundGroup.WOOD,
            BlockSoundGroup.HANGING_SIGN,
            SoundEvents.BLOCK_FENCE_GATE_CLOSE,
            SoundEvents.BLOCK_FENCE_GATE_OPEN
    );
}
