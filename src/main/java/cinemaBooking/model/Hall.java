package cinemaBooking.model;

import java.util.Map;

public abstract class Hall {
    protected int totalSeats;
    protected int vacantSeats;
    protected Map<String, Booking> bookings;
    protected int row;
    protected int col;
    protected String title;
    protected int[][] theatre;

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getVacantSeats() {
        return vacantSeats;
    }

    public void setVacantSeats(int vacantSeats){
        this.vacantSeats = vacantSeats;
    }

    public Map<String, Booking> getBookings() {
        return bookings;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getTitle() {
        return title;
    }

    public int[][] getTheatre() {
        return theatre;
    }

}
