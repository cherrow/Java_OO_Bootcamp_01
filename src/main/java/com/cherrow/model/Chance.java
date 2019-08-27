package com.cherrow.model;

public class Chance {

    private double possibility;

    private static final int MAX_POSSIBILITY = 1;

    private static final int MIN_POSSIBILITY = 0;

    public Chance(double possibility) {
        if (!isPossibilityValid(possibility)) {
            throw new IllegalArgumentException("概率必须在 0 到 1 之间！");
        }
        this.possibility = possibility;
    }

    private boolean isPossibilityValid(double possibility) {
        return possibility >= MIN_POSSIBILITY && possibility <= MAX_POSSIBILITY;
    }

    public Chance not() {
        return new Chance(MAX_POSSIBILITY - possibility);
    }

    public Chance and(Chance anotherChance) {
        return new Chance(possibility * anotherChance.possibility);
    }

    public Chance or(Chance anotherChance) {
        double orPossibility = MAX_POSSIBILITY
                - this.not().and(anotherChance.not()).possibility
                - this.and(anotherChance).possibility;
        return new Chance(orPossibility);
    }
}
