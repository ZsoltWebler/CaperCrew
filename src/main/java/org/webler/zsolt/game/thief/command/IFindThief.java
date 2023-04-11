package org.webler.zsolt.game.thief.command;

import org.webler.zsolt.game.thief.Thief;

import java.util.List;
import java.util.Optional;

public interface IFindThief {

    default Optional<Thief> findByName(List<Thief> thieves, String thiefName) {
        return thieves.stream().filter(_thief -> _thief.getName().equals(thiefName)).findFirst();
    }
}
