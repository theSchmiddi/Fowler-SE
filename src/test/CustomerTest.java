package test;

import main.Customer;
import main.Movie;
import main.Rental;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    private static Customer customer;
    private static String name;

    @BeforeAll
    public static void setUp() {
        name = "Bernd";
        customer = new Customer(name);

        Movie m1 = new Movie("Shrek", 0);
        Movie m2 = new Movie("Step up", 1);

        Rental r1 = new Rental(m1, 5);
        Rental r2 = new Rental(m2, 10);

        customer.addRental(r1);
        customer.addRental(r2);
    }

    @Test
    void getName() {
        Assertions.assertEquals(name, customer.getName());
    }

    @Test
    void statement() {
        String expected = "main.Rental Record for "+name+"\n" +
                "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n" +
                "\t" + "Shrek" + "\t" + "\t" + "5" + "\t" + "6.5" + "\n" +
                "\t" + "Step up" + "\t" + "\t" + "10" + "\t" + "30.0" + "\n" +
                "Amount owed is " + "36.5" + "\n" +
                "You earned " + "3" + " frequent renter points";

        Assertions.assertEquals(expected, customer.statement());
    }

}