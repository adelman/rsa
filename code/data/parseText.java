import java.io.*;
import java.util.*;

public class parseText {

    public static void main(String args[]) {
        /*The following program will take a file, defined by changing
        the fileName string, formatted in the style of the SSL observatory
        dump, parse it, and save a csv in the same folder. The outputted csv
        will have the following columns:
        
        Column 1: Line Number
        Column 2: Public modulus
        Column 3: SHA Fingerprint
        Column 4: Identifying information (column O)  */
        
        
        String fileName = "valid-certs500.csv";
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            int lineNum = 1;
        
            while (line != null) {
                line = br.readLine();
                
                //TESTING TESTING
                System.out.println(line);
                //TESTING TESTING
                
                //We will write the current line to the output file
                writeLine(pareMePlease(line));
                
                //Itterate the lineNum variable
                lineNum++;
            }
            
            br.close();            
        } catch (Exception e) {
            System.out.println("Error! Oh no");
        }
        
        
    }
    
    public static String parseMePlease(String textLine, int lineNum) {
        /*This method will take as its input a line of text and strip
        away all the none-essential information and leave only the
        four pieces of information outlines in main. Each line contains
        70 fields.
        
        input: String textLine
            a line of text with 70 comma delianted fields just asking to
            get their faces parsed.
        intput: int lineNum
            the line number that the string was found on
            
        output: String retString
            a comma deliniated string with four values: 
            1: Line Number
            2: Public modulus
            3: SHA Fingerprint
            4: Identifying information     */
            
        //splitLine is an array of size 70 
        String[] splitLine = textLine.split(",");
        
        String retString = (lineNum + "," + splitLine[10] + "," + 
            splitLine[2] + "," + splitLine[5])
        
        return retString;
        
        
    }
    
    public static void writeLine(String textLine) {
        /*This method will take a line of formatted text and write it 
        to the final output file*/
    }
}





