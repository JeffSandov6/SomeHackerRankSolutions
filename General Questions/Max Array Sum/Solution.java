import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] arr) {
        //in this example we will just overwrite the array given to us but if that
        //were an issue, we would just create an exact copy of this array


        if(arr.length == 0) {return 0;} 
        arr[0] = Math.max(0, arr[0]); //this accounts for the empty subset

        if(arr.length == 1) {return arr[0];}
        arr[1] = Math.max(arr[0], arr[1]); //memoization within the array itself
        
        for(int i = 2; i < arr.length; i++) 
        {
            //at every value of the array, we update its value to the optimal value
            arr[i] = Math.max(arr[i - 1], arr[i] + arr[i - 2]);
            //the 2 possibilities of optimal max up to this i are 
            //1. max is still the same as it was in the previous i
            //2. max of this arr[i] + max from 2 i's ago (arr[i - 2]) is greater
            //than the one of the previous i
            //we are storing the max i at every point. that way we can check for 
            //the i of previous 2 iterations
        }

        return arr[arr.length - 1];
    }





    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
