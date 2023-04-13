package org.webler.zsolt.game.thief.command.heist;

import org.webler.zsolt.game.thief.TrialHandlerSingleton;
import org.webler.zsolt.game.thief.command.Command;
import org.webler.zsolt.game.thief.skill.Mastermind;
import org.webler.zsolt.game.thief.trials.HeistFailureException;

import java.util.Optional;

public class StartHeistCommand implements Command {

    private static final String COMMAND = "/start";


    @Override
    public void execute(Mastermind mastermind, Optional<String> arg) {
        try {
            TrialHandlerSingleton.getInstance().doHeist(mastermind);
            mastermind.setCurrentMoney(mastermind.getCurrentMoney() + mastermind.getCurrentHeist().getReward());
            System.out.println("Sikeres rablás");
        } catch (HeistFailureException e) {
            System.out.println("Sikertelen rablás");
        }
    }

    @Override
    public String getCommandString() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "Start the actual heist with the selected crew!";
    }

}
