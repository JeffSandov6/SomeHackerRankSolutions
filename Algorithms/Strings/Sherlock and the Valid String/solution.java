import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isValid function below.
    static String isValid(String s) {

      HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
      for(char c : s.toCharArray())
      {
        charMap.put(c, charMap.getOrDefault(c, 0) + 1);
      }

      HashMap<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
      for(Map.Entry<Character, Integer> curEntry : charMap.entrySet())
      {
        int curFreq = curEntry.getValue();
        freqMap.put(curFreq, freqMap.getOrDefault(curFreq, 0) + 1);
      }

      if(freqMap.size() > 2)
      {
        return "NO";
      }
      if(freqMap.size() == 1)
      {
        return "YES";
      }

      List<Integer> keys = new ArrayList<Integer>(freqMap.keySet());
      int maxFreq = Math.max(keys.get(0), keys.get(1));
      int maxFreqCount = freqMap.get(maxFreq);

      int minFreq = Math.min(keys.get(0), keys.get(1));
      int minFreqCount = freqMap.get(minFreq);

      if(maxFreqCount > 1 && minFreqCount > 1)
      {
        return "NO";
      }
      
      if(maxFreqCount == 1)
      {
        if(maxFreq - minFreq == 1)
        {
          return "YES";
        }
        else
        {
          return "NO";
        }
      }

      if(minFreq == 1 && minFreqCount == 1)
      {
        return "YES";
      }
      return "NO";
    }
    
    
    
    

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
