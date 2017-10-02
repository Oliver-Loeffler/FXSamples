package net.raumzeitfalle.fxsamples.pocketcalculator.modulesview;

import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ModulesViewController  implements Initializable {

    @FXML
    private TabPane modules;

    private final ModulesViewModel model;

    public ModulesViewController(final ModulesViewModel model) {
        this.model = model;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.modules.getTabs().removeAll();
        model.getTabs().addListener((SetChangeListener<? super Tab>) l -> this.modules.getTabs().addAll(l.getElementAdded()));
    }
}
