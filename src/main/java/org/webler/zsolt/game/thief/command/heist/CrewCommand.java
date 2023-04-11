package org.webler.zsolt.game.thief.command.heist;

import org.webler.zsolt.game.thief.command.Command;
import org.webler.zsolt.game.thief.command.information.CharacterInformation;
import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.Optional;

public class CrewCommand implements Command {

    private static final String COMMAND = "/crew";


    @Override
    public void execute(Mastermind mastermind, Optional<String> arg) {
        new CharacterInformation(mastermind.getSelectedCrew()).printSceneInformation();
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
