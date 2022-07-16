package euler_23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class App {
    /*
     * A perfect number is a number for which the sum of its proper divisors is
     * exactly equal to the number. For example, the sum of the proper divisors of
     * 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
     * 
     * A number n is called deficient if the sum of its proper divisors is less than
     * n and it is called abundant if this sum exceeds n.
     * 
     * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest
     * number that can be written as the sum of two abundant numbers is 24. By
     * mathematical analysis, it can be shown that all integers greater than 28123
     * can be written as the sum of two abundant numbers. However, this upper limit
     * cannot be reduced any further by analysis even though it is known that the
     * greatest number that cannot be expressed as the sum of two abundant numbers
     * is less than this limit.
     * 
     * Find the sum of all the positive integers which cannot be written as the sum
     * of two abundant numbers.
     */
    public static void main(String[] args) {
        // calculate proper divisors of all numbers under 10000 and store same
        int limit = 40000;
        Dictionary<Integer, List<Integer>> listOfDivisors = calcDivisors(limit);
        Dictionary<Integer, Integer> abundantNumbersWithSumOfDivisors = new Hashtable<>();

        // System.out.println(listOfDivisors.size());

        // calculate sum of various divisors
        Dictionary<Integer, Integer> sumsOfDivisors = calcSumOfDivisors(listOfDivisors);

        // System.out.println(sumsOfDivisors.size());

        // determine abundant numbers vs. other (perfect or deficient)
        abundantNumbersWithSumOfDivisors = determineAbundantNumbers(sumsOfDivisors);

        // determine which numbers can be calculated/written as the sum of two abundant
        // numbers
        // convert Dictionary keys to a List<Int> first
        List<Integer> listOfAbundantNumbers = new ArrayList<Integer>();
        for (Enumeration e = abundantNumbersWithSumOfDivisors.keys(); e.hasMoreElements();) {
            Object a = e.nextElement();
            listOfAbundantNumbers.add((Integer) a);
        }
        List<Integer> integersNotSumOfAbundantNumbers = determineWhichIntegersAreNotTheSumOfTwoAbundantNumbers(
                listOfAbundantNumbers, limit);
        System.out.println(integersNotSumOfAbundantNumbers.size());

        // sum the abundant numbers that are not the sum of 2 other abundant numbers
        int sumOfAbundantNumbersNotTheSumOfOtherAbundantNumbers = sumIntegersNotSumOfOtherAbundantNumbers(
                integersNotSumOfAbundantNumbers);

        // determine which positive integers cannot be written "as the sum of two
        // abundant numbers"
        // calculate the list of integers that are positive integers but NOT abundant
        // numbers
        List<Integer> notAbundantNumbers = determineIntegersThatAreNotAbundantNumbers(sumsOfDivisors);
        System.out.println(notAbundantNumbers.size());

        // (and then calculate their sum)
        int sumOfAllPositiveIntegersNotAbundantNumbers = sumNonAbundantNumberIntegers(notAbundantNumbers);
        System.out.println("sum of abundant numbers not the sum of other abundant numbers: "
                + sumOfAbundantNumbersNotTheSumOfOtherAbundantNumbers);
        System.out.println(
                "sum of positive integers that are not abundant numbers:" + sumOfAllPositiveIntegersNotAbundantNumbers);

        int total = sumOfAbundantNumbersNotTheSumOfOtherAbundantNumbers + sumOfAllPositiveIntegersNotAbundantNumbers;

        System.out.println("Total positive integers not the sum of abundant numbers: " + total);
    }

    private static Dictionary<Integer, List<Integer>> calcDivisors(int limit) {
        List<Integer> divisors = new ArrayList<Integer>();
        Dictionary<Integer, List<Integer>> integersAndTheirDivisors = new Hashtable<>();
        for (int i = 1; i < limit; i++) {
            divisors = new ArrayList<Integer>();
            ;
            for (int j = 1; j < i; j++) {
                if (i % j == 0) {
                    divisors.add(j);
                    continue;
                }
            }
            if (divisors != null && !divisors.isEmpty()) {
                integersAndTheirDivisors.put(i, divisors);
            } else {
                integersAndTheirDivisors.put(i, new ArrayList<Integer>());
            }
        }
        return integersAndTheirDivisors;
    }

    private static Dictionary<Integer, Integer> calcSumOfDivisors(Dictionary<Integer, List<Integer>> divisors) {
        int sum = 0;
        Dictionary<Integer, Integer> divisorSums = new Hashtable<>();
        List<Integer> divisorList = new ArrayList<Integer>();

        for (int i = 1; i <= divisors.size(); i++) { // i needs to be value of Key, not index of collection
            divisorList = divisors.get(i);
            sum = 0;
            if (divisorList != null && !divisorList.isEmpty()) {
                for (int j = 0; j < divisorList.size(); j++) {
                    sum += divisorList.get(j);
                }

                divisorSums.put(i, sum);
            } else {
                divisorSums.put(i, 0);
            }
        }
        return divisorSums;
    }

    private static Dictionary<Integer, Integer> determineAbundantNumbers(Dictionary<Integer, Integer> sumsOfDivisors) {
        Dictionary<Integer, Integer> abundantNumbers = new Hashtable<>();
        Dictionary<Integer, Integer> perfectNumbers = new Hashtable<>();
        Dictionary<Integer, Integer> deficientNumbers = new Hashtable<>();
        Integer tmp = 0;

        for (int i = 0; i < sumsOfDivisors.size(); i++) {
            if (sumsOfDivisors.get(i) != null && (tmp = sumsOfDivisors.get(i)) > i) {
                abundantNumbers.put(i, tmp);
            } else if (sumsOfDivisors.get(i) != null && (tmp = sumsOfDivisors.get(i)) == i) {
                perfectNumbers.put(i, tmp);
            } else {
                deficientNumbers.put(i, tmp);
            }
        }
        System.out.println(abundantNumbers.size());
        System.out.println(perfectNumbers.size());
        System.out.println(deficientNumbers.size());

        return abundantNumbers;
    }

    private static List<Integer> determineWhichIntegersAreNotTheSumOfTwoAbundantNumbers(List<Integer> abundantNumbers,
            int limit) {
        List<Integer> notSumOfTwoAbundantNumbers = new ArrayList<Integer>();
        List<Integer> sumOfTwoAbundantNumbers = new ArrayList<Integer>(); // use just to verify overall numbers for

        Boolean canSumTo = false;

        Collections.sort(abundantNumbers);
        for (int i = 0; i < limit; i++) {
            canSumTo = false;
            for (int j = 0; j < abundantNumbers.size(); j++) {
                for (int k = 0; k < abundantNumbers.size(); k++) {
                    if (i == abundantNumbers.get(j) + abundantNumbers.get(k)) {
                        canSumTo = true;
                        sumOfTwoAbundantNumbers.add(i);
                        break;
                    }
                }
                if (canSumTo) {
                    break;
                }
            }
            if (!canSumTo) {
                notSumOfTwoAbundantNumbers.add(i);
                continue;
            }
        }
        System.out.println(notSumOfTwoAbundantNumbers.size());
        System.out.println(sumOfTwoAbundantNumbers.size());
        System.out.println(limit - notSumOfTwoAbundantNumbers.size());

        return notSumOfTwoAbundantNumbers;
    }

    private static int sumIntegersNotSumOfOtherAbundantNumbers(List<Integer> abundantNumbersNotSumOfOthers) {
        int result = 0;

        for (int i = 0; i < abundantNumbersNotSumOfOthers.size(); i++) {
            result += abundantNumbersNotSumOfOthers.get(i);
        }

        return result;
    }

    private static List<Integer> determineIntegersThatAreNotAbundantNumbers(
            Dictionary<Integer, Integer> sumsOfDivisors) {
        List<Integer> notAbundantNumbers = new ArrayList<>();

        for (int i = 0; i < sumsOfDivisors.size(); i++) {
            if (sumsOfDivisors.get(i) != null && (sumsOfDivisors.get(i)) > i) {
                continue;
            } else if (sumsOfDivisors.get(i) != null && (sumsOfDivisors.get(i)) == i) {
                notAbundantNumbers.add(i);
            } else {
                notAbundantNumbers.add(i);
            }
        }
        System.out.println(notAbundantNumbers.size());

        return notAbundantNumbers;
    }

    private static int sumNonAbundantNumberIntegers(List<Integer> nonAbundantNumbers) {
        int result = 0;

        for (int i = 0; i < nonAbundantNumbers.size(); i++) {
            result += nonAbundantNumbers.get(i);
        }

        return result;
    }
}
