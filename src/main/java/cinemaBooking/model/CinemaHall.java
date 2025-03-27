package cinemaBooking.model;

import java.util.HashMap;

public class CinemaHall extends Hall{

    public CinemaHall(int rows, int cols){
        this.row = rows;
        this.col = cols;
        this.totalSeats = (rows*cols);
        this.vacantSeats = (rows*cols);
        this.theatre = new int[rows][cols];
        this.bookings = new HashMap<>();
    }

    public CinemaHall(String title, int rows, int cols){
        this(rows,cols);
        this.title = title;
    }

}
