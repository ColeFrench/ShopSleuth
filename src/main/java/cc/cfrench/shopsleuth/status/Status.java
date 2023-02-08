package cc.cfrench.shopsleuth.status;

public class Status {
    private boolean challengeActive = false;

    public boolean isChallengeActive() {
        return this.challengeActive;
    }

    protected void setChallengeActive(final boolean challengeActive) {
        this.challengeActive = challengeActive;
    }
}
