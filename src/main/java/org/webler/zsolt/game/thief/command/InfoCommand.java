package org.webler.zsolt.game.thief.command;

import org.webler.zsolt.game.thief.command.information.GameProgressInformation;
import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.Optional;

public class InfoCommand implements Command {

    private static final String COMMAND = "/info";

    @Override
    public void execute(Mastermind mastermind, Optional<String> arg) {
        new GameProgressInformation(mastermind).printSceneInformation();
    }

    @Override
    public String getCommandString() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "Print game progress information";
    }
}
