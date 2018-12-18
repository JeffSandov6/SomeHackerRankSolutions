import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the triplets function below.
    static long triplets(int[] a, int[] b, int[] c) {
      Arrays.sort(a);
      Arrays.sort(b);
      Arrays.sort(c);

      int aIter, cIter;
      aIter = cIter = 0;
      long numTriplets, numDiffA, numDiffC;
      numTriplets = numDiffA = numDiffC = 0;

      for(int bIter = 0; bIter < b.length; bIter++)
      {
        int curB = b[bIter];
        while(aIter < a.length && a[aIter] <= curB)
        {
          if(aIter == 0 || a[aIter] != a[aIter - 1])
          { //this ensures we only add distinct values
            numDiffA++;
          }
          aIter++;
        }

        while(cIter < c.length && c[cIter] <= curB)
        {
          if(cIter == 0 || c[cIter] != c[cIter - 1])
          {
            numDiffC++;
          }
          cIter++;
        }

        if(bIter == 0 || curB != b[bIter - 1])
        {
          numTriplets += numDiffA * numDiffC;
        }
      }
      
      return numTriplets;
    }






    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] lenaLenbLenc = scanner.nextLine().split(" ");

        int lena = Integer.parseInt(lenaLenbLenc[0]);

        int lenb = Integer.parseInt(lenaLenbLenc[1]);

        int lenc = Integer.parseInt(lenaLenbLenc[2]);

        int[] arra = new int[lena];

        String[] arraItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lena; i++) {
            int arraItem = Integer.parseInt(arraItems[i]);
            arra[i] = arraItem;
        }

        int[] arrb = new int[lenb];

        String[] arrbItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenb; i++) {
            int arrbItem = Integer.parseInt(arrbItems[i]);
            arrb[i] = arrbItem;
        }

        int[] arrc = new int[lenc];

        String[] arrcItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenc; i++) {
            int arrcItem = Integer.parseInt(arrcItems[i]);
            arrc[i] = arrcItem;
        }

        long ans = triplets(arra, arrb, arrc);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
