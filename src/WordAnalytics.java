import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordAnalytics {

    private List<String> namesList;


    public void CountSpecificString(String subString) {
        int nameCounter = 0;
        for (String name : this.namesList) {
            if (name.contains(subString))
                nameCounter++;
        }
        System.out.println(nameCounter);
    }

    public void CountAllStrings(int numberOfLetters) {
        HashMap<String, Integer> subWordMap;
        for (String name : this.namesList) {
            subWordMap = getSubWordMap(name, numberOfLetters);
//          printSubWordMap(subWordMAp, name, numberOfLetters);//TODO switch print
            for (String subWord:subWordMap.keySet()) {
                System.out.println(subWord+":"+subWordMap.get(subWord).intValue());
            }
        }
    }


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

    public void AllIncludesString(String stringInput){
        stringInput=stringInput.toLowerCase();
        for (String name : this.namesList) {
            if(stringInput.contains(name.toLowerCase())){
                System.out.println(name);
            }
        }
    }

    private void addToAllSubMap(HashMap<String, Integer> allSubWordMap, HashMap<String, Integer> subWordMap) {
        Integer counrValue;
        for (String subWord : subWordMap.keySet()) {
            if (allSubWordMap.containsKey(subWord)) {
                counrValue = new Integer(allSubWordMap.get(subWord).intValue() + subWordMap.get(subWord).intValue());
            } else {
                counrValue = new Integer(subWordMap.get(subWord));
            }
            allSubWordMap.put(subWord, counrValue);
        }
    }

    private void printSubWordMap(HashMap<String, Integer> subWordMAp, String name, int numberOfLetters) {
        String currentSubString;
        for (int i = name.length(); i >= numberOfLetters; i -= numberOfLetters) {
            currentSubString = name.substring(i - numberOfLetters, i);
            System.out.println(currentSubString + ":" + subWordMAp.get(currentSubString).intValue());

        }
    }

    private HashMap<String, Integer> getSubWordMap(String name, int numberOfLetters) {
        if (numberOfLetters <= 0)
            return null;
        HashMap<String, Integer> subWordMap = new HashMap<>();
        Integer counrValue;
        String currentSubString;
        for (int i = 0; i <= name.length() - numberOfLetters; i++) {
            currentSubString = name.substring(i, i + numberOfLetters);
            if (subWordMap.containsKey(currentSubString)) {
                counrValue = new Integer(subWordMap.get(currentSubString).intValue() + 1);
                subWordMap.remove(subWordMap);
            } else {
                counrValue = new Integer(0);
            }
            subWordMap.put(currentSubString, counrValue);
        }
        return subWordMap;
    }

    private List<String> getMaxAppearanceList(HashMap<String, Integer> subWordMap) {
        List<String> maxSubWordList=new ArrayList<>();
        int maxAppearance=0;
        int currentValue;
        for (String subWord :subWordMap.keySet()) {
            currentValue=subWordMap.get(subWord).intValue();
            if(currentValue>maxAppearance){
                maxSubWordList=new ArrayList<>();
                maxSubWordList.add(subWord);
                maxAppearance=currentValue;
            }
            else if(currentValue == maxAppearance){
                maxSubWordList.add(subWord);
            }

        }
        return maxSubWordList;
    }

}
