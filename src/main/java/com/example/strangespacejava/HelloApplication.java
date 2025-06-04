package com.example.strangespacejava;

import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {

    private FadeTransition titleAnimation(Group root, Scene scene) {

        Text text = new Text("StrangeSpace");
        text.setFill(Color.WHITE);
        Font myFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Iceland-Regular.ttf"), 300);
        text.setFont(Font.font(myFont.getName(), 250));
        root.getChildren().add(text);

        // Use listeners to wait until the scene is laid out
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            text.setX((newVal.doubleValue() - text.getBoundsInLocal().getWidth()) / 2);
        });

        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            double centerY = (newVal.doubleValue() / 2);
            double textHeight = text.getLayoutBounds().getHeight();
            text.setY(centerY + textHeight / 4); // approximate vertical centering
        });

        text.setOpacity(0);
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(5), text);
        fadeIn.setInterpolator(Interpolator.EASE_IN);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setCycleCount(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), text);
        fadeOut.setInterpolator(Interpolator.EASE_OUT);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setCycleCount(1);

        fadeIn.setOnFinished(e -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(ev -> fadeOut.play());
            pause.play();
        });

        fadeIn.setDelay(Duration.seconds(2));
        return fadeIn;
    }

    @Override
    public void start(Stage stage) throws IOException {

        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);
        stage.setTitle("StrangeSpace");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaximized(true);

        this.titleAnimation(root, scene).play();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}