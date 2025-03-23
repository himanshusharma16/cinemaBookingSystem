package cinemaBooking.validator;

import cinemaBooking.exception.BookingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookingValidatorTest {

    @Test
    public void bookingValidator_givesFalse_incorrectInputForTheatreCreation_1() {
        assertFalse(BookingValidator.validateUserTheatreInput("asdsadas"));
    }

    @Test
    public void bookingValidator_givesFalse_incorrectInputForTheatreCreation_2() {
        assertFalse(BookingValidator.validateUserTheatreInput("ABC 99 99"));
    }

    @Test
    public void bookingValidator_givesFalse_incorrectInputForTheatreCreation_3() {
        assertFalse(BookingValidator.validateUserTheatreInput("ABC 9 66"));
    }

    @Test
    public void bookingValidator_givesFalse_incorrectInputForTheatreCreation_4() {
        assertFalse(BookingValidator.validateUserTheatreInput("asds adas adas"));
    }

    @Test
    public void bookingValidator_throwsException_incorrectInputForSeatSelection_1() {
        var expectedResponse = "Invalid input entered. Unable to parse row / column.";

        Exception exception = assertThrows(BookingException.class, () -> {
            BookingValidator.validateUserBookingInput("K9K");
        });

        assertTrue(exception.getMessage().equals(expectedResponse));
    }

    @Test
    public void bookingValidator_throwsException_incorrectInputForSeatSelection_2() {
        var expectedResponse = "Invalid input entered. Unable to parse row / column.";

        Exception exception = assertThrows(BookingException.class, () -> {
            BookingValidator.validateUserBookingInput("KKK");
        });

        assertTrue(exception.getMessage().equals(expectedResponse));
    }

    @Test
    public void bookingValidator_throwsException_incorrectInputForSeatSelection_3() {
        var expectedResponse = "Invalid input entered. Unable to parse row / column.";

        Exception exception = assertThrows(BookingException.class, () -> {
            BookingValidator.validateUserBookingInput("9KK");
        });

        assertTrue(exception.getMessage().equals(expectedResponse));
    }

    @Test
    public void bookingValidator_throwsException_incorrectInputForSeatSelection_4() {
        var expectedResponse = "Invalid input entered. Unable to parse row / column.";

        Exception exception = assertThrows(BookingException.class, () -> {
            BookingValidator.validateUserBookingInput("KKKK");
        });

        assertTrue(exception.getMessage().equals(expectedResponse));
    }

    @Test
    public void bookingValidator_validates_correctInputForTheatreCreation() {
        assertTrue(BookingValidator.validateUserTheatreInput("Inception 8 10"));
    }

    @Test
    public void bookingValidator_validates_correctInputForSeatSelection() throws BookingException{
        assertTrue(BookingValidator.validateUserBookingInput("B08"));
    }
}
