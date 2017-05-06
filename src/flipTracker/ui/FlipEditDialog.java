package flipTracker.ui;

import flipTracker.FlipTracker;
import flipTracker.flipping.Flip;
import flipTracker.ui.components.SearchField;
import flipTracker.utils.ui.ComponentUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 * Created on 4.5.2017.
 */
public class FlipEditDialog extends Stage {


    public FlipEditDialog(Flip flip) {
        setScene(new Scene(new FlipEditPanel(flip)));
        setResizable(false);
        show();
    }

    private class FlipEditPanel extends AnchorPane {
        FlipEditPanel(Flip flip) {
            getStyleClass().add("flipPanel");
            HBox rootContainer = new HBox();
            rootContainer.setSpacing(5);
            VBox container = new VBox();
            container.setSpacing(5);

            AnchorPane.setBottomAnchor(rootContainer, 5.0);
            AnchorPane.setTopAnchor(rootContainer, 35.0);
            AnchorPane.setLeftAnchor(rootContainer, 5.0);
            AnchorPane.setRightAnchor(rootContainer, 5.0);

            SearchField nameField = new SearchField(flip.getItemName(), new ArrayList<>(FlipTracker.instance.getProfile().getLimits().keySet()));
            TextField buyPriceField = ComponentUtils.createDecimalInputField(flip.getBuyPrice());
            TextField sellPriceField = ComponentUtils.createDecimalInputField(flip.getSellPrice());
            TextField timeSinceField = ComponentUtils.createIntegerInputField();

            Label itemName = new Label("Item:");
            ComponentUtils.setAnchors(itemName, 5, -1, 5, -1);
            ComponentUtils.setAnchors(nameField, 5, -1, 45, 5);
            getChildren().addAll(itemName, nameField);

            createRow(container, "Buy price: ", buyPriceField);
            createRow(container, "Sell price: ", sellPriceField);
            createRow(container, "Time since edited: ", timeSinceField);

            VBox buttons = new VBox();
            buttons.setAlignment(Pos.BOTTOM_RIGHT);
            buttons.setSpacing(2);
            Button completeButton = ComponentUtils.createIconButton(15, 15, "Confirm.png", "Finish editing");
            Button cancelButton = ComponentUtils.createIconButton(15, 15, "Stop.png", "Cancel the edit");
            buttons.getChildren().addAll(cancelButton, completeButton);
            rootContainer.getChildren().addAll(container, buttons);
            getChildren().add(rootContainer);

            //functionality
            completeButton.setOnAction(e -> {
                flip.updateData(nameField.getText(), Double.valueOf(buyPriceField.getText()), Double.valueOf(sellPriceField.getText()), Long.valueOf(timeSinceField.getText()));
                if (!FlipTracker.instance.getFlipManager().containsFlip(flip))
                    FlipTracker.instance.getFlipManager().addFlip(flip);
                FlipTracker.instance.refresh();
                getScene().getWindow().hide();
            });
            cancelButton.setOnAction(e -> getScene().getWindow().hide());

        }

        private void createRow(VBox container, String title, TextField input) {
            BorderPane row = new BorderPane();
            Label titleLabel = new Label(title);
            input.setAlignment(Pos.CENTER_RIGHT);
            titleLabel.setAlignment(Pos.CENTER_LEFT);
            row.setLeft(titleLabel);
            row.setRight(input);
            container.getChildren().add(row);
        }
    }

}
