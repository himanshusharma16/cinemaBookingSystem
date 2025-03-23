package cinemaBooking.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public abstract class Hall {
    protected int totalSeats;
    @Setter
    protected int vacantSeats;
    protected Map<String, Booking> bookings;
    protected int row;
    protected int col;
    protected String title;
    protected int[][] theatre;

}
