package cinemaBooking.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeatExtractionUtilityTest {

    @Test
    public void whenColExtract_givesCorrectColumn(){
        var res = SeatExtractionUtility.extractColIndex("B03");
        assertEquals(res, 2);
    }

    @Test
    public void whenRowExtract_givesCorrectRow(){
        var res = SeatExtractionUtility.extractRowIndex("B03");
        assertEquals(res, 2);
    }
}
