package timekeeping.my.model.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.util.LanguageManager;

import java.util.Arrays;

public enum Sex {
    NONE(1, "sex.none"),
    MALE(2, "sex.male"),
    FEMALE(3, "sex.female");

    private static final Logger log = LoggerFactory.getLogger(Sex.class);
    private final int id;
    private final String code;

    Sex(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return code;
    }

    public static Sex getSex(String name) {
        for (Sex sex: Sex.values()) {
            if(sex.code.equals(name.toLowerCase())) {
                return sex;
            }
        }
        log.error("Provided wrong name ==> " + name + ". Available now: " + Arrays.toString(Sex.values()));
        throw new IllegalArgumentException("Provided wrong name ==> " + name);
    }

    public static Sex getSex(int id) {
        for (Sex sex: Sex.values()) {
            if(sex.getId() == id) {
                return sex;
            }
        }
        log.error("Provided wrong id ==> " + id);
        throw new IllegalArgumentException("Provided wrong id ==> " + id);
    }

    @Override
    public String toString() {
        return LanguageManager.getInstance().getString(code);
    }
}
