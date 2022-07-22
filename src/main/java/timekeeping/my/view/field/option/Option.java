package timekeeping.my.view.field.option;

import java.io.Serializable;

public final class Option implements Serializable {
    private final String optionText;
    private final String optionValue;

    Option(String optionText, String optionValue) {
        this.optionText = optionText;
        this.optionValue = optionValue;
    }

    public String getValue() {
        return optionValue;
    }
    public String getText() {
        return optionText;
    }
}
