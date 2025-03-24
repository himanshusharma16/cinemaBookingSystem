package cinemaBooking.factory;

import cinemaBooking.impl.CinemaBookingServiceImpl;
import cinemaBooking.model.CinemaHall;
import cinemaBooking.service.BookingService;

import java.util.HashMap;
import java.util.Map;
/*
factory class to create and provide correct booking service using class type of provided object
can add more booking impls on order to scale to other halls like a stadium, concert etc.
can add more entries in the map as per new concrete hall classes and respective booking implementations
 */
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
