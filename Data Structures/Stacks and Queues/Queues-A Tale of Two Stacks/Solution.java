import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Stack;


class MyQueue<Integer>
{
  Stack<Integer> s1;
  Stack<Integer> s2;
  boolean isSorted;
  MyQueue()
  {
    s1 = new Stack<Integer>();
    s2 = new Stack<Integer>();
    isSorted = false;
  }


  public void enqueue(Integer elem)
  {
    if(isSorted == false)
    {
      s1.push(elem);
    }
    else //isSorted
    {
      s2.push(elem);

    }


  }

  public void dequeue() //remove elem at front
  {
    
    if(isSorted == true)
    {
      s1.pop();
    }
    else 
    {
      if(s1.size() == 1)
      {
        s1.pop();
        return;
      }
      sort();
      isSorted = true;
      s1.pop();
    }

    if(s1.empty() && (!s2.empty()))
    {
      s1 = (Stack<Integer>) s2.clone();
      s2.clear();
      isSorted = false;
      return;
    }

    if(s1.empty())
    {
      isSorted = false;
    }

  }

  public Integer peek()
  {
    if(s1.size() == 1)
    {
      return s1.peek();
    }

    if(isSorted == true)
    {
      return s1.peek();
    }
    else
    {
      sort();
      isSorted = true;
      return s1.peek();
    }
  }

  private void sort()
  {
    while(!s1.empty())
    {
      s2.push(s1.pop());
    }

    s1 = (Stack<Integer>) s2.clone();
    s2.clear();
  }
  
}


//EVERYTHING ABOVE THIS IS THE CODE I WROTE
////////////////////////////////////////////


public class Solution {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
              queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
              queue.dequeue();
            } else if (operation == 3) { // print/peek
              System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}

