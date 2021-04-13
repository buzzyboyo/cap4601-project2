import java.util.ArrayList;
import java.util.HashMap;

public class User {

    private String username;

    /*

    TODO: Use these?

    //These three variables will dictate a similarity based upon a cold start
    private int age;
    private ArrayList<String> favoriteTags;
    private ArrayList<String> favoriteMovieType;
    */


    private HashMap<String, Integer> movieRatings;

    public User(String name, int age) {
        this.username = name;
        this.age = age;
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

    public void addMovieRating(String movieName, int rating) {
        //TODO: Code user movie rating input
    }

    public int getMovieRating(String movieName) {
        return 0;
    }
}
