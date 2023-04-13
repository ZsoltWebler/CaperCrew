package org.webler.zsolt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.webler.zsolt.game.thief.Heist;
import org.webler.zsolt.game.thief.HeistFactorySingleton;

public class TestHeistFactorySingleton {
    HeistFactorySingleton heistFactory;

    @BeforeEach
    void setUp() {
        heistFactory = HeistFactorySingleton.getInstance();
    }

    @Test
    void testSingleton() {
        HeistFactorySingleton otherHeistFactory = HeistFactorySingleton.getInstance();
        assert heistFactory == otherHeistFactory;
    }

    @Test
    void testHeistGeneration() {
        assert heistFactory.getHeist() instanceof Heist;

    }

}
