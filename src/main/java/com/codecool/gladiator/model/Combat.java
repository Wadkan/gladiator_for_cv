package com.codecool.gladiator.model;

import com.codecool.gladiator.model.gladiators.Gladiator;
import com.codecool.gladiator.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Combat class, used for simulating fights between pairs of gladiators
 */
public class Combat {
    //    private static Random RANDOM = new Random();
    private static RandomUtils RANDOM = new RandomUtils();

    private final Gladiator gladiator1;
    private final Gladiator gladiator2;

    private final List<String> combatLog;

    public Combat(Contestants contestants) {
        this.gladiator1 = contestants.gladiator1;
        this.gladiator2 = contestants.gladiator2;
        this.combatLog = new ArrayList<>();
    }

    /**
     * calculate the chance for hit
     * divide by own range, multiple by new full range
     * + 10, because new range starts from 10 (%)
     */
    int dexterityRange = (100 - 25) * 2;
    int chanceRange = 100 - 10;

    private double getAttackerChance(double attackerDex, double opponentDex) {
        return (attackerDex - opponentDex / dexterityRange * chanceRange + 10);
    }

    /**
     * toggle gladiator between gladiator1 and gladiator2
     */
    private Gladiator toggleGladiator(Gladiator gladiator) {
        return (gladiator1 == gladiator1) ? gladiator2 : gladiator1;
    }

    /**
     * Simulates the combat and returns the winner.
     * If one of the opponents is null, the winner is the one that is not null
     * If both of the opponents are null, the return value is null
     *
     * @return winner of combat
     */
    public Gladiator simulate() {
        // check for null gladiators:
        if (Objects.isNull(gladiator1) && Objects.isNull(gladiator2)) {
            return null;
        } else if (Objects.isNull(gladiator1)) {
            return gladiator2;
        } else if (Objects.isNull(gladiator2)) {
            return gladiator1;
        }

        // calculate change for hit in percent
        gladiator1.setChance(getAttackerChance(gladiator1.getDex(), gladiator2.getDex()));
        gladiator2.setChance(getAttackerChance(gladiator2.getDex(), gladiator1.getDex()));

        // choose starter gladiator:
        int whoseTheFirstTurn = RANDOM.getIntSmallerOrEqualsTo(1);
        Gladiator attacker = whoseTheFirstTurn == 0 ? gladiator1 : gladiator2;
        Gladiator opponent = toggleGladiator(attacker);

        // ATTACK
        while (true) {
            // hit or miss
            double chance = attacker.getChance();   // get chance
            double damage = attacker.getSp() * RANDOM.getDoubleBetweenInclusive(0.1, 0.5);   // get attacker strength


            // check the winner after each turn
            if (gladiator1.getCurrentHp() < 0) {
                return gladiator2;
            } else if (gladiator2.getCurrentHp() < 0) {
                return gladiator1;
            }

            // toggle attacker and gladiator for the next turn
            attacker = toggleGladiator(attacker);
            opponent = toggleGladiator(attacker);
        }

    }

    public Gladiator getGladiator1() {
        return gladiator1;
    }

    public Gladiator getGladiator2() {
        return gladiator2;
    }

    public String getCombatLog(String separator) {
        return String.join(separator, combatLog);
    }

}
