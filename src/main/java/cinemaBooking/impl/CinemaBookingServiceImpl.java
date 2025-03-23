package cinemaBooking.impl;

import cinemaBooking.exception.BookingException;
import cinemaBooking.model.Booking;
import cinemaBooking.model.Hall;
import cinemaBooking.model.Seating;
import cinemaBooking.service.BookingService;
import cinemaBooking.util.SeatExtractionUtility;

import java.util.ArrayList;
import java.util.List;

public class CinemaBookingServiceImpl implements BookingService {

    @Override
    public Booking bookDefaultSelection(int people, String start, Hall hall) throws BookingException {
        if(people > hall.getVacantSeats())
            throw new BookingException(generateErrorMessage(hall.getVacantSeats()));
        else {
            int rowToSearch = hall.getRow() - calculateRowOffset(start);
            var seatingArrangement = calculateDefaultSeatingArrangement(hall, people, rowToSearch);
            var bookingId = generateNextBookingNumber(hall.getBookings().size() + 1);
            return new Booking(bookingId, people, seatingArrangement);
        }
    }

    private int calculateRowOffset(String start) {
        return (start != null && !start.isEmpty()) ? SeatExtractionUtility.extractRowIndex(start) : 1;
    }

    private List<Seating> calculateDefaultSeatingArrangement(Hall hall, int people, int row) throws BookingException{
        int col = hall.getCol();
        int[][] theatre = hall.getTheatre();
        List<Seating> seatings = new ArrayList<>();
        while(row >= 0 && people > 0){
            Seating seating = new Seating(row, new ArrayList<>());
            int[] middle = findMiddleCol(col);
            while(people > 0 && middle[0] >= 0 && middle[1] < col) {
                if (people > 0 && theatre[row][middle[0]] == 0) {
                    people--;
                    seating.getSeats().add(middle[0]);
                }
                if (middle[1] != middle[0] && people > 0 && theatre[row][middle[1]] == 0) {
                    people--;
                    seating.getSeats().add(middle[1]);
                }
                middle[0]--;
                middle[1]++;
            }
            row--;
            if(seating.getSeats().size() > 0)
                seatings.add(seating);
        }
        if(people > 0){
            throw new BookingException("Unable to seat all people from this start point");
        }
        return seatings;
    }

    private int[] findMiddleCol(int col) { //returns the index of middle most columns
        int mid = col/2;
        if(col % 2 == 0){
            return new int[]{mid-1,mid};
        } else {
            return new int[]{mid,mid};
        }
    }

    private static String generateErrorMessage(int vacantSeats) {
        return String.format("Sorry, there are only %s seats available.", vacantSeats);
    }

    private String generateNextBookingNumber(int nextBookingNumber) {
        return String.format("GIC%04d", nextBookingNumber);
    }

    @Override
    public Booking bookCustomSelection(int people, String start, Hall hall) throws BookingException{
        int row = hall.getRow() - calculateRowOffset(start);
        int col = SeatExtractionUtility.extractColIndex(start);
        if(row >= hall.getRow() || row < 0 || col < 0 || col > hall.getCol())
            throw new BookingException("Unable to locate "+start+" location in Cinema. Please provide a valid location.");
        List<Seating> seatingArrangement = calculateCustomSeatingArrangement(row, col, hall, people);
        return new Booking(generateNextBookingNumber(hall.getBookings().size() + 1), people, seatingArrangement);
    }

    private List<Seating> calculateCustomSeatingArrangement( int row, int col, Hall hall, int people) throws BookingException{
        List<Seating> seatings = new ArrayList<>();
        int[][] theatre = hall.getTheatre();
        Seating seating = new Seating(row, new ArrayList<>());
        seating.setRow(row);
        while(people > 0 && col < hall.getCol()){
            if(theatre[row][col] == 0){
                people--;
                seating.getSeats().add(col);
            }
            col++;
        }
        if(seating.getSeats().size() > 0)
            seatings.add(seating);
        seatings.addAll(calculateDefaultSeatingArrangement(hall, people , row-1));
        return seatings;
    }

    @Override
    public Booking fetch(String bookingId, Hall hall) throws BookingException{
        var bookings = hall.getBookings();
        if(bookings == null || bookings.isEmpty() || bookings.get(bookingId) == null)
            throw new BookingException("No booking found with Booking Id - "+bookingId);
        else
            return bookings.get(bookingId);
    }

    @Override
    public boolean delete(String bookingId, Hall hall) {
        return true;
    }

    @Override
    public boolean confirmSelection(Hall hall, Booking booking) {
        hall.getBookings().put(booking.getBookingId(), booking);
        var theatre = hall.getTheatre();
        for(var seating : booking.getSeatingArrangement()){
            int row = seating.getRow();
            for(int col : seating.getSeats())
                theatre[row][col] = 1;
        }
        hall.setVacantSeats(hall.getVacantSeats() - booking.getPeople());
        return true;
    }
}