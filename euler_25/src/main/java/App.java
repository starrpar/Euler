import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class App {

    // NOTE: to run this, you must run in Debug mode and have it stop at the end of
    // the calcFibAndDetermineNumberOfDigits() method (currently line 76)

    /*
     * The Fibonacci sequence is defined by the recurrence relation:
     * 
     * Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
     * Hence the first 12 terms will be:
     * 
     * F1 = 1
     * F2 = 1
     * F3 = 2
     * F4 = 3
     * F5 = 5
     * F6 = 8
     * F7 = 13
     * F8 = 21
     * F9 = 34
     * F10 = 55
     * F11 = 89
     * F12 = 144
     * The 12th term, F12, is the first term to contain three digits.
     * 
     * What is the index of the first term in the Fibonacci sequence to contain 1000
     * digits?
     */
    public static void main(String[] args) {
        // calculate Fibonacci series to a passed in limit (so can stop or can grow as
        // needed)
        int limit = 4800;
        int current = 2;
        List<BigInteger> fibSeries = new ArrayList<>();
        Dictionary<Integer, Integer> numDigitsInFibSeriesValues = new Hashtable<>();
        Dictionary<Integer, Integer> numDigitsOfAllFibSeriesValues = new Hashtable<>();
        fibSeries.add(new BigInteger("1"));
        fibSeries.add(new BigInteger("1"));
        numDigitsInFibSeriesValues.put(0, 1);
        numDigitsInFibSeriesValues.put(1, 1);
        numDigitsOfAllFibSeriesValues = calcFibAndDetermineNumberOfDigits(numDigitsInFibSeriesValues, fibSeries,
                current, limit);

        // look through Dict<Int, Int> and identify first index that has at least 1000
        // digits in it (could go straight to 1001 or 1002 or 1100), so >= 1000...
        int firstFibValueToHaveAtLeastOneThousandDigits = findIndexOfFibValueWithAtLeastOneThousandDigits(
                numDigitsOfAllFibSeriesValues);

        System.out.println("Index of first Fibonacci value with at least 1000 digits: "
                + (firstFibValueToHaveAtLeastOneThousandDigits + 1));
    }

    private static Dictionary<Integer, Integer> calcFibAndDetermineNumberOfDigits(
            Dictionary<Integer, Integer> listOfNumDigitsInFibValues,
            List<BigInteger> lastTwoInFibSeries, int current, int limit) {
        List<BigInteger> newTwoInFibSeries = new ArrayList<>();
        if (current < limit) {
            BigInteger n = lastTwoInFibSeries.get(lastTwoInFibSeries.size() - 2)
                    .add(lastTwoInFibSeries.get(lastTwoInFibSeries.size() - 1));
            // System.out.println("current: " + current + "::" + n);
            newTwoInFibSeries.add(lastTwoInFibSeries.get(lastTwoInFibSeries.size() - 1));
            newTwoInFibSeries.add(n);
            int lengthOfCurrentFibSeriesValue = countDigits(newTwoInFibSeries);
            listOfNumDigitsInFibValues.put(current, lengthOfCurrentFibSeriesValue);
            current++;
            calcFibAndDetermineNumberOfDigits(listOfNumDigitsInFibValues, newTwoInFibSeries, current, limit);
        }
        return listOfNumDigitsInFibValues;
    }

    private static int countDigits(List<BigInteger> fibSeries) {
        int n = 0;
        for (int i = 1; i < fibSeries.size(); i++) { // only look at the 2nd value of 2 sent
            n = fibSeries.get(i).toString().length();
            // System.out.println(n);
        }
        return n;
    }

    private static int findIndexOfFibValueWithAtLeastOneThousandDigits(
            Dictionary<Integer, Integer> numDigitsOfFibSeriesValues) {
        int result = 0;
        for (int i = 0; i < numDigitsOfFibSeriesValues.size(); i++) {
            if (numDigitsOfFibSeriesValues.get(i) >= 1000) {
                result = i;
                break;
            }
        }
        return result;
    }
}
