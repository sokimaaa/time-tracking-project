package timekeeping.my.view.form;

import timekeeping.my.view.field.FieldEnum;

public final class ConstantForm {

    /**
     * FORM ID
     * */
    public static final String FORM_LOGIN_ID = "loginForm";
    public static final String FORM_REGISTER_ID = "registerForm";
    public static final String FORM_CABINET_ID = "cabinetForm";
    public static final String FORM_ADMIN_CATEGORIES_ID = "categoriesForm";
    public static final String FORM_ADD_ACTIVITY_ID = "addActivityForm";
    public static final String FORM_CALCULATE_ID = "calculateForm";
    public static final String FORM_JOIN_ID = "joinForm";

    /**
     * FORM BUTTON TEXT
     * */
    public static final String FORM_LOGIN_BUTTON_TEXT = "form.button.login";
    public static final String FORM_REGISTER_BUTTON_TEXT = "form.button.register";
    public static final String FORM_CABINET_BUTTON_TEXT = "form.button.cabinet";
    public static final String FORM_ADMIN_CATEGORIES_BUTTON_TEXT = "form.button.admin.categories";
    public static final String FORM_ADD_ACTIVITY_BUTTON_TEXT = "form.button.add.activity";
    public static final String FORM_CALCULATE_BUTTON_TEXT = "form.button.calculate";
    public static final String FORM_JOIN_BUTTON_TEXT = "form.button.join";

    /**
     * FORM TITTLE
     * */
    public static final String FORM_LOGIN_TITTLE = "form.title.login";
    public static final String FORM_REGISTER_TITTLE = "form.title.register";
    public static final String FORM_CABINET_TITTLE = "form.title.cabinet";
    public static final String FORM_ADMIN_CATEGORIES_TITTLE = "form.title.admin.categories";
    public static final String FORM_ADD_ACTIVITY_TITTLE = "form.title.add.activity";
    public static final String FORM_CALCULATE_TITTLE = "form.title.calculate";
    public static final String FORM_JOIN_TITTLE = "form.title.join";

    /**
     * FORM ACTION
     * */
    public static final String FORM_LOGIN_ACTION = "/toLogin";
    public static final String FORM_REGISTER_ACTION = "/toRegister";
    public static final String FORM_CABINET_ACTION = "/update";
    public static final String FORM_ADMIN_CATEGORIES_ACTION = "/add";
    public static final String FORM_ADD_ACTIVITY_ACTION = "/toCreate";
    public static final String FORM_CALCULATE_ACTION = "/calculate?id=";
    public static final String FORM_JOIN_ACTION = "/join?id=";

    /**
     * FORM METHOD
     * */
    public static final String FORM_LOGIN_METHOD = FormMethod.POST.getMethod();
    public static final String FORM_REGISTER_METHOD = FormMethod.POST.getMethod();
    public static final String FORM_CABINET_METHOD = FormMethod.POST.getMethod();
    public static final String FORM_ADMIN_CATEGORIES_METHOD = FormMethod.POST.getMethod();
    public static final String FORM_ADD_ACTIVITY_METHOD = FormMethod.POST.getMethod();
    public static final String FORM_CALCULATE_METHOD = FormMethod.POST.getMethod();
    public static final String FORM_JOIN_METHOD = FormMethod.POST.getMethod();

    /**
     * FORM FIELDS
     * */
    public static final FieldEnum[] FORM_LOGIN_FIELDS = new FieldEnum[]
            {FieldEnum.LOGIN, FieldEnum.PASSWORD};
    public static final FieldEnum[] FORM_REGISTER_FIELDS = new FieldEnum[]
            {FieldEnum.LOGIN, FieldEnum.PASSWORD, FieldEnum.CONFIRM_PASSWORD};
    public static final FieldEnum[] FORM_CABINET_FIELDS = new FieldEnum[]
            {FieldEnum.EMAIL, FieldEnum.PHONE, FieldEnum.SEXES, FieldEnum.CITIES};
    public static final FieldEnum[] FORM_ADMIN_CATEGORIES_FIELDS = new FieldEnum[]
            {FieldEnum.CATEGORY};
    public static final FieldEnum[] FORM_ADD_ACTIVITY_FIELDS = new FieldEnum[]
            {FieldEnum.ACTIVITY_NAME, FieldEnum.CATEGORIES, FieldEnum.CITIES, FieldEnum.DESCRIPTION};
    public static final FieldEnum[] FORM_CALCULATE_FIELDS = new FieldEnum[]
            {FieldEnum.ENTER_TIME};
    public static final FieldEnum[] FORM_JOIN_FIELDS = new FieldEnum[]
            {};

    private ConstantForm() {

    }

}
