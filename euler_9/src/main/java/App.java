
public class App {
    /*
     * Problem statement:
     * A Pythagorean triplet is a set of three natural numbers, a < b < c, for
     * which,
     * 
     * a2 + b2 = c2
     * For example, 32 + 42 = 9 + 16 = 25 = 52.
     * 
     * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
     * Find the product abc.
     */
    public static void main(String[] args) {
    
        // solve a**2 + b**2 = c**2
        // and a + b + c = 1000

        // c = 1000 - (a + b)
        // a**2 + b**2 = (1000 - (a + b))**2
        // a**2 + b**2 = 1000**2 - (2000 * (a+b)) + (a+b)**2
        // a**2 + b**2 - 1000**2 + 2000a + 2000b = a**2 + 2ab + b**2
        // a**2 - a**2 + b**2 - b**2 -1000**2 + 2000a + 2000b - 2ab = 0
        // 2 * (1000a -ab + 1000b) - 1000**2 = 0
        // 1000a - ab + 1000b = 1000000 / 2 = 500000
        // a = 500 - b + ab / 1000

        // 500 - b + ab / 1000 + b + 1000 - a - b = 1000
        // 500 + ab/1000 -a - b = 0
        // 500 + ab/1000 - 500 + b -ab/1000 - b = 0
        // 0 = 0 ;(

        // a^2 + b^2 - 1000^2 + 1000a + 1000b - a^2 - 2ab - b^2
        // 1000^2 - 2ab + 1000 (a+b)
    }
}
