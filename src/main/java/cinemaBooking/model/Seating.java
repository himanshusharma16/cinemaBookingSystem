package cinemaBooking.model;

import java.util.List;


public class Seating {

    private int row;
    private List<Integer> seats;
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public List<Integer> getSeats() {
        return seats;
    }

    public void setSeats(List<Integer> seats) {
        this.seats = seats;
    }

    public Seating(int row, List<Integer> seats){
        this.row = row;
        this.seats = seats;
    }

}
