package com.aboushanab;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    static List<Car> cars = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();
    static List<Reservation> reservations = new ArrayList<>();
    static List<String> tasks = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        addCars();
        addTasks();
        while (true) {
            System.out.println("\t\t\t\t\tWelcome to Car Rental App!\n Please choose one of the following! \n" +
                    "1- Customer \t\t 2- Admin");
            String choice = input.next();
            if (choice.equals("1")) {
                initializeCustomer();
            } else if (choice.equals("2")) {
                initializeAdmin();
            } else {
                System.out.println("Wrong choice, Please try again");
            }
        }
    }
    public static void initializeCustomer() {
        Scanner input = new Scanner(System.in);
        Date date = validateDate();
        System.out.println("Here's a list of the available cars!");
        Car car = validateCar();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Customer customer = createCustomer();
        customers.add(customer);
        System.out.println("How many days would you like to reserve the car? (In numbers!): ");
        int days = Integer.parseInt(String.valueOf(input.next()));
        cal.add(Calendar.DAY_OF_MONTH, days);
        Date date2 = cal.getTime();
        Reservation reservation = new Reservation(customer, reservations.size()+1, car, date, date2);
        System.out.println("Total price will be: " + days * car.price + "$\tType 'ok' to confirm or anything to cancel");
        if (input.next().equalsIgnoreCase("ok")) {
            reservations.add(reservation);
            System.out.println("Reservation done successfully!");
        } else {
            System.out.println("Reservation cancelled");
        }
    }
    public static void initializeAdmin(){
        Scanner input = new Scanner(System.in);
        printTasks();
        System.out.println("Please enter a task to perform!");
        String choice = input.next();
        switch(choice) {
            case "1":
                printReservations();
                Reservation reservation = new Reservation();
                boolean x=false;
                while(!x) {
                    System.out.println("Enter reservation id to see its details");
                    int reservationID = input.nextInt();
                    if (reservationID > 0 && reservationID <= reservations.size()) {
                        reservation = getReservationByID(reservationID);
                        x=true;
                    }
                }
                System.out.println(reservation.toString());
                return;
            case "2":
                if(reservations.size()==0){
                    System.out.println("There no reservations to show!");
                }
                else {
                    printReservations();
                }
                return;
            case "3":
                boolean y=false;
                printCars();
                while(!y) {
                    System.out.println("Please enter the ID of the car to be edited");
                    int carID = input.nextInt();
                    if (carID > 0 && carID <= cars.size()) {
                        System.out.println("Enter car new name: ");
                        getCarByID(carID).setName(input.next());
                        System.out.println("Enter car new price: ");
                        getCarByID(carID).setPrice(input.nextInt());
                        System.out.println("Car updated successfully!");
                        y = true;
                    }
                    else{
                        System.out.println("Please enter a valid ID!");
                    }
                }
                return;
            case "4":
                initializeCustomer();
                return;
            case "5":
                Car newCar = new Car("Default", cars.size() + 1, "\\ō͡≡o˞̶", 0, true);
                System.out.println("Please enter car name");
                newCar.setName(input.next());
                System.out.println("Please enter car price");
                newCar.setPrice(input.nextInt());
                cars.add(newCar);
                System.out.println("Car added Successfully!");
                return;
            case "6":
                boolean z=false;
                printCars();
                while(!z) {
                    System.out.println("Please enter the ID of the car to be deactivated");
                    int carID = input.nextInt();
                    if (carID > 0 && carID <= cars.size()) {
                        getCarByID(carID).setAvailability(false);
                        System.out.println("Car deactivated successfully!");
                        z = true;
                    }
                    else{
                        System.out.println("Please enter a valid ID!");
                    }
                }
                return;
            default:
                System.out.println("Invalid choice");
        }
    }

    public static void addCars() {
        cars.add(new Car("Toyota\t", cars.size() + 1, "\\ō͡≡o˞̶", 23, true));
        cars.add(new Car("Huyndai\t", cars.size() + 1, "\\ō͡≡o˞̶", 30, true));
        cars.add(new Car("Jeep\t", cars.size() + 1, "\\ō͡≡o˞̶", 40, true));
        cars.add(new Car("Mercedes", cars.size() + 1, "\\ō͡≡o˞̶", 50, true));
        cars.add(new Car("BMW\t\t", cars.size() + 1, "\\ō͡≡o˞̶", 30, true));
    }
    public static void addTasks(){
        tasks.add("1-Display Booking");
        tasks.add("2-See all reservations");
        tasks.add("3-Edit car");
        tasks.add("4-Book car");
        tasks.add("5-Add new car");
        tasks.add("6-Deactivate car");
    }
    public static void printTasks(){
        int i;
        for(i=0;i<tasks.size();i++){
            System.out.println(tasks.get(i)+"\n");
        }
    }
    public static void printReservations(){
        int i;
        for(i=0;i<reservations.size();i++){
            System.out.println((i+1)+"-"+reservations.get(i).getCustomer()+"\n");
        }

    }
    public static void printCars(){
        int i;
        for(i=0;i<cars.size();i++){
            if(cars.get(i).availability==true) {
                System.out.println((i + 1) + "-" + cars.get(i).toString() + "\n");
            }
        }

    }
    public static Reservation getReservationByID(int id){
        int i;
        for(i=0;i<reservations.size();i++){
            if (id==reservations.get(i).id){
                return reservations.get(i);
            }
        }
        return null;
    }
    public static Car getCarByID(int id){
        int i;
        for(i=0;i<cars.size();i++){
            if (id==cars.get(i).id){
                return cars.get(i);
            }
        }
        return null;
    }
    public static Date validateDate(){
        String date;
        boolean validDate = false;
        Date date2 = null;
        while (!validDate) {
            System.out.println("Please enter a date in the form of dd-mm-yyyy");
            date = input.next();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                //Parsing the String
                date2 = dateFormat.parse(date);
                validDate = true;
                return date2;
            } catch (ParseException e) {
                System.out.println("Please enter a valid date!");
            }
        }
        return null;
    }
    public static Car validateCar(){
        int i;
        boolean validCar = false;

        while (!validCar) {
            printCars();
            System.out.println("Please enter the id of the car you want (Only numbers allowed): ");
            String carID = input.next();
            int carIDInt = Integer.parseInt(String.valueOf(carID));
            if (carIDInt > 0 && carIDInt <= cars.size()) {
                for (i = 0; i < cars.size(); i++) {
                    if (carIDInt == cars.get(i).id) {
                        validCar = true;
                        return cars.get(i);
                    }
                }
            } else {
                System.out.println("Please enter a valid ID!");
            }
        }
        return null;
    }
    public static Customer createCustomer(){
        Customer customer = new Customer();
        System.out.println("Please enter your details\n Name: ");
        customer.name = input.next();
        System.out.println("E-mail: ");
        customer.email = input.next();
        System.out.println("Address: ");
        customer.address = input.next();
        System.out.println("Title: ");
        customer.title = input.next();
        System.out.println("Phone: ");
        customer.phone = input.next();
        customer.id = customers.size()+1;
        return customer;
    }
}