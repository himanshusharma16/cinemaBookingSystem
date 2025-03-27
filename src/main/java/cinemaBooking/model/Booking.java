package cinemaBooking.model;

import java.util.List;

public class Booking {
    private String bookingId;
    private int people;
    private List<Seating> seatingArrangement;

    public Booking() {
    }

    public Booking(String bookingId, int people, List<Seating> seatingArrangement) {
        this.bookingId = bookingId;
        this.people = people;
        this.seatingArrangement = seatingArrangement;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public List<Seating> getSeatingArrangement() {
        return seatingArrangement;
    }

    public void setSeatingArrangement(List<Seating> seatingArrangement) {
        this.seatingArrangement = seatingArrangement;
    }

}
