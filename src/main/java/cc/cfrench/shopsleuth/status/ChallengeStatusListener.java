package cc.cfrench.shopsleuth.status;

import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChallengeStatusListener {
    private boolean someChallengeActive = false;
    private boolean active = false;

    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Load event) {
        this.active = false;
        this.someChallengeActive = false;
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatReceived(final ClientChatReceivedEvent event) {
        if (!someChallengeActive) {
            if (event.message.getFormattedText().contains(new StringBuilder()
                    .append(EnumChatFormatting.RED)
                    .append(EnumChatFormatting.BOLD)
                    .append("CHALLENGE ACTIVE!"))) {
                this.someChallengeActive = true;
            }
        } else {
            if (event.message.getFormattedText().contains(new StringBuilder()
                    .append(EnumChatFormatting.GOLD)
                    .append(EnumChatFormatting.BOLD)
                    .append("Invisible Shop"))) {
                this.active = true;
                event.message.appendText(String.valueOf(new StringBuilder()
                        .append("\n                              ")
                        .append(EnumChatFormatting.GRAY)
                        .append("ShopSleuth Active")));
            }
        }
    }

    /**
     * @return whether we are in a game with the Invisible Shop challenge enabled
     */
    public boolean isActive() {
        return this.active;
    }
}
