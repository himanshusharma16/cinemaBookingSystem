package cinemaBooking.handler;

import cinemaBooking.model.CinemaHall;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ClassLoaderUtils;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingFlowHandlerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final InputStream originalIn = System.in;

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }

    @BeforeEach
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void whenSelectExitFromWelcomeMenu_emitsGoodbyeMessage() {
        ByteArrayInputStream in = new ByteArrayInputStream("3".getBytes());
        System.setIn(in);
        Scanner myScanner = new Scanner(in);
        var handler = new BookingFlowHandler(myScanner);
        var hall = new CinemaHall(2,2);
        handler.handle(hall);
        assertTrue(outContent.toString().contains("Thank you for using GIC Cinemas system. Bye!"));
    }

    @Test
    public void whenInvalidInputInWelcomeMenu_emitsErrorMessage() {
        InputStream inputStream = ClassLoaderUtils.getDefaultClassLoader().getResourceAsStream("testWelcome.txt");
        Scanner myScanner = new Scanner(inputStream);
        var handler = new BookingFlowHandler(myScanner);
        var hall = new CinemaHall(2,2);
        handler.handle(hall);
        assertTrue(outContent.toString().contains("Wrong input. Please try again!!"));
    }

    @Test
    public void whenSearchInvalidBooking_emitsErrorMessage() {
        InputStream inputStream = ClassLoaderUtils.getDefaultClassLoader().getResourceAsStream("testShowBooking.txt");
        Scanner myScanner = new Scanner(inputStream);
        var handler = new BookingFlowHandler(myScanner);
        var hall = new CinemaHall(2,2);
            handler.handle(hall);
        assertTrue(outContent.toString().contains("No booking found with Booking Id - DummyBooking"));
    }

    @Test
    public void whenBookSeats_hallIsUpdated() {
        InputStream inputStream = ClassLoaderUtils.getDefaultClassLoader().getResourceAsStream("testMakeBooking.txt");
        Scanner myScanner = new Scanner(inputStream);
        var handler = new BookingFlowHandler(myScanner);
        var hall = new CinemaHall("TEST",4,4);
        try {
            handler.handle(hall);
        } catch (NoSuchElementException e){}
        assertEquals(1,hall.getBookings().size());
        assertEquals(10,hall.getVacantSeats());
        assertTrue(outContent.toString().contains("Booking id: GIC0001 confirmed."));
    }
}
