import java.util.ArrayList;

public class User {

    private String username;
    private int age;
    private ArrayList<String> favoriteTags;
    private ArrayList<String> favoriteMovieType;

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
}
