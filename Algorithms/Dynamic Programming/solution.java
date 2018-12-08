import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {



    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {
        //set it to an extra value, so we can also check for either string being
        //empty
        //boolean arrays are initialized to all false
        boolean[][] validTable = new boolean[a.length() + 1][b.length() + 1];

        //this is set to true for the case that both strings are empty
        validTable[0][0] = true;

        boolean hasUppercase = false;
        //get validity of full string against the 0th index of b, which is the 
        //index that represents the instance where b is empty
        //so if b is empty, all values of a MUST be lowercase so that theyll only 
        //match if we remove all values of a
        for(int i = 1; i <= a.length(); i++)
        {
            int charIter = i - 1;
            //get current character in string a
            char aCurrCharValue = a.charAt(charIter);

            if(isUppercase(aCurrCharValue) || hasUppercase)
            {
                hasUppercase = true;
                validTable[i][0] = false;
            }
            else
            {
                validTable[i][0] = true;
            }
        }

        //now we start filling our table from the start of our string a
        for(int i = 1; i <= a.length(); i++)
        {
            int aIter = i - 1;
            char aCharValue = a.charAt(aIter);
            int aCapitalValue = aCharValue - 32;
            //we are matching the current character of string a vs all characters
            //in the string b to check for validity
            for(int j = 1; j <= b.length(); j++)
            {
                int bIter = j - 1;
                char bCharValue = b.charAt(bIter);
                int bIntValue = bCharValue;

                //if the chars are equal, set them valid table curr position 
                //equal to its previous value
                if(aCharValue == bCharValue)
                {
                    validTable[i][j] = validTable[i-1][j-1];
                    continue;
                }
                else
                {
                    //if its capital value is equal to b capital value
                    if(aCapitalValue == bIntValue)
                    {
                        //set it to true if either of the predeccesors were true
                        validTable[i][j] = validTable[i-1][j-1] || validTable[i-1][j];
                        continue;

                    }

                }
                //if b string is done, and a contains an uppercase, validity is false
                if(isUppercase(aCharValue))
                {
                    validTable[i][j] = false;
                }
                else //if a is a lowercase, then we can set it to its prev value
                {
                    validTable[i][j] = validTable[i-1][j];
                }
                

            }
        }

        if(validTable[a.length()][b.length()] == true)
        {
            return "YES";
        }
        else
        {
            return "NO";
        }

    }




    static boolean isUppercase(char character)
    {
        if(character <= 90 && character >= 65)
        {
            return true;
        }
        return false;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
