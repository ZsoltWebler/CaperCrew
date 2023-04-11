package org.webler.zsolt;

import org.webler.zsolt.game.thief.GameController;
import org.webler.zsolt.game.thief.skill.Mastermind;

public class Main {
    public static void main(String[] args) {


        Mastermind mastermind = new Mastermind(20, 2000);
        new GameController(mastermind).startGame();

    }


}