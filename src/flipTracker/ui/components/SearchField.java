package flipTracker.ui.components;

import javafx.scene.control.ComboBox;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 6.5.2017.
 * <p>
 * A search field based on a list of data
 */
public class SearchField extends ComboBox<String> {

    private List<String> shownData;

    public SearchField(String initialValue, List<String> data) {
        getEditor().setText(initialValue);
        setEditable(true);
        setMaxWidth(250);
        getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            getItems().clear();
            if (newValue.length() < 3) {
                if (shownData != null)
                    shownData.clear();
            } else if (oldValue != null && shownData != null && shownData.size() > 0 && newValue.length() - oldValue.length() == 1)
                shownData = shownData.stream().filter(text -> text.toLowerCase().contains(newValue.toLowerCase())).collect(Collectors.toList());
            else //search from all items
                shownData = data.stream().filter(text -> text.toLowerCase().contains(newValue.toLowerCase())).collect(Collectors.toList());
            if (shownData != null)
                getItems().addAll(shownData);
            show();
        });
    }

    public String getText() {
        return getEditor().getText();
    }
}
