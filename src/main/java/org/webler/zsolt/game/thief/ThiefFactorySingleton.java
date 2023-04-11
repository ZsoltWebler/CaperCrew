package org.webler.zsolt.game.thief;

import org.webler.zsolt.game.thief.skill.SkillType;

import java.util.*;
import java.util.stream.Collectors;

public class ThiefFactorySingleton {

    private static ThiefFactorySingleton INSTANCE;

    private ThiefFactorySingleton() {
    }

    public static ThiefFactorySingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThiefFactorySingleton();
        }

        return INSTANCE;
    }

    public List<Thief> getRandomThieves(int amount) {

        List<Thief> thieves = new ArrayList<>();
        List<Character> possibleNames = new ArrayList<>();
        List<Character> usedNames = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            possibleNames.add(((char) ('a' + i)));
        }

        Random rnd = new Random();


        while (thieves.size() < amount) {
            Character name = possibleNames.get(rnd.nextInt(possibleNames.size()));
            if (!usedNames.contains(name)) {
                Thief thief = new Thief("" + name, generateSkills());
                thieves.add(thief);
                usedNames.add(name);
                possibleNames.remove(name);
            }

        }

        return thieves;

    }

    private Map<SkillType, Integer> generateSkills() {
        Map<SkillType, Integer> skills = Arrays.stream(SkillType.values()).map(skillType -> {
            Random rnd = new Random();
            Map.Entry<SkillType, Integer> skill = new AbstractMap.SimpleEntry<SkillType, Integer>(skillType, rnd.nextInt(99) + 1);
            return skill;
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return skills;
    }


}
