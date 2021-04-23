package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Recommender {

    private ArrayList<User> users;

    public Recommender() {
        //create some other users for the recommender to go by
        User dummyOne = new User("Tim");
        User dummyTwo = new User("Tom");
        User dummyThree = new User("Jim");
        User dummyFour = new User("John");

        dummyOne.addMovieRating("Toy Story", 4.0);
        dummyOne.addMovieRating("Toy Story 2", 4.3);
        dummyOne.addMovieRating("Harry Potter and the Sorcerer's Stone", 8.6);
        dummyOne.addMovieRating("Harry Potter and the Prisoner of Azkaban", 8.9);
        dummyOne.addMovieRating("Harry Potter and the Goblet of Fire", 9.6);

        dummyTwo.addMovieRating("Toy Story", 8.6);
        dummyTwo.addMovieRating("Toy Story 2", 8.8);
        dummyTwo.addMovieRating("Harry Potter and the Sorcerer's Stone", 2.0);
        dummyTwo.addMovieRating("The Lion King", 7.6);
        dummyTwo.addMovieRating("The Jungle Book", 7.9);

        dummyThree.addMovieRating("Star Wars Episode I", 9.2);
        dummyThree.addMovieRating("Star Wars Episode II", 6.2);
        dummyThree.addMovieRating("Star Wars Episode III", 9.4);
        dummyThree.addMovieRating("The Lion King", 8.1);
        dummyThree.addMovieRating("1917", 6.5);

        dummyFour.addMovieRating("Toy Story", 5.5);
        dummyFour.addMovieRating("1917", 6.7);
        dummyFour.addMovieRating("The Lion King", 6.9);
        dummyFour.addMovieRating("Star Wars Episode I", 4.7);
        dummyFour.addMovieRating("Harry Potter and the Goblet of Fire", 1.7);

        addUser(dummyOne);
        addUser(dummyTwo);
        addUser(dummyThree);
        addUser(dummyFour);
    }

    public void addUser(User user) {
        users.add(user);
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
        for(int i = 0; i < 4; i++) {
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
     * @param nearestNeighbor The neighbor we will use to make the prediction
     * @return A movie recommendation
     */
    public double predict(String movieTitle, User active, User nearestNeighbor) {
        double activeAverageRating = active.getAverageRating();
        double nearestNeighborAverageRating = nearestNeighbor.getAverageRating();

        double normalizedMovieRating = nearestNeighbor.getMovieRating(movieTitle) - nearestNeighborAverageRating;

        double prediction = sim(active, nearestNeighbor) * normalizedMovieRating;

        prediction /= sim(active, nearestNeighbor);

        return prediction + activeAverageRating;
    }
}
