import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the largestRectangle function below.
    static long largestRectangle(int[] h) {

      int max = 0;
      //stack will hold an integer representing the index of the elements position
      //int the h (height) array
      Stack<Integer> stack = new Stack<>();

      int stackTop;
      int areaWithStackTop;
      int i = 0, n = h.length;

      while(i < n)
      {
        if(stack.empty() || h[stack.peek()] <= h[i])
        {  
          //if stack is empty or the curr height is greater than or equal to the height
          //of the last elem added to stack, add this elem to stack
          stack.push(i);
          i++;
          continue;
        }

        //if the prev element's height was bigger, keep removing those and checking
        //the area until we empty the stack or get to an elem whose height is smaller

        stackTop = stack.pop();

        //the area achieved at the previous elem is equal to that elems height *
        //current i (iteration) if the stack is empty, or
        //i - lastElemInStacksIndex - 1
        areaWithStackTop = h[stackTop] * (stack.empty() ? i : i - stack.peek() - 1);

        //this indicates that the top elem we removed combines to an area from the
        //current iteration all the way down (all elems can combine at this height)
        //or its height can combine with i - stack.peek() - 1 total elements

        max = Math.max(max, areaWithStackTop);

      }

      //here we empty out our stack for any remaining elems in it, again using the 
      //same formulaas above
      while(!stack.empty())
      {
        stackTop = stack.pop();

        areaWithStackTop = h[stackTop] * (stack.empty() ? i : i - stack.peek() - 1);
        max = Math.max(max,areaWithStackTop);
      }

      return max;
    }
    
    
    
    

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] h = new int[n];

        String[] hItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int hItem = Integer.parseInt(hItems[i]);
            h[i] = hItem;
        }

        long result = largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
