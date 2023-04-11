package org.webler.zsolt.game.thief;

public class HeistFactorySingleton {

    private static HeistFactorySingleton INSTANCE;

    private HeistFactorySingleton() {
    }

    public static HeistFactorySingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HeistFactorySingleton();
        }

        return INSTANCE;
    }

    public Heist getHeist() {
        return new Heist();
    }
}
