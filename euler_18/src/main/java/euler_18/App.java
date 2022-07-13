package euler_18;

import java.util.Dictionary;
import java.util.List;
import java.util.ArrayList;

public class App {
    /*
     * By starting at the top of the triangle below and moving to adjacent numbers
     * on the row below, the maximum total from top to bottom is 23.
     * 
     * 3
     * 7 4
     * 2 4 6
     * 8 5 9 3
     * 
     * That is, 3 + 7 + 4 + 9 = 23.
     * 
     * Find the maximum total from top to bottom of the triangle below:
     * 
     * 75
     * 95 64
     * 17 47 82
     * 18 35 87 10
     * 20 04 82 47 65
     * 19 01 23 75 03 34
     * 88 02 77 73 07 63 67
     * 99 65 04 28 06 16 70 92
     * 41 41 26 56 83 40 80 70 33
     * 41 48 72 33 47 32 37 16 94 29
     * 53 71 44 65 25 43 91 52 97 51 14
     * 70 11 33 28 77 73 17 78 39 68 17 57
     * 91 71 52 38 17 14 91 43 58 50 27 29 48
     * 63 66 04 68 89 53 67 30 73 16 69 87 40 31
     * 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
     * 
     * NOTE: As there are only 16384 routes, it is possible to solve this problem by
     * trying every route. However, Problem 67, is the same challenge with a
     * triangle containing one-hundred rows; it cannot be solved by brute force, and
     * requires a clever method! ;o)
     */
    public static void main(String[] args) {
        int sum = maxPathSum();
        System.out.println(sum);
    }

    // private static int maxPathSum(Dictionary<Integer, ArrayList<Integer>> dict){
    private static int maxPathSum() {
        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(3);
        List<Integer> l2 = new ArrayList<Integer>();
        l2.add(7);
        l2.add(4);
        List<Integer> l3 = new ArrayList<Integer>();
        l3.add(2);
        l3.add(4);
        l3.add(6);
        List<Integer> l4 = new ArrayList<Integer>();
        l4.add(8);
        l4.add(5);
        l4.add(9);
        l4.add(3);

        // 0. Have a brute force method that works, but...
        // 1. Need to use recursion
        // 2. Need to find an easy way to create lists using comma-delimited lists (i.e.
        // not manually inserted!)

        List<Integer> maxPath = new ArrayList<Integer>();
        maxPath.add(l1.get(0));
        if (l2.get(0) > l2.get(1)) {
            maxPath.add(l2.get(0));
            if (l3.get(0) > l3.get(1)) {
                maxPath.add(l3.get(0));
                if (l4.get(0) > l4.get(1)) {
                    maxPath.add(l4.get(0));

                } else {
                    maxPath.add(l4.get(1));
                }
            } else {
                maxPath.add(l3.get(1));
                if (l4.get(1) > l4.get(2)) {
                    maxPath.add(l4.get(1));
                } else {
                    maxPath.add(l4.get(2));
                }
            }
        } else {
            maxPath.add(l2.get(1));
            if (l3.get(1) > l3.get(2)) {
                maxPath.add(l3.get(1));
                if (l4.get(1) > l4.get(2)) {
                    maxPath.add(l4.get(1));

                } else {
                    maxPath.add(l4.get(2));
                }
            } else {
                maxPath.add(l3.get(2));
                if (l4.get(2) > l4.get(3)) {
                    maxPath.add(l4.get(2));

                } else {
                    maxPath.add(l4.get(3));
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < maxPath.size(); i++) {
            sum += maxPath.get(i);
        }
        return sum;
    }
}
