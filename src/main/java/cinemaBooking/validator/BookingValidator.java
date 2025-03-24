package cinemaBooking.validator;

import cinemaBooking.exception.BookingException;

/*
class to help validate user inputs
 */
public class BookingValidator {

    public static boolean validateUserTheatreInput(String input){
        String[] inputs = input.split(" ");
        if(inputs.length != 3)
            return false;
        if(!checkForInteger(inputs[1]) || Integer.parseInt(inputs[1]) <= 0 || Integer.parseInt(inputs[1]) > 26)
            return false;
        if(!checkForInteger(inputs[2]) || Integer.parseInt(inputs[2]) <= 0 || Integer.parseInt(inputs[2]) > 50)
            return false;
        return true;
    }

    public static boolean checkForInteger(String input) {
        try{
            Integer.parseInt(input);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean validateUserBookingInput(String input) throws BookingException {
        boolean isValid = true;
        if(input == null)
            return true;
        else {
            if(input.length() > 3)
                isValid = false;
            if(input.charAt(0) < 'A' || input.charAt(0) > 'Z' || input.charAt(1) < '0' || input.charAt(1) > '9')
                isValid = false;
            if(input.length() == 3){
                if(input.charAt(2) < '0' || input.charAt(2) > '9')
                    isValid = false;
            }
        }
        if(!isValid)
            throw new BookingException("Invalid input entered. Unable to parse row / column.");
        return isValid;
    }
}
