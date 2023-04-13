package org.webler.zsolt;

import org.junit.jupiter.api.Test;
import org.webler.zsolt.game.thief.Heist;
import org.webler.zsolt.game.thief.Trial;
import org.webler.zsolt.game.thief.skill.SkillType;

import java.util.HashMap;
import java.util.Map;

public class TestHeist {


    @Test
    void testHeistReward() {
        Heist heist = new Heist();
        int expected_reward = 0;
        for (Trial trial : heist.getTrials()) {
            expected_reward += trial.getDifficulty();
        }
        assert heist.getReward() == expected_reward;
    }

    @Test
    void testHeistTrials() {
        for (int i = 0; i < 1000; i++) {
            Heist heist = new Heist();
            int trialsCount = heist.getTrials().size();
            if (trialsCount > 10 || trialsCount < 4) {
                assert false;
            }
        }
        assert true;
    }

    @Test
    void testHeistTrialsDifficulty() {
        for (int i = 0; i < 1000; i++) {
            Heist heist = new Heist();
            for (Trial trial : heist.getTrials()) {
                int difficulty = trial.getDifficulty();
                if (difficulty > 125 || difficulty < 25) {
                    assert false;
                }
            }
        }
        assert true;
    }

    @Test
    void testHeistTrialsSkillDeviation() {

        Map<SkillType,Integer> trialTypeCount = new HashMap<>();
        int allSkillCount = 1000000;

        for (int i = 0; i < allSkillCount; i++) {
            Heist heist = new Heist();
            for (Trial trial : heist.getTrials()) {
                trialTypeCount.put(trial.getType(),trialTypeCount.getOrDefault(trial.getType(),0)+1);
            }
        }

        Map<SkillType,Double> normalizedTrialTypeCount = new HashMap<>();
        trialTypeCount.forEach((k,v)->{
            normalizedTrialTypeCount.put(k,v/new Double(allSkillCount));
        });

        System.out.println("");
        assert true;
    }

}
