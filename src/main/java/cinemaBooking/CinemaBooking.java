package cinemaBooking;

import cinemaBooking.handler.BookingFlowHandler;
import cinemaBooking.model.CinemaHall;
import cinemaBooking.model.Hall;
import cinemaBooking.util.PrintUtility;
import cinemaBooking.validator.BookingValidator;

import java.util.Scanner;

import static cinemaBooking.util.PrintUtility.printMessageWithNewLine;

public class CinemaBooking {

    Scanner sc = new Scanner(System.in);
    public static final String ERROR_MSG = "Sorry, something went wrong. Please refer below for more info on error. The program will exit now!";
    public static final String FIRST_MSG = "Please define movie title and seating map in [Title] [Row] [SeatsPerRow] format:";
    public static final String INCORRECT_DATA = "Data not entered in correct format. Would you like to try again? Enter anything to try, and 0 to exit.";

    public static void main(String... args){
        try {
            new CinemaBooking().start();
        } catch (Exception e){
            printMessageWithNewLine(ERROR_MSG);
            e.printStackTrace();
        }
    }

    private void start() { //create cinema hall instance and pass over to booking handler
        String input = "";
        while(true) {
            printMessageWithNewLine(FIRST_MSG);
            input = sc.nextLine();
            if (BookingValidator.validateUserTheatreInput(input)) {
                break;
            } else
                printMessageWithNewLine(INCORRECT_DATA);
            var again = sc.nextLine();
            if (again.equals("0")) {
                PrintUtility.printGoodByeMessage();
                return;
            }
        }
        String[] userInput = input.split(" ");
        Hall hall = new CinemaHall(userInput[0], Integer.parseInt(userInput[1]), Integer.parseInt(userInput[2]));
        new BookingFlowHandler(sc).handle(hall);
    }
}
