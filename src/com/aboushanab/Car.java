package com.aboushanab;

public class Car {
    int id;
    String name;
    String pic;
    int price;
    Boolean availability;
public Car(){}
    public Car(String name, int id, String pic, int price, Boolean availability) {
        this.name = name;
        this.id = id;
        this.pic = pic;
        this.price = price;
        this.availability = availability;
    }


    @Override
    public String toString() {
        return this.pic+"\t\t"+this.name+"\t\t"+this.price+"$/day"+"\t\tID: "+this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
