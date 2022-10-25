import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class App {
    /*
     * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file
     * containing over five-thousand first names, begin by sorting it into
     * alphabetical order. Then working out the alphabetical value for each name,
     * multiply this value by its alphabetical position in the list to obtain a name
     * score.
     * 
     * For example, when the list is sorted into alphabetical order, COLIN, which is
     * worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN
     * would obtain a score of 938 × 53 = 49714.
     * 
     * What is the total of all the name scores in the file?
     */
    public static void main(String[] args) throws IOException {
        // read in file and parse into memory
        File file = new File("/Users/starrparker/code/Git/Euler/euler_22/src/main/java/euler_22/names.txt");
        // List<String> names = getNamesFromFile(file);
        String[] names = getNamesFromFile(file);
        List<Integer> positions = new ArrayList<>();

        // sort into alphabetical order
        List<String> namesList = Arrays.asList(names);
        // List<String> sortedNamesList = new ArrayList<>();
        Collections.sort(namesList);
        // System.out.println("Sorted: " + namesList);

        // remove extra double quotes
        for (int i = 1; i <= namesList.size(); i++) {
            // String tmpStr = namesList.get(i).replace("\"\"", "\"");
            // sortedNamesList.add(tmpStr);
            positions.add(i);
        }

        // get ascii values score and position score; multiply them together and retain
        // that value for each
        char[] namesAsCharArray = namesList.toString().toCharArray();
        int nameTotal = 0;
        int wordTotal = 0;
        List<Integer> wordTotalsList = new ArrayList<>();
        int k = 0;

        // System.out.println("namesList size: " + namesList.size());
        // System.out.println("namesAsCharArray size: " + namesAsCharArray.length);
        // System.out.println("positions size: " + positions.size());
        for (int j = 0; j < namesAsCharArray.length; j++) {
            // System.out.println(namesAsCharArray[j]);
            if (namesAsCharArray[j] == '\"' && nameTotal != 0) {
                if (k < positions.size()) {
                    System.out.println("Total for name (name = " + namesList.get(k) + "): " + nameTotal + ", j: " + j
                            + ", k: " + k + ", position: "
                            + positions.get(k));
                    wordTotal = nameTotal * positions.get(k);
                    wordTotalsList.add(wordTotal);
                    nameTotal = 0;
                    k++;
                }
                continue;
            } else if (namesAsCharArray[j] == ',' || namesAsCharArray[j] == '[') {
                j++; // force to index one further to skip following single instance of double quotes
                continue;
            } else {
                if (namesAsCharArray[j] != '\"') {
                    nameTotal += namesAsCharArray[j] - '@';
                }
            }
        }

        // add all name scores together and report final sum
        long overallTotal = 0L;
        for (int m = 0; m < wordTotalsList.size(); m++) {
            overallTotal += wordTotalsList.get(m);
        }

        System.out.println(overallTotal);
    }

    private static String[] getNamesFromFile(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        // List<String> linesOfFile = new ArrayList<String>();
        String currentLine = "";
        String[] tokens;

        // while (currentLine != null) {
        currentLine = reader.readLine();
        // linesOfFile.add(currentLine);
        tokens = new String[currentLine.length()];
        tokens = currentLine.split(",");
        // tokens = currentLine.split("\",\""); //cleaning up the double quotes; this
        // works for all but the first and last instances
        // }
        reader.close();
        // return linesOfFile;
        return tokens;

        /*
         * } catch (IOException e) {
         * System.out.println(e.getLocalizedMessage());
         * } finally {
         * // reader.close();
         * }
         */
    }
}
