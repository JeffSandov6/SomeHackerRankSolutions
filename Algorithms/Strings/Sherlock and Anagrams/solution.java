import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        HashMap<String, Integer> substringMap = new HashMap<String, Integer>();
        int numAnagrammaticPairs = 0;

        for(int i = 0; i < s.length(); i++)
        {
            for(int j = i + 1; j <= s.length(); j++)
            {
                //.substring params are (inclusive, exclusive)
                String currSubstring = s.substring(i, j);
                //turn every substring to a char array and sort them
                char[] currSubAsCharArray = currSubstring.toCharArray();
                Arrays.sort(currSubAsCharArray);
                //get the sorted array back into currSubstring
                currSubstring = String.valueOf(currSubAsCharArray);

                //get the number of times this substring has appeared in the string
                //by checking our map
                int numMatchingSubstrings = substringMap.getOrDefault(currSubstring, 0);

                //add that number to our annagramaticPairs, even if 0
                numAnagrammaticPairs += numMatchingSubstrings;
                
                //finally, update the map value for this substring
                substringMap.put(currSubstring, numMatchingSubstrings + 1);
            }
        }

        return numAnagrammaticPairs;

    }







    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
