package timekeeping.my.model.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Access {
    ALLOW(1, true),
    DENY(2, false);

    private static final Logger log = LoggerFactory.getLogger(Access.class);
    private final Integer id;
    private final boolean access;

    Access(Integer id, boolean access) {
        this.id = id;
        this.access = access;
    }

    public Integer getId() {
        return id;
    }

    public boolean isAccess() {
        return access;
    }

    public static Access getAccess(Integer id) {
        switch (id) {
            case 1:
                return Access.ALLOW;
            case 2:
                return Access.DENY;
            default:
                log.error("Provided wrong id ==> " + id);
                throw new IllegalArgumentException("Provided illegal id ==> " + id);
        }
    }

}
