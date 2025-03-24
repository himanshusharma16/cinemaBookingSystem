package cinemaBooking.exception;

/*custom exception class to throw error messages in case something goes wrong
 can be used to show custom messages wherever required
 */
public class BookingException extends Exception {
    public BookingException(String message) {
        super(message);
    }

}
