package cinemaBooking.service;

import cinemaBooking.exception.BookingException;
import cinemaBooking.model.Booking;
import cinemaBooking.model.Hall;

import java.awt.print.Book;

public interface BookingService {

    Booking bookDefaultSelection(int people, String start, Hall hall) throws BookingException;
    Booking bookCustomSelection(int people, String start, Hall hall) throws BookingException;

    Booking fetch(String bookingId, Hall hall) throws BookingException;
    boolean delete(String bookingId, Hall hall) throws BookingException;
    boolean confirmSelection(Hall hall, Booking booking) throws BookingException;

}
