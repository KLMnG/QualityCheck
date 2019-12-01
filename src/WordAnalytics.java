import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordAnalytics {

    private List<String> namesList;


    public WordAnalytics() {
        this.namesList = getNamesFromFile("words.txt");
    }

    private List<String> getNamesFromFile(String fileName) {
        BufferedReader reader;
        List<String> names=new ArrayList<>();
        try{
            reader =new BufferedReader(new FileReader(fileName));
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
    public void CountSpecificString(String subString) {
        int nameCounter = 0;
        for (String name : this.namesList) {
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
    public void CountAllStrings(int numberOfLetters) {
        HashMap<String, Integer> subWordMap;
        for (String name : this.namesList) {
            subWordMap = getSubWordMap(name, numberOfLetters);
//          printSubWordMap(subWordMAp, name, numberOfLetters);//TODO switch print
            for (String subWord : subWordMap.keySet()) {
                System.out.println(subWord + ":" + subWordMap.get(subWord));
            }
        }
    }

    /**
     * prints the subString of length - numberOfLetters with the maximum occurrences
     *
     * @param numberOfLetters - length of subString
     */
    public void CountMaxString(int numberOfLetters) {
        HashMap<String, Integer> allSubWordMap = new HashMap<>();
        HashMap<String, Integer> subWordMAp;
        for (String name : this.namesList) {
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
    public void AllIncludesString(String stringInput) {
        stringInput = stringInput.toLowerCase();
        for (String name : this.namesList) {
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
    private void addToAllSubMap(HashMap<String, Integer> allSubWordMap, HashMap<String, Integer> subWordMap) {
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
     * @param subWordMAp
     * @param name
     * @param numberOfLetters
     */
    private void printSubWordMap(HashMap<String, Integer> subWordMAp, String name, int numberOfLetters) {
        String currentSubString;
        for (int i = name.length(); i >= numberOfLetters; i -= numberOfLetters) {
            currentSubString = name.substring(i - numberOfLetters, i);
            System.out.println(currentSubString + ":" + currentSubString);

        }
    }

    /**
     * creates a map if every subString of length - numberOfLetters in name and the amount of its occurrences
     *
     * @param name            the given string to extract subStrings
     * @param numberOfLetters the size of the subString
     * @return a map of subString and the number of occurrences in name
     */
    private HashMap<String, Integer> getSubWordMap(String name, int numberOfLetters) {
        if (numberOfLetters <= 0)
            return null;
        HashMap<String, Integer> subWordMap = new HashMap<>();
        Integer countValue;
        String currentSubString;
        for (int i = 0; i <= name.length() - numberOfLetters; i++) {
            currentSubString = name.substring(i, i + numberOfLetters);
            if (subWordMap.containsKey(currentSubString)) {
                countValue =subWordMap.get(currentSubString) + 1;
                subWordMap.remove(subWordMap);
            } else {
                countValue =0;
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
    private List<String> getMaxAppearanceList(HashMap<String, Integer> subWordMap) {
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

}
