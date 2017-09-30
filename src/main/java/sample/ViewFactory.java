package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import sample.modulesview.ModulesViewController;
import sample.modulesview.ModulesViewModel;
import sample.calculator1.CalculatorController;
import sample.calculator1.CalculatorModel;

import java.io.IOException;

public class ViewFactory {

    private ViewFactory() {

    }

    public static Parent createSimpleCalculatorView() throws IOException {
        FXMLLoader loader = new FXMLLoader(CalculatorController.class.getResource("CalculatorView.fxml"));
        CalculatorModel model = new CalculatorModel();
        CalculatorController controller = new CalculatorController(model);
        loader.setController(controller);
        return loader.load();
    }

    public static Parent createModulesView(ModulesViewModel model) throws IOException {
        FXMLLoader loader = new FXMLLoader(ModulesViewController.class.getResource("ModulesView.fxml"));
        ModulesViewController controller = new ModulesViewController(model);
        loader.setController(controller);
        return loader.load();
    }

    public static Parent createApplicationView(Stage primaryStage) throws IOException {
        ModulesViewModel model = new ModulesViewModel();
        Parent modulesView = ViewFactory.createModulesView(model);
        primaryStage.setTitle("Business Application");
        Parent calculatorView = ViewFactory.createSimpleCalculatorView();
        model.addFromNode(calculatorView, "SimpleCalculator");
        return modulesView;
    }
}
