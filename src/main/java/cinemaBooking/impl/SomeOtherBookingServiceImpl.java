package cinemaBooking.impl;

import cinemaBooking.exception.BookingException;
import cinemaBooking.model.Booking;
import cinemaBooking.model.Hall;
import cinemaBooking.service.BookingService;

public class SomeOtherBookingServiceImpl implements BookingService {
    @Override
    public Booking bookDefaultSelection(int people, String start, Hall hall) throws BookingException {
        return null;
    }

    @Override
    public Booking bookCustomSelection(int people, String start, Hall hall) throws BookingException {
        return null;
    }

    @Override
    public Booking fetch(String bookingId, Hall hall) throws BookingException {
        return null;
    }

    @Override
    public boolean delete(String bookingId, Hall hall) throws BookingException {
        return false;
    }

    @Override
    public boolean confirmSelection(Hall hall, Booking booking) throws BookingException {
        return false;
    }
}
