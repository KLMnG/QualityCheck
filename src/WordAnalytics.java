import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordAnalytics {

    private static List<String> namesList= getNamesFromFile("words.txt");;

    private static List<String> getNamesFromFile(String fileName) {
        BufferedReader reader;
        List<String> names=new ArrayList<>();
        try{
            reader = new BufferedReader(new InputStreamReader(WordAnalytics.class.getResourceAsStream(fileName)));
            String line = reader.readLine();
            while (line!=null){
                names.add(line);
                line=reader.readLine();
            }
            reader.close();
        }

        catch (IOException e){
            e.printStackTrace();
        }
        return names;
    }

    /**
     * prints the occurrence of subString in all of the names in the list
     *
     * @param subString the given subString
     */
    public static void CountSpecificString(String subString) {
        int nameCounter = 0;
        for (String name : namesList) {
            if (name.contains(subString))
                nameCounter++;
        }
        System.out.println(nameCounter);
    }

    /**
     * prints the number of occurrences of every subString in length of - numbeOfLettters
     *
     * @param numberOfLetters -size of the subString
     */
    public static void CountAllStrings(int numberOfLetters) {
        HashMap<String, Integer> subWordMap;
        for (String name : namesList) {
            if (name.length() >= numberOfLetters) {
                StringBuilder builder = new StringBuilder();
                subWordMap = getSubWordMap(name, numberOfLetters);
                for (String subWord : subWordMap.keySet()) {
                    builder.append(subWord + ":" + subWordMap.get(subWord) + " ");
                }
                System.out.println(builder.substring(0,builder.length()-1));
            }
        }
    }

    /**
     * prints the subString of length - numberOfLetters with the maximum occurrences
     *
     * @param numberOfLetters - length of subString
     */
    public static void CountMaxString(int numberOfLetters) {
        HashMap<String, Integer> allSubWordMap = new HashMap<>();
        HashMap<String, Integer> subWordMAp;
        for (String name : namesList) {
            subWordMAp = getSubWordMap(name.toLowerCase(), numberOfLetters);
            addToAllSubMap(allSubWordMap, subWordMAp);
        }

        List<String> maxAppearanceList = getMaxAppearanceList(allSubWordMap);

        for (String subWord : maxAppearanceList) {
            System.out.println(subWord);
        }
    }

    /**
     * prints the names that are a subString of stringInput
     *
     * @param stringInput - the given string
     */
    public static void AllIncludesString(String stringInput) {
        stringInput = stringInput.toLowerCase();
        for (String name : namesList) {
            if (stringInput.contains(name.toLowerCase())) {
                System.out.println(name);
            }
        }
    }

    /**
     * adds the int value of the subStrings in subWordMap to allSubWordMap
     *
     * @param allSubWordMap the map to add to
     * @param subWordMap    the map to take the value from
     */
    private static void addToAllSubMap(HashMap<String, Integer> allSubWordMap, HashMap<String, Integer> subWordMap) {
        Integer counrValue;
        for (String subWord : subWordMap.keySet()) {
            if (allSubWordMap.containsKey(subWord)) {
                counrValue = allSubWordMap.get(subWord) + subWordMap.get(subWord);
            } else {
                counrValue = subWordMap.get(subWord);
            }
            allSubWordMap.put(subWord, counrValue);
        }
    }

    /**
     * creates a map if every subString of length - numberOfLetters in name and the amount of its occurrences
     *
     * @param name            the given string to extract subStrings
     * @param numberOfLetters the size of the subString
     * @return a map of subString and the number of occurrences in name
     */
    private static HashMap<String, Integer> getSubWordMap(String name, int numberOfLetters) {
        if (numberOfLetters <= 0)
            return null;
        HashMap<String, Integer> subWordMap = new HashMap<>();
        Integer countValue;
        String currentSubString;
        for (int i = 0; i <= name.length() - numberOfLetters; i++) {
            currentSubString = name.substring(i, i + numberOfLetters);
            if (subWordMap.containsKey(currentSubString)) {
                countValue = subWordMap.get(currentSubString) + 1;
                subWordMap.remove(subWordMap);
            } else {
                countValue = 1;
            }
            subWordMap.put(currentSubString, countValue);
        }
        return subWordMap;
    }

    /**
     * searches for all the subString with maximum occurrences
     *
     * @param subWordMap the map of substrings
     * @return list of strings which occurred the most
     */
    private static List<String> getMaxAppearanceList(HashMap<String, Integer> subWordMap) {
        List<String> maxSubWordList = new ArrayList<>();
        int maxAppearance = 0;
        int currentValue;
        for (String subWord : subWordMap.keySet()) {
            currentValue = subWordMap.get(subWord);
            if (currentValue > maxAppearance) {
                maxSubWordList = new ArrayList<>();
                maxSubWordList.add(subWord);
                maxAppearance = currentValue;
            } else if (currentValue == maxAppearance) {
                maxSubWordList.add(subWord);
            }

        }
        return maxSubWordList;
    }

    public static void main(String[] args) {
        int integerArgumentValue = 0;

        if (args.length == 0) {
            System.out.println("Not enough arguments");
            System.out.println(usage());
            System.exit(-1);
        } else if (args.length == 1 && args[0].equals("GenerateName")) {
            System.out.println("Didn't even flinch");
        } else if (args.length == 2) {
            switch (args[0]) {
                case "CountSpecificString":
                    CountSpecificString(args[1]);
                    break;
                case "CountAllStrings":

                    try {
                        integerArgumentValue = Integer.parseInt(args[1]);
                    }catch (NumberFormatException e){
                        System.out.println("Second argument for 'CountAllStrings' must be a number but got :'" + args[1] + "'");
                        System.exit(-1);
                    }
                    CountAllStrings(integerArgumentValue);
                    break;
                case "CountMaxString":
                    try {
                        integerArgumentValue = Integer.parseInt(args[1]);
                    }catch (NumberFormatException e){
                        System.out.println("Second argument for 'CountMaxString' must be a number but got :'" + args[1] + "'");
                        System.exit(-1);
                    }
                    CountMaxString(integerArgumentValue);
                    break;
                case "AllIncludesString":
                    AllIncludesString(args[1]);
                    break;
                default:
                    System.out.println(usage());
            }
        }
        else{
            System.out.println(usage());
        }
    }

    private static String usage(){
        StringBuilder builder = new StringBuilder();
        builder.append("Usage:\n");
        builder.append("    CountSpecificString <STRING>\n");
        builder.append("    CountAllStrings <LENGTH>\n");
        builder.append("    CountMaxString <LENGTH>\n");
        builder.append("    AllIncludesString <STRING>\n");
        builder.append("    GenerateName\n");

        return builder.toString();

    }
}
