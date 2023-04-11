package org.webler.zsolt.game.thief.command;

import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuCommand implements Command {


    private final List<Command> availableCommands;
    private static final String COMMAND = "/menu";
    private static final String FORMAT_STRING = "%1$-20s \t %2$20s%n";

    public MenuCommand(List<Command> commands) {
        List<Command> availableCommands = new ArrayList<>(commands);
        availableCommands.add(this);
        this.availableCommands = availableCommands;
    }

    @Override
    public void execute(Mastermind mastermind, Optional<String> arg) {
        for (Command command : availableCommands) {
            System.out.format(FORMAT_STRING, command.getCommandString(), command.getDescription());
        }
    }

    @Override
    public String getCommandString() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "List all available commands.";
    }
}
