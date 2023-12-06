import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Dec4 {
    public void task() throws URISyntaxException, IOException {
        URL resource = Dec4.class.getResource("inputDec4");
        String string = Files.readString(Path.of(resource.toURI()));

        String[] lines = string.split("\n");
        int sum = 0;
        int[] countOflines = new int[lines.length];
        Arrays.fill(countOflines, 1);


        for(int i = 0; i < lines.length; ++i){
            String line =  lines[i];
            String[] splitedLine = line.split(" [{-}] ");
            String[] winningNumbers = splitedLine[0].split(": ")[1].split(" ");

            Set<String> myNumbers = new HashSet<>(Arrays.asList(splitedLine[1].split(" ")));
            myNumbers.add("");
            int startingSize = myNumbers.size();
            int winningNumberCount = winningNumbers.length;
            for (String num: winningNumbers){
                if(num.equals("")) {
                    winningNumberCount --;
                    continue;
                }
                myNumbers.add(num);
            }

            int matchingNumbers = winningNumberCount - (myNumbers.size() - startingSize);
            for(int j = 1; j <= matchingNumbers; ++j){
                    countOflines[i+j] += countOflines[i];
            }
            sum = Arrays.stream(countOflines).sum();
            //sum += Math.pow(2,  (winningNumberCount - (myNumbers.size() - startingSize)-1));

        }
        System.out.println("The sum is: " + sum);

    }
}
