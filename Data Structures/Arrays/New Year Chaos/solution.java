import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        int bribes = 0;

        //start from the end of the array, we first check to see if the curr
        //person's curr position is viable (cant be 3 less than its sticker value)
        for(int i = q.length - 1; i >= 0; i--) {
            int currPersonSticker = q[i];
            int currLinePosition = i + 1;

            //a persons sticker value can only be in 2 positions less than its
            //value in the line
            if(currPersonSticker - currLinePosition > 2)
            {
                System.out.println("Too chaotic");
                return;
            }

            //then we check for any bribes this person may have taken, starting from
            //the least possible position this person could have been at, had he
            //bribed 2 people himself (hence the max)
            //then we work our way all the way to this person's position in the line,
            //and check who has a greater value than this person, as this indicates
            //a bribe took place
            for(int j = Math.max(0, currPersonSticker - 2); j < i; j++)
            {
                int previousPersonSticker = q[j];
                if(previousPersonSticker > currPersonSticker)
                {
                    bribes++;
                }
            }
        }

        System.out.println(bribes);

    }








    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}
