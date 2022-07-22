package timekeeping.my.model.entity;

import java.io.Serializable;

public class Category extends Entity implements Serializable {

    private String category;

    public Category() {

    }

    public Category(int id) {
        super(id);
    }

    public String getName() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }

}
