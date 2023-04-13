package org.webler.zsolt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.webler.zsolt.game.thief.Thief;
import org.webler.zsolt.game.thief.ThiefFactorySingleton;
import org.webler.zsolt.game.thief.exceptions.ThiefFactoryException;
import org.webler.zsolt.game.thief.skill.SkillType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class TestThiefFactorySingleton {

    ThiefFactorySingleton thiefFactory;

    @BeforeEach
    void setUp() {
        thiefFactory = ThiefFactorySingleton.getInstance();
    }

    @Test
    void testSingleton() {
        ThiefFactorySingleton otherThiefFactory = ThiefFactorySingleton.getInstance();
        assert thiefFactory == otherThiefFactory;
    }

    @Test
    void testGetRandomThievesReturnAmountSizeCollection() throws ThiefFactoryException {
        int expectedNumberOfThieves = 10;
        List<Thief> randomThieves = thiefFactory.getRandomThieves(expectedNumberOfThieves);
        assert randomThieves.size() == expectedNumberOfThieves;
    }

    @Test
    void testGetRandomThievesReturnThievesWithDistinctName() throws ThiefFactoryException {
        int expectedNumberOfThieves = 26;
        HashSet<String> thiefNames = new HashSet<>();
        thiefFactory.getRandomThieves(expectedNumberOfThieves).forEach(thief -> thiefNames.add(thief.getName()));
        assert thiefNames.size() == expectedNumberOfThieves;
    }

    @Test
    void testGetRandomThievesReturnThievesWithDistinctNameWithMoreThan26Thieves() {
        int expectedNumberOfThieves = 30;
        HashSet<String> thiefNames = new HashSet<>();
        try {
            thiefFactory.getRandomThieves(expectedNumberOfThieves).forEach(thief -> thiefNames.add(thief.getName()));
            assert false;
        } catch (Exception e) {
            assert e instanceof ThiefFactoryException;
        }
    }

    @Test
    void testGetRandomThievesReturnThievesWithDistinctNameWith26Thieves() {
        int expectedNumberOfThieves = 26;
        HashSet<String> thiefNames = new HashSet<>();
        try {
            thiefFactory.getRandomThieves(expectedNumberOfThieves).forEach(thief -> thiefNames.add(thief.getName()));
            assert true;
        } catch (Exception e) {
            assert false;
        }
    }


    @Test
    void testGetRandomThievesReturnThievesSkillsBetween0And100() throws ThiefFactoryException {
        int expectedNumberOfThieves = 26;
        List<Map<SkillType, Integer>> skills = new ArrayList<>();

        thiefFactory.getRandomThieves(expectedNumberOfThieves).forEach(thief -> skills.add(thief.getSkills()));
        boolean skillOutsideOfRange = skills.stream().anyMatch(skillTypeIntegerMap -> skillTypeIntegerMap.values().stream().anyMatch(integer -> integer < 0 || integer > 100));

        assert !skillOutsideOfRange;

    }

}
