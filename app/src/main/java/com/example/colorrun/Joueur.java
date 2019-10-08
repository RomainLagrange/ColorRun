package com.example.colorrun;

import java.io.Serializable;

public class Joueur implements Serializable {
    private String name;
    private int score;
    private String level;

    public Joueur(String name, int score, String level) {
        this.name = name;
        this.score = score;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getLevel() {
        return level;
    }
}
