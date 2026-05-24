package com.pao.laboratory11.exercise1;

import java.util.*;

public class Transaction {
    public static final Set<String> HIGH_RISK_COUNTRIES = new HashSet<>(
            Arrays.asList("RU", "NG", "IR", "KP", "SY")
    );
    public static final int FLAG_THRESHOLD = 60;

    private final int id;
    private final double amount;
    private final String date;
    private final String country;
    private final String channel;

    public Transaction(int id, double amount, String date, String country, String channel) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.country = country;
        this.channel = channel;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getCountry() {
        return country;
    }

    public String getChannel() {
        return channel;
    }

    public int calculateScore() {
        int amountScore = 0;

        // Treptele exclusive top-down
        if (this.amount >= 5000) {
            amountScore = 70;
        } else if (this.amount >= 1000) {
            amountScore = 40;
        } else if (this.amount >= 500) {
            amountScore = 20;
        } else if (this.amount <= 100) {
            amountScore = 5;
        }

        int countryScore = HIGH_RISK_COUNTRIES.contains(this.country) ? 25 : 0;

        int channelScore = 0;
        switch (this.channel) {
            case "CRYPTO":
                channelScore = 30;
                break;
            case "WEB":
                channelScore = 15;
                break;
            case "APP":
                channelScore = 10;
                break;
            case "POS":
                channelScore = 5;
                break;
            case "ATM":
                channelScore = 0;
                break;
            default:
                channelScore = 0;
                break;
        }

        return amountScore + countryScore + channelScore;
    }

    public String getVerdict() {
        return calculateScore() >= FLAG_THRESHOLD ? "FLAG" : "ALLOW";
    }
}
