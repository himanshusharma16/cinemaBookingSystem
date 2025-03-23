package cinemaBooking.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private String bookingId;
    private int people;
    private List<Seating> seatingArrangement;
}
