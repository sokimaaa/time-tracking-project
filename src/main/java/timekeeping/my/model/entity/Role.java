package timekeeping.my.model.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public enum Role implements Serializable {
    ADMIN(1, "admin"),
    USER(2, "user"),
    ANONYMOUS(3, "anonymous");

    private static final Logger log = LoggerFactory.getLogger(Role.class);
    private final int id;
    private final String role;

    Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public static Role getRole(int id) {
        for (Role role: Role.values()) {
            if(role.getId()==id) {
                return role;
            }
        }
        log.error("Provided wrong id ==> " + id);
        throw new IllegalArgumentException("Provided wrong id ==> " + id);
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return role.toUpperCase();
    }

}
