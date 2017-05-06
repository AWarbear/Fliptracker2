package flipTracker.ui.tabs;

import flipTracker.FlipTracker;
import flipTracker.saving.Limits;
import flipTracker.utils.ui.ComponentUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

/**
 * Created on 6.5.2017.
 */
public class SettingsTab extends TrackerTab {


    public SettingsTab() {
        super("Settings");
        TilePane settingsContainer = new TilePane();
        ComponentUtils.setFillAnchor(settingsContainer);
        settingsContainer.setPadding(new Insets(5));
        settingsContainer.setAlignment(Pos.TOP_CENTER);
        container.getChildren().add(settingsContainer);

        Button updateLimits = ComponentUtils.createIconButton(25, 25, "Update.png", "Update limits");
        settingsContainer.getChildren().add(updateLimits);
        updateLimits.setText("Update buy limits");
        updateLimits.setOnAction(e -> Limits.dumpLimits(FlipTracker.instance.getProfile().getLimits()));
    }
}
