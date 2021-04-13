package org.example;

import java.util.ArrayList;

public class Recommender {

    private ArrayList<User> users;

    public Recommender() {
        //Default Constructor
        //TODO: Code AI Logic
    }

    public int sim(User active, User neighbor) {
        //TODO: Calculate the similarity of two users either by Pearson or Cosine
        return 0;
    }

    public int predict(User active, User nearestNeighbor) {
        //TODO: Determine a predictive value for the active user's rating based upon the nearest neighbor
        return 0;
    }
}
