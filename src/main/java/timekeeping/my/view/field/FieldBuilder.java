package timekeeping.my.view.field;

import timekeeping.my.view.field.option.OptionBuilder;

import java.util.Objects;

/**
 * The Field Builder class provided tools to build any field if exists
 * */
public final class FieldBuilder {

    private static FieldBuilder INSTANCE;
    private static final OptionBuilder optionBuilder;

    static {
        optionBuilder = OptionBuilder.getInstance();
    }
    private FieldBuilder() {

    }

    public static synchronized FieldBuilder getInstance() {
        if(Objects.isNull(INSTANCE))
            INSTANCE = new FieldBuilder();
        return INSTANCE;
    }

    public Field createField(FieldEnum fieldName) {
        Field field;

        if(Objects.isNull(fieldName)) {
            field = createEmailField();
            return field;
        }

        switch (fieldName) {
            case LOGIN:
                field = createLoginField();
                break;
            case PASSWORD:
                field = createPasswordField();
                break;
            case CONFIRM_PASSWORD:
                field = createConfirmPasswordField();
                break;
            case LAST:
                field = createLastField();
                break;
            case EMAIL:
                field = createEmailField();
                break;
            case PHONE:
                field = createPhoneField();
                break;
            case SEXES:
                field = createSexesField();
                break;
            case TOTAL:
                field = createTotalField();
                break;
            case CITIES:
                field = createCitiesField();
                break;
            case CATEGORY:
                field = createCategoryField();
                break;
            case CATEGORIES:
                field = createCategoriesField();
                break;
            case ENTER_TIME:
                field = createEnterTimeField();
                break;
            case DESCRIPTION:
                field = createDescriptionField();
                break;
            case ACTIVITY_NAME:
                field = createActivityNameField();
                break;
            default:
                field = createEmptyField();
        }
        return field;
    }

    private Field createActivityNameField() {
        return new Field(
                FieldConstant.FIELD_TITLE_ACTIVITY_NAME,
                FieldConstant.FIELD_NAME_ACTIVITY_NAME,
                FieldType.INPUT,
                InputType.TEXT,
                false,
                false,
                optionBuilder.getEmptyOptions()
        );
    }

    private Field createDescriptionField() {
        return new Field(
                FieldConstant.FIELD_TITLE_DESCRIPTION,
                FieldConstant.FIELD_NAME_DESCRIPTION,
                FieldType.INPUT,
                InputType.TEXT,
                false,
                false,
                optionBuilder.getEmptyOptions()
        );
    }

    private Field createEnterTimeField() {
        return new Field(
                FieldConstant.FIELD_TITLE_ENTER_TIME,
                FieldConstant.FIELD_NAME_ENTER_TIME,
                FieldType.INPUT,
                InputType.NUMBER,
                false,
                false,
                optionBuilder.getEmptyOptions()
        );
    }

    private Field createCategoriesField() {
        return new Field(
                FieldConstant.FIELD_TITLE_CATEGORIES,
                FieldConstant.FIELD_NAME_CATEGORIES,
                FieldType.SELECT,
                InputType.EMPTY,
                false,
                false,
                optionBuilder.getCategoriesOptions()
        );
    }

    private Field createCategoryField() {
        return new Field(
                FieldConstant.FIELD_TITLE_CATEGORY,
                FieldConstant.FIELD_NAME_CATEGORY,
                FieldType.INPUT,
                InputType.TEXT,
                false,
                false,
                optionBuilder.getEmptyOptions()
        );
    }

    private Field createCitiesField() {
        return new Field(
                FieldConstant.FIELD_TITLE_CITIES,
                FieldConstant.FIELD_NAME_CITIES,
                FieldType.SELECT,
                InputType.EMPTY,
                false,
                false,
                optionBuilder.getCitiesOptions()
        );
    }

    private Field createTotalField() {
        return new Field(
                FieldConstant.FIELD_TITLE_TOTAL,
                FieldConstant.FIELD_NAME_TOTAL,
                FieldType.INPUT,
                InputType.TEXT,
                false,
                true,
                optionBuilder.getEmptyOptions()
        );
    }

    private Field createSexesField() {
        return new Field(
                FieldConstant.FIELD_TITLE_SEXES,
                FieldConstant.FIELD_NAME_SEXES,
                FieldType.SELECT,
                InputType.EMPTY,
                false,
                false,
                optionBuilder.getSexesOptions()
        );
    }

    private Field createPhoneField() {
        return new Field(
                FieldConstant.FIELD_TITLE_PHONE,
                FieldConstant.FIELD_NAME_PHONE,
                FieldType.INPUT,
                InputType.NUMBER,
                false,
                false,
                optionBuilder.getEmptyOptions()
        );
    }

    private Field createEmailField() {
        return new Field(
                FieldConstant.FIELD_TITLE_EMAIL,
                FieldConstant.FIELD_NAME_EMAIL,
                FieldType.INPUT,
                InputType.TEXT,
                false,
                false,
                optionBuilder.getEmptyOptions()
        );
    }

    private Field createLastField() {
        return new Field(
                FieldConstant.FIELD_TITLE_LAST,
                FieldConstant.FIELD_NAME_LAST,
                FieldType.INPUT,
                InputType.TEXT,
                false,
                true,
                optionBuilder.getEmptyOptions()
        );
    }

    private Field createConfirmPasswordField() {
        return new Field(
                FieldConstant.FIELD_TITLE_CONFIRM_PASSWORD,
                FieldConstant.FIELD_NAME_CONFIRM_PASSWORD,
                FieldType.INPUT,
                InputType.PASSWORD,
                false,
                false,
                optionBuilder.getEmptyOptions()
        );
    }

    private Field createPasswordField() {
        return new Field(
                FieldConstant.FIELD_TITLE_PASSWORD,
                FieldConstant.FIELD_NAME_PASSWORD,
                FieldType.INPUT,
                InputType.PASSWORD,
                false,
                false,
                optionBuilder.getEmptyOptions()
        );
    }

    private Field createEmptyField() {
        return new Field(
                FieldConstant.FIELD_TITLE_EMPTY,
                FieldConstant.FIELD_NAME_EMPTY,
                FieldType.EMPTY,
                InputType.EMPTY,
                false,
                true,
                optionBuilder.getEmptyOptions()
        );
    }

    private Field createLoginField() {
        return new Field(
                FieldConstant.FIELD_TITLE_LOGIN,
                FieldConstant.FIELD_NAME_LOGIN,
                FieldType.INPUT,
                InputType.TEXT,
                false,
                false,
                optionBuilder.getEmptyOptions()
        );
    }

}
