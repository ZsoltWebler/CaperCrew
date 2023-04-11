package org.webler.zsolt.game.thief.command.information;

import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.HashMap;

public class GameProgressInformation extends GameSceneInformation {


    public static final String SCENE_NAME = "Általános információk";
    private final HashMap<String, String> sceneInformation = new HashMap<>();
    private static final String FORMAT_STRING = "%1$30s \t %2$20s%n";

    public GameProgressInformation(Mastermind mastermind) {
        super(SCENE_NAME);

        int totalMoney = mastermind.getMoneyToCollect();
        int currentMoney= mastermind.getCurrentMoney();
        int totalHeistCount = mastermind.getNumberOfHeist();
        int currentHeistCount = mastermind.getCurrentHeistCount();

        sceneInformation.put("Teljes összeg", String.valueOf(currentMoney));
        sceneInformation.put("Összegyűjtendő összeg", String.valueOf(totalMoney));
        sceneInformation.put("Hiányzó összeg", String.valueOf(totalMoney - currentMoney));
        sceneInformation.put("Összes lehetőség", String.valueOf(totalHeistCount));
        sceneInformation.put("Elhasznált lehetőségek", String.valueOf(currentHeistCount));
        sceneInformation.put("Hátralévő lehetőségek", String.valueOf(totalHeistCount - currentHeistCount));
    }

    @Override
    public void printSceneInformation() {
        sceneInformation.forEach((key, value) -> System.out.format(FORMAT_STRING, key, value));
    }
}
