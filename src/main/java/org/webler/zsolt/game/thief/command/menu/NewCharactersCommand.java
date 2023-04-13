package org.webler.zsolt.game.thief.command.menu;

import org.webler.zsolt.game.thief.ThiefFactorySingleton;
import org.webler.zsolt.game.thief.command.Command;
import org.webler.zsolt.game.thief.exceptions.ThiefFactoryException;
import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.Optional;

public class NewCharactersCommand implements Command {

    private static final String COMMAND = "/new_characters";

    @Override
    public void execute(Mastermind mastermind, Optional<String> arg) {
        try {
            mastermind.setAvailableThieves(ThiefFactorySingleton.getInstance().getRandomThieves(10));
            System.out.println("Új karakterek kerültek a poolba!");
        } catch (ThiefFactoryException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getCommandString() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "Generate new character pool for the 5% of the current money!";
    }
}
