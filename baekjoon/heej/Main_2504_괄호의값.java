package java0809_study;

import java.util.Scanner;
import java.util.Stack;

// Stack 사용 - LIFO
public class Main_2504_괄호의값 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		/* 입력받기 */
		String str = scann.nextLine();
		char[] brakets = str.toCharArray();
		
		Stack<Character> stack = new Stack<>();
		Boolean flag = true;
		
		int result = 0; 
		int cnt = 1; 
		
		/* 로직 */
		// i) 열린 괄호일 경우 - 2 or 3을 cnt에 곱함(*)
		// ii) 닫힌 괄호일 경우 - 스택 맨 위의 값 pop & cnt값 2 or 3으로 나누기(/)
		//     만약, 현재 괄호의 전 괄호 확인 시 정상적인 괄호라면 result 변수에 cnt 값을 더함(+) 
		// iii) 스택이 비었거나 정상적인 괄호가 아닐 경우 false 및 break;
		for (int i = 0; i < brakets.length; i++) {
			
			// 열린 괄호
			if (brakets[i] == '(') {
				stack.push(brakets[i]);
				cnt *= 2; 
			}
			else if (brakets[i] == '[') {
				stack.push(brakets[i]);
				cnt *= 3;
			}
			// 닫힌 괄호
			else {
				if (brakets[i] == ')') {
					if (stack.isEmpty() || stack.peek() != '(') {		// 스택이 비어있거나 정상괄호가 아닐 경우
						flag = false; 
						break;
					}
					// 스택이 비어있지 않고 & 정상괄호 일 경우
					if (brakets[i-1]=='(') {
						result += cnt;
					}
					stack.pop();
					cnt /= 2;
				}
				else if (brakets[i]==']') {
					if (stack.isEmpty() || stack.peek() != '[') {
						flag = false; 
						break;
					}
					if (brakets[i-1]=='[') {
						result += cnt;
					}
					stack.pop();
					cnt /= 3;
				}
				else {
					flag = false;
					break;
				}
			}
		}
		
		
		/* 출력 */
		if (!flag || !stack.isEmpty()) {
			System.out.println(0);
		}else {
			System.out.println(result);
		}
	}

}