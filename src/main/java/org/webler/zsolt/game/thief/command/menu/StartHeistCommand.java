package org.webler.zsolt.game.thief.command.menu;

import org.webler.zsolt.game.thief.GameController;
import org.webler.zsolt.game.thief.HeistFactorySingleton;
import org.webler.zsolt.game.thief.command.Command;
import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.Optional;

public class StartHeistCommand implements Command {

    private static final String COMMAND = "/start_heist";

    private final GameController controller;

    public StartHeistCommand(GameController controller){
        this.controller = controller;
    }

    @Override
    public void execute(Mastermind mastermind, Optional<String> arg) {
        controller.switchToGamePhase();
        System.out.println("Rakd össze a csapatod!");
    }

    @Override
    public String getCommandString() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "Generate a new heist. It takes 1 chance from you!";
    }

}
