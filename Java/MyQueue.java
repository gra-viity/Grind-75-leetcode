import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyQueue {
  Stack<Integer> st1=new Stack<>();
  Stack<Integer> st2=new Stack<>();
    public MyQueue() {

    }

    public void push(int x) {
      while(!st1.isEmpty()){
        st2.add(st1.pop());
      }
      st1.add(x);
      while (!st2.empty()){
        st1.add(st2.pop());
      }
    }

    public int pop() {
     return st1.pop();
    }

    public int peek() {
     return st1.peek();
    }

    public boolean empty() {
      return st1.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
