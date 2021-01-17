package com.example.petshop.Class;

import android.location.Address;

public class Customer {
    public Customer() {
    }
    private String username;
    private String displayName;
    private String address;
    private String password;
    private String phone;

    public Customer(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Customer(String username, String displayName, String address, String password, String phone) {
        this.username = username;
        this.displayName = displayName;
        this.address = address;
        this.password = password;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
