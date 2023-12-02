import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class Dec2 {

    public void game() throws URISyntaxException, IOException {
        URL resource = Dec2.class.getResource("inputDec2");
        String string = Files.readString(Path.of(resource.toURI()));

        int maxRed = 0;
        int maxGreen = 0;
        int maxBlue = 0;

        int gamesSum = 0;

        String[] lines = string.split("\n");
        for(String line: lines) {
            int allRed = 0;
            int allBlue = 0;
            int allGreen = 0;
            String[] game = line.split(":");
            int gameNum = Integer.parseInt(game[0].split(" ")[1]);
            String[] result = game[1].split(";");
            boolean end = false;

            for (String res : result) {
                String[] set = res.split(",");
                for(String s : set){
                    String[] single = s.trim().split(" ");
                    switch (single[1]) {
                        case "blue":
                            if (Integer.parseInt(single[0]) > allBlue) {
                                allBlue = Integer.parseInt(single[0]);
                            }
                            break;
                        case "red":
                            if (Integer.parseInt(single[0]) > allRed) {
                                allRed = Integer.parseInt(single[0]);
                            }
                            break;
                        case "green":
                            if (Integer.parseInt(single[0]) > allGreen) {
                                allGreen = Integer.parseInt(single[0]);
                            }
                            break;
                    }
                }
            }

            gamesSum += allBlue*allGreen*allRed;
        }

        System.out.println(gamesSum);
    }
}
