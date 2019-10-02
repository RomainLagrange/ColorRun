package com.example.colorrun;

import java.io.Serializable;

public class Joueur implements Serializable {
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
