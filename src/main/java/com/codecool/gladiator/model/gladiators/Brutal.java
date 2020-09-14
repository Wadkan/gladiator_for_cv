package com.codecool.gladiator.model.gladiators;

public class Brutal extends Gladiator {
    public Brutal(String name, int baseHp, int baseSp, int baseDex, int level) {
        super(name, baseHp, baseSp, baseDex, level);
    }

    @Override
    protected Multiplier getHpMultiplier() {
        // Todo
        return null;
    }

    @Override
    protected Multiplier getSpMultiplier() {
        // Todo
        return null;
    }

    @Override
    protected Multiplier getDexMultiplier() {
        // Todo
        return null;
    }

    @Override
    public int getAvailableHp() {
        return this.getBaseHp() * getLevel();   // Todo * getHpMultiplier()
    }

    @Override
    public int getAvailableSp() {
        return this.getBaseSp() * getLevel(); // Todo * getSpMultiplier()
    }

    @Override
    public int getAvailableDex() {
        return this.getBaseDex() * getLevel(); // Todo * getDexMultiplier()
    }

    @Override
    public String getFullName() {
        return "Brutal + " + super.getName();
    }
}
