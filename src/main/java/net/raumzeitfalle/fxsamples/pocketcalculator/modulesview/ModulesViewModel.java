package net.raumzeitfalle.fxsamples.pocketcalculator.modulesview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
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
        tab.setId(name.replace("\\s", "").trim());
        tabs.add(tab);
    }

}
