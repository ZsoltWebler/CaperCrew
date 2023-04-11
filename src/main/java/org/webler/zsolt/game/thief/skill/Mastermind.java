package org.webler.zsolt.game.thief.skill;

import lombok.Data;
import org.webler.zsolt.game.thief.Heist;
import org.webler.zsolt.game.thief.Thief;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Mastermind {

    private final int numberOfHeist;
    private final int moneyToCollect;
    private int currentMoney = 0;
    private int currentHeistCount = 0;

    private List<Thief> availableThieves;
    private List<Thief> selectedCrew;
    private Heist currentHeist;

    public Mastermind(int numberOfHeist, int moneyToCollect) {
        this.numberOfHeist = numberOfHeist;
        this.moneyToCollect = moneyToCollect;
        this.currentMoney = 0;
        this.currentHeistCount = 0;
        this.availableThieves = new ArrayList<>();
        this.selectedCrew = new ArrayList<>();
        this.currentHeist = null;
    }
}
