package org.webler.zsolt.game.thief;

public enum Role {

    PARTNER_IN_CRIME(1, 10, ""),
    DRIVER(1, 10, ""),
    GUNMAN(1, 10, ""),
    COORDINATOR(1, 10, ""),
    HACKER(1, 10, ""),
    DISTRACTION(1, 10, ""),
    GADGET_GUY(1, 10, ""),
    BURGLAR(1, 10, ""),
    CON_MAN(1, 10, "");

    public final int maxCount;
    public final int dividend;
    public final String description;

    private Role(int maxCount, int dividend, String description) {
        this.maxCount = maxCount;
        this.dividend = dividend;
        this.description = description;
    }

}
