import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import java.math.BigInteger;
import java.lang.Long;

public class SetInt {

    public static void main(String[] args) {
        
        Random rand = new Random();
        Set<BigInteger> table = new HashSet<BigInteger>(10000);
        BigInteger[] arr = new BigInteger[1000];
        
		Long startTime = new Long(System.currentTimeMillis());
        for (int i = 0; i < 1000; i++) {
            BigInteger big = BigInteger.probablePrime(512, rand);
            arr[i] = big;
        }
        Long endTime = new Long(System.currentTimeMillis());
		System.out.println(endTime.intValue() - startTime.intValue());


		Long startTime2 = new Long(System.currentTimeMillis());
        for (int i = 0; i < 1000; i++) {
            if (table.add(arr[i]) == false) {
                System.out.println(arr[i]);
            }
        }
        Long endTime2 = new Long(System.currentTimeMillis());
		System.out.println(endTime2.intValue() - startTime2.intValue());

    }
}
