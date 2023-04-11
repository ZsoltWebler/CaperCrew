package org.webler.zsolt.game.thief.command.information;

import org.webler.zsolt.game.thief.Role;
import org.webler.zsolt.game.thief.Thief;

import java.util.List;

public class CharacterInformation extends GameSceneInformation {

    public static final String SCENE_NAME = "Tolvaj információk";
    private final List<Thief> thieves;

    public CharacterInformation(List<Thief> thieves) {
        super(SCENE_NAME);
        this.thieves = thieves;
    }

    @Override
    public void printSceneInformation() {
        if (thieves.isEmpty()) {
            System.out.println("[]");
        }
        for (Thief thief : thieves) {

            System.out.println("Név: \t" + thief.getName());
            System.out.println("Képességek:");
            thief.getSkills().forEach((key, value) -> System.out.format("\t %1$-20s %2$2s%n", key, value));
            List<Role> roles = thief.getRoles();
            if (roles.isEmpty()) {
                System.out.println("Szerepek: []");
            } else {
                System.out.println("Szerepek: \t" + roles);
            }
            System.out.println("Osztalék: \t" + thief.getDividend());
            System.out.println("=========");
        }

    }
}
