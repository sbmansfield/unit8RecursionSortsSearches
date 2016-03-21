import java.util.Scanner;
import java.io.File;

/**
 *  Write a program that asks the user for a file name and prints the 
 *  number of characters, words, and lines in that file.
 */
public class WC
{
    private int countChar = 0;
    private int countWords = 0;
    private int countLines = 0;
    
    public WC()
    {
        
    }
    
    public void readFile()
    {
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();
        
        File inputFile = new File(fileName);
        Scanner in = new Scanner(inputFile);
        
    }
}
