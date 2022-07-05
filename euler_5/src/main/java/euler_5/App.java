package euler_5;

import java.util.*;

public class App 
{
    public static void main( String[] args )
    {
        int x = 1000000000;
        Boolean notDivisible = false;
        List<Integer> divisibleByAll = new ArrayList<Integer>();
        for(int i = x; i > 0; i--){
            notDivisible = false;
            int max = 20;
            for(int j = 1; j <= max; j++){
                if(i%j == 0){
                    if(j < max){
                        continue;
                    }
                    else{
                        if(!notDivisible){
                            divisibleByAll.add(i);
                        }
                    }
                }
                else{
                    notDivisible = true;
                }
                break;
            }
        }

        Collections.sort(divisibleByAll);
        if(!divisibleByAll.isEmpty())
                System.out.println(divisibleByAll.get(0));
    }
}
