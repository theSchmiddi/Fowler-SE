package main;

import java.lang.*;
import java.util.*;

public class Customer {
    private String name;
    private Vector rentals = new Vector();
    public Customer (String newname){
        name = newname;
    };
    public void addRental(Rental arg) {
        rentals.addElement(arg);
    };
    public String getName (){
        return name;
    };
    public String statement() {
       return getRentalRecord();
    }

    private String getRentalRecord() {
        String result = "Rental Record for " + this.getName() + "\n";
        result += "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";
        result += calculateRentalRecord();
        return result;
    }

    private String calculateRentalRecord() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration enum_rentals = rentals.elements();
        String allRentalDates = "";

        while (enum_rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental rental = (Rental) enum_rentals.nextElement();
            //determine amounts for each line
            thisAmount = rental.getAmountFor();
            // add frequent renter points
            frequentRenterPoints = addFrequentRenterPoints(frequentRenterPoints);
            // add bonus for a two day new release rental
            if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1)
                frequentRenterPoints ++;
            //show figures for this rental
            allRentalDates += "\t" + rental.getMovie().getTitle()+ "\t" + "\t" + rental.getDaysRented() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        //add footer lines
        allRentalDates += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        allRentalDates += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return allRentalDates;
    }

    private int addFrequentRenterPoints(int frequentRenterPoints) {
        frequentRenterPoints++;
        return frequentRenterPoints;
    }

}
    