import java.util.ArrayList;
import java.util.List;

public class App {
    /*
     * Starting with the number 1 and moving to the right in a clockwise direction a
     * 5 by 5 spiral is formed as follows:
     * 
     * 21 22 23 24 25
     * 20 7 8 9 10
     * 19 6 1 2 11
     * 18 5 4 3 12
     * 17 16 15 14 13
     * 
     * It can be verified that the sum of the numbers on the diagonals is 101.
     * 
     * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral
     * formed in the same way?
     */
    public static void main(String[] args) {
        // form the spiral to a passed-in limit
        int sideLength = 1001;
        List<Integer> spiral = constructTheSpiralMatrix(sideLength);

        // sum the diagonals (only count every number once (so the center counts once))
        int sum = sumSpiralDiagonals(spiral, sideLength);

        System.out.println(sum);
    }

    private static List<Integer> constructTheSpiralMatrix(int sideLength) {
        List<Integer> spiral = new ArrayList<Integer>();
        int limit = sideLength * sideLength;
        for (int i = 1; i <= limit; i++) {
            spiral.add(i);
        }
        return spiral;
    }

    private static int sumSpiralDiagonals(List<Integer> spiral, int sideLength) {
        int sum = 0;
        int step = 2;
        int currentOdd = 1; // not intended to be used at this value
        List<Integer> oddValues = new ArrayList<>();

        // sum += spiral.get(0);
        for (int i = 3; i <= sideLength; i++) {
            if (i % 2 != 0) {
                oddValues.add(i);
            }
        }
        int oddToGet = 0;
        currentOdd = oddValues.get(oddToGet); // get first value (which is 3 - lowest non "1" odd)
        for (int j = 1; j <= spiral.size();) { // control j directly within for loop
            // observed pattern is "+2" for 4 add'l values, then "+4" for 4 values, then
            // "+6" for 4 values, then "+8", etc...
            // need to put that into a conditinoal rule and we're good...
            // this hinges on squares of odd values (3^2 = 9, 5^2 = 25, 7^2 = 49, ...)
            if (j == (currentOdd * currentOdd)) {
                step += 2;// always bumps by 2
                if (j < spiral.size()) {
                    oddToGet++;// increment to next value in oddValues array
                    currentOdd = oddValues.get(oddToGet);
                }
            }
            if (j % 2 != 0) {
                sum += spiral.get(j - 1);
                j += step;
            }
        }
        return sum;
    }
}
