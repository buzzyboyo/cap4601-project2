package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class User {

    private final double RATING_MEDIAN = 5;

    private String username;

    /*

    TODO: Use these?

    //These three variables will dictate a similarity based upon a cold start
    private int age;
    private ArrayList<String> favoriteTags;
    private ArrayList<String> favoriteMovieType;
    */


    private HashMap<String, Double> movieRatings;

    public User(String name) {
        this.username = name;
        //this.age = age;

        movieRatings = new HashMap<>();
    }

    public void loadUser(String name) {
        //TODO: Load stored user data
    }

    public void addFavoriteTag(String tag) {
        //TODO: Code user tag favoriting
    }

    public void addFavoriteType(String type) {
        //TODO: Code user movie type preference
    }

    public void addMovieRating(String movieName, double rating) {
        if(rating < 0.0)
            rating = 0.0;
        else if(rating >= 10.0)
            rating = 10.0;

        movieRatings.put(movieName, rating);
    }

    public String getName() {
        return this.username;
    }

    public double getMovieRating(String movieName) {
        //if the rating does not exist, return the average
        if(!movieRatings.containsKey(movieName)) {
            return getAverageRating();
        }

        return movieRatings.get(movieName);
    }

    public boolean hasSeenMovie(String movieName) {
        if(!movieRatings.containsKey(movieName))
            return false;

        return true;
    }

    public String getMovie(int index) {
        int i = 0;

        if(index > movieRatings.size())
            return "";

        for(String name : movieRatings.keySet()) {
            if(i == index)
                return name;

            i++;
        }

        return "";
    }

    public ArrayList<String> getMoviesByRating(int rating) {
        ArrayList<String> matches = new ArrayList<>();

        for(String name : movieRatings.keySet()) {
            if(movieRatings.get(name) == rating)
                matches.add(name);
        }

        return matches;
    }

    public double getAverageRating() {
        int total = 0;

        for(String name : movieRatings.keySet()) {
            total += movieRatings.get(name);
        }

        return total / movieRatings.size();
    }

    public int numMoviesRated() { return movieRatings.size(); }
}
