package sample.modulesview;

import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
