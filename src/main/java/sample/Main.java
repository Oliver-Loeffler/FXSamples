package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.modulesview.ModulesViewController;
import sample.modulesview.ModulesViewModel;

import javax.swing.text.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        ModulesViewModel model = new ModulesViewModel();
        Parent modulesView = ViewFactory.createModulesView(model);
        primaryStage.setTitle("Business Application");
        Scene scene = new Scene(modulesView);
        scene.getStylesheets().add(Main.class.getResource("Calculator.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        Parent view1 = ViewFactory.createView1();
        model.addFromNode(view1,"SimpleCalculator");
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(450);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
