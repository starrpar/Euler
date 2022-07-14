package euler_23;

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
        int limit = 10000;
        Dictionary<Integer, List<Integer>> listOfDivisors = calcDivisors(limit);

        // calculate sum of various divisors
        Dictionary<Integer, Integer> sumsOfDivisors = calcSumOfDivisors(listOfDivisors);

        // determine abundant numbers vs. other (perfect or deficient)

        // determine which numbers can be calculated as the sum of two abundant numbers

        // determine which positive integers cannot be written "as the sum of two
        // abundant numbers"
        // (and then calculate their sum)
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
}
