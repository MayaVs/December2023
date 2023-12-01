import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class LastYearDec1 {
    public void first() throws IOException, URISyntaxException {
        URL resource = LastYearDec1.class.getResource("inputOld.txt");
        String string = Files.readString(Path.of(resource.toURI()));

        List<Integer> max = Arrays.stream(string.split("\n\n")).
                map(str -> Arrays.stream(str.split("\n"))
                        .map(Integer::valueOf)
                        .reduce(0, Integer::sum)
                ).sorted(Comparator.reverseOrder()).toList();

        System.out.println(max.get(0)+max.get(1)+max.get(2));

        System.out.println("Code of Advent");
    }
}
