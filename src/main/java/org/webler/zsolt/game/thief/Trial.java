package org.webler.zsolt.game.thief;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.webler.zsolt.game.thief.skill.SkillType;

@Data
@AllArgsConstructor
public class Trial {
    SkillType type;
    int difficulty;
}
