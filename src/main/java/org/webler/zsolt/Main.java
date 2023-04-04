package org.webler.zsolt;

import org.webler.zsolt.game.thief.Thief;
import org.webler.zsolt.game.thief.skill.SkillType;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


        List<Thief> thieves = thiefGenerator();

        System.out.println("asd");
    }


    public static List<Thief> thiefGenerator() {

        List<Thief> thieves = new ArrayList<>();
        List<Character> possibleNames = new ArrayList<>();
        List<Character> usedNames = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            possibleNames.add(((char) ('a' + i)));
        }

        Random rnd = new Random();


        while (thieves.size() < 10) {
            Character name = possibleNames.get(rnd.nextInt(possibleNames.size()));
            if (!usedNames.contains(name)) {
                Thief thief = new Thief("" + name, getSkills());
                thieves.add(thief);
                usedNames.add(name);
                possibleNames.remove(name);
            }

        }

        return thieves;

    }

    private static Map<SkillType, Integer> getSkills() {
        Map<SkillType, Integer> skills = Arrays.stream(SkillType.values()).map(skillType -> {
            Random rnd = new Random();
            Map.Entry<SkillType, Integer> skill = new AbstractMap.SimpleEntry<SkillType, Integer>(skillType, rnd.nextInt(99) + 1);
            return skill;
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return skills;

    }
}