import java.util.Stack;

public class StackLeetCode {

    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for(int i=0;i<s.length();i++){
            st.push(s.charAt(i));
        }
        if (st.isEmpty()) {
            return true;
        }
        else {
            return false;
        }

    }

    public static void main(String[] args) {
        String s="(([]]}}";
    }
}
