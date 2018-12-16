/* Hidden stub code will pass a root argument to the function below. Complete the function to solve the challenge. Hint: you may want to write one or more helper functions.  

The Node class is defined as follows:
    class Node {
        int data;
        Node left;
        Node right;
     }
*/
    boolean checkBST(Node root) {
      
      return checkChildren(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean checkChildren(Node node, int minValue, int maxValue)
    {
      if(node == null) {return true;}
      
      boolean leftChild = true, rightChild = true;
      if(node.data > minValue && node.data < maxValue)
      {
        //for left child, we send the existing minValue, and the current data as the max
        //value, as the child cannot surpass this value, but also cant be less than minValue
        leftChild = checkChildren(node.left, minValue, node.data);
        
        //for right child, send existing max value, and the current data as the min value,
        //as the child cant be less than this, but also cant be higher than maxValue
        rightChild = checkChildren(node.right, node.data, maxValue);
      }
      else
      {
        return false;
      }
      
      return (leftChild && rightChild);

    }

