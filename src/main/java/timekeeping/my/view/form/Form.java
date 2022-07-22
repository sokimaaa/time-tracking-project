package timekeeping.my.view.form;

import timekeeping.my.view.field.Field;

import java.io.Serializable;
import java.util.*;

public final class Form implements Serializable {

    private final String id;
    private final String action;
    private final String method;
    private final String buttonText;
    private final String title;
    private final Queue<String> errors;
    private final List<Field> fields;

    {
        errors = new LinkedList<>();
    }

    Form(String id, String action, String method, String buttonText, String title, List<Field> fields) {
        this.id = id;
        this.action = action;
        this.method = method;
        this.buttonText = buttonText;
        this.title = title;
        this.fields = fields;

    }

    public List<Field> getFields() {
        return fields;
    }

    public void addError(String error) {
        this.errors.offer(error);
    }

    public boolean isHasErrors() {
        return !errors.isEmpty();
    }

    public String getErrors() {
        return errors.poll();
    }

    public String getAction() {
        return action;
    }

    public String getMethod() {
        return method;
    }

    public String getButtonText() {
        return buttonText;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

}
