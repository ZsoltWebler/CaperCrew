package org.webler.zsolt.game.thief.command.menu;

import org.webler.zsolt.game.thief.HeistFactorySingleton;
import org.webler.zsolt.game.thief.command.Command;
import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.Optional;

public class NewHeistCommand implements Command {
    private static final String COMMAND = "/new_heist";

    @Override
    public void execute(Mastermind mastermind, Optional<String> arg) {
        mastermind.setCurrentHeist(HeistFactorySingleton.getInstance().getHeist());
        System.out.println("Új rablási helyszín lett elérhető.");
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
