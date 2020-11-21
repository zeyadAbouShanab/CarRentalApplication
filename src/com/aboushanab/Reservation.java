package com.aboushanab;

import java.util.Date;

public class Reservation {
    int id;
    Customer customer;
    Car car;
    Date date1;
    Date date2;

    public Reservation(){}
    public Reservation(Customer customer, int id, Car car, Date date1,Date date2) {
        this.car = car;
        this.id= id;
        this.customer = customer;
        this.date1 = date1;
        this.date2 = date2;

    }

    @Override
    public String toString() {
        return "Id: "+this.id+"\n"+"Booked by: "+this.customer.name +"\n"+"Car: "+this.car.name+"\n"+"From: "+date1.toString()+"\n"+"To: "+date2.toString();
    }
    public String getCustomer(){
        return this.date1+"\t\t"+this.customer.name+"\t\t"+"ID: "+this.id;
    }
}
