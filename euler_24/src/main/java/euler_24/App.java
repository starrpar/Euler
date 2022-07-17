package euler_24;

public class App {

    /*
     * A permutation is an ordered arrangement of objects. For example, 3124 is one
     * possible permutation of the digits 1, 2, 3 and 4. If all of the permutations
     * are listed numerically or alphabetically, we call it lexicographic order. The
     * lexicographic permutations of 0, 1 and 2 are:
     * 
     * 012 021 102 120 201 210
     * 
     * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4,
     * 5, 6, 7, 8 and 9?
     */
    public static void main(String[] args) {
        // use lowest value digits first
        // 0123456789 is first value
        // 0123456798 is next - switch the last digit with the one previous (9 to 8, 8
        // to 9)
        // 0123456879 next shift the 8 with the 7
        // 0123456897 then the 9 with the 7 (having the 8 already in the original
        // position of the 7)
        // 0123456978 then switch 9 with 8 and have 7 before 8
        // 0123456987 then 9 but followed by 8 switched with 7
        // 0123457689 then switch 7 with 6 and lowest numbers first following
        // 0123457698 then switch 9 and 8
        // 0123457869 then switch 8 and 6
        // 0123457896 then 9 and 6, keeping 8 in place
        // 0123457968
        // 0123457986
        // this gives us a pattern, but need to extend it to the entire set of digits
        // and have a programmatic way of identifying the next order
    }
}
