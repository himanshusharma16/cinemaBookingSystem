package cinemaBooking.factory;

import cinemaBooking.impl.CinemaBookingServiceImpl;
import cinemaBooking.model.CinemaHall;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingServiceFactoryTest {


    @Test
    public void whenGetServiceWithRightClassType_correctServiceIsProvided(){
        assertEquals(BookingServiceFactory.getBookingService(CinemaHall.class).getClass(), CinemaBookingServiceImpl.class);
    }

    @Test
    public void whenGetServiceWithIncorrectClassType_nullIsProvided(){
        assertEquals(BookingServiceFactory.getBookingService(Mockito.any()), null);
    }
}
