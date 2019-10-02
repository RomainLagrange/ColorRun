package com.example.colorrun;

public class Joueur {
    private String name;
    private int score;

    public Joueur(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
