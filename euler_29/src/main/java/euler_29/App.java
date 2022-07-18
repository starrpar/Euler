package euler_29;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.math.BigInteger;

public class App {
    /*
     * Consider all integer combinations of a^b for 2 ≤ a ≤ 5 and 2 ≤ b ≤ 5:
     * 
     * 2^2=4, 2^3=8, 2^4=16, 2^5=32
     * 3^2=9, 3^3=27, 3^4=81, 3^5=243
     * 4^2=16, 4^3=64, 4^4=256, 4^5=1024
     * 5^2=25, 5^3=125, 5^4=625, 5^5=3125
     * 
     * If they are then placed in numerical order, with any repeats removed, we get
     * the following sequence of 15 distinct terms:
     * 
     * 4, 8, 9, 16, 25, 27, 32, 64, 81, 125, 243, 256, 625, 1024, 3125
     * 
     * How many distinct terms are in the sequence generated by ab for 2 ≤ a ≤ 100
     * and 2 ≤ b ≤ 100?
     */
    public static void main(String[] args) {
        // calculate the various power/exponent values
        // need to create a generation approach
        int low = 2;
        int high = 100;

        List<BigInteger> products = new ArrayList<>();
        products = calculatePowerProducts(low, high);

        // go through resulting list and remove duplicates
        List<BigInteger> listRidOfDuplicates = removeDuplicates(products);

        System.out.println("Number of distinct terms for low: " + low + " to high: " + high + " is: "
                + listRidOfDuplicates.size());

        // System.out.println("////////////////////////////////////////////////////////////");
        // Collections.sort(listRidOfDuplicates);
        // for (int i = 0; i < listRidOfDuplicates.size(); i++) {
        // System.out.println(listRidOfDuplicates.get(i));
        // }
    }

    private static List<BigInteger> calculatePowerProducts(int low, int high) {
        List<BigInteger> products = new ArrayList<>();
        BigInteger tmp = new BigInteger("1");

        for (int i = low; i <= high; i++) {
            for (int j = low; j <= high; j++) {
                tmp = new BigInteger("1");
                for (int k = 1; k <= j; k++) {
                    tmp = tmp.multiply(BigInteger.valueOf(i));
                }
                products.add(tmp);
            }
        }
        return products;
    }

    private static List<BigInteger> removeDuplicates(List<BigInteger> products) {
        List<BigInteger> nonDuplicateList = new ArrayList<>();
        List<BigInteger> listOfDupes = new ArrayList<>();
        Boolean duplicate = false;
        Boolean alreadyAdded = false;

        for (int i = 0; i < products.size(); i++) {
            duplicate = false;
            alreadyAdded = false;
            for (int j = 0; j < products.size(); j++) {
                if (i != j && products.get(i).equals(products.get(j))) {
                    // if value is in list elsewhere
                    duplicate = true;
                    listOfDupes.add(products.get(i));

                    for (int k = 0; k < nonDuplicateList.size(); k++) {
                        if (products.get(i).equals(nonDuplicateList.get(k))) {
                            alreadyAdded = true; // redundant
                        }
                    }
                    // and if it is not already
                }
            }
            if (duplicate) {
                if (!alreadyAdded) {
                    nonDuplicateList.add(products.get(i));
                    continue;
                }
            } else {
                nonDuplicateList.add(products.get(i));
                continue;
            }
        }
        System.out.println("Number of dupes: " + listOfDupes.size());
        // for (int n = 0; n < listOfDupes.size(); n++) {
        // System.out.println(listOfDupes.get(n));
        // }
        return nonDuplicateList;
    }
}