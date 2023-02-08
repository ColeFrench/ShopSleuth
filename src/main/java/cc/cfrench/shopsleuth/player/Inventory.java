package cc.cfrench.shopsleuth.player;

import net.minecraft.item.ItemStack;

import java.util.Optional;

public class Inventory {
    private ItemStack lastMousedItemStack;

    public Optional<ItemStack> getLastMousedItemStack() {
        return Optional.ofNullable(this.lastMousedItemStack);
    }

    protected void setLastMousedItemStack(final ItemStack lastMousedItemStack) {
        this.lastMousedItemStack = lastMousedItemStack;
    }
}
