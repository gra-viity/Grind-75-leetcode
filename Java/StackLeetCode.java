import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        Stack<Integer> numStack=new Stack<>();
        Stack<StringBuilder> stringStack=new Stack<>();

        StringBuilder decodeString=new StringBuilder();
        int num=0;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
           if(Character.isDigit(c)){
               //what if number greater than 9
               // Build number if multi-digit
               num=num * 10 +( c -'0');
           }
           else if(c=='['){
               numStack.push(num);
               stringStack.push(decodeString);
               decodeString=new StringBuilder(); // reset string
               num=0;   //reset num
           }
           else if(c==']'){
               // Making iterations for multiplication of bracket contained String
               // Update current String
               int count=numStack.pop();
               StringBuilder temp=stringStack.pop();
               while(count-- >0){ //keep append into temp  similar to string.repeat(num)
                   temp.append(decodeString);
               }
               decodeString=temp;   // assign into main string next time it will push
           }
           else {
               decodeString.append(c);
           }

        }

        return decodeString.toString();
    }

    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<asteroids.length;i++){
            //handles +ve ele or -ve ele push is stack is emp
            if(stack.isEmpty() || asteroids[i]>0){
                stack.push(asteroids[i]);
            }
            //-ve number rece
            else{
                while (!stack.isEmpty()){
                    int topEl=stack.peek();
                    //what if stack top is -ve, and we rec -ve so push it
                    if(topEl<0){
                        stack.push(asteroids[i]);
                        break;
                    }
                    int modVal=Math.abs(asteroids[i]);
                    //if both are equal remove last ele
                    if(modVal == topEl) {
                        stack.pop();
                        break;
                    }
                    //if top ele is greater do nothing
                    else if(modVal <topEl){
                        break;
                    }
                    else {
                        stack.pop();
                        if(stack.isEmpty()){
                            stack.push(asteroids[i]);
                            break;
                        }
                    }

                }
            }
        }
        int[] ans=new int[stack.size()];
        for(int i=stack.size()-1;i>=0;i--){
            ans[i]=stack.pop();
        }
return ans;

    }

    public static int trap(int[] height) {
        int ans = 0;
        int lmax = 0;
        int rmax = 0;
        int l=0;
        int r=height.length-1;

        while (l < r) {
            lmax=Math.max(lmax,height[l]);
            rmax=Math.max(rmax, height[r]);

            if(lmax<rmax){
                ans+=lmax-height[l];
                l++;
            }
            else {
                ans+=rmax-height[r];
                r--;
            }

        }
        return ans;
    }

    public static int calculate(String s) {
        int ans=0;
        int num=0;
        int sign=1;
        Stack<Integer> st=new Stack<>();
        // op used + -
        // brackets ( )

        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(Character.isDigit(c)){
                //what if number greater than 9
                // Build number if multi-digit
                num=num * 10 +( c -'0');
            }
            if(c =='+' || c =='-'){
                ans+= num*sign;
                num=0;
                sign=(c=='+') ?1 : -1; //if sign is plus then 1 else -1

            }  else if (c == '(') {
                st.push(ans);
                st.push(sign);
                ans=0;
                sign=1;

            } else if (c == ')') {
                ans+= sign *num;
                num=0;
                ans *=st.pop();
                ans+= st.pop();
            }
        }
        ans+=sign* num;
        return ans;

    }

    // For handling multiplication and division
    public int calculateWithMD(String s) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i++) - '0');
                }
                i--;
                nums.push(num);
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (ops.peek() != '(') {
                    process(nums, ops);
                }
                ops.pop(); // Remove '('
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(c)) {
                    process(nums, ops);
                }
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) {
            process(nums, ops);
        }
        return nums.pop();
    }

    private void process(Stack<Integer> nums, Stack<Character> ops) {
        int b = nums.pop();
        int a = nums.pop();
        char op = ops.pop();
        int result = 0;
        switch (op) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
        }
        nums.push(result);
    }

    private int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    public static int largestRectangleArea(int[] heights) {
        /*
        //brute force
        for(int i=0;i<heights.length;i++){
            int min_ele=heights[i];
            for (int j=i;j<heights.length;j++){
                min_ele=Math.min(min_ele,heights[j]);
                int len=j-i+1;
                int curr_area=min_ele * len;
                result=Math.max(result,curr_area);
            }
        }
         */
        //optimal

        Stack<Integer> st=new Stack<>();
        //nsl
        int[] nsl = new int[heights.length];
        for (int j = 0; j < heights.length; j++) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[j]) {
                st.pop();
            }
            nsl[j]=(st.isEmpty() ? -1 : st.peek());
            st.push(j);
        }
        st.clear();
        //nsr
        int[] nsr = new int[heights.length];
        for (int j = heights.length - 1; j >= 0; j--) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[j]) {
                st.pop();
            }
            nsr[j]=(st.isEmpty() ? heights.length : st.peek());
            st.push(j);
        }

        int max=Integer.MIN_VALUE;
        for(int i=0;i< heights.length;i++){
            int width= nsr[i]-nsl[i]-1;
            int area=width * heights[i];
            max=Math.max(max,area);
        }


        return max;
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
        // System.out.println(decodeString(decodeString));
        int [] asteroids = {1,-2,-2,-2};
        // System.out.println(Arrays.toString(asteroidCollision(asteroids)));
        int [] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        // System.out.println(trap(height));
        String cals = "(1+(4+5+2)-3)+(6+8)";
        // System.out.println(calculate(cals));
        int [] largRectHis = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(largRectHis));

    }
}
