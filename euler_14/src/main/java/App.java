import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Dictionary;
import java.util.Hashtable;

public class 7 {

    /*
     * Problem statement:
     * The following iterative sequence is defined for the set of positive integers:
     * 
     * n → n/2 (n is even)
     * n → 3n + 1 (n is odd)
     * 
     * Using the rule above and starting with 13, we generate the following
     * sequence:
     * 
     * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
     * It can be seen that this sequence (starting at 13 and finishing at 1)
     * contains 10 terms. Although it has not been proved yet (Collatz Problem), it
     * is thought that all starting numbers finish at 1.
     * 
     * Which starting number, under one million, produces the longest chain?
     * 
     * NOTE: Once the chain starts the terms are allowed to go above one million.
     */
    public static void main(String[] args) {
        long chainLength = 0L;
        long seedCausingLongestChainLength = 0L;
        int limit = 25000;
        Dictionary<Integer, Long> lengths = new Hashtable<>();

        for (int i = 13; i < 1000000; i++) {
            chainLength = collatzProblemProcessing(i, limit);
            lengths.put(i, chainLength);
        }

        checkForGreatestLength(lengths);
    }

    private static Long collatzProblemProcessing(int seed, int controlLimit) {
        long tmpValue = seed;
        int counter = 0;
        long chainLength = 0L;

        while (tmpValue > 1 && counter < controlLimit) {
            if (tmpValue % 2 == 0) { // i.e. EVEN
                tmpValue = tmpValue / 2;
                chainLength++;
            } else { // i.e. ODD
                tmpValue = tmpValue * 3 + 1;
                chainLength++;
            }
            counter++;
            // System.out.println("Seed: " + seed + ", tmpValue: " + tmpValue);
        }
        return chainLength;
    }

    private static void checkForGreatestLength(Dictionary<Integer, Long> chainLengths) {
        Long highestChainLengthValue = 0L;
        Long currentChainLength = 0L;
        Integer currentKey = 0;

        for (Enumeration e = chainLengths.keys(); e.hasMoreElements();) {
            Object a = e.nextElement();
            if (e.hasMoreElements() && chainLengths.get(a) >= 524) {
                System.out.println("nextElement value: " + a + " and chainLength: "
                        + chainLengths.get(a));
            }
            currentChainLength = chainLengths.get(a);
            if (currentChainLength > highestChainLengthValue) {
                System.out.println("chainLength: " + currentChainLength);
                highestChainLengthValue = currentChainLength;
            }
        }
    }
}
