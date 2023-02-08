package cc.cfrench.shopsleuth.status;

import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class StatusListener {
    private final Status status;
    private boolean someChallengeActive = false;

    public StatusListener(final Status status) {
        this.status = status;
    }

    @SubscribeEvent
    public void onWorldLoad_SetChallengesInactive(final WorldEvent.Load event) {
        this.status.setChallengeActive(false);
        this.someChallengeActive = false;
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatReceived_UpdateChallengeStatus(final ClientChatReceivedEvent event) {
        if (!this.someChallengeActive) {
            if (event.message.getFormattedText().contains(new StringBuilder()
                    .append(EnumChatFormatting.RED)
                    .append(EnumChatFormatting.BOLD)
                    .append("CHALLENGE ACTIVE!"))) {
                this.someChallengeActive = true;
            }
        } else if (event.message.getFormattedText().contains(new StringBuilder()
                .append(EnumChatFormatting.GOLD)
                .append(EnumChatFormatting.BOLD)
                .append("Invisible Shop"))) {
            this.status.setChallengeActive(true);
            event.message.appendText(String.valueOf(new StringBuilder()
                    .append("\n                              ")
                    .append(EnumChatFormatting.GRAY)
                    .append("ShopSleuth Active")));
        }
    }
}
