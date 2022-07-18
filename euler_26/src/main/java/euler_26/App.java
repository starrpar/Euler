package euler_26;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class App {
    /*
     * A unit fraction contains 1 in the numerator. The decimal representation of
     * the unit fractions with denominators 2 to 10 are given:
     * 
     * 1/2 = 0.5
     * 1/3 = 0.(3)
     * 1/4 = 0.25
     * 1/5 = 0.2
     * 1/6 = 0.1(6)
     * 1/7 = 0.(142857)
     * 1/8 = 0.125
     * 1/9 = 0.(1)
     * 1/10 = 0.1
     * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be
     * seen that 1/7 has a 6-digit recurring cycle.
     * 
     * Find the value of d < 1000 for which 1/d contains the longest recurring cycle
     * in its decimal fraction part.
     */
    public static void main(String[] args) {
        // get a list of decimal values for all unit fractions (List<double>)
        int limit = 1000;
        Dictionary<Integer, Double> decimalsForUnitFractions = calcDecimalValuesForUnitFractions(limit);

        // examine fractions for increasing lengths or repeat - save when found
        int indexOfLongestRepeatingPattern = calcLongestRepeatingPatternFromDecimalForUnitFractions(
                decimalsForUnitFractions);
    }

    private static Dictionary<Integer, Double> calcDecimalValuesForUnitFractions(int limit) {
        Dictionary<Integer, Double> decimalsForUnitFractions = new Hashtable<>();

        for (int i = 2; i < limit; i++) {
            double tmp = (double) 1 / i;
            System.out.println(tmp);
            decimalsForUnitFractions.put(i, tmp);
        }
        return decimalsForUnitFractions;
    }

    private static int calcLongestRepeatingPatternFromDecimalForUnitFractions(
            Dictionary<Integer, Double> unitFractionDecimals) {
        for (int i = 2; i < unitFractionDecimals.size(); i++) {
            int decimalLength = 0;
            char[] tmpArray = new char[19];
            tmpArray = String.valueOf(unitFractionDecimals.get(i)).toCharArray();
            decimalLength = tmpArray.toString().length();

            // methodology: start with half of the full length of the value and compare
            // first half to second half
            // if no pattern match, cut back length of substring by 1 digit and repeat
            // comparison
            // repeat until only comparing 1st and 2nd digits
            // also need to be able to find repeating characters starting anywhere too, for
            // example: 0.1326836466666666666

            for (int j = 1; j < decimalLength; j++) {
                for (int k = 2; k < decimalLength; k++) {

                }
            }
        }
    }
}