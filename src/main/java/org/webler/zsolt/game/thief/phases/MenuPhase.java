package org.webler.zsolt.game.thief.phases;

import org.webler.zsolt.game.thief.GameController;
import org.webler.zsolt.game.thief.command.*;
import org.webler.zsolt.game.thief.command.menu.NewCharactersCommand;
import org.webler.zsolt.game.thief.command.menu.NewHeistCommand;
import org.webler.zsolt.game.thief.command.menu.StartHeistCommand;
import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.Arrays;
import java.util.List;

public class MenuPhase extends GamePhase {

    public static final String phaseDescription = "DESC";
    private final List<Command> availableCommands;
    private final Mastermind mastermind;
    private final GameController controller;

    public MenuPhase(GameController controller, Mastermind mastermind) {
        this.mastermind = mastermind;
        this.controller = controller;
        this.availableCommands = initAvailableCommands();
    }

    private List<Command> initAvailableCommands() {

        InfoCommand info = new InfoCommand();
        CharactersCommand characters = new CharactersCommand();
        HeistCommand heist = new HeistCommand();
        NewCharactersCommand newCharactersCommand = new NewCharactersCommand();
        NewHeistCommand newHeistCommand = new NewHeistCommand();
        StartHeistCommand startHeistCommand = new StartHeistCommand(controller);
        MenuCommand menu = new MenuCommand(Arrays.asList(info, characters, heist, newCharactersCommand, newHeistCommand, startHeistCommand));


        return Arrays.asList(info, characters, heist, menu, newCharactersCommand, newHeistCommand, startHeistCommand);

    }


    @Override
    public List<Command> getAvailableCommands() {
        return this.availableCommands;
    }
}
