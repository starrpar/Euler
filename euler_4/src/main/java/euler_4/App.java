package euler_4;

import java.util.*;

public class App 
{
    public static void main( String[] args )
    {
        List<Long> palindromicValues = new ArrayList<Long>();

        long k = 0;
        for(int i = 100; i <= 999; i++){
            for(int j = 100; j <= 999; j++){
                //get product
                k = i * j;
                //System.out.println(k);
                //test if product is a palindrome; if it is, put it into list
                if(isPalindrome(k)){
                    palindromicValues.add(k);
                }
            }
        }
        int palindromicValsSize = palindromicValues.size();
        Collections.sort(palindromicValues);
        System.out.println(palindromicValues.get(palindromicValsSize-1));
    }

    private static Boolean isPalindrome(long k){
        String kStr = Long.toString(k);

        //System.out.println(k);

        int originalStrLen = kStr.length();
        char[] reversedString = new char[originalStrLen];
        char[] originalString = kStr.toCharArray();

        for(int i = 0; i < originalStrLen; i++){
            //System.out.print(originalString[(originalStrLen - 1) - i]);
            reversedString[i] = originalString[(originalStrLen - 1) - i];
        }
        //System.out.println(reversedString);

        Long revStrAsInt = Long.parseLong(new String(reversedString));
        //System.out.println(revStrAsInt);

        return revStrAsInt.equals(k);
    }
}