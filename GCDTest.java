/**
 * Matt Adelman.
 * GCD Test
 */
import java.util.Random;
import java.math.BigInteger;
import java.lang.Long;

public class GCDTest {

    public static void main(String[] args) {

        // For creating random primes
        Random rand = new Random();

        // Bit length to test
        int bits = 1024;

        // arrays of big ints
        BigInteger[] num1 = new BigInteger[100];
        BigInteger[] num2 = new BigInteger[num1.length];
        // GCD array
        BigInteger[] data = new BigInteger[num2.length];

        BigInteger big;

        // Uses the same big int for num1[i] and num2[i+1] therefore
        // we only need to generate num1.length big ints
        for (int i = 0; i < num1.length - 1; i++) {
            big = BigInteger.probablePrime(bits, rand);
            num1[i] = big;        
            num2[i + 1] = big;
        }
        // The last and first ones
        big = BigInteger.probablePrime(bits, rand);
        num1[num1.length - 1] = big;
        num2[0] = big;

        // Timing of gcd
		Long startTime = new Long(System.currentTimeMillis());
        for (int i = 0; i < num1.length; i++) {
            data[i] = num1[i].gcd(num2[i]);
        }
        Long endTime = new Long(System.currentTimeMillis());
		System.out.println(endTime.intValue() - startTime.intValue());


    }

}
