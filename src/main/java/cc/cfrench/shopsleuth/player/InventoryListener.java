package cc.cfrench.shopsleuth.player;

import cc.cfrench.shopsleuth.status.Status;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InventoryListener {
    private static final Set<Item> RESOURCE_ITEMS = Stream.of(
            "minecraft:iron_ingot",
            "minecraft:gold_ingot",
            "minecraft:emerald",
            "minecraft:diamond")
        .map(Item::getByNameOrId)
        .collect(Collectors.collectingAndThen(
            Collectors.toCollection(HashSet::new),
            Collections::unmodifiableSet));

    private final Status status;
    private final Inventory inventory;

    public InventoryListener(final Status status, final Inventory inventory) {
        this.status = status;
        this.inventory = inventory;
    }

    @SubscribeEvent
    public void onPlayerTick_UpdateResourceCounts(final TickEvent.PlayerTickEvent event) {
        if (event.player instanceof EntityPlayerSP
                && this.status.isChallengeActive()
                && event.phase == TickEvent.Phase.END) {
            final Stream<ItemStack> resourceStream = Stream.concat(
                    Arrays.stream(event.player.inventory.mainInventory),
                    Stream.of(event.player.inventory.getItemStack()))
                .filter(Objects::nonNull)
                .filter(itemStack -> RESOURCE_ITEMS.contains(itemStack.getItem()));
            final Map<Item, Long> resourceCounts = resourceStream.collect(
                Collectors.groupingBy(ItemStack::getItem, Collectors.mapping(
                    itemStack -> itemStack.stackSize,
                    Collectors.counting())));
            this.inventory.setResourceCounts(resourceCounts);
        }
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
