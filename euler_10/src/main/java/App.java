

import java.util.List;
import java.util.ArrayList;

public class App {

    /*
     * Problem statement:
     * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
     * Find the sum of all the primes below two million.
     */
    public static void main(String[] args) {
        long limit = 10L;
        //long limit = 2000000L;
        List<Integer> primes = new ArrayList<Integer>();

        long startTime = System.currentTimeMillis();
        primes = findAllPrimesBelow(limit);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        int currentPrime, sum = 0;
        for (int i = 0; i < primes.size(); i++) {
            currentPrime = primes.get(i);
            // System.out.println(currentPrime);
            sum += currentPrime;
        }
        System.out.println("Primes below " + limit + " sum to: " + sum);
        System.out.println("findAllPrimesBelow: " + elapsedTime + " milliseconds");
    }

    private static List<Integer> findAllPrimesBelow(long limit) {
        ArrayList<Integer> primes = new ArrayList<Integer>();
        Boolean divisible = false;
        for (int i = 2; i < limit; i++) {
            divisible = false;
            for (int j = 2; j < i; j++) { // < vs. <= because dividing primes by themselves will eliminate them from selection
                if (i % j == 0) {
                    divisible = true;
                    continue;
                }
            }
            if (!divisible) {
                primes.add(i);
                divisible = false;
                continue;
            }
        }
        return primes;
    }
}