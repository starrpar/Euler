public class App {
    /*
     * Surprisingly there are only three numbers that can be written as the sum of
     * fourth powers of their digits:
     * 
     * 1634 = 1^4 + 6^4 + 3^4 + 4^4
     * 8208 = 8^4 + 2^4 + 0^4 + 8^4
     * 9474 = 9^4 + 4^4 + 7^4 + 4^4
     * As 1 = 1^4 is not a sum it is not included.
     * 
     * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
     * 
     * Find the sum of all the numbers that can be written as the sum of fifth
     * powers of their digits.
     */
    public static void main(String[] args) {
        //
        // for all numbers (to a limit - pass it in)
        // calculate the value of the sum of their digits to the fifth power
        // only keep those where the sum equals the original value (factor out any '1's)
        // once found, change the limit and see if we get any new numbers
        int limit = 1000000;
        int sumOfDigitsToFifthPower = calculateForthPowersOfDigits(limit);
        System.out.println(sumOfDigitsToFifthPower);
        sumOfDigitsToFifthPower = calculateFifthPowersOfDigits(limit);
        System.out.println(sumOfDigitsToFifthPower);
    }

    private static int calculateForthPowersOfDigits(int limit) {
        int sumOfDigitsToFifthPower = 0;
        char[] tmpArray = new char[4];

        for (int i = 2; i < limit; i++) {
            tmpArray = String.valueOf(i).toCharArray();
            int interimSum = 0;
            int tmpInt = 0;
            int tmpFifthPower = 1;
            for (int j = 0; j < tmpArray.length; j++) {
                tmpFifthPower = 1;
                tmpInt = Character.getNumericValue(tmpArray[j]);
                for (int k = 0; k < 4; k++) {
                    tmpFifthPower *= tmpInt;
                }
                interimSum += tmpFifthPower;
            }
            if (interimSum == i) {
                sumOfDigitsToFifthPower += i;
            }
        }
        return sumOfDigitsToFifthPower;
    }

    private static int calculateFifthPowersOfDigits(int limit) {
        int sumOfDigitsToFifthPower = 0;
        char[] tmpArray = new char[4];

        for (int i = 2; i < limit; i++) {
            tmpArray = String.valueOf(i).toCharArray();
            int interimSum = 0;
            int tmpInt = 0;
            int tmpFifthPower = 1;
            for (int j = 0; j < tmpArray.length; j++) {
                tmpFifthPower = 1;
                tmpInt = Character.getNumericValue(tmpArray[j]);
                for (int k = 0; k < 5; k++) {
                    tmpFifthPower *= tmpInt;
                }
                interimSum += tmpFifthPower;
            }
            if (interimSum == i) {
                sumOfDigitsToFifthPower += i;
            }
        }
        return sumOfDigitsToFifthPower;
    }
}
