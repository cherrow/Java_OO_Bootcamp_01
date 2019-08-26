package com.cherrow.model;

public class PossibleThing {

    private double possibility;

    public PossibleThing(double possibility) {
        if (!isPossibilityValid(possibility)) {
            throw new IllegalArgumentException("概率必须在 0 到 1 之间！");
        }
        this.possibility = possibility;
    }

    private boolean isPossibilityValid(double possibility) {
        return possibility >= 0 && possibility <= 1;
    }

    public double getHappenPossibility() {
        return possibility;
    }

    public double getNotHappenPossibility() {
        return 1 - possibility;
    }

    public PossibleThing and(PossibleThing anotherThing) {
        return new PossibleThing(possibility * anotherThing.getHappenPossibility());
    }

    public PossibleThing or(PossibleThing anotherThing) {
        double orPossibility = possibility + anotherThing.getHappenPossibility();
        orPossibility = orPossibility > 1 ? 1 : orPossibility;
        return new PossibleThing(orPossibility);
    }
}
