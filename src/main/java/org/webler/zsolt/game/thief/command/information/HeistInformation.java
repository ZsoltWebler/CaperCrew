package org.webler.zsolt.game.thief.command.information;

import org.webler.zsolt.game.thief.Heist;
import org.webler.zsolt.game.thief.Trial;
import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.List;

public class HeistInformation extends GameSceneInformation {

    public static final String SCENE_NAME = "Rablás információk";

    private final Heist heist;

    public HeistInformation(Mastermind mastermind) {
        super(SCENE_NAME);
        this.heist = mastermind.getCurrentHeist();
    }

    @Override
    public void printSceneInformation() {

        List<Trial> trials = heist.getTrials();
        System.out.println("Próbák: ");
        for (int i = 0; i < trials.size() - 1; i++) {
            int index = i + 1;
            System.out.println("\t" + index + " - " + trials.get(i).getType());
        }
        System.out.println("Jutalom: " + heist.getReward());

    }
}
