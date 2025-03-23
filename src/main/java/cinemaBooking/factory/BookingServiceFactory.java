package cinemaBooking.factory;

import cinemaBooking.impl.CinemaBookingServiceImpl;
import cinemaBooking.model.CinemaHall;
import cinemaBooking.service.BookingService;

import java.util.HashMap;
import java.util.Map;

public class BookingServiceFactory {
    static Map<Class,BookingService> serviceMap;

    static{
        serviceMap = new HashMap<>();
        serviceMap.put(CinemaHall.class,new CinemaBookingServiceImpl());
    }

    public static BookingService getBookingService(Class classType) {
        return serviceMap.get(classType);
    }
}
