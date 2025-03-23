package cinemaBooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Seating {
    private int row;
    private List<Integer> seats;
}
