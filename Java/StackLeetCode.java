import java.util.Arrays;
import java.util.Stack;

public class StackLeetCode {

    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++) {
            if (s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '[') {     //opening...
                st.push(s.charAt(i));
                System.out.println("Pushed :" + s.charAt(i));
            } else {
                if (st.isEmpty()) {
                    return false;
                }

                if (st.peek() == '{' && s.charAt(i) == '}' ||
                        st.peek() == '(' && s.charAt(i) == ')' ||
                        st.peek() == '[' && s.charAt(i) == ']') {      //closing..
                    st.pop();
                    System.out.println("Popped :" + s.charAt(i));
                } else {
                    return false;
                }
            }
        }
        return st.isEmpty();
    }

    public static boolean backspaceCompare(String s, String t) {

        Stack<Character> s_stack=new Stack<>();
        Stack<Character> t_stack=new Stack<>();
        for(char c:s.toCharArray()){
            if(c=='#' ){
                if(!s_stack.isEmpty()) {
                    s_stack.pop();
                }

            }
            else {
                s_stack.push(c);
            }
        }
        System.out.println("s"+ s_stack);
        for(char c: t.toCharArray()){
            if(c=='#') {
                if(!t_stack.isEmpty()) {
                t_stack.pop();
            }
            }
            else {
                t_stack.push(c);
            }

        }
        System.out.println("t"+ t_stack);

         return s_stack.equals(t_stack);

       // return buildString(s).equals(buildString(t));
    }



    public static String buildString(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c == '#') {
                if (!sb.isEmpty()) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static int evalRPN(String[] tokens) {

        int op1;
        int op2;
        Stack<Integer> st=new Stack<>();
        int ans=0;
        for(String c: tokens) {
            switch (c) {
                case "+":
                    op2=st.pop();
                    op1=st.pop();
                    ans=op1 + op2;
                    st.push(ans);
                    System.out.println("Op1: "+ op1 + " +" +" Op2: " +op2);
                    System.out.println("Stack " +st);
                    break;
                case "-":
                    op2=st.pop();
                    op1=st.pop();
                    ans=op1- op2;
                    st.push(ans);
                    System.out.println("Op1: "+ op1 + " -" +" Op2: " +op2);
                    System.out.println("Stack " +st);
                    break;
                case "*":
                    op2=st.pop();
                    op1=st.pop();
                    ans=op1 * op2;
                    st.push(ans);
                    System.out.println("Op1: "+ op1 + " *" +" Op2: " +op2);
                    System.out.println("Stack " +st);
                    break;
                case "/":
                    op2=st.pop();
                    op1=st.pop();
                    ans=(op1) / (op2);
                    st.push(ans);
                    System.out.println("Op1: "+ op1 + " / " +" Op2: " +op2);
                    System.out.println("Stack " +st);
                    break;
                default:
                    st.push(Integer.parseInt(c));
            }
        }
        if(!st.isEmpty()){
            ans=st.pop();
        }

        return ans;
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        //monotonic decreasing stack
        //next greater element
        Stack<Integer> st=new Stack<>(); //store index here
        int ar_size=temperatures.length;
        int [] result=new int[ar_size];
        Arrays.fill(result,0);

        for(int i=0;i<ar_size;i++){
            //while stack
            while(!st.isEmpty() && temperatures[i] > temperatures[st.peek()]){
                int index=st.pop(); //pop the top element
                //change it according, like max element
                result[index]=i-index; // calculate the wait time
            }
            st.push(i);
        }
        return result;
    }

    public static String decodeString(String s) {
        Stack<Integer> stack=new Stack<>();
        String decodeString="";
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(Character.isDigit(c)){
                int k=c;

            }
        }

    }


    public static void main(String[] args) {
        String s="()[]{}";
        String ss="y#fo##f", t="y#f#o##f";
       // System.out.println(backspaceCompare(ss,t));
        String [] rpn={"18"};
       // System.out.println(evalRPN(rpn));
        int [] temperatures = {73,74,75,71,69,72,76,73};
        //System.out.println(Arrays.toString(dailyTemperatures(temperatures)));
        String decodeString = "3[a2[c]]";
        System.out.println(decodeString(decodeString));

    }
}
