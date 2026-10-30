package com.cubuk.mod.handler;

import com.cubuk.mod.CubukMod;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class PacketHandler {

    @SubscribeEvent
    public void onClientPacket(FMLNetworkEvent.ClientCustomPacketEvent event) {
        // Kanal adı kontrolü
        if (!event.packet.channel().equals("CubukMod")) return;

        try {
            ByteBuf buf = event.packet.payload();
            int seconds = buf.readInt();

            CubukMod.maxSeconds = seconds;
            CubukMod.cooldownEnd = System.currentTimeMillis() + (seconds * 1000L);
            CubukMod.onCooldown = true;
        } catch (Exception ignored) {
            // Bozuk paket gelirse yoksay
        }
    }
}
