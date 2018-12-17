import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

class Node 
{
  public int nodeNum;
  public int depth;
  public Node left, right;
}

public class Solution {

  static Node buildTree(int[][] indexes)
  {
    Node root = addToTree(indexes, 0, 1, 1);

    return root;
  }

  static Node addToTree(int[][] indexes, int iter, int depth, int nodeNumber)
  {
    Node node = new Node();
    node.nodeNum = nodeNumber;
    node.depth = depth;

    if(indexes[iter][0] == -1)
    {
      node.left = null;
    }
    else
    {
      int nextLeftNum = indexes[iter][0];
      node.left = addToTree(indexes, nextLeftNum-1, depth+1, nextLeftNum);
    }

    if(indexes[iter][1] == -1)
    {
      node.right = null;
    }
    else
    {
      int nextRightNum = indexes[iter][1];
      node.right = addToTree(indexes, nextRightNum-1, depth+1, nextRightNum);
    }
    return node;
  }

    /*
     * Complete the swapNodes function below.
     */
    static int[][] swapNodes(int[][] indexes, int[] queries) {

      Node root = buildTree(indexes);

      int[][] outputs = new int[queries.length][indexes.length];

      for(int i = 0; i < queries.length; i++)
      {
        int query = queries[i];
        ArrayList<Integer> output = new ArrayList<Integer>();
        inOrderTraverse(root, output, query);
        
        int[] finalOutput = output.stream().mapToInt(j -> j).toArray();

        outputs[i] = finalOutput;
      }

      return outputs;
    }

  static void inOrderTraverse(Node currNode, ArrayList<Integer> outputArr, int queryNum)
  {
    int curDepth = currNode.depth;
    if(curDepth%queryNum == 0)
    {
      Node temp = currNode.left;
      currNode.left = currNode.right;
      currNode.right = temp;
    }

    if(currNode.left == null)
    {
      outputArr.add(currNode.nodeNum);
      //System.out.println(currNode.nodeNum + " " + currNode.depth);
    }
    else
    {
      inOrderTraverse(currNode.left, outputArr, queryNum);
      outputArr.add(currNode.nodeNum);
      //System.out.println(currNode.nodeNum + " " + currNode.depth);
    }

    if(currNode.right == null)
    {
      return;
    }
    else
    {
      inOrderTraverse(currNode.right, outputArr, queryNum);
    }
  }


    



    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
            for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
                bufferedWriter.write(String.valueOf(result[resultRowItr][resultColumnItr]));

                if (resultColumnItr != result[resultRowItr].length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            if (resultRowItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
