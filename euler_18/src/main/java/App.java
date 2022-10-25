//import java.util.Dictionary;
//import java.util.HashSet;
//import java.util.LinkedHashSet;
import java.util.List;
//import java.util.Set;
//import java.util.SortedSet;
//import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Arrays;

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

    /* Temp Note - almost done with this, but will have to come back to it later */
    public static void main(String[] args) {
        int sum = maxPath();
        System.out.println(sum);
    }

    private static int maxPath() {
        int sum = 0;
        Integer[] nums = { 3, 7, 4, 2, 4, 6, 8, 5, 9, 3 };

        // Integer[] nums = { 75, 95, 64, 17, 47, 82, 18, 35, 87, 10, 20, 04, 82, 47,
        // 65, 19, 01, 23, 75, 03, 34, 88, 02,
        // 77, 73, 07, 63, 67, 99, 65, 04, 28, 06, 16, 70, 92, 41, 41, 26, 56, 83, 40,
        // 80, 70, 33, 41, 48, 72, 33,
        // 47, 32, 37, 16, 94, 29, 53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14, 70, 11,
        // 33, 28, 77, 73, 17, 78, 39,
        // 68, 17, 57, 91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48, 63, 66, 04,
        // 68, 89, 53, 67, 30, 73, 16,
        // 69, 87, 40, 31, 04, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 04, 23 };

        List<Integer> list = Arrays.asList(nums);

        // note to self - TreeSet and HashSet seems to force sorted order, whereas
        // LinkedHashSet leaves the order as it was originally (though of course
        // ALL remove duplicates because they are sets)

        // Set<Integer> tree = new LinkedHashSet<>(list);
        // System.out.println(tree);

        // could do some form of row 1 has 1, 2 has 2, 3 has 3, etc... management
        // a bit excessive to manage, but could be made to work

        List<List<Integer>> rows = createStructureWithRows(list);

        sum = findMaxPath(rows);

        return sum;
    }

    private static List<List<Integer>> createStructureWithRows(List<Integer> numList) {
        List<List<Integer>> listOfRows = new ArrayList<>();
        int counter = 0;
        int row = 0;

        row++; // start with a row of 1
        for (int i = 0; i < numList.size();) {
            List<Integer> tmpRow = new ArrayList<>();
            for (int j = 0; j < row; j++) {
                tmpRow.add(numList.get(i++));
                counter++;
            }
            listOfRows.add(tmpRow);
            // if (i < list.size() - 1) {
            if (counter < numList.size()) {
                row++;
            }
        }
        return listOfRows;
    }

    private static int navigateRowsGetSum(List<List<Integer>> rows) {
        int sum = 0;
        int position = 0;
        int newPosition = 0;

        // NOTE:
        // clarification - problem is NOT to solve greatest
        // values from top down; but rather highest overall
        // sum of ANY path - I missed that first time;
        // will take another look later...

        // just need to add selectivity of highest value
        // based on position in row above to complete

        // navigate through rows in list of lists
        for (int i = 0; i < rows.size(); i++) {
            int maxVal = 0;
            position = newPosition;
            // for (int j = 0; j < rows.get(i).size(); j++) {
            for (int j = position; j <= i; j++) {
                if (rows.get(i).get(j) > maxVal && (j >= position && j <= position + 1)) {
                    maxVal = rows.get(i).get(j);
                    newPosition = j;
                }
            }
            System.out.println(maxVal);
            sum += maxVal;
        }

        return sum;
    }

    private static int findMaxPath(List<List<Integer>> rows) {
        int sum = 0;
        int row = 0;
        List<Integer> pivotRows = new ArrayList<>();
        int pivotRow = rows.size();
        Boolean wait = false;
        List<Integer> pathSums = new ArrayList<>();

        // NOTE:
        // clarification - problem is NOT to solve greatest
        // values from top down; but rather highest overall
        // sum of ANY path through adjacent values in the next
        // row down - I missed that first time;
        // will take another look later...

        // just need to add selectivity of highest value
        // based on position in row above to complete

        // navigate through rows in list of lists
        for (int i = 0; i < rows.size();) {
            for (int j = 0; j < rows.get(i).size();) {
                for (int k = 0; k < pivotRows.size(); k++) {
                    if (row == pivotRows.get(k)) {
                        j++;
                    }
                }
                sum += rows.get(i).get(j);
                row++;
                if (row < rows.size()) {
                    i++;
                } else {
                    j = 0;
                    break;
                }
            }
            pathSums.add(sum);
            System.out.println(sum);
            // reset params
            // if (!right) {
            // right = true;
            // } else {
            // right = false;
            // }
            sum = 0;
            i = 0;
            if (wait) {
                pivotRow--;
                pivotRows.clear();
                pivotRows.add(pivotRow);
            }
            if (row == rows.size() && wait) {
                wait = false;
                row = 0;
                continue;
            }
            if (row == rows.size() && !wait) {
                if (pivotRow != 3) {
                    pivotRow = 3;
                    pivotRows.add(pivotRow);
                }
                wait = true;
            }

            row = 0;
        }

        return sum;
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
