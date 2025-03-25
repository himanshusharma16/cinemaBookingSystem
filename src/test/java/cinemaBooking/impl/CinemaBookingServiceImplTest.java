package cinemaBooking.impl;

import cinemaBooking.exception.BookingException;
import cinemaBooking.model.Booking;
import cinemaBooking.model.CinemaHall;
import cinemaBooking.model.Hall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CinemaBookingServiceImplTest {

    private CinemaBookingServiceImpl bookingService;
    private CinemaHall theatre;

    @BeforeEach
    public void setUp() {
        theatre = new CinemaHall(2,2);
        bookingService = new CinemaBookingServiceImpl();
    }

    @Test
    public void whenBookExtraSeats_throwsBookingException() {
        var expectedResponse = "Sorry, there are only 4 seats available.";

        Exception exception = assertThrows(BookingException.class, () -> {
            bookingService.bookDefaultSelection(100, null, theatre);
        });

        assertTrue(exception.getMessage().equals(expectedResponse));
    }

    @Test
    public void whenBookAllSeats_givesSuccess() throws BookingException {
        var expectedResponseBooking1 = "GIC0001";
        var expectedResponseBooking10 = "GIC0010";
        var response = bookingService.bookDefaultSelection(2, null, theatre);
        assertEquals(response.getBookingId(), expectedResponseBooking1);

        for(int i = 1; i <= 9; i++) //add 9 bookings
            theatre.getBookings().put(String.valueOf(i),null);

        var response2 = bookingService.bookDefaultSelection(2, null, theatre);
        assertEquals(response2.getBookingId(), expectedResponseBooking10);
    }

    @Test
    public void whenBookSeats_defaultConfiguration_givesValidBooking(){
        List<Integer> expectedResponse = new ArrayList<>();
        expectedResponse.add(1);expectedResponse.add(2);expectedResponse.add(3);expectedResponse.add(4);expectedResponse.add(0);
        Hall hall = new CinemaHall(5,5);
        try {
            var booking = bookingService.bookDefaultSelection(7, null, hall);
            assertNotNull(booking);
            assertNotNull(booking.getSeatingArrangement());
            assertEquals(booking.getBookingId(), "GIC0001");
            assertEquals(booking.getPeople(),7);
            assertEquals(booking.getSeatingArrangement().size(),2);
            assertEquals(booking.getSeatingArrangement().get(0).getSeats().size(),5);
            assertEquals(booking.getSeatingArrangement().get(0).getRow(),4);
            assertTrue(booking.getSeatingArrangement().get(0).getSeats().containsAll(expectedResponse));
            assertEquals(booking.getSeatingArrangement().get(1).getSeats().size(),2);
            assertEquals(booking.getSeatingArrangement().get(1).getRow(),3);
            assertTrue(booking.getSeatingArrangement().get(1).getSeats().contains(2));

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Test
    public void whenBookSeats_customConfiguration_givesValidBooking(){
        List<Integer> expectedResponse = new ArrayList<>();
        expectedResponse.add(2);expectedResponse.add(3);expectedResponse.add(4);
        Hall hall = new CinemaHall(5,5);
        try {
            var booking = bookingService.bookCustomSelection(7, "C03", hall);
            assertNotNull(booking);
            assertNotNull(booking.getSeatingArrangement());
            assertEquals(booking.getBookingId(), "GIC0001");
            assertEquals(booking.getPeople(),7);
            assertEquals(booking.getSeatingArrangement().size(),2);
            assertEquals(booking.getSeatingArrangement().get(0).getSeats().size(),3);
            assertEquals(booking.getSeatingArrangement().get(0).getRow(),2);
            assertTrue(booking.getSeatingArrangement().get(0).getSeats().containsAll(expectedResponse));
            assertEquals(booking.getSeatingArrangement().get(1).getSeats().size(),4);
            assertEquals(booking.getSeatingArrangement().get(1).getRow(),1);
            assertTrue(booking.getSeatingArrangement().get(1).getSeats().contains(0));
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Test
    public void whenBookSeats_customConfiguration_withInvalidInput_throwsException() {
        var expectedResponse = "Unable to locate Z99 location in Cinema. Please provide a valid location.";

        Exception exception = assertThrows(BookingException.class, () -> {
            bookingService.bookCustomSelection(100, "Z99", theatre);
        });

        assertTrue(exception.getMessage().equals(expectedResponse));
    }

    @Test
    public void whenDeleteBooking_vacatesSeats(){
        assertTrue(bookingService.delete(null,null));
    }

    @Test
    public void whenShowBooking_validBooking_givesCorrectBookingDetails() throws BookingException {
        Booking booking = new Booking("VALID_BOOKING",1,null);
        theatre.getBookings().put("VALID_BOOKING", booking);
        var response = bookingService.fetch("VALID_BOOKING", theatre);
        assertEquals(response, booking);
    }

    @Test
    public void whenShowBooking_invalidBooking_givesError(){
        var expectedResponse = "No booking found with Booking Id - INVALID_BOOKING";

        Exception exception = assertThrows(BookingException.class, () -> {
            bookingService.fetch("INVALID_BOOKING", theatre);
        });

        assertTrue(exception.getMessage().equals(expectedResponse));
    }

    @Test
    public void whenConfirmBooking_theatreIsUpdated(){
        Hall hall = new CinemaHall(5,5);
        try {
            var booking = bookingService.bookDefaultSelection(8, null, hall);
            assertTrue(bookingService.confirmSelection(hall, booking));
            assertEquals(hall.getBookings().get(booking.getBookingId()), booking);
        } catch (Exception e) {

        }
        assertTrue(hall.getBookings().size() > 0);
        assertTrue(hall.getTheatre()[3][2] == 1);
    }
}
