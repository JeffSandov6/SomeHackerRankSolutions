import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


public class Solution {

    //this solution is based on the binary search principle, as it will reduce the number
    //of days in half at each iteration in the while loop. the while loop itself runs
    //in O(logn) time, whilst within the loop, our Arrays.stream action which returns
    //the productivity at each day in the iteration, runs in O(n) time. Thus, this
    //solution's time complexity is O(nlogn)

    static long minTime(long[] machines, long goal) {

      int n = machines.length;
      //this is our upper bound (longest time itll take is the slowest machine
      //being the only one working)
      long maxDays = goal * Arrays.stream(machines).min().getAsLong();
      //this is our lower bound (min number of days), for now
      long minDays  = maxDays / n; //this is our lower bou
      while(minDays < maxDays - 1) 
      {
        //get the mid day value of the 2
        long mid = (minDays + maxDays) / 2;

        //the stream here returns to us the total production for all machines by the 
        //current mid day
        if(goal > Arrays.stream(machines).map(i->mid/i).sum())
        {
          //if by the current mid day, our productivity still hasn't surpassed our goal,
          //then we increase our minDays to the value of mid, so that our next mid 
          //will be higher, thus increasing the productivity
          minDays = mid;
        } 
        else 
        {
          //if by the current mid day, all our productivity has surpassed our goal,
          //then decrease our maxDays to the value of mid, so that our next mid value
          //will be smaller, thus decreasing the productivity. this also lowers our
          //max days, which is what we return, the lowest possible max day value
          maxDays = mid;
        }

      }
      
      return maxDays;

    }



    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nGoal = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nGoal[0]);

        long goal = Long.parseLong(nGoal[1]);

        long[] machines = new long[n];

        String[] machinesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long machinesItem = Long.parseLong(machinesItems[i]);
            machines[i] = machinesItem;
        }

        long ans = minTime(machines, goal);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
