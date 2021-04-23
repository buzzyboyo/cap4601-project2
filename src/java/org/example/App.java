package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    private User activeUser;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage){
        primaryStage.setTitle("Movie Recommender");

        GridPane root = new GridPane();
        root.setHgap(10.0);
        root.setVgap(10.0);
        root.setPadding(new Insets(10, 10, 10, 10));

        var usernameLabel = new Label("Username");
        root.add(usernameLabel, 1, 0);

        var usernameTextArea = new TextArea();
        root.add(usernameTextArea, 1, 1);

        showLoginScreen();

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();

        MovieMap.generateDefaultMap();

        Recommender r = new Recommender();
    }

    public void showLoginScreen() {

    }
}