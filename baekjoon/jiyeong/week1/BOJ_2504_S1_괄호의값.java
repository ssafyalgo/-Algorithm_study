import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2504_S1_괄호의값 {

	static char[] bracket = new char[30];
	static int[] num = new int[40];
	static int top = -1;
	static int answer = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		
		for(int i=0;i<input.length;i++) {
			if(input[i]=='('||input[i]=='[') {
				bracket[++top]=input[i];
			}
			else if(0<=top&&bracket[top]=='('&&input[i]==')') {
				if (top == 0 && num[top] == 0) answer += 2;
				else if (top == 0 && num[top] != 0) answer += 2 * num[top];
				else if (num[top] == 0) num[top - 1] += 2;
				else num[top - 1] += num[top] * 2;
				
				num[top] = 0; top--;
			}
			else if(0<=top&&bracket[top]=='['&&input[i]==']') {
				if (top == 0 && num[top] == 0) answer += 3;
				else if (top == 0 && num[top] != 0) answer += 3 * num[top];
				else if (num[top] == 0) num[top - 1] += 3;
				else num[top - 1] += num[top] * 3;

				num[top] = 0; top--;
			}
			else {
				answer = 0;
				break;
			}
		}
		System.out.println(answer);
	}

}
