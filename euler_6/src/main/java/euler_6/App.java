

public class App 
{
    public static void main( String[] args )
    {
        int n, m = 0;
        int delta = 0;
        int limit = 1002;
        for(int i = 1; i <= limit; i++){
            //square of values
            n = i*i;
            //summed
            m += n;
        }
        System.out.println("Sum of squares: " + m);
        delta = m;

        //reset aggregates
        n = 0; m = 0;

        for (int j = 1; j <= limit; j++){
            //sum of values
            n += j;
        }
        //square the result
        m = n*n;
        System.out.println("Square of sum: " + m);
        delta = m - delta;
        System.out.println("Delta: " + delta);
    }
}
