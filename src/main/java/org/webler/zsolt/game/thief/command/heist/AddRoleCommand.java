package org.webler.zsolt.game.thief.command.heist;

import org.webler.zsolt.game.thief.Role;
import org.webler.zsolt.game.thief.Thief;
import org.webler.zsolt.game.thief.command.Command;
import org.webler.zsolt.game.thief.command.IFindRole;
import org.webler.zsolt.game.thief.command.IFindThief;
import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.Arrays;
import java.util.Optional;

public class AddRoleCommand implements Command, IFindRole, IFindThief {
    private static final String COMMAND = "/add_role";


    @Override
    public void execute(Mastermind mastermind, Optional<String> arg) {

        if (!arg.isPresent() || arg.get().trim().split(" ").length != 2) {
            System.out.println("Rossz paraméterek!");
        }
        String[] args = arg.get().split(" ");
        Optional<Thief> thief = findByName(mastermind.getSelectedCrew(), args[0]);
        Optional<Role> role = findByRole(Arrays.asList(Role.values()),args[1]);
        if (thief.isPresent() && role.isPresent()) {
            thief.get().getRoles().add(role.get());
            System.out.println(thief.get().getName() + " hez sikeresen beosztva a " + role.get().name() + " szerepbe.");
        } else {
            System.out.println("Rossz paraméterek!");
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
