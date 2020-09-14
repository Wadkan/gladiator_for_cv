package com.codecool.gladiator.model.gladiators;

import com.codecool.gladiator.util.RandomUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

public class GladiatorFactory {
    Random RANDOM = new Random();

    private List<String> names;

    public GladiatorFactory(String fileOfNames) {
        try {
            File file = new File(getClass().getClassLoader().getResource(fileOfNames).getFile());
            names = Files.readAllLines(file.toPath());
        } catch (IOException | NullPointerException e) {
            System.out.println("Names file not found or corrupted!");
            System.exit(1);
        }
    }

    /**
     * Picks a random name from the file given in the constructor
     *
     * @return gladiator name
     */
    private String getRandomName() {
        int randomNameRowNumber = RANDOM.nextInt(names.size());
        return names.get(randomNameRowNumber);
    }

    /**
     * Instantiates a new gladiator with random name and type.
     * Creating an Archer, an Assassin, or a Brutal has the same chance,
     * while the chance of creating a Swordsman is the double of the chance of creating an Archer.
     *
     * @return new Gladiator
     */
    public Gladiator generateRandomGladiator() {
//        RandomUtils RANDOM = new RandomUtils();
        int gladiatorType = RANDOM.nextInt(5);
        int baseHp = RANDOM.nextInt(101 - 25) + 25; // range: 25-100
        int baseSp = RANDOM.nextInt(101 - 25) + 25;
        int baseDex = RANDOM.nextInt(101 - 25) + 25;
        int baseLevel = RANDOM.nextInt(6 - 1) + 1;  // range: 1-5


        System.out.println("random gladiatorType is: " + gladiatorType);

        switch (gladiatorType) {
            case 0:
                return new Archer(getRandomName(), baseHp, baseSp, baseDex, baseLevel);
            case 1:
                return new Assassin(getRandomName(), baseHp, baseSp, baseDex, baseLevel);
            case 2:
                return new Brutal(getRandomName(), baseHp, baseSp, baseDex, baseLevel);
            case 3:
            case 4:
                return new Swordsman(getRandomName(), baseHp, baseSp, baseDex, baseLevel);
            default:
                throw new IllegalStateException("Unexpected value: " + gladiatorType);
        }
    }
}
