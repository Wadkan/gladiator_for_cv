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

    private void addTwoSubBranch(Tournament mainBranch) {
        Contestants contestants = null;
        mainBranch.leftBranch = new Tournament(contestants);
        mainBranch.rightBranch = new Tournament(contestants);
        mainBranch.contestants = null;
    }

    private List<Contestants> recursiveAdd(Tournament mainBranch, List<Contestants> values, int n) {
        Contestants value;
        Contestants nullContestant = new Contestants(null, null);

        if ((int) Math.pow(2, n) < size) {
            // PARENT LEVEL(S)
            value = nullContestant;
            mainBranch.leftBranch = new Tournament(value);
            values = recursiveAdd(mainBranch.leftBranch, values, n + 1);
            mainBranch.rightBranch = new Tournament(value);
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

//        if (size >= 2) {
//            System.out.println("---> 2");
//            leftBranch = new Tournament(values.get(0));
//            rightBranch = new Tournament(values.get(1));
//        }
//        if (size >= 4) {
//            System.out.println("---> 4");
//            leftBranch.leftBranch = new Tournament(values.get(0));
//            leftBranch.rightBranch = new Tournament(values.get(1));
//            rightBranch.leftBranch = new Tournament(values.get(2));
//            rightBranch.rightBranch = new Tournament(values.get(3));
//            leftBranch.contestants = null;
//            rightBranch.contestants = null;
//        }
//        if (size >= 8) {
//            System.out.println("---> 8");
//            leftBranch.leftBranch.leftBranch = new Tournament(values.get(0));
//            leftBranch.leftBranch.rightBranch = new Tournament(values.get(1));
//            leftBranch.rightBranch.leftBranch = new Tournament(values.get(2));
//            leftBranch.rightBranch.rightBranch = new Tournament(values.get(3));
//            rightBranch.leftBranch.leftBranch = new Tournament(values.get(4));
//            rightBranch.leftBranch.rightBranch = new Tournament(values.get(5));
//            rightBranch.rightBranch.leftBranch = new Tournament(values.get(6));
//            rightBranch.rightBranch.rightBranch = new Tournament(values.get(7));
//
//            leftBranch.leftBranch.contestants = null;
//            leftBranch.rightBranch.contestants = null;
//            rightBranch.leftBranch.contestants = null;
//            rightBranch.rightBranch.contestants = null;
//        }


//        Tournament currentLevel;
//        Tournament leftSide;
//        Tournament rightSide;
//        boolean continoue = true;
//
//        for (int i = 2; i <= size; i++) {
//            currentLevel = this;
//            continoue = true;
//            left = true;
//            while (continoue) {
//                for (int multiple = 1; multiple < i; multiple++) {
//                    if (left) {         // call it for the left side
//                        currentLevel = currentLevel.leftBranch;
//                    } else {         // call it for the right side
//                        currentLevel = currentLevel.rightBranch;
//                        continoue = false;
//                    }
//                }
//                left = (left == true) ? false : true;
//                currentLevel = new Tournament(values.get(i++));     // divide main branch and load with contestants
//            }
//        }

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
