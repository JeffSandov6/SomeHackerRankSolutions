import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    //this is a 2-sum problem, O(n)
    static void whatFlavors(int[] cost, int money) {
      //this map will store the cost and the iterator
      HashMap<Integer, Integer> costMap = new HashMap<Integer, Integer>();
      //this list will hold all of the costs
      List<Integer> costArr = new ArrayList<Integer>();

      int id1 = -1, id2 = -1;
      for(int i = 0; i < cost.length; i++)
      {
        int currCost = cost[i];
        //complement is the remaining $ needed to spend all
        int complement = money - currCost;

        if(costMap.containsKey(complement))
        {
          id1 = costMap.get(complement);
          id2 = i+1;
          break;
        }
        costMap.put(currCost, i+1);
      }
      
      System.out.println(id1 + " " + id2);
    }




    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int money = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] cost = new int[n];

            String[] costItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int costItem = Integer.parseInt(costItems[i]);
                cost[i] = costItem;
            }

            whatFlavors(cost, money);
        }

        scanner.close();
    }
}
