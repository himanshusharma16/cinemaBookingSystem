package cinemaBooking;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class CinemaBookingTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final InputStream originalIn = System.in;
    private ByteArrayInputStream in;

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }

    @BeforeEach
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }


    @Test
    public void whenIncorrectInputGiven_errorMessageIsPrinted(){
        in = new ByteArrayInputStream(("ABCDABCD" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        CinemaBooking.main();
        Assertions.assertTrue(outContent.toString().contains(CinemaBooking.INCORRECT_DATA));
    }

    @Test
    public void whenIncorrectInputGiven_afterChoosingExit_goodbyeMessageIsPrinted(){
        in = new ByteArrayInputStream(("ABCDABCD" + System.lineSeparator() + "0").getBytes());
        System.setIn(in);
        CinemaBooking.main();
        Assertions.assertTrue(outContent.toString().contains("Thank you for using GIC Cinemas system. Bye!"));
    }

    @Test
    public void whenCorrectInputGiven_noErrorInConsole(){
        in = new ByteArrayInputStream(("Inception 5 10" + System.lineSeparator() + "0").getBytes());
        System.setIn(in);
        CinemaBooking.main();
        Assertions.assertFalse(outContent.toString().contains("Thank you for using GIC Cinemas system. Bye!"));
        Assertions.assertFalse(outContent.toString().contains(CinemaBooking.INCORRECT_DATA));
    }

}
