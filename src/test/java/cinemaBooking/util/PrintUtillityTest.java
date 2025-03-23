package cinemaBooking.util;

import cinemaBooking.exception.BookingException;
import cinemaBooking.model.Booking;
import cinemaBooking.model.CinemaHall;
import cinemaBooking.model.Hall;
import cinemaBooking.model.Seating;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.image.AreaAveragingScaleFilter;
import java.awt.print.Book;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintUtillityTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @BeforeEach
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void printError_printsErrorMessageToConsole() {
        BookingException exception = Mockito.mock(BookingException.class);
        Mockito.when(exception.getMessage()).thenReturn("Mocked error message");
        PrintUtility.printError(exception);
        assertEquals(outContent.toString().trim(), "Mocked error message");
    }

    @Test
    public void printBooking_printsCorrectBooking() {
        Hall hall = Mockito.mock(CinemaHall.class);
        Map<String,Booking> bookings = Mockito.mock(HashMap.class);
        Booking booking = Mockito.mock(Booking.class);
        Mockito.when(hall.getBookings()).thenReturn(bookings);
        Mockito.when(bookings.get(Mockito.anyString())).thenReturn(booking);
        Mockito.when(booking.getBookingId()).thenReturn("Mocked Booking Id");
        Mockito.when(hall.getCol()).thenReturn(2);
        Mockito.when(hall.getRow()).thenReturn(2);
        Mockito.when(hall.getTheatre()).thenReturn(new int[2][2]);
        List<Seating> seatingArrangemnt = getSeatingArrangement();
        Mockito.when(booking.getSeatingArrangement()).thenReturn(seatingArrangemnt);
        PrintUtility.printBooking(hall,"any string");
        String[] output = outContent.toString().split(System.lineSeparator());
        assertEquals(output[0],"Booking id: Mocked Booking Id");
        assertEquals(output[1],"Selected seats:");
        assertEquals(output[2],"");
        assertEquals(output[3]," SCREEN");
        assertEquals(output[4],"_______");
        assertEquals(output[5],"B  .  .");
        assertEquals(output[6],"A  .  O");
        assertEquals(output[7],"   1  2");
    }

    private List<Seating> getSeatingArrangement() {
        List<Seating> seatings = new ArrayList<>();
        List<Integer> seats = new ArrayList<>();
        seats.add(1);
        Seating seating = new Seating(1,seats);
        seatings.add(seating);
        return seatings;
    }

}
