package com.aboushanab;

public class Customer {
    int id;
    String name;
    String email;
    String address;
    String title;
    String phone;
public Customer(){}

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                '}';
    }
}
