package euler_21;

import java.util.List;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class App {
    /*
     * Let d(n) be defined as the sum of proper divisors of n (numbers less than n
     * which divide evenly into n).
     * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and
     * each of a and b are called amicable numbers.
     * 
     * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44,
     * 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4,
     * 71 and 142; so d(284) = 220.
     * 
     * Evaluate the sum of all the amicable numbers under 10000.
     */
    public static void main(String[] args) {
        // calculate proper divisors of all numbers under 10000 and store same
        int limit = 10;
        Dictionary<Integer, List<Integer>> listOfDivisors = calcDivisors(limit);

        // calculate sum of various divisors
        Dictionary<Integer, Integer> sumsOfDivisors = calcSumOfDivisors(listOfDivisors);

        for (int i = 0; i < sumsOfDivisors.size(); i++) {
            int sum = sumsOfDivisors.get(i);
            System.out.println("i: " + i + ", sum: " + sum);
        }
    }

    private static Dictionary<Integer, List<Integer>> calcDivisors(int limit) {
        List<Integer> divisors = new ArrayList<Integer>();
        Dictionary<Integer, List<Integer>> integersAndTheirDivisors = new Hashtable<>();
        Boolean divisible = false;
        for (int i = 1; i < limit; i++) {
            divisible = false;
            divisors = new ArrayList<Integer>();
            ;
            for (int j = 1; j < i; j++) {
                if (i % j == 0) {
                    divisible = true;
                    divisors.add(j);
                    System.out.println("i: " + i + " is divisible by j: " + j);
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

        for (int i = 0; i <= divisors.size(); i++) {
            divisorList = divisors.get(i);
            if (divisorList != null && !divisorList.isEmpty()) {
                for (int j = 0; j < divisorList.size(); j++) {
                    sum += divisorList.get(j);
                    System.out.println("i,j: " + i + "," + j + ", adding " + divisorList.get(j));
                }

                divisorSums.put(i, sum);
            } else {
                divisorSums.put(i, 0);
            }
        }
        return divisorSums;
    }
}
