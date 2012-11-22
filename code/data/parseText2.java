import java.io.*;
import java.util.*;

public class parseText2 {

    public static void main(String args[]) {
        /*The following code will be given a directory of files containing
        decoded certificates from our own website crawler. They will be 
        organized as follows:
        
        Column 1: SHA Fingerprint
        Column 2: Unique ID number
        Column 3: IP Address
        Column 4: Public Modulus
        Column 5: The size of the Modulus
        Column 6: The Signature         
        
        The program will output a file in the directory that is comma dilineated
        between fields and records by new lines.*/
        
        //Enter the location of the files here
        String directoryLoc = "/home/adam/Desktop/decoded-certs";
        String wfileName = "formatted-certs-ec.csv";
        
        final File folder = new File(directoryLoc);
        int numFiles = folder.listFiles().length;
        String[] fileList = new String[numFiles];
        int fileNum = 0;
        
        //Populate the fileList array with the contents of each file
        try {
            FileWriter fstream = new FileWriter(wfileName, false);
            BufferedWriter out = new BufferedWriter(fstream);
            
            for (final File fileEntry : folder.listFiles()) {
                fileList[fileNum] = readFile(directoryLoc + "/" + 
                                    fileEntry.getName());
                out.write(parse(fileList[fileNum], fileNum));
                out.newLine();
                
                fileNum++;
            }
            
            out.close();
        } catch (Exception e) {
            System.out.println("*****************************\n" +
            "ERROR: The File crash on file " + (fileNum) + 
            "\n*****************************");
        }
    }
    
    public static void printArr(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
   
    public static String parse(String text, int fileNum) {
        /*This function will take as its input a line of text and strip
        away all the none-essential information and leave only the
        four pieces of information outlines in main.
        
        input: String textLine
            a String formatted in the style of evan carmi. Each field in the
            file is delineated with new lines and prefaced with a name
            of what that field is
        input: int fileNum
            an indentifier labeling the file so that it can be identified
            later. Each file will be given a unique ID.
            
        output: String retString
            a comma deliniated string with four values: 
                Column 1: SHA Fingerprint
                Column 2: Unique ID number
                Column 3: IP Address
                Column 4: Public Modulus
                Column 5: The size of the Modulus
                Column 6: The Signature         */ 
        
        String retString = "";
        
        retString += getSHA(text) + ",";
        retString += "ec" + fileNum + ",";
        retString += "1,";
        retString += getMod(text) + ",";
        retString += getModSize(text) + ",";
        retString += getSig(text);
        
        /*  1: 6 - "    Signature Algorithm: sha1WithRSAEncryption" 
            2: lineNum concattenated with "ec" + "___"
            3: no IP address
            4: 15: after "Modulus" and before "Exponent";
            5: line 14 after "public key"
            6: last lines*/   
        return retString;
    }
    
    public static String getSHA(String text) {
        /*This function will return the SHA fingerprint of the decoded 
        certificate.
        
        input: String text
            The full text of the file
        output: String
            The SHA fingerprint of the certificate*/
        
        int start = text.indexOf("Signature Algorithm: ");
        int end = text.indexOf("\n", start);
        
        //The value 21 is from the length of the string "Signature Alg..."
        return text.substring(start + 21, end); 
    }
    
    public static String getModSize(String text) {
        /*This function will return the size of the modulus of the decoded 
        certificate.
        
        input: String text
            The full text of the file
        output: String 
            The size of the modulus of the certificate*/
        
        int start = text.indexOf("Public-Key: ");
        int end = text.indexOf("\n", start);
        
        //The value 12 is from the length of the string "Public-Key: "
        //The +1 is to avoid the first parenthesis
        //The -4 is to avoid the "bit)"
        return text.substring(start + 12 + 1, end - 5); 
    } 
    
    public static String getSig(String text) {
        /*This function will return the SHA fingerprint of the decoded 
        certificate.
        
        input: String text
            The full text of the file
        output: String
            The signature of the certificate*/
        
        //Because there are two instances of the words "Signature Algorithm"
        //and we are looking for the second instance of the substring because
        //that is where the signature is found;
        int start = text.indexOf("Signature Algorithm: ");
        start = text.indexOf("Signature Algorithm: ", start + 21);
        start = text.indexOf("\n", start);
        
        //The +1 is to remove a newline
        return formatModulus(text.substring(start + 1, text.length())); 
    } 
    
    public static String getMod(String text) {
        /*This function will get the modulus from the file and format it
        
        input: String text
            the entire text of the file
        output: String modulus
            the formatted modulus of the certificate;   */
            
        int start = text.indexOf("Modulus:");
        int end = text.lastIndexOf("\n", text.indexOf("Exponent: "));
        
        //The +10 is for the length of the string "Modulus:"
        return formatModulus(text.substring(start + 10, end));
    }
    
    public static String formatModulus(String modulus) {
        /*This function will take as its input an unformatted modulus
        and format it to be a single line
        
        input: String modulus
            the unformatted modulus from evan's files
        output: String formattedMod
            the formatted modulus to be included in the final output*/        
        
        String formattedMod = "";
        String[] splitMod = modulus.split("\n");
        
        for (int i = 0; i < splitMod.length; i++) {
            formattedMod += splitMod[i].trim();
        }
        
        return formattedMod;
    }
    
    public static String readFile(String fileName) {
        /*This method will read a file of name "fileName" and return said
        file's contents.
        
        input: String fileName
            The name of the file to be read
        output: String retString
            The contents of the file in a string */
        String retString = "";
            
        try {            
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String line = in.readLine();
        
            while (line != null) {
                retString += "\n" + line;
                line = in.readLine();
            }
            in.close();
        } catch (Exception e) {
            System.out.println("*****************************\n" +
            "ERROR: The File crash on file " + fileName + 
            "\n*****************************");
        }
        return retString;               
    }
}





