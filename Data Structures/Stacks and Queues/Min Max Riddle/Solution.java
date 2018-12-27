import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

  // Complete the riddle function below.
  static long[] riddle(long[] arr) {
    int[] lefts = buildLeft(arr);
    int[] rights = buildRight(arr);

    SortedMap<Long, Integer> numToLen = new TreeMap<>(Collections.reverseOrder());
    //numToLen map holds the numbers in our array, and the maximum length that they span in the array, where they are the
    //smallest number, whether to its left or right. this map is also sorted so that the key-value pairs with the highest
    //key value are the front
    for (int i = 0; i < arr.length; i++) {
      numToLen.put(arr[i], Math.max(numToLen.getOrDefault(arr[i], -1), lefts[i] + rights[i] + 1));
    }

    Iterator<Long> iter = numToLen.keySet().iterator();
    long number = iter.next();
    long[] result = new long[arr.length];
    
    for (int i = 0; i < result.length; i++) {
      //while the max length that the current number spans is less than the current iteration (i), we must iterate to the 
      //next possible number that spans a length greater than the current i value
      //if the current number already does span a length greater than the current i value, then just add this number to
      //the result array
      while (numToLen.get(number) <= i) {
        number = iter.next();
      }
      
      //here we add the current greatest number (key) that spans a length greater than current i value
      result[i] = number;
    }
    return result;
    
  }

  
  //buildLeft essentially returns an array that says, for some element in arr, say the elem in arr at index 2. 
  //for all lefts[2] elements to the left of me, i am the smallest
  static int[] buildLeft(long[] arr)
  {
    int[] lefts = new int[arr.length];
    Stack<Integer> indices = new Stack<>();

    for(int i = 0; i < arr.length; i++)
    {
      while(indices.empty() == false && arr[i] <= arr[indices.peek()])
      {
        indices.pop();
      }
      lefts[i] = i - (indices.empty() ? -1 : indices.peek()) - 1;
      indices.push(i);
    }

    return lefts;

  }

  
  //buildRight essentially returns an array that says, for some element in arr, say the elem in arr at index 2. 
  //for all right[2] elements to the right of me, i am the smallest.
  //if the value of right[i] is 0, then that means the elem to its right is smaller than the elem in arr[i]
  static int[] buildRight(long[] arr)
  {
    int[] rights = new int[arr.length];
    Stack<Integer> indices = new Stack<>();

    for(int i = arr.length - 1; i >= 0; i--)
    {
      while(indices.empty() == false && arr[i] <= arr[indices.peek()])
      {
        indices.pop();
      }
      rights[i] = (indices.empty() ? arr.length : indices.peek()) - i - 1;
      indices.push(i);
    }

    return rights;
  }
  
  
  
  
  
  
    // long[] windows = new long[arr.length];
    // int windowsIter = 0;

    // for(int i = 0; i < arr.length; i++)
    // {
    //   List<Long> windowMinimas = new ArrayList<Long>();

    //   long[] temp = Arrays.copyOfRange(arr, 0, i+1);

    //   long curWindomMin = Arrays.stream(temp).min().getAsLong();
    //   long prevSum = Arrays.stream(temp).sum();

    //   long curMax = curWindomMin;

    //   for(int j = i+1; j < arr.length; j++)
    //   {
    //     //temp = Arrays.copyOfRange(arr, j, j+i+1);
    //     long curSum = prevSum - arr[j-i-1] + arr[j];
    //     if(curSum >= prevSum)
    //     {          
    //       temp = Arrays.copyOfRange(arr, j-i, j+1);
    //       curWindomMin = Arrays.stream(temp).min().getAsLong();
    //       curMax = Math.max(curMax, curWindomMin);
    //     }

    //     prevSum = curSum;

    //   }
    //   windows[windowsIter] = curMax;
    //   windowsIter++;
    // }


    //return windows;
  //}
  
  
  
  

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(
      new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    long[] arr = new long[n];

    String[] arrItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      long arrItem = Long.parseLong(arrItems[i]);
      arr[i] = arrItem;
    }

    long[] res = riddle(arr);

    for (int i = 0; i < res.length; i++) {
      bufferedWriter.write(String.valueOf(res[i]));

      if (i != res.length - 1) {
          bufferedWriter.write(" ");
      }
    }

    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
