package com.cubuk.mod.render;

import com.cubuk.mod.CubukMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.Color;

public class HudRenderer {

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.ALL) return;
        if (!CubukMod.onCooldown) return;

        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc);
        int width = sr.getScaledWidth();
        int height = sr.getScaledHeight();

        long remaining = CubukMod.cooldownEnd - System.currentTimeMillis();
        if (remaining <= 0) { CubukMod.onCooldown = false; return; }

        float progress = 1.0f - (remaining / (float) (CubukMod.maxSeconds * 1000L));
        int barWidth = 120;
        int barHeight = 8;
        int x = (width - barWidth) / 2;
        int y = height - 55;

        // Arka plan
        Gui.drawRect(x - 2, y - 2, x + barWidth + 2, y + barHeight + 2, new Color(0, 0, 0, 180).getRGB());
        // Dolum
        int fillWidth = (int) (barWidth * progress);
        Gui.drawRect(x, y, x + fillWidth, y + barHeight, 0xFFFF6600);
        // Çerçeve
        drawBorder(x - 2, y - 2, barWidth + 4, barHeight + 4, 0xFFAA00);

        // Saniye yazısı
        int seconds = (int) Math.ceil(remaining / 1000.0);
        String text = "§6Ateş Çubuğu: §e" + seconds + "s";
        mc.fontRendererObj.drawStringWithShadow(text, x + barWidth / 2 - mc.fontRendererObj.getStringWidth(text) / 2, y - 12, 0xFFFFFF);
    }

    private void drawBorder(int x, int y, int w, int h, int color) {
        Gui.drawRect(x, y, x + w, y + 1, color);
        Gui.drawRect(x, y + h - 1, x + w, y + h, color);
        Gui.drawRect(x, y, x + 1, y + h, color);
        Gui.drawRect(x + w - 1, y, x + w, y + h, color);
    }
}
