package com.cubuk.mod;

import com.cubuk.mod.handler.PacketHandler;
import com.cubuk.mod.render.HudRenderer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = CubukMod.MODID, version = CubukMod.VERSION, clientSideOnly = true)
public class CubukMod {
    public static final String MODID = "cubukmod";
    public static final String VERSION = "1.0";

    public static boolean onCooldown = false;
    public static long cooldownEnd = 0L;
    public static int maxSeconds = 3;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new HudRenderer());
            MinecraftForge.EVENT_BUS.register(new PacketHandler());
        }
    }
}
