package org.webler.zsolt.game.thief;

import org.webler.zsolt.game.thief.skill.Mastermind;
import org.webler.zsolt.game.thief.skill.SkillType;
import org.webler.zsolt.game.thief.trials.DrivingTrial;
import org.webler.zsolt.game.thief.trials.HeistFailureException;

import java.util.*;

public class TrialHandlerSingleton {

    private static TrialHandlerSingleton INSTANCE;

    private TrialHandlerSingleton() {
    }

    public static TrialHandlerSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TrialHandlerSingleton();
        }

        return INSTANCE;
    }

    public void doHeist(Mastermind mastermind) throws HeistFailureException {
        Random rnd = new Random();
        List<Trial> trials = Arrays.asList(new Trial(SkillType.DRIVING, rnd.nextInt(100) + 250));
        for (Trial trial : trials) {
            System.out.println("A következő próba: " + trial);
            switch (trial.getType()) {
                case DRIVING:
                    new DrivingTrial(mastermind, trial.difficulty).tryTrial();
            }


        }
    }

    private void onSuccess() {
        System.out.println("\tSikeres próba");
    }

    private void onFailure() {
        System.out.println("\tSikertelen próba");
    }

    private void doTrials(Map<SkillType, List<Thief>> thievesBySkillType, Heist heist) {
        //List<Trial> trials = heist.getTrials();

    }

    private Map<SkillType, List<Thief>> getThievesForTrials(List<Thief> crew) {
        Map<SkillType, List<Thief>> thievesBySkillType = new HashMap<>();

        for (SkillType skill : SkillType.values()) {
            for (Thief thief : crew) {
                for (Role role : thief.getRoles()) {
                    if (role.skills.contains(skill)) {
                        List<Thief> thieves = thievesBySkillType.get(skill);
                        if (thieves != null) {
                            List<Thief> newThieves = new ArrayList<>(thieves);
                            if (!newThieves.contains(thief)) {
                                newThieves.add(thief);
                                thievesBySkillType.put(skill, newThieves);
                            }
                        } else {
                            thievesBySkillType.put(skill, Arrays.asList(thief));
                        }
                    }
                }
            }
        }
        return thievesBySkillType;
    }


}
