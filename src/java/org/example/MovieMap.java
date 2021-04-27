package org.example;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

public class MovieMap {

    private static ArrayList<String> movies = new ArrayList<>();

    public static void mapMovie(String title) {
        movies.add(title);
    }

    public static void generateDefaultMap() {
        mapMovie("Harry Potter I");
        mapMovie("Harry Potter II");
        mapMovie("Harry Potter III");
        mapMovie("Star Wars Episode I");
        mapMovie("Star Wars Episode II");
        mapMovie("Star Wars Episode III");
    }

    public static ArrayList<String> getMovies() { return movies; }
}
