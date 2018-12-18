import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


//this solution involves creating a DayProd type and creating a comparator for it, so as to use and 
//store the different objects in a priorityQueue.
//this solution does return the correct answer, but it fails some test cases due to timeout. however, i am saving this here
//so that i can reference this, should i ever need to work with a priority queue of objects



class DayProd
{
  public long curDay;
  public long parent;
}

class CompareDay implements Comparator<DayProd> {

  public int compare(DayProd day1, DayProd day2)
  {
    if(day1.curDay > day2.curDay)
    {
      return 1;
    }

    return -1;
    // return day1.curDay.compareTo(day2.curDay);
  }
}



public class Solution {

    // Complete the minTime function below.
    static long minTime(long[] machines, long goal) {

      long days = 0;
      long curProd = 0;
      HashMap<Long, Integer> dayMap = new HashMap<Long, Integer>();

      // ArrayList<DayProd> dayList = new ArrayList<DayProd>();


      for(long day : machines)
      {
        dayMap.put(day, dayMap.getOrDefault(day, 0) + 1);
      }

      Comparator<DayProd> comparator = new CompareDay();
      Queue<DayProd> daysPriorityQueue = new PriorityQueue<>(dayMap.size(), comparator);

      for(Map.Entry<Long, Integer> curEntry : dayMap.entrySet())
      {
        long day = curEntry.getKey();
        int count = curEntry.getValue();

        DayProd cur = new DayProd();
        cur.curDay = day;
        cur.parent = day;

        daysPriorityQueue.add(cur);

        // dayList.add(cur);
      }


      while(curProd < goal)
      {
        // DayProd today = dayList.get(0);
        // dayList.remove(0);
        DayProd today = daysPriorityQueue.poll();

        days = today.curDay;

        curProd += dayMap.get(today.parent);


        today.curDay += today.parent;

        System.out.println(days + " " + curProd + " " + today.parent);

        // if(today.curDay <= dayList.get(0).curDay)
        // {
        //   dayList.add(0, today);
        //   continue;
        // }

        daysPriorityQueue.add(today);

        // dayList.add(today);
      }

      return days;


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
