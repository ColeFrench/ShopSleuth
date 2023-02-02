package cc.cfrench.shopsleuth.status;

import net.minecraftforge.common.MinecraftForge;

public class Status {
    private final ChallengeStatusListener challengeStatusListener = new ChallengeStatusListener();

    public void listen() {
        MinecraftForge.EVENT_BUS.register(this.challengeStatusListener);
    }

    /**
     * @return whether we are in a game with the Invisible Shop challenge enabled
     */
    public boolean isActive() {
        return this.challengeStatusListener.isActive();
    }
}
