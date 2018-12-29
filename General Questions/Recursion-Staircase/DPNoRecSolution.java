import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

  //this solution is a slight modification of the fibonacci sequence, no recursion needed, as we just use dynamic
  //programming. we use memoization in our array ar, so as to not recompute values every time
    static int stepPerms(int n) {
      int[] ar = new int[n];

      if(n == 1) {return 1;}
      if(n == 2) {return 2;}
      if(n == 3) {return 4;}
      ar[0] = 1;
      ar[1] = 2;
      ar[2] = 4;

      for(int i = 3; i < n; i++)
      {
        ar[i] = ar[i-3] + ar[i-2] + ar[i-1];
      }

      return ar[n-1];

    }





    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int sItr = 0; sItr < s; sItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int res = stepPerms(n);

            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
