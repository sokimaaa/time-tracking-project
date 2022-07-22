package timekeeping.my.model.dao;

public final class ConstantJDBC {

    public static final String INSERT = "INSERT INTO ";
    public static final String UPDATE = "UPDATE ";
    public static final String SELECT = "SELECT ";
    public static final String DELETE = "DELETE ";
    public static final String FROM = " FROM ";
    public static final String SET = " SET ";
    public static final String AND = " AND ";
    public static final String WHERE = " WHERE ";
    public static final String JOIN = " JOIN ";
    public static final String ON = " ON ";
    public static final String VALUES = " VALUES ";
    public static final String GET_QUERY = SELECT + "*" + FROM;

    public static final String ENTITY_ID = "id";

    /**
     * Table name
     * */
    public static final String USER_TABLE_NAME = "user";
    public static final String ACTIVITY_TABLE_NAME = "activity";
    public static final String CITY_TABLE_NAME = "city";
    public static final String CATEGORY_TABLE_NAME = "category";
    public static final String ACTION_TABLE_NAME = "users_activities";

    /**
     * User column's name
     * */
    public static final String USER_ID = ENTITY_ID;
    public static final String USER_ROLE_ID = "role_id";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String USER_EMAIL = "email";
    public static final String USER_PHONE = "phone";
    public static final String USER_SEX_ID = "sex_id";
    public static final String USER_CITY_ID = "city_id";
    public static final String USER_ACCESS_ID = "access_id";
    public static final String USER_CREATE_DATE = "creating_date";
    public static final String[] USER_FIELDS = {USER_ID, USER_ROLE_ID, USER_USERNAME, USER_PASSWORD,
            USER_EMAIL, USER_PHONE, USER_SEX_ID, USER_CITY_ID, USER_ACCESS_ID, USER_CREATE_DATE};
    public static final Integer USER_LENGTH = USER_FIELDS.length;

    /**
     * Activity column's name
     * */
    public static final String ACTIVITY_ID = ENTITY_ID;
    public static final String ACTIVITY_NAME = "activity_name";
    public static final String ACTIVITY_CATEGORY_ID = "category_id";
    public static final String ACTIVITY_CITY_ID = "city_id";
    public static final String ACTIVITY_DESCRIPTION = "description";
    public static final String ACTIVITY_STATUS_ID = "status_id";
    public static final String ACTIVITY_COUNT = "count_customers";
    public static final String ACTIVITY_OWNER = "owner_id";
    public static final String[] ACTIVITY_FIELDS = {ACTIVITY_ID, ACTIVITY_NAME, ACTIVITY_CATEGORY_ID, ACTIVITY_CITY_ID,
            ACTIVITY_DESCRIPTION, ACTIVITY_STATUS_ID, ACTIVITY_COUNT, ACTIVITY_OWNER};
    public static final Integer ACTIVITY_LENGTH = ACTIVITY_FIELDS.length;

    /**
     * City column's name
     * */
    public static final String CITY_ID = ENTITY_ID;
    public static final String CITY_NAME = "city_name";
    public static final String[] CITY_FIELDS = {CITY_ID, CITY_NAME};
    public static final Integer CITY_LENGTH = CITY_FIELDS.length;

    /**
     * Category column's name
     * */
    public static final String CATEGORY_ID = ENTITY_ID;
    public static final String CATEGORY_NAME = "category_name";
    public static final String[] CATEGORY_FIELDS = {CATEGORY_ID,
            CATEGORY_NAME};
    public static final Integer CATEGORY_LENGTH = CATEGORY_FIELDS.length;

    /**
     * Action column's name
     * */
    public static final String ACTION_USER_ID = "user_id";
    public static final String ACTION_ACTIVITY_ID = "activity_id";
    public static final String ACTION_SPENT_TIME_LAST = "spent_time_last_update_minute";
    public static final String ACTION_SPENT_TIME_ALL = "total_spent_time_minute";
    public static final String ACTION_ACCESS_ID = "access_id";
    public static final String[] ACTION_FIELDS = {ACTION_USER_ID, ACTION_ACTIVITY_ID, ACTION_SPENT_TIME_LAST,
            ACTION_SPENT_TIME_ALL, ACTION_ACCESS_ID};
    public static final Integer ACTION_LENGTH = ACTION_FIELDS.length;



    private ConstantJDBC() {

    }
}
