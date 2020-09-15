package com.codecool.gladiator.util;

import java.util.Random;

public class RandomUtils {

    private static final Random RANDOM = new Random();

    public int getIntSmallerOrEqualsTo(int max) {
        return RANDOM.nextInt((max) + 1);
    }

    public int getIntBetweenInclusive(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }

    public double getDoubleBetweenInclusive(double minDouble, double maxDouble) {
        int min = (int) (minDouble * 10);
        int max = (int) (maxDouble * 10);
        return (double)(RANDOM.nextInt((max - min) + 1) + min) / 10;
    }
}
