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

    static void updateHash(HashMap<Integer, Integer> map1, HashMap<Integer, Integer> map2, int query, int qValue)
    {
        
        if(map1.containsKey(qValue))
        {
            int oldCount = map1.get(qValue);

            if(query == 1) //insert
            {
                map1.put(qValue, oldCount + 1);
            }
            else // == 2, delete one occurrence
            {
                //if oldCount was 0, and we are being asked to delete, we dont need to 
                //do anything, so just return
                if(oldCount == 0) {return;}
                map1.put(qValue, oldCount - 1);
            }
            
            //add 1 to the map that holds our different count values
            int newCount = map1.get(qValue);
            map2.put(newCount, map2.getOrDefault(newCount, 0) + 1);           

            //subtract 1 from the old count value in our second map
            map2.put(oldCount, map2.get(oldCount) - 1);
        }
        else //if this isnt a key in map, add it to map
        {
            //if the key doesn't exist and we're asked to delete it,
            //we dont have to do anything, so return
            if(query == 2) {return;}
            map1.put(qValue, 1);
            //update map2
            map2.put(1, map2.getOrDefault(1, 0) + 1);           
        }
    }

    static List<Integer> freqQuery(int[][] queries) {
        List<Integer> outputs = new ArrayList<Integer>();
        //this map holds the integers in our data struct and their counts
        HashMap<Integer, Integer> intMap = new HashMap<Integer, Integer>();
        //this map holds the different count values of our integers
        HashMap<Integer, Integer> countMap = new HashMap<Integer, Integer>();

        for(int[] query : queries)
        {
            int currQuery = query[0];
            int queryValue = query[1];

            if(currQuery == 1 || currQuery == 2)
            {
                updateHash(intMap, countMap, currQuery, queryValue);
            }
            else //if == 3
            {
                if(countMap.containsKey(queryValue) && countMap.get(queryValue) > 0)
                {
                    outputs.add(1);
                }
                else
                {
                    outputs.add(0);
                }
            }
        }
        return outputs;
    }



    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(System.in))) {
            
            int q = Integer.parseInt(bufferedReader.readLine().trim());
            int[][] queries = new int[q][2];
           
            for (int i = 0; i < q; i++) {
                String[] query = bufferedReader.readLine().split(" ");
                queries[i][0] = Integer.parseInt(query[0]);
                queries[i][1] = Integer.parseInt(query[1]);
            }

            List<Integer> ans = freqQuery(queries);

            try (BufferedWriter bufferedWriter = new BufferedWriter(
                            new FileWriter(System.getenv("OUTPUT_PATH")))) {
                
                    bufferedWriter.write(ans.stream().map(Object::toString)
                                .collect(joining("\n")) + "\n");
                }
        }
    }

}
