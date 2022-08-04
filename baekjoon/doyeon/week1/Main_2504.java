import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 백준 2504번. 괄호의 값
 */
public class Main_2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s=br.readLine();
        int result=0;
        char prev=' ';
        Stack<Character> p = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)){
                case ')':
                    if(p.peek()=='('){
                        if(prev==s.charAt(i))
                            result*=2;
                        else
                            result+=2;
                        prev=')';
                        p.pop();
                    }
                    break;
                case ']':
                    if(p.peek()=='['){
                        if(prev==s.charAt(i))
                            result*=2;
                        else
                            result+=2;
                        prev=']';
                        p.pop();
                    }
                    break;
                default:
                    p.push(s.charAt(i));
                    break;
            }
//            if(i==s.length()-1){
//            }
        }
        if(!p.empty())
            result=0;

        System.out.println(result);
    }
}
