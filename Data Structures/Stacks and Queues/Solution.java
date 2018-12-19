import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String isBalanced(String s) {

      // //a stack for right bracket items (stack is LIFO)
      Stack<Character> leftBracketStack = new Stack<Character>();
      
      for(int i = 0; i < s.length(); i++)
      {
        char cur = s.charAt(i);
        //if the current char is a left-side bracket (these are their ASCII values)
        if(cur == 40 || cur == 91 || cur == 123)
        {
          leftBracketStack.push(cur);
          continue;
        }
        //everything below here handles the case of right-side brackets

        //if the left bracket stack is empty, this means we have a right sided
        //bracket w no left side
        if(leftBracketStack.isEmpty())
        {
          return "NO";
        }

        //if there are elems in the stack, the last elem added to this stack should
        //be the complementary left-side bracket of the current right-side bracket
        //subtracting the ASCII values of right - left always yield 2 or 1 if the 
        //left bracket is the right sides complementary bracket 
        char prevLeft = leftBracketStack.pop();
        int result = cur - prevLeft;

        if(result == 1 || result == 2)
        {
          continue;
        }
        else
        {
          return "NO";
        }

      }

      //this checks the special case of, say, our input string is ((
      //for this case, stack would not be empty, thus we would return no
      if(leftBracketStack.isEmpty())
      {
        return "YES";
      }
      else
      {
        return "NO";
      }

    }
    
    
    
    
    

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
