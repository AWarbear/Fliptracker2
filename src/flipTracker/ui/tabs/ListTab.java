package flipTracker.ui.tabs;

import flipTracker.ui.FlipPanel;
import flipTracker.utils.ui.ComponentUtils;
import javafx.scene.control.ListView;

/**
 * Created on 5.5.2017.
 */
public abstract class ListTab extends TrackerTab {

    private ListView<FlipPanel> flips;

    ListTab(String name) {
        super(name);
        flips = new ListView<>();
        ComponentUtils.setFillAnchor(flips);
        ComponentUtils.setAnchors(flips, 0, 27.0, 0, 0);
        container.getChildren().add(flips);
    }

    ListView<FlipPanel> getFlips() {
        return flips;
    }

    public abstract void update();
}
