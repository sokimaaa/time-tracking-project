package timekeeping.my.model.entity;

import java.io.Serializable;

public class User extends Entity implements Serializable {

    private Role role;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Sex sex;
    private City city;
    private Access access;

    public User() {

    }

    public User(int id) {
        super(id);
    }

    public Role getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Sex getSex() {
        return sex;
    }

    public City getCity() {
        return city;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    @Override
    public String toString() {
        return "User{" +
                "role=" + role +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", city=" + city +
                ", access=" + access +
                '}';
    }

}
