package org.webler.zsolt.game.thief.phases;

import org.webler.zsolt.game.thief.GameController;
import org.webler.zsolt.game.thief.command.*;
import org.webler.zsolt.game.thief.command.heist.*;
import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.Arrays;
import java.util.List;

public class HeistPhase extends GamePhase {

    public static final String phaseDescription = "DESC";
    private final List<Command> availableCommands;
    private final Mastermind mastermind;
    private final GameController controller;

    public HeistPhase(GameController controller, Mastermind mastermind) {
        this.mastermind = mastermind;
        this.controller = controller;
        this.availableCommands = initAvailableCommands();
    }

    private List<Command> initAvailableCommands() {

        InfoCommand info = new InfoCommand();
        CharactersCommand characters = new CharactersCommand();
        HeistCommand heist = new HeistCommand();
        AddCrewCommand add = new AddCrewCommand();
        AddRoleCommand addRole = new AddRoleCommand();
        RemoveRoleCommand removeRole = new RemoveRoleCommand();
        RemoveCrewCommand remove = new RemoveCrewCommand();
        CrewCommand crew = new CrewCommand();
        StartHeistCommand start = new StartHeistCommand();
        MenuCommand menu = new MenuCommand(Arrays.asList(info, characters, heist, add, addRole, remove, removeRole, crew, start));


        return Arrays.asList(info, characters, heist, add, addRole, remove, removeRole, crew, menu, start);

    }


    @Override
    public List<Command> getAvailableCommands() {
        return this.availableCommands;
    }
}
