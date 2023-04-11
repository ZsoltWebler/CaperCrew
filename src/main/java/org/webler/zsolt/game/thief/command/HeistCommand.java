package org.webler.zsolt.game.thief.command;

import org.webler.zsolt.game.thief.command.information.HeistInformation;
import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.Optional;

public class HeistCommand implements Command {

    private static final String COMMAND = "/heist";

    @Override
    public void execute(Mastermind mastermind, Optional<String> arg) {
        new HeistInformation(mastermind).printSceneInformation();
    }

    @Override
    public String getCommandString() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "Print information about the current heist.";
    }
}
