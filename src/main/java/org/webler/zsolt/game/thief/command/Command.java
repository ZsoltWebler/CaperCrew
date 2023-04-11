package org.webler.zsolt.game.thief.command;


import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.Optional;

public interface Command {

    void execute(Mastermind mastermind, Optional<String> arg);

    String getCommandString();

    String getDescription();


}
