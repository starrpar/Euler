package euler_16;

import java.math.BigInteger;

public class App {
    /*
     * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
     * 
     * What is the sum of the digits of the number 2^1000?
     */
    public static void main(String[] args) {
        int base = 2;
        int power = 1000;
        BigInteger result = calculatePowerOfX(base, power);
        System.out.println("result: " + result);
        int sum = addDigitsOfBigInt(result);
        System.out.println("sum: " + sum);
    }

    private static BigInteger calculatePowerOfX(int base, int power) {
        BigInteger biBase = BigInteger.valueOf(base);
        BigInteger result = biBase;
        for (int i = 2; i <= power; i++) {
            result = result.multiply(biBase);
        }
        return result;
    }

    private static int addDigitsOfBigInt(BigInteger input) {
        String bigIntAsStr = input.toString();
        char[] digitsOfBigInt = bigIntAsStr.toCharArray();
        int sum = 0;

        for (int i = 0; i < bigIntAsStr.length(); i++) {
            System.out.println(digitsOfBigInt[i]);
            int tmpInt = Character.getNumericValue(digitsOfBigInt[i]);
            sum += tmpInt;
        }
        return sum;
    }
}
