package org.webler.zsolt.game.thief.command.heist;

import org.webler.zsolt.game.thief.Thief;
import org.webler.zsolt.game.thief.command.Command;
import org.webler.zsolt.game.thief.command.information.CharacterInformation;
import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.Optional;

public class CrewCommand implements Command {

    private static final String COMMAND = "/crew";


    @Override
    public void execute(Mastermind mastermind, Optional<String> arg) {
        new CharacterInformation(mastermind.getSelectedCrew()).printSceneInformation();
        int dividend = mastermind.getSelectedCrew().stream().mapToInt(Thief::getDividend).sum();
        int reward = mastermind.getCurrentHeist().getReward();
        int realReward = (int) (reward * (dividend / 100.0f));
        System.out.println("A várható jutalom ezzel a csapattal: " + realReward);
    }

    @Override
    public String getCommandString() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "Print information about your current crew!";
    }
}
