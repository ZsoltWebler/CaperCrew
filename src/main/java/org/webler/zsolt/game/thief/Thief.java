package org.webler.zsolt.game.thief;

import lombok.Data;
import org.webler.zsolt.game.thief.skill.SkillType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Data
public class Thief {

    private String name;
    private Map<SkillType, Integer> skills;
    private final List<Role> roles;
    private final int dividend;

    public Thief(String name, Map<SkillType, Integer> skills) {
        this.name = name;
        this.skills = skills;
        this.dividend = getDividend();
        this.roles = new ArrayList<>();
    }

    public void addRole(Role role) throws IndexOutOfBoundsException {
        if (roles.size() < 20) {
            roles.add(role);
        } else {
            throw new IndexOutOfBoundsException("Max 2 roles");
        }
    }


    private int getDividend() {

        /*
        skills.entrySet().stream().max((entry1, entry2) -> {
            return Integer.compare(entry1.getValue(), entry2.getValue());
        }).get();
        */

        int baseDividend = skills
                .entrySet()
                .stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .get()
                .getValue() / 10;

        /*
        mapToInt(Role::getDividend)

        mapToInt(role -> {
            return role.getDividend();
        })
         */

        //int dividendByRole = roles.stream().mapToInt(role -> role.dividend).sum();

        return baseDividend ;//dividendByRole;
    }

}
