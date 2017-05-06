package flipTracker.ui.tabs;

import flipTracker.FlipTracker;
import flipTracker.flipping.Flip;
import flipTracker.ui.FlipEditDialog;
import flipTracker.ui.FlipPanel;
import flipTracker.utils.ui.ComponentUtils;
import javafx.scene.control.Button;

import java.util.stream.Collectors;

/**
 * Created on 5.5.2017.
 */
public class FlipsTab extends ListTab {

    public FlipsTab() {
        super("Flips");
        Button addButton = ComponentUtils.createIconButton(15, 15, "Add.png", "Add new flip");
        controls.getChildren().add(addButton);

        //functionality
        addButton.setOnAction((e) -> new FlipEditDialog(new Flip()));
    }

    @Override
    public void update() {
        getFlips().getItems().clear();
        getFlips().getItems().addAll(FlipTracker.instance.getFlipManager().getActiveFlips().parallelStream().map(FlipPanel::new).collect(Collectors.toList()));
    }
}
