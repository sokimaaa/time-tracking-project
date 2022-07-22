package timekeeping.my.view.form;

/**
 * The form method enum provided all supported methods
 * */
public enum FormMethod {
    GET("get"), POST("post");

    private final String method;

    FormMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
