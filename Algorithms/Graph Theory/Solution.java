import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

  private static boolean[] visited;
  private static ArrayList<Integer>[] adjacencies;

  // Complete the roadsAndLibraries function below.
  static long roadsAndLibraries(int numCities, long c_lib, long c_road, int[][] cities) {
    
    long totalCost = 0;
    
    //we create an array of ArrayLists of size cities + 1 (bc arrays start at 0
    //and this problem nodes start at 1)
    adjacencies = new ArrayList[numCities+1];

    //initialize the visited boolean array
    visited = new boolean[numCities + 1];
    //initialize all the array lists
    for(int i = 0; i <= numCities; i++)
    {
      adjacencies[i] = new ArrayList<Integer>();
    }

    for(int i = 0; i < cities.length; i++)
    {
      int city1 = cities[i][0];
      int city2 = cities[i][1];
      //add the adjacent cities to each cities' adjacencies list
      adjacencies[city1].add(city2);
      adjacencies[city2].add(city1);
    }


    for(int i = 1; i <= numCities; i++)
    {
      //if this city has already been visited, then skip it
      if(visited[i] == true)
      {
        continue;
      }
      //visit all of the cities that are part of this cities component (connected 
      //to this city through roads), so as to get all total components
      long numConnected = depthFirstSearch(i) - 1;
      totalCost += (numConnected * c_road);
      totalCost += c_lib;
    }

    return totalCost;
  }

  static long depthFirstSearch(int curCity)
  {
    //set the city visited as true
    visited[curCity] = true;
    long neighborCount = 0;
    for(int i = 0; i < adjacencies[curCity].size(); i++)
    {
      int adjacentCity = adjacencies[curCity].get(i);
      //if this cities cur adjacent city has been visited, skip it
      if(visited[adjacentCity] == true)
      {
        continue;
      }
      //if the next adjacent city hasn't been visited, continu
      neighborCount += depthFirstSearch(adjacentCity);
    }
    return neighborCount + 1;

  }



  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      int q = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      //q is number of queries
      for (int qItr = 0; qItr < q; qItr++) {
          String[] nmC_libC_road = scanner.nextLine().split(" ");
          
          int numCities = Integer.parseInt(nmC_libC_road[0]);

          int numRoads = Integer.parseInt(nmC_libC_road[1]);

          int c_lib = Integer.parseInt(nmC_libC_road[2]);

          int c_road = Integer.parseInt(nmC_libC_road[3]);


          int[][] cities = new int[numRoads][2];

          for (int i = 0; i < numRoads; i++) {
              String[] citiesRowItems = scanner.nextLine().split(" ");
              scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

              for (int j = 0; j < 2; j++) {
                  int citiesItem = Integer.parseInt(citiesRowItems[j]);
                  cities[i][j] = citiesItem;
              }
          }

          //if the cost of a library is less than or equal to cost of road
          if(c_lib <= c_road)
          {
            bufferedWriter.write(String.valueOf(c_lib*numCities));
            bufferedWriter.newLine();
            continue;
          }

          if(numCities == 1 || numRoads == 0)
          {
            bufferedWriter.write(String.valueOf(c_lib*numCities));
            bufferedWriter.newLine();
            continue;
          }

          long result = roadsAndLibraries(numCities, (long) c_lib, (long) c_road, cities);

          bufferedWriter.write(String.valueOf(result));
          bufferedWriter.newLine();
      }

      bufferedWriter.close();

      scanner.close();
  }
}
