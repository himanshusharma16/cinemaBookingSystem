package cinemaBooking.util;

import cinemaBooking.exception.BookingException;
import cinemaBooking.model.Booking;
import cinemaBooking.model.Hall;

public class PrintUtility {

    public static void printMessageWithNewLine(String message){
        System.out.println(message);
    }

    public static void printError(BookingException exception) {
        System.out.println();
        System.out.println(exception.getMessage());
    }

    public static void printWelcomeMenu(Hall hall) {
        printBlankLine();
        System.out.println("Welcome to GIC Cinemas");
        System.out.println("[1] Book tickets for "+ hall.getTitle() +" ("+hall.getVacantSeats()+" seats available)");
        System.out.println("[2] Check bookings");
        System.out.println("[3] Exit");
        System.out.println("Please enter your selection: ");
    }

    private static void printBlankLine() {
        System.out.println();
    }

    public static void printBooking(Hall hall, String bookingId) {
        var booking = hall.getBookings().get(bookingId);
        printBooking(hall,booking);
    }


    public static void printBooking(Hall hall, Booking booking) {
        System.out.println("Booking id: "+ booking.getBookingId());
        System.out.println("Selected seats:");
        printBlankLine();

        printScreen(hall.getCol());
        printLinesAfterScreen(hall.getCol());

        char maxChar = (char) (hall.getRow()+64);
        var theatre = new boolean[hall.getRow()][hall.getCol()];

        for(var seating : booking.getSeatingArrangement()) {
            int row = seating.getRow();
            for(var col : seating.getSeats()){
                theatre[row][col] = true;
            }
        }
        for(int i = 0; i < hall.getRow() ; i++){
            System.out.print(maxChar--);
            for(int j = 0; j < hall.getCol(); j++) {
                if(theatre[i][j]) {
                    System.out.print("  O");
                    continue;
                }
                if(hall.getTheatre()[i][j] == 0)
                    System.out.print("  .");
                else if (hall.getTheatre()[i][j] == 1)
                    System.out.print("  #");
            }
            printBlankLine();
        }
        System.out.print(" ");
        for(int i = 1 ; i <= hall.getCol(); i++){
            if(i < 10)
                System.out.print("  "+i);
            else
                System.out.print(" "+i);
        }
        printBlankLine();        printBlankLine();
    }

    private static void printLinesAfterScreen(int col) {
        for(int i = 0; i < col; i++)
            System.out.print("___");
        System.out.println("_");
    }

    private static void printScreen(int col) {
        int spaces = col*3+1 - 6;
        for(int i = 0; i <= spaces/2; i++)
            System.out.print(" ");
        System.out.println("SCREEN");
    }

    public static void printGoodByeMessage() {
        System.out.println("Thank you for using GIC Cinemas system. Bye!");
    }

    public static void wrongInputMessage() {
        System.out.println("Wrong input. Please try again!!");
    }
}
