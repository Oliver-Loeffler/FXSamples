package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import sample.modulesview.ModulesViewController;
import sample.modulesview.ModulesViewModel;
import sample.view1.View1Controller;
import sample.view1.View1Model;

import java.io.IOException;

public class ViewFactory {

    private ViewFactory() {

    }

    public static Parent createView1() throws IOException {
        FXMLLoader loader = new FXMLLoader(View1Controller.class.getResource("View1.fxml"));
        View1Model model = new View1Model();
        View1Controller controller = new View1Controller(model);
        loader.setController(controller);
        return loader.load();
    }

    public static Parent createModulesView(ModulesViewModel model) throws IOException {
        FXMLLoader loader = new FXMLLoader(ModulesViewController.class.getResource("ModulesView.fxml"));
        ModulesViewController controller = new ModulesViewController(model);
        loader.setController(controller);
        return loader.load();
    }
}
