import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        long tripletsCount = 0L;
        //this map will hold possible second triplet values
        HashMap<Long, Long> trip2Map = new HashMap<Long, Long>();
        //this map will hold possible third triplet values
        HashMap<Long, Long> trip3Map = new HashMap<Long, Long>();

        for(long currNum : arr)
        {
            //if this number is in our trip3Map already, add its current count
            //in the trip 3 to our total count
            tripletsCount += trip3Map.getOrDefault(currNum, 0L);

            if(trip2Map.containsKey(currNum))
            {
                //if our currNum is part of trip2Map, add its value multiplied by r
                //to trip 3 map, updating its count if it already exists
                trip3Map.put(
                    currNum*r, trip3Map.getOrDefault(
                        currNum*r, 0L) + trip2Map.get(currNum));
            }
            //add this current numbers value multiplied by r to our trip2Map
            //as this value can of course possibly be part of a triplet,
            //or update its value if it already exists as a possible part of a triplet
            trip2Map.put(currNum*r, trip2Map.getOrDefault(currNum*r, 0L) + 1);

        }
        return tripletsCount;
    }






    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
