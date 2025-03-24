package cinemaBooking.util;

/*
utility to extract seat information from user provided input
 */
public class SeatExtractionUtility {

    public static int extractRowIndex(String input){
        return (int)(input.charAt(0)- 'A') + 1;
    }

    public static int extractColIndex(String input){
        return Integer.parseInt(input.substring(1))-1;
    }
}
