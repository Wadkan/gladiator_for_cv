package com.codecool.gladiator.model;

import com.codecool.gladiator.model.gladiators.Gladiator;
import com.codecool.gladiator.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Combat class, used for simulating fights between pairs of gladiators
 */
public class Combat {
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

    private double getAttackerChance(double attackerDex, double enemyDex) {
        return (attackerDex - enemyDex / dexterityRange * chanceRange + 10);
    }

    /**
     * toggle gladiator between gladiator1 and gladiator2
     */
    private Gladiator toggleGladiator(Gladiator gladiator) {
        return (gladiator == gladiator1) ? gladiator2 : gladiator1;
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

        // calculate chance for hit in percent
        gladiator1.setChance(getAttackerChance(gladiator1.getDex(), gladiator2.getDex()));
        gladiator2.setChance(getAttackerChance(gladiator2.getDex(), gladiator1.getDex()));

        // choose starter gladiator:
        int whoseTheFirstTurn = RANDOM.getIntSmallerOrEqualsTo(1);
        Gladiator attacker = whoseTheFirstTurn == 0 ? gladiator1 : gladiator2;
        Gladiator enemy = toggleGladiator(attacker);
        Gladiator winner = null;

        // ATTACK
        while (true) {
            // hit or miss
            double chance = attacker.getChance();   // get chance
            int damage = (int) (attacker.getSp() * RANDOM.getDoubleBetweenInclusive(0.1, 0.5));   // get attacker strength
            double numberForChance = RANDOM.getIntBetweenInclusive(1, 100);

            String logMessage = "";
            if (numberForChance <= chance) {     // hit the enemy
                enemy.decreaseHpBy(damage);
                logMessage = String.format("%s deals %d damage", attacker.getName(), damage);
            } else {
                logMessage = String.format("%s missed", attacker.getName());
            }
            combatLog.add(logMessage);

            // check the winner after each turn
            if (gladiator1.getAvailableHp() < 0) {
                logMessage = String.format("%s has died, %s wins!", gladiator1.getName(), gladiator2.getName());
                combatLog.add(logMessage);
                winner = gladiator2;
            }
            if (gladiator2.getAvailableHp() < 0) {
                logMessage = String.format("%s has died, %s wins!", gladiator2.getName(), gladiator1.getName());
                combatLog.add(logMessage);
                winner = gladiator1;
            }
            if (winner != null) {
                gladiator1.healUp();
                gladiator2.healUp();
                return winner;
            }

            // toggle attacker and gladiator for the next turn
            attacker = toggleGladiator(attacker);
            enemy = toggleGladiator(attacker);
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
