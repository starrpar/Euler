

import java.util.ArrayList;

public class App
{
    public static void main( String[] args )
    {
        int number = 10001; //6;
        findRequestedPrime(number);
    }

    private static void findRequestedPrime(int nthPrime)
    {
        ArrayList<Integer> primes = new ArrayList<Integer>();
        int numPrime = 0;
        Boolean divisible = false;
        for(int i = 2; numPrime < nthPrime; i++){
            divisible = false;
            for(int j = 2; j < i; j++){
                if(i%j==0){
                    divisible = true;
                    continue;
                }
            }
            if(!divisible){
                primes.add(i);
                numPrime++;
                divisible = false;
                continue;
            }
        }

        System.out.println("Prime number " + nthPrime + " is: " + primes.get(primes.size() - 1));

    }
}
