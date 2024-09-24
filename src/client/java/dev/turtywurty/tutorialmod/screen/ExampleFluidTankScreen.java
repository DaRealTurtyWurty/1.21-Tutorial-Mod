package dev.turtywurty.tutorialmod.screen;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.screenhandler.ExampleFluidTankScreenHandler;
import dev.turtywurty.tutorialmod.screenhandler.ExampleInventoryScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ExampleFluidTankScreen extends HandledScreen<ExampleFluidTankScreenHandler> {
    private static final Identifier TEXTURE = TutorialMod.id("textures/gui/container/example_fluid_tank.png");

    public ExampleFluidTankScreen(ExampleFluidTankScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(TEXTURE, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight);

        // TODO: Draw fluid tank
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);

        // TODO: Draw fluid tooltip
    }
}
