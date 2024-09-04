package dev.turtywurty.tutorialmod.screen;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.screenhandler.ExampleEnergyGeneratorScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class ExampleEnergyGeneratorScreen extends HandledScreen<ExampleEnergyGeneratorScreenHandler> {
    private static final Identifier TEXTURE = TutorialMod.id("textures/gui/container/example_energy_generator.png");

    public ExampleEnergyGeneratorScreen(ExampleEnergyGeneratorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 170;
        this.backgroundHeight = 200;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(TEXTURE, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight);

        int energyBarSize = MathHelper.ceil(this.handler.getEnergyPercent() * 66);
        context.fill(this.x + 144, this.y + 10 + 66 - energyBarSize, this.x + 144 + 20, this.y + 10 + 66, 0xFFD4AF37);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);

        int energyBarSize = MathHelper.ceil(this.handler.getEnergyPercent() * 66);
        if (isPointWithinBounds(144, 10 + 66 - energyBarSize, 20, energyBarSize, mouseX, mouseY)) {
            context.drawTooltip(this.textRenderer, Text.literal(this.handler.getEnergy() + " / " + this.handler.getMaxEnergy() + " FE"), mouseX, mouseY);
        }
    }
}
