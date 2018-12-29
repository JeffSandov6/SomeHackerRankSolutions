import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

  //this solution uses recursion and DP. it avoids a timeout by using a map as our cache (memoization)
    static int stepPerms(int n) {
      Map<Integer, Integer> cacheMap = new HashMap<Integer, Integer>();
      return staircase(n, cacheMap);
    }

    static int staircase(final int n, final Map<Integer, Integer> cacheMap){
      //first we put in our base cases of 1, 2, and 4
      cacheMap.put(1, 1);
      cacheMap.put(2, 2);
      cacheMap.put(3, 4);
      //then we check for n in our map, if it isn't yet in our map, we add it to our map
      //by adding (through recursing) the values in our map at n-1, n-2, and n-3
      //if n is much greater than 4, than we will recurse until we reach a base case
      //of n = 1, 2, or 3
      if (cacheMap.get(n) == null){
          cacheMap.put(n, staircase(n-1, cacheMap) + 
                      staircase(n-2, cacheMap) + 
                      staircase(n-3, cacheMap)
                  );
      }
      return cacheMap.get(n);
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
