import java.io.*;
import java.util.*;

public class Solution {

  private static ArrayList<Integer>[] adjacencies;
  private static boolean[] visited;

  private static final Scanner scan = new Scanner(System.in);
  public static void main(String[] args) 
  {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class
    should be named Solution. */
    int queries = scan.nextInt();

    for(int i = 0; i < queries; i++)
    {
      int numNodes = scan.nextInt();
      int numEdges = scan.nextInt();

      adjacencies = new ArrayList[numNodes + 1];

      //initialize all arraylists within our adjacencies array 
      for(int c = 0; c <= numNodes; c++)
      {
        adjacencies[c] = new ArrayList<Integer>();
      }

      //create our graph
      for(int j = 0; j < numEdges; j++)
      {
        int node1 = scan.nextInt();
        int node2 = scan.nextInt();
        adjacencies[node1].add(node2);
        adjacencies[node2].add(node1);
      }

      int startingNode = scan.nextInt();
      shortestReach(startingNode, numNodes);
    }

  }


  static void shortestReach(int root, int numNodes)
  {
    LinkedList<Integer> queue = new LinkedList<Integer>();
    visited = new boolean[numNodes + 1];

    int[] elemDistFromRoot = new int[numNodes + 1];
    for(int i = 0; i <= numNodes; i++)
    {
      elemDistFromRoot[i] = -1;
    }

    elemDistFromRoot[root] = 0;

    queue.add(root);
    visited[root] = true;

    while(queue.size() != 0)
    {
      int curRoot = queue.pop();
      //the distance this root is from the original root 'root'
      int curRootLevel = elemDistFromRoot[curRoot];
      ArrayList<Integer> neighbors = adjacencies[curRoot];

      for(int neighbor : neighbors)
      {
        if(visited[neighbor] == true)
        {
          continue;
        }
        queue.add(neighbor);
        //the dist from the original root this elem is, is 1 more than its parent
        elemDistFromRoot[neighbor] = curRootLevel + 1;
        visited[neighbor] = true;
      }

    }

    for(int i = 1; i <= numNodes; i++)
    {
      int distFromRoot = elemDistFromRoot[i];

      if(distFromRoot == 0)
      {
        continue;
      }

      if(distFromRoot == -1)
      {
        System.out.print(distFromRoot + " ");
        continue;
      }
      System.out.print(distFromRoot*6 + " ");
    }

    System.out.println();

  }


}

