package com.pao.laboratory03.exercise.model;

public enum Subject {
    PAOJ("PAOJ", 6),
    BD("BD", 5),
    SO("SO", 5),
    RC("RC", 4);

    private final String fullName;
    private final int credits;

    Subject(String fullName, int credits) {
        this.fullName = fullName;
        this.credits = credits;
    }

    @Override
    public String toString() {
        return name() + " (" + fullName + ", " + credits + " credite)";
    }
}
