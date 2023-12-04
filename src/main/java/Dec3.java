import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Dec3 {

    public class StarInfo{
        int row;
        int column;
        long number;
        int howManyTimes;
        String values;

        StarInfo(int row, int column, long number, int howManyTimes, String values){
            this.row = row;
            this.column = column;
            this.number = number;
            this.howManyTimes = howManyTimes;
            this.values = values;
        }
    }

    public void task1() throws URISyntaxException, IOException {
        URL resource = Dec3.class.getResource("inputDec3");
        String string = Files.readString(Path.of(resource.toURI()));

        String[] lines = string.split("\n");

        ArrayList<StarInfo> stars = new ArrayList<>();


        for(int lineIndex = 0; lineIndex < lines.length; lineIndex++){
            String line = lines[lineIndex];
            String elem;
            int dotIndex = -1;
            int globalIndex = 0;

            do{
                dotIndex= line.indexOf(46);
                if(dotIndex == 0){
                    globalIndex++;
                    if(line.length()>1) {
                        line = line.substring(1);
                    }
                    else line = "";
                }
                else if(dotIndex>0){
                    elem = line.substring(0, dotIndex);
                    if(elem.matches("\\d+")){
                        checkForSymbol(lineIndex, lines, globalIndex, globalIndex+dotIndex, stars, elem);
                        globalIndex += elem.length();
                    }
                    else{
                        if (elem.length()!=1) {
                            if(!elem.substring(0, 1).matches("\\d")){

                                if(elem.substring(0, 1).equals("*")) {
                                    boolean addNew = true;
                                    for(StarInfo existing: stars){
                                        if(existing.row == lineIndex && existing.column == globalIndex){
                                            existing.number *= Integer.parseInt(elem.substring(1));
                                            existing.howManyTimes ++;
                                            existing.values += elem.substring(1) + ", ";
                                            addNew = false;
                                            break;
                                        }
                                    }
                                    if(addNew) stars.add(new StarInfo(lineIndex, globalIndex, Integer.parseInt(elem.substring(1)), 1, elem.substring(1) + ", "));
                                }

                            }
                            else if(elem.substring(0, elem.length()-1).matches("\\d*")) {
                                if(elem.substring(elem.length()-1, elem.length()).equals("*")) {
                                    boolean addNew = true;
                                    for (StarInfo existing : stars) {
                                        if (existing.row == lineIndex && existing.column == globalIndex + elem.length()-1) {
                                            existing.number *= Integer.parseInt(elem.substring(0, elem.length() - 1));
                                            existing.howManyTimes++;
                                            existing.values += elem.substring(0, elem.length() - 1) + ", ";
                                            addNew = false;
                                            break;
                                        }
                                    }
                                    if (addNew)
                                        stars.add(new StarInfo(lineIndex, globalIndex + elem.length()-1, Integer.parseInt(elem.substring(0, elem.length() - 1)), 1, elem.substring(0, elem.length() - 1) + ", "));
                                }
                            }
                            else {
                                String temp = "";
                                for(int i = 0; i < elem.length(); ++i){
                                    char c = elem.charAt(i);
                                    if (Character.isDigit(c)) temp += c;
                                    else{
                                        if(c == '*') {
                                            boolean addNew = true;
                                            for(StarInfo existing: stars){
                                                if(existing.row == lineIndex && existing.column == globalIndex+i){
                                                    existing.number *= Integer.parseInt(elem.substring(0,i));
                                                    existing.howManyTimes ++;
                                                    existing.values += elem.substring(0,i) + ", ";
                                                    addNew = false;
                                                    break;
                                                }
                                            }
                                            if(addNew) stars.add(new StarInfo(lineIndex, globalIndex+i, Integer.parseInt(elem.substring(0, i)), 1, elem.substring(0, i) + ", "));

                                            boolean addNew2 = true;
                                            for(StarInfo existing: stars){
                                                if(existing.row == lineIndex && existing.column == globalIndex+i){
                                                    existing.number *= Integer.parseInt(elem.substring(i+1));
                                                    existing.howManyTimes ++;
                                                    existing.values += elem.substring(i+1) + ", ";
                                                    addNew2 = false;
                                                    break;
                                                }
                                            }
                                            if(addNew2) stars.add(new StarInfo(lineIndex, globalIndex+i, Integer.parseInt(elem.substring(i+1)), 1, elem.substring(i+1) + ", "));

                                            temp="";
                                        }
                                    }
                                }
                            }
                        }
                        globalIndex += dotIndex;
                    }
                    line = line.substring(dotIndex);
                }
                else if(dotIndex < 0){
                    elem = line;
                    if(elem.matches("\\d+")){
                        checkForSymbol(lineIndex, lines, globalIndex, lines[lineIndex].length()-1, stars, elem);
                    }
                    globalIndex += line.length();
                    line = "";
                }

            }while(line.length() >= 1);

           }
        System.out.println("We have that many stars: " + stars.size());
        long starResult = 0;
        for(StarInfo si: stars){
            if(si.howManyTimes == 2)
                starResult += si.number;
        }
        System.out.println("starResult = " + starResult);
        }

        boolean checkForSymbol(int lineIndex, String[] lines, int startIndex, int endIndex, ArrayList<StarInfo> stars, String number) {
        int topIndex;
        int bottomIndex;
        int leftIndex;
        int rightIndex;

        if(startIndex == 0){
            leftIndex = 0;
        }
        else{
            leftIndex = startIndex-1;
        }

        if(endIndex == lines[lineIndex].length()-1){
            rightIndex = endIndex;
        }
        else {
            rightIndex = endIndex + 1;
        }

        if(lineIndex == 0){
            topIndex =0;
        }
        else{
            topIndex = lineIndex-1;
            for(int i = leftIndex; i < rightIndex; ++i)
            {
                Character c = lines[topIndex].toCharArray()[i];
                if(isStar(c)){
                    boolean addNew = true;
                    for(StarInfo existing: stars){
                        if(existing.row == topIndex && existing.column == i){
                            existing.number *= Integer.parseInt(number);
                            existing.howManyTimes ++;
                            existing.values += number + ", ";
                            addNew = false;
                            break;
                        }
                    }
                    if(addNew) stars.add(new StarInfo(topIndex, i, Integer.parseInt(number), 1, number + ", "));
                    return true;
                }
            }
        }

        if(lineIndex == lines.length - 1){
            bottomIndex = lineIndex;
        }
        else{
            bottomIndex = lineIndex+1;
            for(int i = leftIndex; i < rightIndex; ++i)
            {
                char c = lines[bottomIndex].toCharArray()[i];
                if(isStar(c)){
                    boolean addNew2 = true;
                    for(StarInfo existing: stars){
                        if(existing.row == bottomIndex && existing.column == i){
                            existing.number *= Integer.parseInt(number);
                            existing.howManyTimes ++;
                            existing.values += number + ", ";
                            addNew2 = false;
                            break;
                        }
                    }
                    if(addNew2) stars.add(new StarInfo(bottomIndex, i, Integer.parseInt(number), 1, number + ", "));
                    return true;
                }
            }
        }
        return false;
        }
    boolean isSymbol(char c){
        if((c >= '0' && c <= '9')|| c == 46)
            return false;
        else return true;
    }

    boolean isStar(char c){
        return (c == '*');
    }
    }

