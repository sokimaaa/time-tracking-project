package timekeeping.my.view.form;

import timekeeping.my.view.field.Field;
import timekeeping.my.view.field.FieldBuilder;
import timekeeping.my.view.field.FieldEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The form builder class provided tools to receive prepared form
 * */
public final class FormBuilder {

    private static FormBuilder INSTANCE;
    private static final FieldBuilder fieldBuilder;

    static {
        fieldBuilder = FieldBuilder.getInstance();
    }

    private FormBuilder() {

    }

    public static synchronized FormBuilder getInstance() {
        if(Objects.isNull(INSTANCE))
            INSTANCE = new FormBuilder();
        return INSTANCE;
    }

    public Form getLoginForm() {
        return createForm(
                ConstantForm.FORM_LOGIN_ID,
                ConstantForm.FORM_LOGIN_ACTION,
                ConstantForm.FORM_LOGIN_METHOD,
                ConstantForm.FORM_LOGIN_BUTTON_TEXT,
                ConstantForm.FORM_LOGIN_TITTLE,
                ConstantForm.FORM_LOGIN_FIELDS
        );
    }

    public Form getRegistrationForm() {
        return createForm(
                ConstantForm.FORM_REGISTER_ID,
                ConstantForm.FORM_REGISTER_ACTION,
                ConstantForm.FORM_REGISTER_METHOD,
                ConstantForm.FORM_REGISTER_BUTTON_TEXT,
                ConstantForm.FORM_REGISTER_TITTLE,
                ConstantForm.FORM_REGISTER_FIELDS
        );
    }

    public Form getPersonalCabinetForm() {
        return createForm(
                ConstantForm.FORM_CABINET_ID,
                ConstantForm.FORM_CABINET_ACTION,
                ConstantForm.FORM_CABINET_METHOD,
                ConstantForm.FORM_CABINET_BUTTON_TEXT,
                ConstantForm.FORM_CABINET_TITTLE,
                ConstantForm.FORM_CABINET_FIELDS
        );
    }

    public Form getAdminCategoriesForm() {
        return createForm(
                ConstantForm.FORM_ADMIN_CATEGORIES_ID,
                ConstantForm.FORM_ADMIN_CATEGORIES_ACTION,
                ConstantForm.FORM_ADMIN_CATEGORIES_METHOD,
                ConstantForm.FORM_ADMIN_CATEGORIES_BUTTON_TEXT,
                ConstantForm.FORM_ADMIN_CATEGORIES_TITTLE,
                ConstantForm.FORM_ADMIN_CATEGORIES_FIELDS
        );
    }

    public Form getAddActivityForm() {
        return createForm(
                ConstantForm.FORM_ADD_ACTIVITY_ID,
                ConstantForm.FORM_ADD_ACTIVITY_ACTION,
                ConstantForm.FORM_ADD_ACTIVITY_METHOD,
                ConstantForm.FORM_ADD_ACTIVITY_BUTTON_TEXT,
                ConstantForm.FORM_ADD_ACTIVITY_TITTLE,
                ConstantForm.FORM_ADD_ACTIVITY_FIELDS
        );
    }

    public Form getCalculateForm() {
        return createForm(
                ConstantForm.FORM_CALCULATE_ID,
                ConstantForm.FORM_CALCULATE_ACTION,
                ConstantForm.FORM_CALCULATE_METHOD,
                ConstantForm.FORM_CALCULATE_BUTTON_TEXT,
                ConstantForm.FORM_CALCULATE_TITTLE,
                ConstantForm.FORM_CALCULATE_FIELDS
        );
    }

    public Form getSendRequestForm() {
        return createForm(
                ConstantForm.FORM_JOIN_ID,
                ConstantForm.FORM_JOIN_ACTION,
                ConstantForm.FORM_JOIN_METHOD,
                ConstantForm.FORM_JOIN_BUTTON_TEXT,
                ConstantForm.FORM_JOIN_TITTLE,
                ConstantForm.FORM_JOIN_FIELDS
        );
    }

    private Form createForm(String formId, String action, String method,
                            String buttonText, String tittle, FieldEnum[] formFields) {
        List<Field> fields = Arrays.stream(formFields)
                .map(fieldBuilder::createField)
                .collect(Collectors.toList());

        return new Form(formId, action, method, buttonText, tittle, fields);
    }


}
