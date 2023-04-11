package org.webler.zsolt.game.thief.command.heist;

import org.webler.zsolt.game.thief.Thief;
import org.webler.zsolt.game.thief.command.Command;
import org.webler.zsolt.game.thief.command.IFindRole;
import org.webler.zsolt.game.thief.command.IFindThief;
import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.Optional;

public class AddCrewCommand implements Command, IFindThief {
    private static final String COMMAND = "/add";


    @Override
    public void execute(Mastermind mastermind, Optional<String> arg) {

        if (!arg.isPresent()) {
            System.out.println("Hiányzó karakter név!");
        }
        String characterName = arg.get();
        Optional<Thief> thief = findByName(mastermind.getAvailableThieves(),characterName);
        if (thief.isPresent()) {
            mastermind.getSelectedCrew().add(thief.get());
            mastermind.getAvailableThieves().remove(thief.get());
            System.out.println(thief.get().getName() + " sikeresen hozzá lett adva a csapathoz.");
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
        return "Add character to crew!";
    }
}
