import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        
        int numNotifs = 0;

        //we will use counting sort to solve this. 201 is the size bc 200
        //is the max possible value of any particular expenditure
        int[] countSortArr = new int[201];

        
        //adding the expenditures for the first d days to our data array
        for(int i = 0; i < d; i++)
        {
            countSortArr[expenditure[i]]++;
        }

        for(int i = d; i < expenditure.length; i++)
        {
            double median = getMedian(countSortArr, d);

            if(expenditure[i] >= 2*median)
            {
                numNotifs++;
            }

            //add this days expenses to our count sort array
            countSortArr[expenditure[i]]++;
            //remove the expense for the i (currDay) - d (numDays) days ago from our
            //countSortArr as we no longer need it 
            countSortArr[expenditure[i-d]]--;
        }        


        return numNotifs;

    }

    public static double getMedian(int[] dataArr, int days)
    {
        if(days % 2 == 0) //if we have an even number of days to account for
        {
            return evenNumDaysMedian(dataArr, days);
        }
        else //odd number of days to account for
        {
            return oddNumDaysMedian(dataArr, days);
        }
        
    }



    private static double oddNumDaysMedian(int dataArr[], int days)
    {
        int count = 0;
        double median = 0;
        for(int i = 0; i < dataArr.length; i++)
        {
            //count numbers at this position of our dataArr
            count += dataArr[i];
            //if we've accounted for more than half of the days, then
            //the current number is our median
            if(count > days/2)
            {
                median = i;
                break;
            }
        }
        return median;
    }


    private static double evenNumDaysMedian(int dataArr[], int days)
    {
        Integer medianOne = null;
        Integer medianTwo = null;
        //count keeps track of the count of days we've iterated past
        int count = 0;
        for(int i = 0; i < dataArr.length; i++)
        {
            //count numbers at this position of our dataArr
            count += dataArr[i];
            //this will get our first median once we have days/2 amount counted
            if(medianOne == null && count >= days/2)
            {
                medianOne = i;
            }
            //this will get our second median once we have days/2+1 amount counted
            if(medianTwo == null && count >= days/2 + 1)
            {
                medianTwo = i;
                break;
            }
        }

        return (medianOne + medianTwo)/2.0;

    }






    

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
