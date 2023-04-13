package org.webler.zsolt.game.thief.trials;

import org.webler.zsolt.game.thief.Role;
import org.webler.zsolt.game.thief.Thief;
import org.webler.zsolt.game.thief.skill.Mastermind;
import org.webler.zsolt.game.thief.skill.SkillType;

import java.util.List;
import java.util.stream.Collectors;

public interface ITrial {

    void onFailure() throws HeistFailureException;
    void onSuccess();
    int getDificulty();

    SkillType getSkillType();

    List<Thief> getCrew();


    default void tryTrial() throws HeistFailureException {
        if (getDificulty() < getThievesForTrial().stream().mapToInt(thief -> thief.getSkills().get(getSkillType())).sum()) {
            onSuccess();
        } else {
            onFailure();
        }
    }

    default List<Thief> getThievesForTrial() {

        return getCrew().stream().filter(thief -> {
            List<Role> roles = thief.getRoles();
            for (Role role : roles) {
                if (role.skills.contains(getSkillType())) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
    }
}

