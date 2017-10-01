package net.raumzeitfalle.fxsamples.pocketcalculator;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent modulesView = ViewFactory.createApplicationView(primaryStage);
        Scene scene = new Scene(modulesView);
        scene.getStylesheets().add(Main.class.getResource("AppSample.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(450);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
