package org.webler.zsolt.game.thief.command.information;

public abstract class GameSceneInformation {

    private final String sceneName;

    protected GameSceneInformation(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getSceneName() {
        return sceneName;
    }

    public abstract void printSceneInformation();

}
