/*
Adam Forbes
RSA Project
*/


import java.math.*;
import java.util.Arrays;

public class statTest {
    public static void main(String[] args) {
        //The following program is written to figure out the chances of getting
        //no pairs in our smaller set of moduli collected.
        //Lenstra et al. used 6 386 984 moduli and found 14901 distinct primes
        int numTests = 10000;
        int lenstraSetSize = 6386984;
        int numberOfPairs = 14901*2;
        int ourSetSize = 105984;
        int[] numPairs = new int[numTests];
        for (int i = 0; i < numTests; i++) {
            int[] lenstraSet = new int[lenstraSetSize];
            populateZero(lenstraSet);
            populatePairs(lenstraSet, numberOfPairs);
            
            int[] ourSet = new int[ourSetSize];
            populateSubset(lenstraSet, ourSet);
            
            numPairs[i] = numPairs(ourSet);
            
            if (i % 100 == 0) {
                System.out.println("Test number: " + i + " of " + numTests);
            }
        }
        
        double sum = 0;
        for (int i = 0; i < numTests; i++) {
            sum += numPairs[i];
        }
        
        System.out.println("Average number of pairs: " + (sum/numTests));
    }
    
    public static void populatePairs(int[] arr, int numPairs) {
        //We will use the following notation:
        //0: an unbroken moduli
        //k: a broken moduli p1
        //-k: the pair of p1
        
        //we don't want to use 0 here because our default slot is 0!
        for (int i = 1; i <= numPairs; i++) {
            int p1 = (int)(Math.random() * arr.length);
            int p2 = (int)(Math.random() * arr.length);
            
            while (arr[p1] != 0) {
                p1 = (int)(Math.random() * arr.length);
            }
            while (arr[p2] != 0 && p2 != p1) {
                p2 = (int)(Math.random() * arr.length);
            }
            //Now we have chosen two empty spots the pair
            
            arr[p1] = i;
            arr[p2] = -i;
        }
    }
    
    public static void populateSubset(int[] arr, int[] subArr) {
        for (int i = 0; i < subArr.length; i++) {
            //Because the array is populated at random we can assume
            //that by choosing the first subArr.length number of 
            //moduli we are getting a random sample
            subArr[i] = arr[i];
        }
    }
    
    public static void populateZero(int[] arr) {
        for (int i = 0; i < arr.length; i++){
            arr[i] = 0;
        }
    }
    
    public static int numPairs(int[] arr) {
        //this method will check for the number of pairs in an array
        int[] sortedArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sortedArr);
        int numPairs = 0;
        
        //in order to check for pairs we need only to itterate from the start
        //of the sorted array to the beginning of the 0s, each time checking
        //if there exists a match to the negative number in the positive side
        //A custom built sorting algorithm would probably be the most efficient
        //method to do this... but time constraints have always been a problem
        int i = 0;
        while (sortedArr[i] < 0) {
            //if the search is < 0 then there is no pair!!
            if (Arrays.binarySearch(sortedArr, sortedArr[i]*-1) > 0) {
                numPairs++;
            }
            i++;
        }
        
        return numPairs;
    }
    
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {  
            if (i % 20 != 0) {
                System.out.print("|" + arr[i]);
            }
            else {
                System.out.println();
            }
        }
        System.out.println();
    }
}







