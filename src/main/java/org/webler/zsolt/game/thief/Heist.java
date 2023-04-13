package org.webler.zsolt.game.thief;

import lombok.Data;
import org.webler.zsolt.game.thief.skill.SkillType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Data
public class Heist {

    private final List<Trial> trials;
    private int reward;

    public Heist() {
        this.trials = generateTrials();
        this.reward = calculateReward();
    }

    private int calculateReward() {
        return this.trials.stream().mapToInt(Trial::getDifficulty).sum();
    }

    private List<Trial> generateTrials() {
        List<Trial> trials = new ArrayList<>();
        Random rnd = new Random();
        int trialCount = rnd.nextInt(4) + 5;

        for (int i = 0; i < trialCount; i++) {

            SkillType type = Arrays.asList(SkillType.values()).get(rnd.nextInt(SkillType.values().length));
            trials.add(new Trial(type, rnd.nextInt(100) + 25));

        }

        if (rnd.nextInt(4) < 1) {
            trials.add(new Trial(SkillType.DRIVING, rnd.nextInt(100) + 25));
        }

        if (rnd.nextInt(4) < 1) {
            trials.add(new Trial(SkillType.VEHICLE_SELECT, rnd.nextInt(100) + 25));
        }

        return trials;
    }

}
