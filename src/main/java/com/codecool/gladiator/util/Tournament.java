package com.codecool.gladiator.util;

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

    /**
     * Constructor with initial value
     *
     * @param contestants the initial value to be added to the tree
     */
    public Tournament(Contestants contestants) {
        add(contestants);
    }

    /**
     * Constructor with initial list of values
     *
     * @param values the list of values to be added to the tree
     */
    public Tournament(List<Contestants> values) {
        addAll(values);
    }

    /**
     * Getter for the value (must be null if there are further branches)
     *
     * @return the value
     */
    public Contestants getContestants() {
        return contestants;
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
        contestants = value;
        size = 1;
    }

    /**
     * Adds multiple values to the tree
     *
     * @param values the list of values to be added to the tree
     */
    public void addAll(List<Contestants> values) {
        contestants = null; // this will be the winner at the end
        size = values.size();

        if (size == 2) {
            leftBranch = new Tournament(values.get(0));
            rightBranch = new Tournament(values.get(1));
        } else if (size == 4) {
            leftBranch.leftBranch = new Tournament(values.get(0));
            leftBranch.rightBranch = new Tournament(values.get(1));
            rightBranch.leftBranch = new Tournament(values.get(3));
            rightBranch.rightBranch = new Tournament(values.get(4));
        } else if (size == 8) {
            leftBranch.leftBranch.leftBranch = new Tournament(values.get(0));
            leftBranch.leftBranch.rightBranch = new Tournament(values.get(1));
            leftBranch.rightBranch.leftBranch = new Tournament(values.get(2));
            leftBranch.rightBranch.rightBranch = new Tournament(values.get(3));
            rightBranch.leftBranch.leftBranch = new Tournament(values.get(4));
            rightBranch.leftBranch.rightBranch = new Tournament(values.get(5));
            rightBranch.rightBranch.leftBranch = new Tournament(values.get(6));
            rightBranch.rightBranch.rightBranch = new Tournament(values.get(7));
        }

        // left = (left == true) ? false : true;


//        Tournament actualBranch;
//        for (int i = 0; i < size; i++) {
//            if (left) {
//                leftBranch = new Tournament(values.get(i));
//            } else {
//                rightBranch = new Tournament(values.get(i));
//            }
//            left = (left == true) ? false : true;
//        }
    }
}
