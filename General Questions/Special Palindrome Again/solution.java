import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {

        //all single chars are palindromes by default
        int palindromeCount = n;
        
        //current sequence starts at 0 by default
        int currSeqCount = 1;

        //this will keep track of where our middle index's position is, if
        //applicable, else it will just be -1
        int midIndex = -1;

        for(int i = 1; i < s.length(); i++)
        {
            char currChar = s.charAt(i);

            if(s.charAt(i-1) == currChar)
            {
                //this handles condition 1, and we add to palindrome count
                //the current sequence count (aa should add 1, aaa should add 2)
                palindromeCount += currSeqCount;
                currSeqCount++;

                //check condition 2, all chars the same except middle 
                if(midIndex > 0) //ensures one mid char exists
                {
                    //abaa,1-2=-1 | aabaa, 2-2=0 a == a| cabaaa, 2-3 = 1, a == a  
                    if((midIndex - currSeqCount) >= 0 && s.charAt(midIndex-currSeqCount) == currChar)
                    {
                        palindromeCount++;
                    }
                    else
                    {
                        //abaa, reset midIndex to -1 (no middle index), as this
                        //midIndex can no longer be part of a special palindrome
                        midIndex = -1;
                    }

                }
            }
            else //current char doesn't match prev char
            {
                currSeqCount = 1;
                //check for 3 char symmetry, aba, aaba
                if((i-2 >= 0) && s.charAt(i-2) == currChar)
                {
                    palindromeCount++;
                    //set the middle index's position    
                    midIndex = i - 1;
                }
                else
                {
                    //if no symmetry, cba, aaab
                    //set this to -1, which means neg index doesn't exist
                    midIndex = -1;
                }
            }

        }

        return palindromeCount;
    }






    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
