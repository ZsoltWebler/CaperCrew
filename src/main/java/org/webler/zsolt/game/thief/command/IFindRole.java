package org.webler.zsolt.game.thief.command;

import org.webler.zsolt.game.thief.Role;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface IFindRole {
    default Optional<Role> findByRole(List<Role> roles, String roleName) {
        return roles.stream().filter(_role -> _role.name().equals(roleName)).findFirst();
    }
}
