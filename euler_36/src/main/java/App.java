import java.util.ArrayList;
import java.util.List;

public class App {

/* 
* The decimal number, (585 = 1001001001[base 2]), is palindromic in both bases (base 10 and base 2).
* Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
* (Please note that the palindromic number, in either base, may not include leading zeros.)
*/

    public static void main(String[] args) {
        //
        // figure out an approach
        //
        // determine if the decimal value is palindromic
        // if so, calculate the binary value
        // and then determine if that value is palindromic
        // if yes in both cases, store the values in a list of dictionary
        // (could use decimal value as key and binary value as value in K-V pair,
        // but don't really need to keep binary value - could recalculate)
        // once reach limit (1 million) then sum all decimal values (from list) and return result

        int limit = 1000000;
        List<Integer> decimalValuesThatAreAlsoBinaryPalindromes = new ArrayList<Integer>();
        int[] binaryValue = new int[1000];
        boolean decimalIsPalindrome = false;
        boolean binaryIsPalindrome = false;

        for(int i = 1; i < limit; i++){
            decimalIsPalindrome = isDecimalValuePalindrome(i, limit);
            if(decimalIsPalindrome){
                //System.out.println("\ndecimalIsPalindrome: " + i);
                binaryValue = calculateBinaryFromDecimalReturnString(i);
                int binValueLength = binaryValue.length;
                //System.out.println("len: " + binValueLength);
                String binValueStr = "";
                for (int j = binValueLength - 1; j >= 0; j--){
                    binValueStr += binaryValue[j];
                    //System.out.print(binaryValue[j]);
                }
                binaryIsPalindrome = isBinaryValuePalindrome(binValueStr);
                if(binaryIsPalindrome){
                    System.out.println("\n" + i);
                    decimalValuesThatAreAlsoBinaryPalindromes.add(i);
                }
            }
        }

        int sumOfPalindromicDecimalValues = 0;
        for(int i = 0; i < decimalValuesThatAreAlsoBinaryPalindromes.size(); i++){
            //System.out.println(decimalValuesThatAreAlsoBinaryPalindromes.get(i));
            sumOfPalindromicDecimalValues += decimalValuesThatAreAlsoBinaryPalindromes.get(i);
        }

        System.out.println("sum of decimal numbers that are palindromic both in base 10 and base 2: " + sumOfPalindromicDecimalValues);
    }

    private static boolean isDecimalValuePalindrome(int number, int limit) {
        //convert value to string and compare for symmetry backwards/forwards
        String numAsStr = String.valueOf(number);
        char[] numCharArray = new char[numAsStr.length()];
        //System.out.println(numAsStr);
        String reversedStr = "";
        numCharArray = numAsStr.toCharArray();
        //System.out.println(numCharArray);
        for(int i = numCharArray.length-1; i >= 0; i--){
            //System.out.print("\ni: " + i);
            reversedStr += numCharArray[i];
        }
        char[] revStrCharArray = new char[reversedStr.length()];
        revStrCharArray = reversedStr.toCharArray();
        int strLength = reversedStr.length();
        for(int i = 0; i < strLength; i++){
            //System.out.println(revStrCharArray[i]);
            //System.out.println(numCharArray[i]);

            if(revStrCharArray[i] != numCharArray[i]){
                break;
            }
            if(i == strLength - 1){
                /*
                System.out.println("**************");
                System.out.println("    MATCH     ");
                System.out.println("**************");
                System.out.println(reversedStr + ":" + numAsStr);
                */
                return true;
            }
        }
        return false;
    }

    private static int[] calculateBinaryFromDecimalReturnString(int number) {
        int[] binaryNum = new int[1000];
        int i = 0;
        while (number > 0) {
            binaryNum[i] = number % 2;
            number = number / 2;
            i++;
        }
        int[] binaryNumNew = new int[i];
        for (int j = i - 1; j >= 0; j--){
            //System.out.print(binaryNum[j]);
            binaryNumNew[j] = binaryNum[j];
        }
        
        return binaryNumNew;
    }

    private static boolean isBinaryValuePalindrome(String binaryValue) {
        int arrLength = binaryValue.length();
        char[] binValueCharArray = new char[arrLength];
        char[] reversedCharArray = new char[arrLength];
        binValueCharArray = binaryValue.toCharArray();
        
        for(int i = arrLength-1; i >= 0; i--){
            reversedCharArray[arrLength - 1 - i] += binValueCharArray[i];
        }

        for(int i = 0; i < arrLength; i++){
            if(reversedCharArray[i] != binValueCharArray[i]){
                break;
            }
            if(i == arrLength - 1){
                
                // System.out.println("**************");
                // System.out.println("    MATCH     ");
                // System.out.println("**************");
                System.out.println(reversedCharArray);
                System.out.println(binValueCharArray);
                
                return true;
            }
        }
        return false;
    }
}
