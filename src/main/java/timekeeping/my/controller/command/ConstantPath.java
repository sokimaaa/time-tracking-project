package timekeeping.my.controller.command;

import java.util.Collections;
import java.util.List;

public class ConstantPath {

    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String HOME = "/home";
    public static final String ACCESS_DENY = "/deny";
    public static final String PERSONAL_CABINET = "/cabinet";
    public static final String CREATE_CABINET = "/create";
    public static final String ACTIVITY_CABINET = "/activity";
    public static final String ADMIN_PAGE = "/admin";
    public static final String TOP_USERS = "/top";

    public static final List<String> ADMIN_URL = Collections.emptyList();
    public static final List<String> USER_URL = List.of(ADMIN_PAGE);
    public static final List<String> ANONYMOUS_URL = List.of(ADMIN_PAGE, ACTIVITY_CABINET, CREATE_CABINET,
            HOME, PERSONAL_CABINET, TOP_USERS);

    private ConstantPath() {

    }
}
