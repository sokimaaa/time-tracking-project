package timekeeping.my.view.field;

import timekeeping.my.view.field.option.Option;

import java.io.Serializable;
import java.util.List;

public final class Field implements Serializable {

    private final String fieldTittle;
    private final String fieldName;
    private final FieldType fieldType;
    private final InputType fieldInputType;
    private final boolean fieldHidden;
    private final boolean fieldDisabled;
    private final List<Option> fieldOptions;

    Field(String fieldTittle, String fieldName, FieldType fieldType,
          InputType fieldInputType, boolean fieldHidden, boolean fieldDisabled,
          List<Option> fieldOptions) {
        this.fieldTittle = fieldTittle;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldInputType = fieldInputType;
        this.fieldHidden = fieldHidden;
        this.fieldDisabled = fieldDisabled;
        this.fieldOptions = fieldOptions;

    }

    public String getName() {
        return fieldName;
    }
    public FieldType getType() { return fieldType; }
    public InputType getInputType() {
        return fieldInputType;
    }
    public String getTittle() {
        return fieldTittle;
    }
    public boolean isDisabled() {
        return fieldDisabled;
    }
    public boolean isHidden() {
        return fieldHidden;
    }
    public List<Option> getOptions() {
        return fieldOptions;
    }

}
