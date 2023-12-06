import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class Dec6 {
    public void task() throws URISyntaxException, IOException {
        URL resource = Dec6.class.getResource("inputDec6");
        String string = Files.readString(Path.of(resource.toURI()));

        String[] lines = string.split("\n");

        String timeString = lines[0].replace(" ", "").split(":")[1];

        String dinstanceString = lines[1].replace(" ", "").split(":")[1];

        BigInteger time = BigInteger.valueOf(Long.parseLong(timeString));
        BigInteger distance = BigInteger.valueOf(Long.parseLong(dinstanceString));
        BigInteger wins = BigInteger.ZERO;

        for(BigInteger hold=BigInteger.ZERO; hold.compareTo(time) < 0; hold = hold.add(BigInteger.ONE)){
            BigInteger speed = hold;
            BigInteger leftTime = time.subtract(hold);
            BigInteger passed = speed.multiply(leftTime);
            if (passed.compareTo(distance) > 0)
                wins = wins.add(BigInteger.ONE);
        }
        System.out.println(wins.toString());
    }

}
