package model;

import java.util.List;

public class Company {

    private String id;
    private List<User> users;

    public Company(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", users=" + users +
                '}';
    }
}
