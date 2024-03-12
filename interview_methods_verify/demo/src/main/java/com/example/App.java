package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        String testStr = "Are we not drawn onward, we few, drawn onward to new era?";
        System.out.println("Palindrome? " + isPalindrome(testStr));
        System.out.println("Palindrome? " + isPalindromeAllowOneCharFix(testStr));

        Integer[] testArr = { 0, 1, 2, 1, 0, 0, 2, 0, 1, 1, 2, 0, 2, 1, 2, 1, 2, 2, 0, 0, 2 };
        List<Integer> listArr = Arrays.asList(testArr);
        System.out.println("listArr: " + listArr);
        List<Integer> resultArr = moveZeroes(listArr);
        System.out.println("resultArr: " + resultArr);
        resultArr = moveZeroesMoreEfficient(testArr);
        System.out.println("resultArr: " + resultArr);

    }

    // this is the brute force O(n^2) version I was trying during the interview
    public static List<Integer> moveZeroes(List<Integer> arr) {
        int arrayLen = arr.size();

        for (int i = 0; i < arrayLen; i++) {
            int k = i;
            while (i < arrayLen && k < arrayLen && arr.get(i) == 0) {
                // if moving 0 to current position 'i', then need to ALSO move that one to the
                // end as well (ongoing)
                // once have moved a non-zero value to current position 'i', THEN can increment
                // i and continue
                for (int j = i; j < arrayLen - 1; j++) {
                    arr.set(j, arr.get(j + 1));
                }
                arr.set(arrayLen - 1, 0);

                k++;
            }
        }
        return arr;
    }

    // todo: find a better than O(n^2) means of solving this
    // following is O(n)
    public static List<Integer> moveZeroesMoreEfficient(Integer[] arr) {
        int arrayLen = arr.length;
        List<Integer> arrList;
        List<Integer> newArr = new ArrayList<>();
        List<Integer> tmpArr = new ArrayList<>();

        arrList = Arrays.asList(arr);

        for (int i = 0; i < arrayLen; i++) {
            if (arrList.get(i) == 0) {
                // capture zeroes
                tmpArr.add(0);
            } else {
                // capture non-zeroes
                newArr.add(arr[i]);
            }
        }
        newArr.addAll(tmpArr);

        return newArr;
    }

    // this is the palindrome testing method I was doing in the interview
    // I have only added the cleanup steps (convert to lowercase, remove whitespace,
    // etc..)
    public static boolean isPalindrome(String s) {
        // todo: use Regex to cleanup instead of explicit listing
        String strCleaned = s.toLowerCase().replaceAll("\\s", "").replaceAll(",", "").replaceAll(";", "")
                .replaceAll("-", "").replaceAll("'", "").replaceAll(":", "");
        int strLength = strCleaned.length();
        System.out.println("Cleaned string: " + strCleaned);
        char[] cArr = strCleaned.toCharArray();
        for (int i = 0; i < strLength / 2; i++) {
            if (cArr[i] != cArr[strLength - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    // todo: this is the more advanced fault-tolerant palindrome checker method
    // tolerates only a single character mistake/typo
    public static boolean isPalindromeAllowOneCharFix(String s) {
        // todo: use Regex to cleanup instead of explicit listing
        String strCleaned = s.toLowerCase().replaceAll("\\s", "").replaceAll(",", "").replaceAll(";", "")
                .replaceAll("-", "").replaceAll("'", "").replaceAll(":", "");
        int strLength = strCleaned.length();
        char[] charArray = strCleaned.toCharArray();
        HashMap<Character, Integer> charCount = new HashMap<>();

        char middleChar = '\0';
        List<Character> possibleMiddleChars = new ArrayList<>();
        if (strCleaned.length() % 2 != 0) { // i.e. odd length, so single middle character for a valid palindrome
            middleChar = charArray[strCleaned.length() / 2]; // int division should result in middle char - if not,
                                                             // force using floor()
            System.out.println("\nmiddleChar: " + middleChar);
        } else {
            possibleMiddleChars.add(charArray[(strCleaned.length() / 2) - 1]);
            possibleMiddleChars.add(charArray[(strCleaned.length() / 2) + 1]);
        }

        Set<Character> keys = charCount.keySet();
        boolean found;
        for (int i = 0; i < charArray.length; i++) {
            found = false;
            for (char c : keys) {
                if (c == charArray[i]) {
                    charCount.replace(charArray[i], charCount.get(c) + 1);
                    found = true;
                }
            }
            if (!found) {
                charCount.put(charArray[i], 1);
            }
        }

        List<Character> candidates = new ArrayList<>();
        for (char c : keys) {
            if (charCount.get(c) % 2 != 0) {
                candidates.add(c);
            }
        }

        char likelyMiddleChar = '\0';
        for (char c : candidates) {
            for (char ch : possibleMiddleChars) {
                if (c == ch) {
                    likelyMiddleChar = c;
                }
            }
        }

        // determine either 'extra' characters or center (single) characters
        char likelyTypoChar = '\0';
        if (candidates.size() > 2) {
            System.out.println(
                    "Something is not right - more than only one char typo and center/middle character present in candidates list");
        }
        for (char c : candidates) {
            if (c != likelyMiddleChar) {
                likelyTypoChar = c;
            }
        }

        // based on odd-count occurring characters, determine which is likely the typo
        for (int i = 0; i < strLength; i++) {
            if (charArray[i] != charArray[strLength - 1 - i]) {
                if (charArray[i] == likelyTypoChar) {
                    // remove extra char and test resulting string as a Palindrome
                    System.out.println("Likely typo identified: " + likelyTypoChar);
                    break;
                }
            }
        }

        // once likely typo is identified, determine which occurrence does not "align"
        // with another, if more than one exist in string/potential palindrome
        String newStr = "";
        for (int i = 0; i < strLength; i++) {
            if (charArray[i] == likelyTypoChar && charArray[i] != charArray[strLength - 1 - i]) {
                System.out.println("Character to remove: " + strCleaned.charAt(i) + " from position: " + (i + 1));
                String tmpStr1 = strCleaned.substring(0, i);
                String tmpStr2 = strCleaned.substring(i + 1, strLength);
                newStr = tmpStr1 + tmpStr2;
                break;
            }
        }

        return isPalindrome(newStr);
    }
}
