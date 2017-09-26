package sample.modulesview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tab;

public class ModulesViewModel {

    private final ObservableSet<Tab> tabs = FXCollections.observableSet();

    public ObservableSet<Tab> getTabs() {
        return tabs;
    }

    public void addFromNode(Parent parent, String name){
        Tab tab = new Tab(name);
        tab.setContent(parent);
        tabs.add(tab);
    }

}
