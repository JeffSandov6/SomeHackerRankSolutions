import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumPasses function below.
    static long minimumPasses(long m, long w, long p, long n) {
        
        if(m>=n || w>=n || (double) m*w >= n) 
        {
          return 1;
        }

        // if(p>=n)
        // {
        //   return (long) Math.ceil((double)n / (double)(m*w));
        // }

        long minPass = Long.MAX_VALUE;
        long curPass = 0;
        long production = 0;
        while(true)
        {
          long remainingPass = divideToCeil(n - production, m * w);
          minPass = Math.min(minPass, curPass + remainingPass);

          if(remainingPass == 1)
          {
            break;
          }

          if(production < p)
          {
            long extraPass = divideToCeil(p - production, m*w);
            curPass += extraPass;
            production += extraPass * m * w;

            if(production >= n)
            {
              minPass = Math.min(minPass, curPass);

              break;
            }

          }

          production -= p;

          if(m <= w)
          {
            m++;
          }
          else
          {
            w++;
          }

        }
        return minPass;
    }





    static long divideToCeil(long x, long y)
    {
      return x / y + (x % y == 0 ? 0 : 1);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] mwpn = scanner.nextLine().split(" ");

        long m = Long.parseLong(mwpn[0]);

        long w = Long.parseLong(mwpn[1]);

        long p = Long.parseLong(mwpn[2]);

        long n = Long.parseLong(mwpn[3]);

        long result = minimumPasses(m, w, p, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
