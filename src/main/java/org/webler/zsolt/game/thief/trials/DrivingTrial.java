package org.webler.zsolt.game.thief.trials;

import org.webler.zsolt.game.thief.Role;
import org.webler.zsolt.game.thief.Thief;
import org.webler.zsolt.game.thief.skill.Mastermind;
import org.webler.zsolt.game.thief.skill.SkillType;

import java.util.List;
import java.util.stream.Collectors;

public class DrivingTrial implements ITrial {

    public static final SkillType skill = SkillType.DRIVING;

    private final Mastermind mastermind;
    private final int dificulty;

    public DrivingTrial(Mastermind mastermind, int dificulty) {
        this.mastermind = mastermind;
        this.dificulty = dificulty;
    }
    public void onFailure() throws HeistFailureException {
        int driverCount = mastermind.getSelectedCrew()
                .stream()
                .filter(thief -> thief.getRoles().contains(Role.DRIVER))
                .collect(Collectors.toList())
                .size();
        if (driverCount < 2) {
            throw new HeistFailureException("Meghalt az egyetlen sofőr, a rablásnak vége!");
        }
        System.out.println("Az egyik sofőröd elveszett, a fele pénzed oda!");
        mastermind.getCurrentHeist().setReward(mastermind.getCurrentHeist().getReward() / 2);
    }

    @Override
    public void onSuccess() {
        System.out.println("Sikeres próba");
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
