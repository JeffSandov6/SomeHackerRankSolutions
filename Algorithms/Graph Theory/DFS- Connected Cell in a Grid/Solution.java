import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the maxRegion function below.
    static int maxRegion(int[][] grid) {
      //visited array
      boolean[][] visited = new boolean[grid.length][grid[0].length];
      
      int max = 0;
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) 
        {
          //if this cell hasnt been visited, find its region size
          if (visited[i][j] == false) 
          {
            int curRegionSize = findRegionDFS(grid, visited, i, j);
            max = Math.max(max, curRegionSize);
          }
        }
      }      
      return max;
    }

    static int findRegionDFS(int[][] grid, boolean[][] visited, int row, int column)
    {
      //first we have to handle these exceptions
      if(row < 0 || column < 0 || row >= grid.length || column >= grid[0].length)
      {
        return 0;
      }

      if(visited[row][column] == true)
      {
        return 0;
      }

      //by setting this cell's visited value to true, we ensure we wont count this cell
      //twice in a region
      visited[row][column] = true;

      //if the current cell is 0, then it cant be part of a region, so return 0
      if(grid[row][column] == 0)
      {
        return 0;
      }
      int count = 1; //set count to 1

      //now we check all of our diagonal, horizontal, and vertical positions 
      count += findRegionDFS(grid, visited, row - 1, column - 1); //top left
      count += findRegionDFS(grid, visited, row - 1, column); //top
      count += findRegionDFS(grid, visited, row - 1, column + 1); //top right
      count += findRegionDFS(grid, visited, row, column - 1); //left
      count += findRegionDFS(grid, visited, row, column + 1); //right
      count += findRegionDFS(grid, visited, row + 1, column - 1); //bottom left
      count += findRegionDFS(grid, visited, row + 1, column); //bottom
      count += findRegionDFS(grid, visited, row + 1, column + 1); //bottom right

      return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] gridRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int gridItem = Integer.parseInt(gridRowItems[j]);
                grid[i][j] = gridItem;
            }
        }

        int res = maxRegion(grid);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
