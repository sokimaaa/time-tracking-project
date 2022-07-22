package timekeeping.my.model.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Status {
    PRIVATE(1, "private"),
    PUBLIC(2, "public");

    private static final Logger log = LoggerFactory.getLogger(Status.class);
    private final int id;
    private final String value;

    Status(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public static Status getStatus(int id) {
        for (Status status: Status.values()) {
            if(status.getId()==id) {
                return status;
            }
        }
        log.error("Provided wrong id ==>" + id);
        throw new IllegalArgumentException();
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toUpperCase();
    }

}
