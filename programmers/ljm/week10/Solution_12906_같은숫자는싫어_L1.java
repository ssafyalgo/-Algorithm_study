import java.util.*;

public class Solution_12906_같은숫자는싫어_L1 {
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