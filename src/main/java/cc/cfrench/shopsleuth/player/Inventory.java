package cc.cfrench.shopsleuth.player;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Inventory {
    private final Map<Item, Long> resourceCounts = new HashMap<>();
    private ItemStack lastMousedItemStack;

    public long getResourceCount(final Item item) {
        return this.resourceCounts.getOrDefault(item, 0L);
    }

    public Optional<ItemStack> getLastMousedItemStack() {
        return Optional.ofNullable(this.lastMousedItemStack);
    }

    protected void setResourceCounts(final Map<? extends Item, ? extends Long> resourceCounts) {
        this.resourceCounts.putAll(resourceCounts);
    }

    protected void setLastMousedItemStack(final ItemStack lastMousedItemStack) {
        this.lastMousedItemStack = lastMousedItemStack;
    }
}
