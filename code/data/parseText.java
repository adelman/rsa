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
                System.out.println(parse(line, lineNum) +"\n");
                //TESTING TESTING
                
                //We will write the current line to the output file
                //writeLine(parse(line));
                
                //Itterate the lineNum variable
                lineNum++;
            }
            
            br.close();            
        } catch (Exception e) {
            System.out.println("************\nError! Oh no\n");
        }
    }
    
    public static String parse(String text, int lineNum) {
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
        
        //The text cannot be parsed on commas because there are certain fields
        //(indentification data) that include commas between quotes meaning
        //their input is a string. There are two types of fields, strings 
        //inclosed in quotes and "\N". We resolve it as follows
        String temp = text.replace("\\N", "\"\\N\"");
        //By splitting the text on quotes we generate an array of twice the 
        //size of the original input
        String[] splitLineReplaced = temp.split("\"");
        
        String[] splitLine = new String[70];
                        
        int a = 1;
        
        //Here the quote deliniated array will only select the text inside 
        //the quotes
        for (int i = 0; i < splitLine.length; i++) {
            splitLine[i] = splitLineReplaced[a];
            a+=2;
        }
        
        //And finally we put together the information we care about
        String retString = lineNum + "," + splitLine[9] + "," + splitLine[2] +
                           "," + splitLine[14].replace(",", "|");        
        
        return retString;
    }
    
    public static void writeLine(String textLine) {
        /*This method will take a line of formatted text and write it 
        to the final output file*/
    }
}





