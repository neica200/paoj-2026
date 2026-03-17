package com.pao.laboratory03.enums;

public enum Priority {
    LOW(1, "green") {
        @Override
        public String getEmoji() { return "🟢"; }
    },
    MEDIUM(2, "yellow") {
        @Override
        public String getEmoji() { return "🟡"; }
    },
    HIGH(3, "orange") {
        @Override
        public String getEmoji() { return "🟠"; }
    },
    CRITICAL(4, "red") {
        @Override
        public String getEmoji() { return "🔴"; }
    };

    private final int level;
    private final String color;

    // Constructor privat (implicit privat în Enum)
    Priority(int level, String color) {
        this.level = level;
        this.color = color;
    }

    // Getteri
    public int getLevel() { return level; }
    public String getColor() { return color; }

    // Metodă abstractă pe care fiecare constantă trebuie să o implementeze
    public abstract String getEmoji();
}
