package src.main.java.euler_8;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class App 
{
    public static void main( String[] args )
    {
        Long inputValue = 13529989054354232L;
        //String inputValue = "13529989054634235423424242L";
        int numAdjacentValues = 4;
        int outputProduct = 0;

        outputProduct = findNthAdjacentProduct(inputValue, numAdjacentValues);
        System.out.println(outputProduct);
    }

    private static int findNthAdjacentProduct(Long input, int numAdj){
        //Long inputStrAsLong = Long.parseLong(new String(input));
        //List<Integer> listOfValues = new ArrayList<Integer>();
        //char[] inputStrAsChar = input.toCharArray();
        //for(int i = 0; i < input.length(); i++){
        //    listOfValues.add(Integer.parseInt(inputStrAsChar[i]));
        //}
        String inputAsString = input.toString();
        char[] values = inputAsString.toCharArray();
        List<Integer> listOfValues = new ArrayList<Integer>();
        
        //initially populate
        char[] subset = new char[numAdj];
        for(int i = 0; i < numAdj; i++){
            subset[i] = values[i];
        }
        int subsetAsInt = Integer.parseInt(new String(subset));

        
        //for(int j = 0; j < (values.length - numAdj); j++){
        //    listOfValues.add(Integer.parseInt(subsetAsStr));
        //}

        return calcProduct(subsetAsInt);
    }

    private static int calcProduct(Integer inputValue){
        int product = 0;
        char[] inputValueAsCharArray = inputValue.toString().toCharArray();
        for(int i = 0; i < inputValueAsCharArray.length; i++){
            product *= inputValueAsCharArray[i];
        }
        return product;
    }
}
