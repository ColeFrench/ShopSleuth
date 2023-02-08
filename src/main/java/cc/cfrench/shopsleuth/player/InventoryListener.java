package cc.cfrench.shopsleuth.player;

import cc.cfrench.shopsleuth.status.Status;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Optional;

public class InventoryListener {
    private final Status status;
    private final Inventory inventory;

    public InventoryListener(final Status status, final Inventory inventory) {
        this.status = status;
        this.inventory = inventory;
    }

    @SubscribeEvent
    public void onPlayerTick_UpdateLastMousedItemStack(final TickEvent.PlayerTickEvent event) {
        if (event.player instanceof EntityPlayerSP
                && this.status.isChallengeActive()
                && event.phase == TickEvent.Phase.END) {
            final Optional<ItemStack> maybeMousedItemStack = Optional.ofNullable(Minecraft.getMinecraft().currentScreen)
                    .filter(GuiChest.class::isInstance)
                    .map(GuiChest.class::cast)
                    .map(GuiChest::getSlotUnderMouse)
                    .map(Slot::getStack);
            if (maybeMousedItemStack.isPresent()
                    && !maybeMousedItemStack.equals(this.inventory.getLastMousedItemStack())) {
                this.inventory.setLastMousedItemStack(maybeMousedItemStack.get());
            }
        }
    }
}
