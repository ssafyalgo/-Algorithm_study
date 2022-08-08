package java08_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
/*  괄호의 값  2504 S1
 * 
 *  () = 2
 *  [] = 3
 *  (X) = X*2
 *  [X] = X*3
 *  
 *  괄호안에 괄호가 있으면 닫는 괄호가 나올 때 까지 곱해주는 역할. 2*, 3*
 *   -> 빈괄호가 나오면 구해진 값을 저장.
 *  
 * {1}    {1}
 * 	(()[[]]) ([])
 * 
 *  2{1}*(2+3*3)+2*3 => {2(1)*2}+{2(1)*3*3}+{2*3}
 *  //닫는 괄호를 안만나면 다음에올 숫자에도 *2를 해줘야함.
 */
public class Main_2504_괄호의값_S1 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] arr = br.readLine().toCharArray();
		Stack<Character> brackets = new Stack<>();
		int num = 1;
		int ans = 0;
		boolean chk = false;
		for (int i = 0; i < arr.length; i++) {
			//여는 괄호가 나오면 현재 값에서 곱하기 
			if(arr[i]=='(') {
				brackets.add(arr[i]);
				num*=2; 
			}else if(arr[i]=='[') { 
				brackets.add(arr[i]);
				num*=3;
			}//닫는 괄호가 나오면 앞에서 곱한 숫자 다시 줄여주기.
			 else if(arr[i]==')') {
				if(brackets.isEmpty()||brackets.peek()!='(') { //무조건 안되는 조건
					chk = true;
					break; 
				} else if(arr[i-1]=='('){
					ans+=num;
				}
				brackets.pop();
				num/=2;
			}else if(arr[i]==']'){
				if(brackets.isEmpty()||brackets.peek()!='[') { //무조건 안되는 조건
					chk = true;
					break; 
				}else if(arr[i-1]=='['){
					ans+=num;
				}
				brackets.pop();
				num/=3;
			} else {
				break;
			}
			System.out.println(num);
		}
		
		if(chk||!brackets.isEmpty()) {
			System.out.println(0);
		}else {
			System.out.println(ans);
		}
	}

}
