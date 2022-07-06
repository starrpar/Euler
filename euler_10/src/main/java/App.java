

import java.util.List;
import java.util.ArrayList;

public class App {

    /*
     * Problem statement:
     * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
     * Find the sum of all the primes below two million.
     */
    public static void main(String[] args) {
        //long limit = 10L;
        long limit = 2000000L;
        List<Long> primes = new ArrayList<Long>();

        long startTime = System.currentTimeMillis();
        primes = findAllPrimesBelow(limit);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        long currentPrime, sum = 0;
        for (int i = 0; i < primes.size(); i++) {
            currentPrime = primes.get(i);
  
            System.out.println(currentPrime);
            sum += currentPrime;
        }
        System.out.println("Primes below " + limit + " sum to: " + sum);
        System.out.println("findAllPrimesBelow: " + elapsedTime + " milliseconds");
    }

    private static List<Long> findAllPrimesBelow(long limit) {
        ArrayList<Long> primes = new ArrayList<Long>();
        Boolean divisible = false;
        for (long i = 2; i < limit; i++) {
            divisible = false;
            for (int j = 2; j < i; j++) { // < vs. <= because dividing primes by themselves will eliminate them from selection
                if((i%50000 == 0) && (j%(50000) == 0)){
                    System.out.println("i,j: " + i + " :: " + j);
                }
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