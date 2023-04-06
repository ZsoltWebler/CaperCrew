package org.webler.zsolt.game.thief;

import org.webler.zsolt.game.thief.skill.SkillType;

import java.util.Arrays;
import java.util.List;

public enum Role {

    PARTNER_IN_CRIME(1, 10, "", Arrays.asList(SkillType.STRATEGY)),
    DRIVER(1, 10, "", Arrays.asList(SkillType.DRIVING, SkillType.VEHICLE_SELECT)),
    GUNMAN(1, 10, "", Arrays.asList(SkillType.WEAPON_KNOWLEDGE, SkillType.ACCURACY, SkillType.REFLEX)),
    COORDINATOR(1, 10, "", Arrays.asList(SkillType.STRATEGY, SkillType.REFLEX)),
    HACKER(1, 10, "", Arrays.asList(SkillType.COMPUTER_SKILL)),
    DISTRACTION(1, 10, "", Arrays.asList(SkillType.CHARISMA, SkillType.ACCURACY)),
    GADGET_GUY(1, 10, "", Arrays.asList(SkillType.COMPUTER_SKILL, SkillType.SPEED, SkillType.STEALTH)),
    BURGLAR(1, 10, "", Arrays.asList(SkillType.SPEED, SkillType.STEALTH)),
    CON_MAN(1, 10, "", Arrays.asList(SkillType.CHARISMA));

    public final int maxCount;
    public final int dividend;
    public final String description;
    public final List<SkillType> skills;

    private Role(int maxCount, int dividend, String description, List<SkillType> skills) {
        this.maxCount = maxCount;
        this.dividend = dividend;
        this.description = description;
        this.skills = skills;
    }

}
