import java.math.BigInteger;

public class App {
    /*
     * n! means n × (n − 1) × ... × 3 × 2 × 1
     * 
     * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
     * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 =
     * 27.
     * 
     * Find the sum of the digits in the number 100!
     */
    public static void main(String[] args) {
        int n = 100;
        BigInteger fact = new BigInteger("0");
        int sum = 0;

        fact = calcFactorial(n);
        sum = calcSumOfDigits(fact);

        System.out.println("Factorial: " + fact + ", sum: " + sum);
    }

    private static BigInteger calcFactorial(int n) {
        BigInteger result = new BigInteger("1");

        for (int i = 1; i < n; i++) {
            BigInteger iAsBigInt = new BigInteger(Integer.toString(i));
            result = result.multiply(iAsBigInt);
            // System.out.println("i: " + i + ", iAsBigInt: " + iAsBigInt + ", result: " +
            // result);
        }
        return result;
    }

    private static int calcSumOfDigits(BigInteger value) {
        int sum = 0;
        char[] bigIntAsCharArray = value.toString().toCharArray();
        for (int i = 0; i < bigIntAsCharArray.length; i++) {
            sum += Character.getNumericValue(bigIntAsCharArray[i]);
        }
        return sum;
    }
}
