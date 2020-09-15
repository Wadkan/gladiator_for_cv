package com.codecool.gladiator.controller;

import com.codecool.gladiator.model.Combat;
import com.codecool.gladiator.model.Contestants;
import com.codecool.gladiator.model.gladiators.*;
import com.codecool.gladiator.util.Tournament;
import com.codecool.gladiator.view.Viewable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Colosseum {

    public static final int MIN_TOURNAMENT_STAGES = 1;
    public static final int MAX_TOURNAMENT_STAGES = 10;

    private final Viewable view;
    private final GladiatorFactory gladiatorFactory;
    private int stages = 2;

    List<Tournament> tournamentTree = null;

    public Colosseum(Viewable view, GladiatorFactory gladiatorFactory) {
        this.view = view;
        this.gladiatorFactory = gladiatorFactory;
    }

    /**
     * Runs the Tournament simulation
     */
    public void runSimulation() {
        var numberOfGladiators = (int) Math.pow(2, stages);
        var gladiators = generateGladiators(numberOfGladiators);    // gladiators SOLO
        var contestants = splitGladiatorsIntoPairs(gladiators);   // list of gladiator PAIRs - contestant - a pair
        var tournamentTree = new Tournament(contestants);                        // tree of pairs
        var champion = getChampion(tournamentTree);                     // one winner
        announceChampion(champion);

        // The following line chains the above lines:
        // announceChampion(getChampion(new BinaryTree<>(generateGladiators((int) Math.pow(2, stages)))));
    }

    private List<Gladiator> generateGladiators(int numberOfGladiators) {
        List<Gladiator> gladiators = new ArrayList<>();
        for (int i = 0; i < numberOfGladiators; i++) {
            gladiators.add(gladiatorFactory.generateRandomGladiator());
        }
        introduceGladiators(gladiators);
        return gladiators;
    }

    private List<Contestants> splitGladiatorsIntoPairs(List<Gladiator> gladiators) {
        LinkedList<Contestants> gladiatorsInPairs = new LinkedList<>();
        for (int i = 0; i < gladiators.size(); i += 2) {
            Contestants contestant = new Contestants(gladiators.get(i), gladiators.get(i + 1));
            gladiatorsInPairs.add(contestant);
        }
        return gladiatorsInPairs;
    }


    private void doTheCombat(Tournament actualLevel) {
        // LEVEL 2 – the deepest
        Combat combatLeft;
        Combat combatRight;

        combatLeft = new Combat(actualLevel.getLeftBranch().getContestants());
        combatRight = new Combat(actualLevel.getRightBranch().getContestants());
        Contestants winners = new Contestants(simulateCombat(combatLeft), simulateCombat(combatRight));
        actualLevel.setContestants(winners);
    }

    private Gladiator getChampion(Tournament tournament) {
        // Todo - call simulateCombat as many times as needed

        boolean continoueTheTournament = true;
        boolean noContestantsTheLevelBelow;
        boolean left = true;
        Tournament actualLevel;
        Contestants actualLeftContestants;
        Contestants actualRightContestants;
        Combat combatLeft;
        Combat combatRight;

        int size = tournament.size();
        if (size == 2) {
            // LEVEL 2 – the deepest
            combatLeft = new Combat(tournament.getLeftBranch().getContestants());
            combatRight = new Combat(tournament.getRightBranch().getContestants());
            Contestants winners = new Contestants(simulateCombat(combatLeft), simulateCombat(combatRight));
            tournament.setContestants(winners);

            // LEVEL 1 – the final
            Combat combat = new Combat(tournament.getContestants());
            return simulateCombat(combat);
        } else return null;


//        while (continoueTheTournament) {
//            if (tournament.getContestants() != null) {       // if final tournament
//                Combat combat = new Combat(tournament.getContestants());
//                return simulateCombat(combat);
//            }
//
//            actualLevel = tournament;
//            noContestantsTheLevelBelow = true;
//            while (noContestantsTheLevelBelow) {
//                actualLeftContestants = actualLevel.getLeftBranch().getContestants();
//                actualRightContestants = actualLevel.getRightBranch().getContestants();
//                if (actualLeftContestants != null && actualRightContestants != null) {    // check the leftbranch and rightbranch for contestants
//                    combatLeft = new Combat(actualLeftContestants);
//                    combatRight = new Combat(actualRightContestants);
//                    Contestants winners = new Contestants(simulateCombat(combatLeft), simulateCombat(combatRight));
//                    actualLevel.setContestants(winners);
//                } else {                                             // there are contestants in THIS level
//                    actualLevel = tournament.getLeftBranch();
//                    left = (left == true) ? false : true;
//                }
//            }
//        }
//        return null;
    }

    private Gladiator simulateCombat(Combat combat) {
        Gladiator gladiator1 = combat.getGladiator1();
        Gladiator gladiator2 = combat.getGladiator2();
        announceCombat(gladiator1, gladiator2);

        // Todo
        System.out.println("before combat");
        Gladiator winner = combat.simulate();
        Gladiator loser = winner == gladiator1 ? gladiator2 : gladiator1;
        displayCombatLog(combat);
        announceWinnerAndLoser(winner, loser);
        return winner;
    }

    public void welcome() {
        view.display("Ave Caesar, and welcome to the Colosseum!");
    }

    public void welcomeAndAskForStages() {
        welcome();
        view.display("How many stages of the Tournament do you wish to watch? (1-10)");
        stages = view.getNumberBetween(MIN_TOURNAMENT_STAGES, MAX_TOURNAMENT_STAGES);
    }

    private void introduceGladiators(List<Gladiator> gladiators) {
        view.display(String.format("\nWe have selected Rome's %d finest warriors for today's Tournament!", gladiators.size()));
        for (Gladiator gladiator : gladiators) {
            view.display(String.format(" - %s", gladiator.getFullName()));
        }
        view.display("\n\"Ave Imperator, morituri te salutant!\"");
    }

    private void announceCombat(Gladiator gladiator1, Gladiator gladiator2) {
        view.display(String.format("\nDuel %s versus %s:", gladiator1.getName(), gladiator2.getName()));
        view.display(String.format(" - %s (%s/%s HP, %s SP, %s DEX, %s LVL) ", gladiator1.getFullName(), gladiator1.getAvailableHp(), gladiator1.getBaseHp(), gladiator1.getSp(), gladiator1.getDex(), gladiator1.getLevel()));
        view.display(String.format(" - %s (%s/%s HP, %s SP, %s DEX, %s LVL) ", gladiator2.getFullName(), gladiator2.getAvailableHp(), gladiator2.getBaseHp(), gladiator2.getSp(), gladiator2.getDex(), gladiator2.getLevel()));
    }

    private void displayCombatLog(Combat combat) {
        view.display(String.format(" - %s", combat.getCombatLog(", ")));
    }

    private void announceWinnerAndLoser(Gladiator winner, Gladiator loser) {
        view.display(String.format("%s has died, %s wins!", loser.getFullName(), winner.getFullName()));
    }

    private void announceChampion(Gladiator champion) {
        if (champion != null) {
            view.display(String.format("\nThe Champion of the Tournament is %s!", champion.getFullName()));
        } else {
            view.display("\nHave mercy, Caesar, the Tournament will start soon!");
        }
    }

}
