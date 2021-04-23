package org.example;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

public class MovieMap {

    private static final String API_URL = "https://omdbapi.com/?apikey=2f3a2846&";
    private static ArrayList<Movie> movies;

    public static void mapMovie(String title) {
        Movie movie = new Movie();
        title = title.replace(" ", "+");

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            movie = mapper.readValue(new URL(API_URL + "t=" + title), new TypeReference<Movie>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        movies.add(movie);
    }

    public static void generateDefaultMap() {
        mapMovie("Toy Story");
        mapMovie("Toy Story 2");
        mapMovie("Harry Potter and the Sorcerer's Stone");
        mapMovie("Harry Potter and the Prisoner of Azkaban");
        mapMovie("Harry Potter and the Goblet of Fire");
        mapMovie("Star Wars Episode I");
        mapMovie("Star Wars Episode II");
        mapMovie("Star Wars Episode III");
        mapMovie("1917");
        mapMovie("The Lion King");
        mapMovie("Aladdin");
        mapMovie("The Jungle Book");
    }

    public ArrayList<Movie> getMovies() { return movies; }
}
