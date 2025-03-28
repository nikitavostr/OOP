package ru.nsu.vostrikov;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        int rows = 20;
        int cols = 20;
        int foodCount = 5;

        GameController gameController = new GameController(rows, cols, foodCount);
        StackPane root = new StackPane(gameController.getCanvas());
        Scene scene = new Scene(root);

        scene.setOnKeyPressed((KeyEvent event) -> gameController.setupHandlers(event));

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        gameController.startGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
