package cc.cfrench.shopsleuth;

import cc.cfrench.shopsleuth.status.ChallengeStatusListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = ShopSleuth.MODID, version = ShopSleuth.VERSION)
public class ShopSleuth {
    public static final String MODID = "shopsleuth";
    public static final String VERSION = "0.1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ChallengeStatusListener());
    }
}
