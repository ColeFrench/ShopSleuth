package cc.cfrench.shopsleuth;

import cc.cfrench.shopsleuth.player.Inventory;
import cc.cfrench.shopsleuth.player.InventoryListener;
import cc.cfrench.shopsleuth.status.Status;
import cc.cfrench.shopsleuth.status.StatusListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = ShopSleuth.MODID, version = ShopSleuth.VERSION, clientSideOnly = true)
public class ShopSleuth {
    public static final String MODID = "shopsleuth";
    public static final String VERSION = "0.1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        final Status status = new Status();
        MinecraftForge.EVENT_BUS.register(new StatusListener(status));
        MinecraftForge.EVENT_BUS.register(new InventoryListener(status, new Inventory()));
    }
}
