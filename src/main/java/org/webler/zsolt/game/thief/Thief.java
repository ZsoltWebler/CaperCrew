package org.webler.zsolt.game.thief;

import lombok.Data;
import org.webler.zsolt.game.thief.skill.SkillType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Data
public class Thief {

    private final String name;
    private final Map<SkillType, Integer> skills;
    private final List<Role> roles;

    public Thief(String name, Map<SkillType, Integer> skills) {
        this.name = name;
        this.skills = skills;
        this.roles = new ArrayList<>();

    }

    public void addRole(Role role) throws IndexOutOfBoundsException {
        if (roles.size() < 20) {
            roles.add(role);
        } else {
            throw new IndexOutOfBoundsException("Max 2 roles");
        }
    }


    public int getDividend() {

        int baseDividend = skills
                .entrySet()
                .stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .get()
                .getValue() / 10;


        int dividendByRole = roles.stream().mapToInt(role -> role.dividend).sum();

        return baseDividend + dividendByRole;
    }

}
