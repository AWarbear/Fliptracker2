package flipTracker.utils.ui;

import flipTracker.ui.FlipPanel;
import flipTracker.utils.fileUtils.PathUtils;
import flipTracker.utils.input.StringUtils;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Created on 4.5.2017.
 */
public class ComponentUtils {

    /**
     * Create a new button with an icon
     */
    public static Button createIconButton(int width, int height, String iconName, String tooltip) {
        Button button = new Button("",
                new ImageView(new Image(FlipPanel.class.getResourceAsStream(PathUtils.getIcon(iconName)), width, height, true, true)));
        button.setTooltip(new Tooltip(tooltip));
        return button;
    }

    public static TextField createDecimalInputField(double initialValue) {
        TextField result = new TextField("" + initialValue);
        result.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null && oldValue.equals(newValue)) return;
            if (!StringUtils.isDecimal(newValue) && !newValue.isEmpty())
                result.setText(oldValue == null || oldValue.isEmpty() ? "0" : oldValue);
        });
        return result;
    }

    public static TextField createIntegerInputField() {
        TextField result = new TextField("" + 0);
        result.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!StringUtils.isInteger(newValue) && !newValue.isEmpty())
                result.setText(oldValue == null || oldValue.isEmpty() ? "0" : oldValue);
        });
        return result;
    }

    public static void setFillAnchor(Node node) {
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
    }

    public static void setAnchors(Node node, double top, double bottom, double left, double right) {
        if (top != -1)
            AnchorPane.setTopAnchor(node, top);
        if (bottom != -1)
            AnchorPane.setBottomAnchor(node, bottom);
        if (left != -1)
            AnchorPane.setLeftAnchor(node, left);
        if (right != -1)
            AnchorPane.setRightAnchor(node, right);
    }
}
