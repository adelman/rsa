/*
 * Matt Adelman
 * RSA Final Project
 */

import java.math.BigInteger;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.Long;
import java.util.StringTokenizer;

public class HexParser {

    public static void main(String[] args) {

        int count = 0;
        // Public moduli
        BigInteger[] keys = new BigInteger[1002];
        // associated data
        String[][] data = new String[keys.length][4];

        try {
            // Getting the file
            String fileName = "/Users/Matt/COMP360/1000certs.csv";
            Scanner file = new Scanner(new FileReader(fileName));
            // Looping through the file
            for (int i = 0; i < keys.length; i++) {
                // Getting the line of the file with pertitant info
                String line = file.nextLine();
                // Break the line, because it's a CSV
                StringTokenizer lineScan = new StringTokenizer(line, ",");
                data[i][1] = lineScan.nextToken(); //SHA print
                data[i][0] = lineScan.nextToken(); //id number
                data[i][2] = lineScan.nextToken();  //ip address
                String modulus = lineScan.nextToken().replace(":", "");
                data[i][3] = lineScan.nextToken(); //size of modulus
                BigInteger big = new BigInteger(modulus, 16);
                keys[count] = big;
                count++;
            }

            // Timing
            Long startTime = new Long(System.currentTimeMillis());
            // Pairwise GCD of moduli
            for (int i = 0; i < count; i++) {
                for (int j = i + 1; j < count; j++) {
                    BigInteger gcd = keys[i].gcd(keys[j]);
                    BigInteger one = BigInteger.ONE;
                    // If the Moduli are equal we get no useful info
                    if (!(one.equals(gcd)) && !(keys[i].equals(keys[j]))) {
                        System.out.println(data[i][0] + ", " + data[j][0] + 
                        ", " + gcd + ", " + gcd.isProbablePrime(80) + ", " +
                        keys[i].mod(gcd) + ", " + keys[j].mod(gcd));
                        
                    }
                }
            }
            
		    Long endTime = new Long(System.currentTimeMillis());

		    System.out.println(endTime.intValue() - startTime.intValue());
            //System.out.println(count);
            file.close();
        }

		// Catching a FileNotFoundException exception
        catch (FileNotFoundException fnfe) {
            System.out.println("File was not found");
            System.exit(0);
        }
    }

}
