package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    private User activeUser;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage){
        primaryStage.setTitle("Movie Recommender");

        StackPane root = new StackPane();

        showLoginScreen(root);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showLoginScreen(StackPane root) {

        var usernameLabel = new Label("Username");
        var usernameTextArea = new TextArea();

        root.getChildren().add(usernameLabel);
        root.getChildren().add(usernameTextArea);
    }
}