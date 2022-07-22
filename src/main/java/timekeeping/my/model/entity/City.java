package timekeeping.my.model.entity;

import timekeeping.my.util.LanguageManager;

import java.io.Serializable;
import java.util.Objects;

public class City extends Entity implements Serializable {

    private String name;

    public City() {

    }

    public City(int id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        if(Objects.isNull(name)) {
            return "null";
        } else {
            return LanguageManager.getInstance().getString(name);
        }
    }

}
