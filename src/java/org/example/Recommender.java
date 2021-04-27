package org.example;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Recommender {

    private ArrayList<User> users;

    private double lastNearestNeighborSimilarity = -1.0;
    private double lastPrediction = 0.0;

    public Recommender() {
        //create some other users for the recommender to go by
        User dummyOne = new User("Tim");
        User dummyTwo = new User("Tom");
        User dummyThree = new User("Jim");
        User dummyFour = new User("John");
        User dummyFive = new User("Bob");
        User dummySix = new User("Rob");

        dummyOne.addMovieRating("Star Wars I", 4.0);
        dummyOne.addMovieRating("Star Wars II", 4.3);
        dummyOne.addMovieRating("Star Wars III", 8.6);
        dummyOne.addMovieRating("Harry Potter I", 8.9);
        dummyOne.addMovieRating("Harry Potter II", 9.6);
        dummyOne.addMovieRating("Harry Potter III", 9.4);

        dummyTwo.addMovieRating("Star Wars I", 8.6);
        dummyTwo.addMovieRating("Star Wars II", 8.8);
        dummyTwo.addMovieRating("Star Wars III", 2.0);
        dummyTwo.addMovieRating("Harry Potter I", 7.6);
        dummyTwo.addMovieRating("Harry Potter II", 7.9);
        dummyTwo.addMovieRating("Harry Potter III", 7.0);

        dummyThree.addMovieRating("Star Wars I", 9.2);
        dummyThree.addMovieRating("Star Wars II", 6.2);
        dummyThree.addMovieRating("Star Wars III", 9.4);
        dummyThree.addMovieRating("Harry Potter I", 8.1);
        dummyThree.addMovieRating("Harry Potter II", 6.5);
        dummyThree.addMovieRating("Harry Potter III", 5.5);

        dummyFour.addMovieRating("Star Wars I", 5.5);
        dummyFour.addMovieRating("Star Wars II", 6.7);
        dummyFour.addMovieRating("Star Wars III", 6.9);
        dummyFour.addMovieRating("Harry Potter I", 4.7);
        dummyFour.addMovieRating("Harry Potter II", 1.7);
        dummyFour.addMovieRating("Harry Potter III", 3.3);

        dummyFive.addMovieRating("Star Wars I", 9.0);
        dummyFive.addMovieRating("Star Wars II", 8.5);
        dummyFive.addMovieRating("Star Wars III", 9.6);
        dummyFive.addMovieRating("Harry Potter I", 8.7);
        dummyFive.addMovieRating("Harry Potter II", 9.1);
        dummyFive.addMovieRating("Harry Potter III", 9.3);

        dummySix.addMovieRating("Star Wars I", 3.6);
        dummySix.addMovieRating("Star Wars II", 3.2);
        dummySix.addMovieRating("Star Wars III", 4.2);
        dummySix.addMovieRating("Harry Potter I", 9.0);
        dummySix.addMovieRating("Harry Potter II", 9.2);
        dummySix.addMovieRating("Harry Potter III", 9.4);

        users = new ArrayList<>();

        addUser(dummyOne);
        addUser(dummyTwo);
        addUser(dummyThree);
        addUser(dummyFour);
        addUser(dummyFive);
        addUser(dummySix);
    }

    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Get the nearest neighbor to this input user from the sim function
     * @param active The User for whom to find the nearest neighbor
     * @return A User representing the nearest neighbor
     */
    public User getNearestNeighbors(User active) {
        double bestSimilarity = -1.0;
        double currentSimilarity = -1.0;
        User bestUser = users.get(0);

        for(User neighbor : users) {
            currentSimilarity = sim(active, neighbor);

            if(currentSimilarity > bestSimilarity) {
                bestSimilarity = currentSimilarity;
                bestUser = neighbor;
            }
        }

        return bestUser;
    }

    /**
     * Calculates the similarity of two users by Pearson Correlation
     * @param active The active user
     * @param neighbor The neighbor to determine similarity
     * @return A similarity value between -1 and 1
     */
    public double sim(User active, User neighbor) {
        double activeAverageRating = active.getAverageRating();
        double neighborAverageRating = neighbor.getAverageRating();

        int comparisonSize = Math.max(active.numMoviesRated(), neighbor.numMoviesRated());
        User comparisonUser;

        if(comparisonSize == active.numMoviesRated())
            comparisonUser = active;
        else
            comparisonUser = neighbor;

        double currentNormalActive = 0.0;
        double currentNormalNeighbor = 0.0;

        double numerator = 0.0;
        double denominator = 0.0;

        double denomLeftSqrt = 0.0;
        double denomRightSqrt = 0.0;
        String currentMovie;
        //Pearson Correlation
        for(int i = 0; i < comparisonSize; i++) {
            currentMovie = comparisonUser.getMovie(i);

            currentNormalActive = active.getMovieRating(currentMovie) - activeAverageRating;
            currentNormalNeighbor = neighbor.getMovieRating(currentMovie) - neighborAverageRating;

            numerator += currentNormalActive * currentNormalNeighbor;

            denomLeftSqrt += Math.pow(currentNormalActive, 2);
            denomRightSqrt += Math.pow(currentNormalNeighbor, 2);
        }

        denominator = Math.sqrt(denomLeftSqrt) * Math.sqrt(denomRightSqrt);

        return (numerator / denominator);
    }

    /**
     * Make a rating prediction for the average user based upon the nearest neighbor.
     * Neighbor selected must have a rating for the movie we're predicting for
     * @param movieTitle The movie to generate a predicted rating for
     * @param active The active user
     * @return A movie recommendation
     */
    public double predict(String movieTitle, User active) {
        ArrayList<User> neighborhood = new ArrayList<>();

        double best = -1.0;
        double current = 0.0;
        User bestNeighbor = null;
        while(neighborhood.size() < 4) {
            for (User neighbor : users) {
                if(!neighborhood.contains(neighbor)) {
                    current = sim(active, neighbor);
                    if (current > best) {
                        best = current;
                        bestNeighbor = neighbor;
                    }
                }
            }

            neighborhood.add(bestNeighbor);

            if(best > lastNearestNeighborSimilarity)
                lastNearestNeighborSimilarity = best;

            best = -1.0;
            bestNeighbor = null;
        }

        double activeAverageRating = active.getAverageRating();
        double prediction = 0.0;

        double similarity;
        double neighborNormal;
        double numerator = 0.0;
        double denominator = 0.0;
        for(User neighbor : neighborhood) {
            similarity = sim(active, neighbor);
            neighborNormal = neighbor.getMovieRating(movieTitle) - neighbor.getAverageRating();

            numerator += (similarity * neighborNormal);
            denominator += similarity;
        }

        prediction = (numerator / denominator);

        lastPrediction = prediction + activeAverageRating;
        return prediction + activeAverageRating;
    }

    /**
     * Generate a movie recommendation for the input user
     * @param active The User for whom to generate the recommendation
     * @return A String containing the title of the movie
     */
    public String movieRecommendation(User active) {
        double bestRecommendation = 0.0;
        double currentRecommendation;
        String bestMovie = "You've seen it all! Wow!";

        for(String movie : MovieMap.getMovies()) {
            //Don't recommend it if they've already seen it
            if(active.hasSeenMovie(movie))
                continue;

            currentRecommendation = predict(movie, active);

            if(currentRecommendation > bestRecommendation) {
                bestRecommendation = currentRecommendation;
                bestMovie = movie;
            }
        }

        return bestMovie;
    }

    public double getLastNearestNeighborSimilarity() {
        return lastNearestNeighborSimilarity;
    }

    public double getLastPrediction() {
        return lastPrediction;
    }
}
