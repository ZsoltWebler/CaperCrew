package org.webler.zsolt.game.thief.command;

import org.webler.zsolt.game.thief.command.information.CharacterInformation;
import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.Optional;

public class CharactersCommand implements Command {

    private static final String COMMAND = "/characters";


    @Override
    public void execute(Mastermind mastermind, Optional<String> arg) {
        new CharacterInformation(mastermind.getAvailableThieves()).printSceneInformation();
    }

    @Override
    public String getCommandString() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "Print the available characters from the character pool!";
    }
}
