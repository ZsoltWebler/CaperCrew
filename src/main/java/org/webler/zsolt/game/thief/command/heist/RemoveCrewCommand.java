package org.webler.zsolt.game.thief.command.heist;

import org.webler.zsolt.game.thief.Thief;
import org.webler.zsolt.game.thief.command.Command;
import org.webler.zsolt.game.thief.command.IFindThief;
import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.Optional;

public class RemoveCrewCommand implements Command, IFindThief {
    private static final String COMMAND = "/remove";


    @Override
    public void execute(Mastermind mastermind, Optional<String> arg) {

        if (!arg.isPresent()) {
            System.out.println("Hiányzó karakter név!");
        }
        String characterName = arg.get();
        Optional<Thief> thief = findByName(mastermind.getSelectedCrew(), characterName);
        if (thief.isPresent()) {
            mastermind.getAvailableThieves().add(thief.get());
            thief.get().getRoles().clear();
            mastermind.getSelectedCrew().remove(thief.get());
            System.out.println(thief.get().getName() + " sikeresen el lett távolítva a csapatból.");
        } else {
            System.out.println("Nincs ilyen nevű karater!");
        }


    }

    @Override
    public String getCommandString() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "Remove character from crew!";
    }
}
