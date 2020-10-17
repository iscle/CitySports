package me.iscle.elclub.model;

public class Player {
    private int id;
    private String name;
    private String license;
    private String phone;

    public Player(int id, String name, String license, String phone) {
        this.id = id;
        this.name = name;
        this.license = license;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLicense() {
        return license;
    }

    public String getPhone() {
        return phone;
    }
}
