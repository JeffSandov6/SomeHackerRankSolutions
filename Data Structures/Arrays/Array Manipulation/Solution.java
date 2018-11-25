import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
        long[] arr = new long[n];

        for(int i = 0; i < queries.length; i++)
        {
            int leftSide = queries[i][0] - 1;
            int rightSide = queries[i][1] - 1;
            int valueToAdd = queries[i][2];

            //we only add the value to the first index it asks us to
            arr[leftSide] += valueToAdd;

            if(rightSide + 1 < arr.length)
            {
                //this the first index after the right side inclusive index that we
                //would be adding to
                arr[rightSide + 1] -= valueToAdd;
                //this will be used to let us know that all values from left side up to
                //the index before this one had a value added to them
            }
        }

        long maxValue = 0;
        long accumulatingSum = 0;
        for(int k = 0; k < arr.length; k++)
        {
            accumulatingSum += arr[k];
            if(accumulatingSum > maxValue)
            {
                maxValue = accumulatingSum;
            }
        }

        return maxValue;
    }





    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

