package com.codecool.gladiator.view;

import java.util.Scanner;

/**
 * Basic console view implementation
 */
public class ConsoleView implements Viewable {

    @Override
    public void display(String text) {
        System.out.println(text);
    }

    @Override
    public int getNumberBetween(int min, int max) {
        // Todo
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Give a number berween " + min + " and " + max + " : ");
            int input = sc.nextInt();
            if (min <= input && input <= max) {
                return input;
            }
        }
    }

}
