package org.webler.zsolt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.webler.zsolt.game.thief.GameController;
import org.webler.zsolt.game.thief.Heist;
import org.webler.zsolt.game.thief.Role;
import org.webler.zsolt.game.thief.Thief;
import org.webler.zsolt.game.thief.exceptions.ThiefFactoryException;
import org.webler.zsolt.game.thief.skill.Mastermind;
import org.webler.zsolt.game.thief.skill.SkillType;

import java.util.*;
import java.util.stream.Collectors;

public class GameControllerTest {

    GameController controller;

    @BeforeEach
    void setUp() throws ThiefFactoryException {
        controller = new GameController(new Mastermind(20,20));
    }

    @Test
    void doHeistTest(){
        List<Thief> selectedThieves = new ArrayList<>();

        selectedThieves.add(new Thief("TestElek",setSkills(100)));
        selectedThieves.add(new Thief("TestElek2",setSkills(100)));
        selectedThieves.add(new Thief("TestElek3",setSkills(100)));

        selectedThieves.forEach(thief -> {
            for (Role role : Role.values()){
                thief.addRole(role);
            }
        });

        Heist nextHeist = new Heist();

       // controller.doHeist(selectedThieves,nextHeist);

    }

    private Map<SkillType, Integer> setSkills(int skillValue) {
        Map<SkillType, Integer> skills = Arrays.stream(SkillType.values()).map(skillType -> {
            Random rnd = new Random();
            Map.Entry<SkillType, Integer> skill = new AbstractMap.SimpleEntry<SkillType, Integer>(skillType, skillValue);
            return skill;
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return skills;

    }


}
