import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the findShortest function below.

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
     *
     */
  private static ArrayList<Integer>[] adjacencies;
  private static boolean[] visited;


  static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, long val) {

    //we hold our adjacencies in an array of ArrayLists (+1 bc we wont be using index 0)
    adjacencies = new ArrayList[graphNodes + 1];
    adjacencies[0] = new ArrayList<Integer>();

    visited = new boolean[graphNodes + 1];

    //initialize all the array lists within the adjacencies array and check to see if we
    //have at least 2 pairs
    int numCorrectColors = 0;
    for(int i = 1; i <= graphNodes; i++)
    {
      adjacencies[i] = new ArrayList<Integer>();
      //this will check to see if we have a valid number of pairs
      if(ids[i-1] == val)
      {
        numCorrectColors++;
      }
    }

    //checks for the case that there aren't any possible pair combos
    if(numCorrectColors < 2)
    {
      return -1;
    }

    //add the adjacencies for our graph
    for(int i = 0; i < graphFrom.length; i++)
    {
      int node1 = graphFrom[i];
      int node2 = graphTo[i];
      adjacencies[node1].add(node2);
      adjacencies[node2].add(node1);
    }     
    
    //initially, we set shortestPath to longest path possible, which is # of graphNodes
    int shortestPath = graphNodes;
    for(int i = 1; i < adjacencies.length; i++)
    {
      //i-1 bc that ids array is indexed starting at 0
      long curNodeColor = ids[i - 1];
      int curPath = 0;

      //if this node's color isn't the one we're looking for, skip it
      if(curNodeColor != val)
      {
        continue;
      }
      
      //find the shortest path length for this node to another of a matching color
      curPath += findPathLength(i, val, ids);
      shortestPath = Math.min(shortestPath, curPath);
    }

    return shortestPath;
  }

  static int findPathLength(int curNode, long color, long[] ids)
  {
    visited[curNode] = true;
    ArrayList<Integer> curAdj = adjacencies[curNode];
    int minPath = ids.length;

    for(int i = 0; i < curAdj.size(); i++)
    {
      int curNeighbor = curAdj.get(i);
      int curPath = 1;

      //if this neighbor has already been visited, skip it
      if(visited[curNeighbor] == true)
      {
        continue;
      }
      
      //if this neighbor has the matching pair, then 1 is the shortest path
      if(ids[curNeighbor - 1] == color)
      {
        return curPath;
      }
      
      //if this neighbor doesn't meet any of the if conditions, then we must recurse
      curPath += findPathLength(curNeighbor, color, ids);
      minPath = Math.min(minPath, curPath);
    }
    
    return minPath;
  }







  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] graphNodesEdges = scanner.nextLine().split(" ");
    int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
    int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

    int[] graphFrom = new int[graphEdges];
    int[] graphTo = new int[graphEdges];

    for (int i = 0; i < graphEdges; i++) {
      String[] graphFromTo = scanner.nextLine().split(" ");
      graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
      graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
    }

    long[] ids = new long[graphNodes];

    String[] idsItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < graphNodes; i++) {
      long idsItem = Long.parseLong(idsItems[i]);
      ids[i] = idsItem;
    }

    long val = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

    bufferedWriter.write(String.valueOf(ans));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
