package org.webler.zsolt.game.thief.trials;

import org.webler.zsolt.game.thief.Thief;
import org.webler.zsolt.game.thief.skill.Mastermind;
import org.webler.zsolt.game.thief.skill.SkillType;

import java.util.List;
import java.util.Random;

public class VehicleSelectTrial implements ITrial {

    public static final SkillType skill = SkillType.VEHICLE_SELECT;

    private final Mastermind mastermind;
    private final int dificulty;

    public VehicleSelectTrial(Mastermind mastermind, int dificulty) {
        this.mastermind = mastermind;
        this.dificulty = dificulty;
    }

    @Override
    public void onFailure() throws HeistFailureException {

        int difficulty = new Random().nextInt(100) + 25;
        try {
            new WeaponKnowledgeTrial(mastermind, difficulty).tryTrial();
        }catch (HeistFailureException e){
            throw new HeistFailureException("A fegyverismeret próbán is elbuktál a teljes vagyon elveszik!");
        }

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
