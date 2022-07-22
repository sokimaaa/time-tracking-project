package timekeeping.my.model.entity;

import java.io.Serializable;
import java.math.BigInteger;

public class Activity extends Entity implements Serializable {

    private String name;
    private Category category;
    private City city;
    private String description;
    private Status status;
    private BigInteger count;
    private User owner;

    {
        count = new BigInteger("0");
    }

    public Activity() {

    }

    public Activity(int id) {
        super(id);
    }

    public void incrementCount() {
        this.count = this.count.add(new BigInteger("1"));
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public City getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public BigInteger getCount() {
        return count;
    }

    public User getOwner() {
        return owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setDescriptions(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", city=" + city +
                ", descriptions='" + description + '\'' +
                ", status=" + status +
                ", count=" + count +
                ", owner=" + owner +
                '}';
    }

}
