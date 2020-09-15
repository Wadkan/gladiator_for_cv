package com.codecool.gladiator.model.gladiators;

public abstract class Gladiator {

    private final String name;
    private final int baseHp;
    private final int baseSp;
    private final int baseDex;
    private int level;

    private double availableHp;
    private double availableSp;
    private double availableDex;

    /**
     * Constructor for Gladiators
     *
     * @param name    the gladiator's name
     * @param baseHp  the gladiator's base Health Points
     * @param baseSp  the gladiator's base Strength Points
     * @param baseDex the gladiator's base Dexterity Points
     * @param level   the gladiator's starting Level
     */
    public Gladiator(String name, int baseHp, int baseSp, int baseDex, int level) {
        this.name = name;
        this.baseHp = baseHp;
        this.baseSp = baseSp;
        this.baseDex = baseDex;
        this.level = level;

        this.availableHp = this.getBaseHp() * getLevel() * getHpMultiplier().getValue();
        this.availableSp = this.getBaseSp() * getLevel() * getSpMultiplier().getValue();
        this.availableDex = this.getBaseDex() * getLevel() * getDexMultiplier().getValue();

    }

    private double chance;

    public void setChance(double chance) {
        this.chance = chance;
    }

    public double getChance() {
        return chance;
    }

    /**
     * @return HP multiplier of the gladiator subclass
     */
    protected abstract Multiplier getHpMultiplier();

    /**
     * @return SP multiplier of the gladiator subclass
     */
    protected abstract Multiplier getSpMultiplier();

    /**
     * @return DEX multiplier of the gladiator subclass
     */
    protected abstract Multiplier getDexMultiplier();

    public double getHp() {
        // Todo: duplication.....
        return this.getBaseHp();
    }

    public double getAvailableHp() {
        return this.availableHp;
    }

    public double getSp() {
        return this.availableSp;
    }

    public double getDex() {
        return this.availableDex;
    }


    public int getBaseHp() {
        return baseHp;
    }

    public int getBaseSp() {
        return baseSp;
    }

    public int getBaseDex() {
        return baseDex;
    }

    /**
     * @return Gladiator's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the full name of the gladiator
     * assembled by the subtype and the name
     * (e.g. "Brutal Brutus" or "Archer Leo")
     *
     * @return the full name
     */
    public String getFullName() {
        return name;
    }

    public enum Multiplier {
        Low(0.75),
        Medium(1.0),
        High(1.25);

        private final double value;

        Multiplier(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }
    }

    public void levelUp() {
        this.level++;
    }

    public int getLevel() {
        return this.level;
    }

    public void decreaseHpBy(int decreaseValue) {
        this.availableHp -= decreaseValue;
    }

    public boolean isDead() {
        return this.availableHp < 1 ? true : false;
    }

    public void healUp() {
        this.availableHp = this.baseHp;
    }


}
