import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    //this problem was listed under DP, but i didnt see the need to do it that way, so heres my O(n) solution
    //it involves 2 passes
    static long candies(int n, int[] arr) {

      int[] candyAmount = new int[n];
      candyAmount[0] = 1;

      int curCandyAmt = 1;
      //we first pass from left to right
      for(int i = 1; i < n; i++)
      {
        if(arr[i] > arr[i-1])
        {
          curCandyAmt++;
        }
        else //if arr[i] <= arr[i-1]
        {
          curCandyAmt = 1;
        }
        candyAmount[i] = curCandyAmt;
      }

      curCandyAmt = 1;
      long minCandies = candyAmount[n-1];
      //then we do a pass from right to left
      for(int i = n-2; i >= 0; i--)
      {
        if(arr[i] > arr[i+1])
        {
          curCandyAmt++;
        }
        else // arr[i] <= arr[i+1]
        {
          curCandyAmt = 1;
        }
        //the number of candies this kid will get is the highest amount we found, either
        //the candies value in the first pass, or our curCandyAmt value
        candyAmount[i] = (candyAmount[i] > curCandyAmt) ? candyAmount[i] : curCandyAmt;
        minCandies += candyAmount[i];
      }

      return minCandies;
    }







    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        long result = candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
