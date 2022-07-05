package euler_8;

import java.util.List;
import java.util.ArrayList;

public class App 
{
    public static void main( String[] args )
    {
        //Long inputValue = 13529989054354232L;
        //String inputValue = "13529989054634235423424242";
        String inputValue = "7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450";
        int numAdjacentValues = 13;
        long outputProduct = 0L;

        outputProduct = findNthAdjacentProduct(inputValue, numAdjacentValues);
        System.out.println(outputProduct);
    }

    private static long findNthAdjacentProduct(String input, int numAdj){
        char[] inputAsCharArray = input.toCharArray();
        List<Long> listOfProducts = new ArrayList<Long>();
        long largestProduct = 0L;
        int numLeadingZero = 0;

        //initially populate
        char[] subset = new char[numAdj];
        long subsetAsLong = 0L;
        int k = 0;
        long tmp = 0L;
        for(int i = 0; i < inputAsCharArray.length - numAdj; i++){
            k = 0;
            for(int j = i; j < i + numAdj; j++){
                subset[k++] = inputAsCharArray[j];
            }
            //handle special cases
            if(subset[0] == '0'){
                numLeadingZero++;
                continue;
            }

            subsetAsLong = Long.parseLong(new String(subset));
            //System.out.print(subsetAsLong);
            tmp = calcProduct(subsetAsLong);
            //System.out.println("------> " + tmp);
            listOfProducts.add(tmp);
        }
        System.out.println("Number of values skipped because of leading zeros: " + numLeadingZero);
        
        for(int n = 0; n < listOfProducts.size(); n++){
            long currentProduct = 0;
            if((currentProduct = listOfProducts.get(n)) > largestProduct)
                largestProduct = currentProduct;
        }
        return largestProduct;
    }

    private static long calcProduct(Long inputValue){
        long product = 1L;
        char[] inputValueAsCharArray = inputValue.toString().toCharArray();
        List<Long> inputValuesAsList = new ArrayList<Long>();
        for(int i = 0; i < inputValueAsCharArray.length; i++){
            inputValuesAsList.add(Long.parseLong(String.valueOf(inputValueAsCharArray[i])));
        }
        for(int j = 0; j < inputValuesAsList.size(); j++){
            product *= inputValuesAsList.get(j);
        }
        return product;
    }
}
