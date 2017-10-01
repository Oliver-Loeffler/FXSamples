package net.raumzeitfalle.fxsamples.pocketcalculator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import net.raumzeitfalle.fxsamples.pocketcalculator.bigdecimal.BigDecimalCalculatorController;
import net.raumzeitfalle.fxsamples.pocketcalculator.bigdecimal.BigDecimalCalculatorModel;
import net.raumzeitfalle.fxsamples.pocketcalculator.modulesview.ModulesViewController;
import net.raumzeitfalle.fxsamples.pocketcalculator.modulesview.ModulesViewModel;
import net.raumzeitfalle.fxsamples.pocketcalculator.simple.CalculatorController;
import net.raumzeitfalle.fxsamples.pocketcalculator.simple.CalculatorModel;

import java.io.IOException;

public class ViewFactory {

    private ViewFactory() {

    }
    
    public static Parent createBigDecimalCalculatorView() throws IOException {
    		FXMLLoader loader = new FXMLLoader(BigDecimalCalculatorController.class.getResource("BigDecimalCalculatorView.fxml"));
    		BigDecimalCalculatorModel model = new BigDecimalCalculatorModel();
    		BigDecimalCalculatorController controller = new BigDecimalCalculatorController(model);
    		loader.setController(controller);
    		return loader.load();
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
        
        Parent bigDecimalCalcView = ViewFactory.createBigDecimalCalculatorView();
        model.addFromNode(bigDecimalCalcView, "BigDecimalCalculator");
        
        return modulesView;
    }
}
