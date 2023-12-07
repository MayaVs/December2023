import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public class Dec7Test {
    Dec7 dec7 = new Dec7();
    @Test
    public void taskTest1() throws URISyntaxException, IOException, ParseException {
        dec7.task1();
    }
    @Test
    public void taskTest2() throws URISyntaxException, IOException, ParseException {
        dec7.task2();
    }
}
