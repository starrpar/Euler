public class App {
    /*
     * In the United Kingdom the currency is made up of pound (£) and pence (p). There are eight coins in general circulation:
     * 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p).
     * It is possible to make £2 in the following way:
     * 
     * 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
     * How many different ways can £2 be made using any number of coins?
     * 
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
