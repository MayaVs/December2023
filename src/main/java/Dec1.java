import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Dec1 {
    public void getNumbers() throws IOException, URISyntaxException {
        URL resource = Dec1.class.getResource("inputDec1");
        String string = Files.readString(Path.of(resource.toURI()));

        List<String> words = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        Integer val = Arrays.stream(string.split("\n"))
                .map(str -> {
                    int first = str.length();
                    String firstWord = "";
                    int last = -1;
                    String lastWord = "";
                    for(String num : words){
                        int ind = str.indexOf(num);
                        if(ind >=0 && ind < first) {
                            first = ind;
                            firstWord = num;
                        }
                        int lastInd = str.lastIndexOf(num);
                        if(lastInd > last) {
                            last = lastInd;
                            lastWord = num;
                        }
                    }
                    return numbers.get(words.indexOf(firstWord))*10 + numbers.get(words.indexOf(lastWord));
                })
                .reduce(0, Integer::sum);
        System.out.println(val);
    }
}
