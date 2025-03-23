package cinemaBooking.handler;

import cinemaBooking.exception.BookingException;
import cinemaBooking.factory.BookingServiceFactory;
import cinemaBooking.model.Hall;
import cinemaBooking.service.BookingService;
import cinemaBooking.util.PrintUtility;
import cinemaBooking.validator.BookingValidator;

import java.util.Scanner;

import static cinemaBooking.util.PrintUtility.printMessageWithNewLine;

public class BookingFlowHandler {

    public static final String ENTER_BOOKING_ID = "Enter booking id, or enter blank to go back to main menu:";
    public static final String ENTER_NUMBER_OF_TICKETS_TO_BOOK = "Enter number of tickets to book, or enter blank to go back to main menu:";
    public static final String ACCEPT_SEAT_SELECTION_OR_ENTER_NEW = "Enter blank to accept seat selection, or enter new seating position";
    private Scanner sc;

    public BookingFlowHandler(Scanner sc){
        this.sc = sc;
    }

    public void handle(Hall hall) {
        var bookingService = BookingServiceFactory.getBookingService(hall.getClass());
        var doContinue = true;
        do {
            PrintUtility.printWelcomeMenu(hall);
            var input = sc.nextLine().trim();
            System.out.println();
            switch(input) {
                case "1":
                    handleMakeBookingsFlow(hall, bookingService);
                    break;
                case "2":
                    handleCheckBookingsFlow(hall, bookingService);
                    break;
                case "3":
                    doContinue = false;
                    PrintUtility.printGoodByeMessage();
                    break;
                default:
                    PrintUtility.wrongInputMessage();
                    break;
            }
        } while(doContinue);
    }

    private void handleCheckBookingsFlow(Hall hall, BookingService bookingService) {
        var doContinue = true;
        do {
            printMessageWithNewLine(ENTER_BOOKING_ID);
            var input = sc.nextLine().trim();
            if(input == null || input.isBlank())
                break;

            try {
                var booking = bookingService.fetch(input, hall);
                if (booking != null) {
                    System.out.println();
                    PrintUtility.printBooking(hall, booking.getBookingId());
                }
            } catch (BookingException e) {
                PrintUtility.printError(e);
            }
        } while (doContinue);
    }

    private void handleMakeBookingsFlow(Hall hall, BookingService bookingService) {
        printMessageWithNewLine(ENTER_NUMBER_OF_TICKETS_TO_BOOK);
        var input = sc.nextLine().trim();
        if (input == null || input.isBlank() || input.isEmpty())
            return;
        else if (!BookingValidator.checkForInteger(input))
            PrintUtility.wrongInputMessage();
        else {
            handleValidInput(hall, bookingService, input);
        }
    }

    private void handleValidInput(Hall hall, BookingService bookingService, String input) {
        var doContinue = true;
        try {
            int people = Integer.parseInt(input);
            var booking = bookingService.bookDefaultSelection(people, null, hall);
            System.out.println();
            printMessageWithNewLine(String.format("Successfully reserved %d %s tickets.", people, hall.getTitle()));
            do {
                PrintUtility.printBooking(hall, booking);
                printMessageWithNewLine(ACCEPT_SEAT_SELECTION_OR_ENTER_NEW);
                input = sc.nextLine().trim();
                if(input == null || input.isEmpty() || input.isBlank()){
                    bookingService.confirmSelection(hall, booking);
                    printMessageWithNewLine(String.format("Booking id: %s confirmed.", booking.getBookingId()));
                    doContinue = false;
                } else {
                    BookingValidator.validateUserBookingInput(input);
                    System.out.println();
                    booking = bookingService.bookCustomSelection(people, input, hall);
                }
            } while (doContinue);
        } catch (BookingException e) {
            PrintUtility.printError(e);
            System.out.println();
            handleMakeBookingsFlow(hall, bookingService);
        }
    }
}
