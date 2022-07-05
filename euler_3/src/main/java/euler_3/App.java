//package euler_3;

import java.util.ArrayList;

public class App
{
    public static void main( String[] args )
    {
        long input = 600851475143L;
        //long input = 1851475143;
        //long input = 13195;
        long startTime = System.currentTimeMillis();
        method1(input);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("method1: " + elapsedTime);

        long startTime2 = System.currentTimeMillis();
        method2(input);
        long stopTime2 = System.currentTimeMillis();
        long elapsedTime2 = stopTime2 - startTime2;
        System.out.println("method2: " + elapsedTime2);
    }

    private static void method1(long x)
    {
        ArrayList<Long> candidates = new ArrayList<Long>();
        ArrayList<Long> primes = new ArrayList<Long>();

        long sum = 0;

        Boolean alreadydivisible = false;
        for(long i = 2; i < x; i++){
            //first off calculate divisors
            if(x%i==0){
                for(int o = 0; o < candidates.size(); o++){
                    if(candidates.get(o)%i==0){
                        alreadydivisible = true;
                        continue;
                    }
                }
                if(!alreadydivisible){
                    candidates.add(i);
                    alreadydivisible = false;
                }
            }
        }

        for(int n = 0; n < candidates.size(); n++){
            System.out.println(candidates.get(n));
        }

        Boolean notaprime = false;
        for(int j = 0; j < candidates.size(); j++){
            for(long k = 2; k < candidates.get(j); k++){
                //if candidates[j] is divisible by anything other than 1 an itself, it is not a prime
                if(candidates.get(j)%k == 0){
                    notaprime = true;
                    continue;
                }
            }
            if(!notaprime){
                primes.add(candidates.get(j));
                notaprime = false;
            }
        }

        for(int m = 0; m < primes.size(); m++){
            sum+=primes.get(m);
        }

        System.out.println(sum);
    }


    private static void method2(long x)
    {
        ArrayList<Long> candidates = new ArrayList<Long>();
        ArrayList<Long> primes = new ArrayList<Long>();

        long sum = 0;

        Boolean alreadydivisible = false;
        for(long i = 2; i < x; i++){
            //first off calculate divisors
            alreadydivisible = false;

            for(int o = 0; o < candidates.size(); o++){
                if(i%candidates.get(o)==0){
                    //System.out.println(i + " is already divisible by " + candidates.get(o) + ", the value of o is: " + o);
                    alreadydivisible = true;
                    break;
                }
            }
            //if not found to already be divisible into an existing candidate, test if divides into number
            //and insert into candidates array if so
            if(!alreadydivisible){
                if(x%i==0){
                    candidates.add(i);
                }
            }
        }

        for(int n = 0; n < candidates.size(); n++){
            System.out.println(candidates.get(n));
        }

        Boolean notaprime = false;
        for(int j = 0; j < candidates.size(); j++){
            for(long k = 2; k < candidates.get(j); k++){
                //if candidates[j] is divisible by anything other than 1 an itself, it is not a prime
                if(candidates.get(j)%k == 0){
                    notaprime = true;
                    break;
                }
            }
            if(!notaprime){
                primes.add(candidates.get(j));
                notaprime = false;
            }
        }

        for(int m = 0; m < primes.size(); m++){
            sum+=primes.get(m);
        }

        System.out.println(sum);
    }
}