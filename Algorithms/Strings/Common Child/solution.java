import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {
        //make it 1 extra longer so as to account for empty strings
        int[][] longestSubsequence = new int[s1.length() + 1][s2.length() + 1];


        for(int i = 1; i <= s1.length(); i++)
        {
            int s1Iter = i - 1;
            char s1Char = s1.charAt(s1Iter);
            for(int j = 1; j <= s2.length(); j++)
            {
                int s2Iter = j - 1;
                char s2Char = s2.charAt(s2Iter);
                if(s1Char == s2Char)
                {
                    longestSubsequence[i][j] = longestSubsequence[i-1][j-1] + 1;
                }
                else //they dont match
                {
                    longestSubsequence[i][j] = Math.max(
                        longestSubsequence[i][j-1], longestSubsequence[i-1][j]);
                }
            }
        }

        return longestSubsequence[s1.length()][s2.length()];
    }



    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
