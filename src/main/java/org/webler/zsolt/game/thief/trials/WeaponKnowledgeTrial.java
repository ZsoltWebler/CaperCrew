package org.webler.zsolt.game.thief.trials;

import org.webler.zsolt.game.thief.Thief;
import org.webler.zsolt.game.thief.skill.Mastermind;
import org.webler.zsolt.game.thief.skill.SkillType;

import java.util.List;
import java.util.Random;

public class WeaponKnowledgeTrial implements ITrial {

    public static final SkillType skill = SkillType.WEAPON_KNOWLEDGE;

    private final Mastermind mastermind;
    private final int dificulty;

    public WeaponKnowledgeTrial(Mastermind mastermind, int dificulty) {
        this.mastermind = mastermind;
        this.dificulty = dificulty;
    }

    @Override
    public void onFailure() throws HeistFailureException {
        mastermind.getCurrentHeist().setReward((mastermind.getCurrentHeist().getReward() / 10) * 9);

        Random rnd = new Random();
        mastermind.getSelectedCrew().remove(mastermind.getSelectedCrew().get(rnd.nextInt(getThievesForTrial().size())));

        throw new HeistFailureException("Sikertelen fegyverismeret próba!");

    }

    @Override
    public void onSuccess() {
        return;
    }

    @Override
    public int getDificulty() {
        return this.dificulty;
    }

    @Override
    public SkillType getSkillType() {
        return skill;
    }

    @Override
    public List<Thief> getCrew() {
        return mastermind.getSelectedCrew();
    }
}
