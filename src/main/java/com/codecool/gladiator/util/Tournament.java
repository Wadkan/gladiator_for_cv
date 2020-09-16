package com.codecool.gladiator.util;

import com.codecool.gladiator.model.Combat;
import com.codecool.gladiator.model.Contestants;

import java.util.List;

/**
 * Custom implementation of the binary tree data structure
 */
public class Tournament {

    private Contestants contestants;
    private Tournament leftBranch;
    private Tournament rightBranch;
    private int size;
    /**
     * A boolean value used for navigating between left and right branches when adding new values
     */
    private boolean left = true;

    public Tournament(){
        this.contestants = null;
        size = 0;
    }

    /**
     * Constructor with initial value
     *
     * @param contestants the initial value to be added to the tree
     */
    public Tournament(Contestants contestants) {
        this.contestants = contestants;
        size = 1;
    }

    /**
     * Constructor with initial list of values
     *
     * @param values the list of values to be added to the tree
     */
    public Tournament(List<Contestants> values) {
        if (values.size() == 1) {
            add(values.get(0));
        } else {
            addAll(values);
        }
    }

    /**
     * Getter for the value (must be null if there are further branches)
     *
     * @return the value
     */
    public Contestants getContestants() {
        return this.contestants;
    }

    /**
     * Getter for the left branch
     *
     * @return the left branch
     */
    public Tournament getLeftBranch() {
        return leftBranch;
    }

    /**
     * Getter for the right branch
     *
     * @return the right branch
     */
    public Tournament getRightBranch() {
        return rightBranch;
    }

    /**
     * Setter for current contestants
     *
     * @param contestants contestants of the Tournament
     */
    public void setContestants(Contestants contestants) {
        this.contestants = contestants;
    }

    /**
     * Returns the number of values put in the tree
     *
     * @return the size of the tree
     */
    public int size() {
        return size;
    }

    /**
     * Adds a new value to the tree
     *
     * @param value the value to be added to the tree
     */
    public void add(Contestants value) {
        this.contestants = value;
        size = 1;
    }

    private List<Contestants> recursiveAdd(Tournament mainBranch, List<Contestants> values, int n) {
        Contestants value;
//        Contestants nullContestant = new Contestants(null, null);

        if ((int) Math.pow(2, n) < size) {
            // PARENT LEVEL(S)
//            value = nullContestant;
            mainBranch.leftBranch = new Tournament();
            values = recursiveAdd(mainBranch.leftBranch, values, n + 1);
            mainBranch.rightBranch = new Tournament();
            values = recursiveAdd(mainBranch.rightBranch, values, n + 1);
        } else {
            // DEEPEST LEVEL
            value = values.get(0);
            values.remove(0);
            mainBranch.leftBranch = new Tournament(value);
            value = values.get(0);
            values.remove(0);
            mainBranch.rightBranch = new Tournament(value);
        }
        return values;
    }

    /**
     * Adds multiple values to the tree
     *
     * @param values the list of values to be added to the tree
     */
    public void addAll(List<Contestants> values) {
        size = values.size();
        left = true;

        values = recursiveAdd(this, values, 1);

    }
}
