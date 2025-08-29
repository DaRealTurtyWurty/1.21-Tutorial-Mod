package dev.turtywurty.tutorialmod.screen;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.screenhandler.ExampleEntityScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

// entity area at 8,18 (52x52)
public class ExampleEntityScreen extends HandledScreen<ExampleEntityScreenHandler> {
    private static final Identifier TEXTURE_LOCATION = TutorialMod.id("textures/gui/container/example_entity.png");

    public ExampleEntityScreen(ExampleEntityScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(TEXTURE_LOCATION, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
