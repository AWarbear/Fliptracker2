package flipTracker.ui.tabs;

import flipTracker.FlipTracker;
import flipTracker.utils.ui.ComponentUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Created on 6.5.2017.
 * <p>
 * A tab that defines a content area with a small control bar on the bottom
 */
class TrackerTab extends Tab {

    VBox controls = new VBox();
    AnchorPane container;

    TrackerTab(String name) {
        setText(name);
        setClosable(false);
        container = new AnchorPane();
        container.getStyleClass().add("tracker-tab");
        container.getChildren().add(controls);
        controls.setAlignment(Pos.CENTER_RIGHT);
        controls.getStyleClass().add("footer");
        controls.setMinHeight(28);
        ComponentUtils.setAnchors(controls, -1, 0, 0, 0);
        setOnSelectionChanged(event -> {
            if (FlipTracker.instance != null) FlipTracker.instance.refresh();
        });
        setContent(container);
    }
}
