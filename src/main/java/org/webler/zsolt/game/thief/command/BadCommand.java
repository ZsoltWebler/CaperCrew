package org.webler.zsolt.game.thief.command;

import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.Optional;

public class BadCommand implements Command {

    private static final String COMMAND = "/bad";

    @Override
    public void execute(Mastermind mastermind, Optional<String> arg) {
        System.out.println("Nincs ilyen parancs!");
    }

    @Override
    public String getCommandString() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "This is a placeholder command, if the command is not recognized!";
    }
}
