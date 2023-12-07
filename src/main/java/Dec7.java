import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.*;

public class Dec7 {
    public void task1() throws URISyntaxException, IOException, ParseException {
        URL resource = Dec7.class.getResource("inputDec7");
        String string = Files.readString(Path.of(resource.toURI()));
        String[] lines = string.split("\n");
        List<String> fiveOfAKind = new ArrayList<>();
        List<String> fourOfAKind = new ArrayList<>();
        List<String> fullHouse = new ArrayList<>();
        List<String> threeOfAKind = new ArrayList<>();
        List<String> twoPairs = new ArrayList<>();
        List<String> onePair = new ArrayList<>();
        List<String> highCard = new ArrayList<>();

        long result = 0;

        for (String line : lines) {
            String hand = line.split(" ")[0];

            Set<Character> getHandType = new HashSet<>();
            for (Character c : hand.toCharArray()) {
                getHandType.add(c);
            }
            switch (getHandType.size()) {
                case 1:
                    fiveOfAKind.add(hand);
                    break;
                case 2: {
                    long check = hand.chars().filter(ch -> ch == hand.charAt(0)).count();
                    if (check == 4 || check == 1) fourOfAKind.add(hand);
                    else fullHouse.add(hand);
                }
                break;
                case 3: {
                    boolean added = false;
                    for (Character first : getHandType) {
                        long check1 = hand.chars().filter(ch -> ch == first).count();
                        if (check1 == 3) {
                            threeOfAKind.add(hand);
                            added = true;
                            break;
                        }
                    }
                    if (!added) twoPairs.add(hand);
                    break;
                }
                case 4:
                    onePair.add(hand);
                    break;
                case 5:
                    highCard.add(hand);
                    break;
            }
        }
        String rule = "< 2 < 3 & 3 < 4 & 4 < 5 & 5 < 6 & 6 < 7 & 7 < 8 & 8 < 9 & 9 < T & T < J & J < Q & Q < K & K < A";
        RuleBasedCollator rulesCollator = new RuleBasedCollator(rule);
        fiveOfAKind.sort(rulesCollator);
        fourOfAKind.sort(rulesCollator);
        fullHouse.sort(rulesCollator);
        threeOfAKind.sort(rulesCollator);
        twoPairs.sort(rulesCollator);
        onePair.sort(rulesCollator);
        highCard.sort(rulesCollator);

        int handsCount = 1;
    for(String cardHand : highCard){
        int position = string.indexOf(cardHand) + cardHand.length() + 1;
        int nextNewLine = string.substring(position).indexOf('\n');
        String bid = string.substring(position, position+nextNewLine);
        result += Integer.parseInt(bid) * handsCount;
        handsCount++;
    }
        for(String cardHand : onePair){
            int position = string.indexOf(cardHand) + cardHand.length() + 1;
            int nextNewLine = string.substring(position).indexOf('\n');
            String bid = string.substring(position, position+nextNewLine);
            result += Integer.parseInt(bid) * handsCount;
            handsCount++;
        }
        for(String cardHand : twoPairs){
            int position = string.indexOf(cardHand) + cardHand.length() + 1;
            int nextNewLine = string.substring(position).indexOf('\n');
            String bid = string.substring(position, position+nextNewLine);
            result += Integer.parseInt(bid) * handsCount;
            handsCount++;
        }
        for(String cardHand : threeOfAKind){
            int position = string.indexOf(cardHand) + cardHand.length() + 1;
            int nextNewLine = string.substring(position).indexOf('\n');
            String bid = string.substring(position, position+nextNewLine);
            result += Integer.parseInt(bid) * handsCount;
            handsCount++;
        }
        for(String cardHand : fullHouse){
            int position = string.indexOf(cardHand) + cardHand.length() + 1;
            int nextNewLine = string.substring(position).indexOf('\n');
            String bid = string.substring(position, position+nextNewLine);
            result += Integer.parseInt(bid) * handsCount;
            handsCount++;
        }
        for(String cardHand : fourOfAKind){
            int position = string.indexOf(cardHand) + cardHand.length() + 1;
            int nextNewLine = string.substring(position).indexOf('\n');
            String bid = string.substring(position, position+nextNewLine);
            result += Integer.parseInt(bid) * handsCount;
            handsCount++;
        }
        for(String cardHand : fiveOfAKind){
            int position = string.indexOf(cardHand) + cardHand.length() + 1;
            int nextNewLine = string.substring(position).indexOf('\n');
            String bid = string.substring(position, position+nextNewLine);
            result += Integer.parseInt(bid) * handsCount;
            handsCount++;
        }

        System.out.println("result is " + result);
    }

    public void task2() throws URISyntaxException, IOException, ParseException {
        URL resource = Dec7.class.getResource("inputDec7");
        String string = Files.readString(Path.of(resource.toURI()));
        String[] lines = string.split("\n");
        List<String> fiveOfAKind = new ArrayList<>();
        List<String> fourOfAKind = new ArrayList<>();
        List<String> fullHouse = new ArrayList<>();
        List<String> threeOfAKind = new ArrayList<>();
        List<String> twoPairs = new ArrayList<>();
        List<String> onePair = new ArrayList<>();
        List<String> highCard = new ArrayList<>();

        long result = 0;

        for (String line : lines) {
            String hand = line.split(" ")[0];

            Set<Character> getHandType = new HashSet<>();
            for (Character c : hand.toCharArray()) {
                getHandType.add(c);
            }
            switch (getHandType.size()) {
                case 1:
                    fiveOfAKind.add(hand);
                    break;
                case 2: {
                    if (hand.contains("J")) {
                        fiveOfAKind.add(hand);
                        break;
                    }
                    long check = hand.chars().filter(ch -> ch == hand.charAt(0)).count();
                    if (check == 4 || check == 1) fourOfAKind.add(hand);
                    else fullHouse.add(hand);
                    break;
                }
                case 3: {
                    boolean added = false;
                    for (Character first : getHandType) {
                        long check1 = hand.chars().filter(ch -> ch == first).count();
                        if (check1 == 3) {
                            added = true;
                            if (hand.contains("J")) {
                                fourOfAKind.add(hand);
                                break;
                            }
                            threeOfAKind.add(hand);
                            break;
                        }
                    }
                    if (!added) {
                        if (hand.contains("J")) {
                            if (hand.replaceAll("J", "").length() == 3) {
                                fourOfAKind.add(hand);
                                break;
                            } else {
                                fullHouse.add(hand);
                                break;
                            }
                        }
                        twoPairs.add(hand);
                    }
                    break;
                }
                case 4: {
                    if (hand.contains("J")) {
                        threeOfAKind.add(hand);
                        break;
                    }
                    onePair.add(hand);
                }
                break;
                case 5: {
                    if (hand.contains("J")) {
                        onePair.add(hand);
                        break;
                    } else highCard.add(hand);
                }
                break;
            }
        }
        String rule = "< J < 2 & 2 < 3 & 3 < 4 & 4 < 5 & 5 < 6 & 6 < 7 & 7 < 8 & 8 < 9 & 9 < T & T < Q & Q < K & K < A";
        RuleBasedCollator rulesCollator = new RuleBasedCollator(rule);
        fiveOfAKind.sort(rulesCollator);
        fourOfAKind.sort(rulesCollator);
        fullHouse.sort(rulesCollator);
        threeOfAKind.sort(rulesCollator);
        twoPairs.sort(rulesCollator);
        onePair.sort(rulesCollator);
        highCard.sort(rulesCollator);

        int handsCount = 1;
        for(String cardHand : highCard){
            int position = string.indexOf(cardHand) + cardHand.length() + 1;
            int nextNewLine = string.substring(position).indexOf('\n');
            String bid = string.substring(position, position+nextNewLine);
            result += Integer.parseInt(bid) * handsCount;
            handsCount++;
        }
        for(String cardHand : onePair){
            int position = string.indexOf(cardHand) + cardHand.length() + 1;
            int nextNewLine = string.substring(position).indexOf('\n');
            String bid = string.substring(position, position+nextNewLine);
            result += Integer.parseInt(bid) * handsCount;
            handsCount++;
        }
        for(String cardHand : twoPairs){
            int position = string.indexOf(cardHand) + cardHand.length() + 1;
            int nextNewLine = string.substring(position).indexOf('\n');
            String bid = string.substring(position, position+nextNewLine);
            result += Integer.parseInt(bid) * handsCount;
            handsCount++;
        }
        for(String cardHand : threeOfAKind){
            int position = string.indexOf(cardHand) + cardHand.length() + 1;
            int nextNewLine = string.substring(position).indexOf('\n');
            String bid = string.substring(position, position+nextNewLine);
            result += Integer.parseInt(bid) * handsCount;
            handsCount++;
        }
        for(String cardHand : fullHouse){
            int position = string.indexOf(cardHand) + cardHand.length() + 1;
            int nextNewLine = string.substring(position).indexOf('\n');
            String bid = string.substring(position, position+nextNewLine);
            result += Integer.parseInt(bid) * handsCount;
            handsCount++;
        }
        for(String cardHand : fourOfAKind){
            int position = string.indexOf(cardHand) + cardHand.length() + 1;
            int nextNewLine = string.substring(position).indexOf('\n');
            String bid = string.substring(position, position+nextNewLine);
            result += Integer.parseInt(bid) * handsCount;
            handsCount++;
        }
        for(String cardHand : fiveOfAKind){
            int position = string.indexOf(cardHand) + cardHand.length() + 1;
            int nextNewLine = string.substring(position).indexOf('\n');
            String bid = string.substring(position, position+nextNewLine);
            result += Integer.parseInt(bid) * handsCount;
            handsCount++;
        }

        System.out.println("result for task 2 is " + result);
    }
}
