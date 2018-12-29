import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


//i solved this w/out the use of DFS. the other file, Solution.java uses DFS
public class Solution {

    static int maxRegion(int[][] grid) {
      
      int max = 0;
      for(int i = 0; i < grid.length; i++)
      {
        for(int j = 0; j < grid[0].length; j++)
        {
          max = Math.max(max, countCells(i, j, grid));
        }
      }

      return max;
    }

    static int countCells(int row, int column, int[][] grid)
    {
      //first we have to handle these exceptions
      if(row < 0 || column < 0 || row >= grid.length || column >= grid[0].length)
      {
        return 0;
      }

      //if the current cell is 0, then it cant be part of a region, so return 0
      if(grid[row][column] == 0)
      {
        return 0;
      }

      int count = grid[row][column]; //set count to 1
      //set this cell to 0 now, as it's now been accounted for, and it is safe to 
      //set it to 0 as every 1 cell can only be a part of one region
      grid[row][column] = 0; 

      //now we check all of our diagonal, horizontal, and vertical positions 
      count += countCells(row - 1, column - 1, grid); //top left
      count += countCells(row - 1, column, grid); //top
      count += countCells(row - 1, column + 1, grid); //top right
      count += countCells(row, column - 1, grid); //left
      count += countCells(row, column + 1, grid); //right
      count += countCells(row + 1, column - 1, grid); //bottom left
      count += countCells(row + 1, column, grid); //bottom
      count += countCells(row + 1, column + 1, grid); //bottom right

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
