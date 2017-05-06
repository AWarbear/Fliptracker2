package flipTracker.ui;

import flipTracker.FlipTracker;
import flipTracker.ui.tabs.FlipsTab;
import flipTracker.ui.tabs.HistoryTab;
import flipTracker.ui.tabs.ListTab;
import flipTracker.ui.tabs.SettingsTab;
import flipTracker.utils.ui.ComponentUtils;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;

/**
 * Created on 5.5.2017.
 */
public class MainUI extends AnchorPane {

    private TabPane tabPane;
    private Label profitLabel = new Label("Profit: 0K");

    public MainUI() {
        getStylesheets().add("custom.css");
        tabPane = new TabPane();
        ComponentUtils.setFillAnchor(tabPane);
        FlipsTab flipsTab = new FlipsTab();
        tabPane.getTabs().add(flipsTab);
        HistoryTab historyTab = new HistoryTab();
        tabPane.getTabs().add(historyTab);
        getChildren().add(tabPane);
        getChildren().add(profitLabel);
        tabPane.getTabs().add(new SettingsTab());
        ComponentUtils.setAnchors(profitLabel, -1, 5, 5, -1);
        profitLabel.setTooltip(new Tooltip("Displays your current profits from all of your completed flips."));
    }

    public void updateFlips() {
        if (tabPane.getSelectionModel().getSelectedItem() instanceof ListTab)
            ((ListTab) tabPane.getSelectionModel().getSelectedItem()).update();
        profitLabel.setText("Profit: " + FlipTracker.instance.getFlipManager().getProfits() + "K");
    }
}
