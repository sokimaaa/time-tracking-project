package timekeeping.my.controller.features.mail;

public enum Event {
    UPDATE_ACTIVITY("Updating Activity"),
    UPDATE_TOP_10_USERS("Top 10 Users");

    private final String name;
    Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
