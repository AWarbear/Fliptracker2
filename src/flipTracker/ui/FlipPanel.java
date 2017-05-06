package flipTracker.ui;

import flipTracker.FlipTracker;
import flipTracker.flipping.Flip;
import flipTracker.utils.ui.ComponentUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * Created on 4.5.2017.
 */
public class FlipPanel extends AnchorPane {

    public FlipPanel(Flip flip) {
        getStyleClass().add("flipPanel");

        //time since
        Label timeLabel = new Label(flip.timeSinceLastEdit());
        setRightAnchor(timeLabel, 0.0);
        setTopAnchor(timeLabel, 0.0);

        //create buttons
        VBox buttons = new VBox();
        setRightAnchor(buttons, 0.0);
        buttons.setSpacing(2);
        getChildren().add(buttons);
        Button removeButton = ComponentUtils.createIconButton(15, 15, "Recycle.png", "Remove flip");
        Button editButton = ComponentUtils.createIconButton(15, 15, "Edit.png", "Edit flip");
        Button completeButton = ComponentUtils.createIconButton(15, 15, "Confirm.png", "Mark the flip as completed");
        buttons.getChildren().addAll(completeButton, editButton, removeButton, timeLabel);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        //item data
        VBox geInfo = new VBox();
        Label itemName = new Label(flip.getItemName() + " X"+flip.getAmount() );
        itemName.getStyleClass().add("title");
        Label buyInfo = new Label(flip.getBuyInfo());
        Label sellInfo = new Label(flip.getSellInfo());
        Label profitInfo = new Label(flip.getProfitInfo());
        setTopAnchor(itemName, 2.0);
        setLeftAnchor(itemName, 2.0);
        setLeftAnchor(geInfo, 0.0);
        setBottomAnchor(geInfo, 0.0);
        geInfo.setSpacing(3);
        geInfo.getChildren().addAll(buyInfo, sellInfo, profitInfo);
        getChildren().addAll(geInfo, itemName);

        //functionality
        editButton.setOnAction((e) -> new FlipEditDialog(flip));
        removeButton.setOnAction((e) -> FlipTracker.instance.getFlipManager().removeFlip(flip));
        completeButton.setOnAction((e) -> {
            if (flip.getSellPrice() > 0) {
                flip.setCompleted();
                FlipTracker.instance.refresh();
            } else
                new FlipEditDialog(flip);
        });
    }

}
