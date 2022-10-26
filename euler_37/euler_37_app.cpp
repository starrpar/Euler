
#include <iostream>
#include <vector>
#include <string>
#include <list>
#include <cmath>

using namespace std;

//Euler 37 - problem statement:
// The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right,
// and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.
// Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
// NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.

std::list<long> findAllPrimesBelow(long limit){
    std::list<long> primes;
    bool divisible = false;

    for(long i = 2; i < limit; i++){
        divisible = false;
        for (int j = 2; j < i; j++){
            if((i%50000 == 0) && (j%50000 == 0)){
                cout << "i, j: " << i << "::" << j;
            }
            if (i%j == 0){
                divisible = true;
                continue;
            }
        }
        if (!divisible){
            primes.push_back(i);
            divisible = false;
            continue;
        }
    }
    return primes;
}

bool isPrime(long value){

    if(value < 2){
        return false;
    }

    bool divisible = false;

    for (int j = 2; j < value; j++){
        if (value%j == 0){
            divisible = true;
            return false;  
        }
        else {
            continue;
        }
    }
    if (!divisible){
        return true;
    }
    return false;
}

bool testIfBoolFullyTruncatableRtoL(long currentValue){
    if (currentValue == 0){
        return true;
    }

    bool retVal = false;

    //truncate R-to-L
    if(isPrime(currentValue)){
        //cout << "Before: " << currentValue << endl;
        currentValue = currentValue/10;
        //cout << "After: " << currentValue << endl;
        retVal = testIfBoolFullyTruncatableRtoL(currentValue);
    }
    else {
        return false;
    }
    return retVal;
}

bool testIfBoolFullyTruncatableLtoR(long currentValue){
    if (currentValue < 10 && (isPrime(currentValue) || currentValue == 1)){
        return true;
    }

    bool retVal = false;

    //truncate L-to-R
    if(isPrime(currentValue)){
        double valAsDbl = (double)currentValue;

        int amtToDivideBy = 1;

        if(log10(valAsDbl) > 0){
            int numDigits = ceil(log10(valAsDbl));
            int multiple = 10;
            for(int i = 1; i < numDigits; i++){
                amtToDivideBy = amtToDivideBy * multiple;
            }

            //cout << "Before: " << currentValue << endl;
        }

        if(amtToDivideBy > 1){
            currentValue = currentValue%amtToDivideBy;
            //cout << "After: " << currentValue << endl;
            retVal = testIfBoolFullyTruncatableLtoR(currentValue);
        }
    }
    else {
        return false;
    }
    return retVal;
}

int main()
{
    string s = "Hello World";
    cout << s << endl;
    int max = 10000000;
    std::list<long> primesFound;
    std::list<long> primesPotentiallyTruncatable;
    std::list<long> primesPotentiallyTruncatableToPrint;
    std::list<long> primesTruncatable;
    primesFound = findAllPrimesBelow(max);
    cout << primesFound.size() << endl;

    for(int i = 0; i < primesFound.size(); i++){
        long currentPrime = primesFound.front();
        //cout << currentPrime << endl;
        primesFound.pop_front();

        if(testIfBoolFullyTruncatableRtoL(currentPrime)){
            primesPotentiallyTruncatableToPrint.push_front(currentPrime);
            primesPotentiallyTruncatable.push_front(currentPrime);
        }
    }

    cout << "Primes potentially truncatable: " << primesPotentiallyTruncatableToPrint.size() << endl;
    while(primesPotentiallyTruncatableToPrint.empty()){
        //cout << primesPotentiallyTruncatableToPrint.front() << endl;
        primesPotentiallyTruncatableToPrint.pop_front();
    }

    for(int i = 0; i < primesPotentiallyTruncatable.size(); i++){
        long currentPrime = primesPotentiallyTruncatable.front();
        //cout << currentPrime << endl;
        primesPotentiallyTruncatable.pop_front();

        if(testIfBoolFullyTruncatableLtoR(currentPrime)){
            primesTruncatable.push_front(currentPrime);
        }
    }

    cout << "Primes truncatable: " << primesTruncatable.size() << endl;
    while(!primesTruncatable.empty()){
        cout << primesTruncatable.front() << endl;
        primesTruncatable.pop_front();
    }
}


/*    private static List<Long> findAllPrimesBelow(long limit) {
        ArrayList<Long> primes = new ArrayList<Long>();
        Boolean divisible = false;
        for (long i = 2; i < limit; i++) {
            divisible = false;
            for (int j = 2; j < i; j++) { // < vs. <= because dividing primes by themselves will eliminate them from selection
                if((i%50000 == 0) && (j%(50000) == 0)){
                    System.out.println("i,j: " + i + " :: " + j);
                }
                if (i % j == 0) {
                    divisible = true;
                    continue;
                }
            }
            if (!divisible) {
                primes.add(i);
                divisible = false;
                continue;
            }
        }
        return primes;
    }
*/