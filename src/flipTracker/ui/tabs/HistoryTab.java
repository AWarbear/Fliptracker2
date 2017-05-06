package flipTracker.ui.tabs;

import flipTracker.FlipTracker;
import flipTracker.ui.FlipPanel;
import flipTracker.utils.ui.ComponentUtils;
import javafx.scene.control.Button;

import java.util.stream.Collectors;

/**
 * Created on 5.5.2017.
 */
public class HistoryTab extends ListTab {

    public HistoryTab() {
        super("History");
        Button clearButton = ComponentUtils.createIconButton(15, 15, "Recycle.png", "Clear history");
        controls.getChildren().add(clearButton);

        //functionality
        clearButton.setOnAction((e) ->
                FlipTracker.instance.getFlipManager().clearHistory());
    }

    @Override
    public void update() {
        getFlips().getItems().clear();
        getFlips().getItems().addAll(FlipTracker.instance.getFlipManager().getFlipHistory().parallelStream().map(FlipPanel::new).collect(Collectors.toList()));
    }
}
