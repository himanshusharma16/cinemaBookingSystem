package cinemaBooking.util;

public class SeatExtractionUtility {

    public static int extractRowIndex(String input){
        return (int)(input.charAt(0)- 'A') + 1;
    }

    public static int extractColIndex(String input){
        return Integer.parseInt(input.substring(1))-1;
    }
}
