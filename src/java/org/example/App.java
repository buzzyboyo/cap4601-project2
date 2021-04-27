package org.example;

import java.util.Scanner;

public class App{

    public static void main(String[] args) {
        User activeUser;
        Recommender recommender = new Recommender();
        Scanner kbd = new Scanner(System.in);
        MovieMap.generateDefaultMap();
        String input;
        String title;
        double rating;
        int moviesRated = 0;

        System.out.println("Movie Recommender");

        //set up the user
        System.out.print("Enter username: ");
        input = kbd.nextLine();
        activeUser = new User(input);

        //get initial ratings for a cold start
        for(int i = 0; i < MovieMap.getMovies().size(); i++) {
            System.out.println("\nHave you seen " + MovieMap.getMovies().get(i) + "? (y/n)");

            input = kbd.nextLine();
            while(!input.equals("y") && !input.equals("n")) {
                System.out.println("Invalid. Answer should be 'y' or 'n'");
                System.out.println("Have you seen " + MovieMap.getMovies().get(i) + "? (y/n)");
                input = kbd.nextLine();
            }

            if(input.equals("y")) {
                System.out.println("How would you rate it out of 10?");
                input = kbd.nextLine();

                while(!canParse(input)) {
                    System.out.println("Invalid. Enter a decimal number from 0 to 10.");
                    System.out.println("How would you rate " + MovieMap.getMovies().get(i) + " out of 10?");
                    input = kbd.nextLine();
                }

                rating = Double.parseDouble(input);

                if(rating > 10.0)
                    rating = 10.0;
                else if(rating < 0.0)
                    rating = 0.0;

                activeUser.addMovieRating(MovieMap.getMovies().get(i), rating);

                moviesRated++;
            }

            //We are only asking for 3 initial ratings
            if(moviesRated > 2)
                break;
        }

        System.out.println("User " + activeUser.getName() + " created");
        System.out.println("Type 'quit' to quit");
        System.out.println("Type 'rec' to get a movie recommendation");
        System.out.println("Type 'rate' to rate a movie you've seen");
        System.out.println("Type 'movies' to see what movies have been rated");
        System.out.println("Type 'mylist' to see what movies you've rated");
        input = kbd.next();

        while(!input.equals("quit")) {

            if(input.equals("rec")) {
                System.out.println("You might enjoy " + recommender.movieRecommendation(activeUser));
                System.out.println("Similarity to best User who did enjoy it: " + String.format("%1.2f", recommender.getLastNearestNeighborSimilarity()));
                System.out.println("Predicted rating: " + recommender.getLastPrediction());
            }

            if(input.equals("rate")) {
                System.out.println("Movie title to rate: ");
                kbd.nextLine();
                title = kbd.nextLine();

                if(!MovieMap.getMovies().contains(title)) {
                    MovieMap.mapMovie(title);
                }

                System.out.println("How would you rate it out of 10?");
                input = kbd.nextLine();

                while(!canParse(input)) {
                    System.out.println("Invalid. Enter a decimal number from 0 to 10.");
                    System.out.println("How would you rate " + title + " out of 10?");
                    input = kbd.nextLine();
                }

                rating = Double.parseDouble(input);
                activeUser.addMovieRating(title, rating);
            }

            if(input.equals("movies")) {
                for(String movie : MovieMap.getMovies()) {
                    System.out.println(movie);
                }
            }

            if(input.equals("mylist")) {
                for(int i = 0; i < activeUser.numMoviesRated(); i++) {
                    System.out.println(activeUser.getMovie(i));
                }
            }

            input = kbd.nextLine();
        }
    }

    private static boolean canParse(String result) {
        try {
            Double.parseDouble(result);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}