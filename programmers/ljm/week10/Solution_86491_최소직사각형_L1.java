import java.util.*;

public class Solution_86491_최소직사각형_L1 {
	public int[] solution(int []arr) {
        
        Stack<Integer> stack = new Stack<>();
        for(int i:arr) {
            if(stack.isEmpty()) stack.push(i);
            else if(stack.peek()!=i) stack.push(i);
        }
        
        int s = stack.size();
        int[] answer = new int[s];
        for(int i=s-1; i>=0; i--) answer[i] = stack.pop(); 
        
        return answer;
    }
}