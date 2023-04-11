package org.webler.zsolt.game.thief.phases;

import org.webler.zsolt.game.thief.command.Command;

import java.util.List;

public abstract class GamePhase {

    public abstract List<Command> getAvailableCommands();
}
