package java0809_study;

import java.util.Scanner;
import java.util.Stack;

// Stack 사용 - LIFO
public class Main_2504_괄호의값_2 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		String str = scann.nextLine();
		str = str.replace("()", "2");
		str = str.replace("[]", "3");
		//System.out.println(str);
		
		char[] braket = str.toCharArray();
		
		Boolean flag = true;
		Stack<String> stack = new Stack<>();
		for (int i = 0; i < braket.length; i++) {
			
			// (1) 열린괄호 && 숫자 일 경우 스택에 넣기
			if (braket[i]=='(' || braket[i]=='[' || braket[i]=='2' || braket[i]=='3') {
				stack.push(Character.toString(braket[i]));
			}
			
			// (2) 닫힌괄호 ) 일 경우 정상괄호인지 확인 후 연산 진행
			else if ((int)(braket[i])==41) {							// == 으로 비교할 경우 제대로 비교하지 못함
				// 숫자인지 확인
				if (!stack.isEmpty() && stack.peek().matches("[+-]?\\d*(\\.\\d+)?")) {
					int temp = Integer.parseInt(stack.pop());
					if (!stack.isEmpty() && stack.peek().equals("(")) {
						stack.pop();
						temp *= 2;
						
						if (!stack.isEmpty() && (stack.peek().equals("2") || stack.peek().equals("3"))) {
							temp += Integer.parseInt(stack.pop());
							stack.push(Integer.toString(temp));
						}else {
							stack.push(Integer.toString(temp));
						}
					}
					
				}else flag = false; //break;
				
			}
			
			// (2) 닫힌괄호 ]
			else if ((int)(braket[i])==93) {
				// 숫자인지 확인
				if (!stack.isEmpty() && stack.peek().matches("[+-]?\\d*(\\.\\d+)?")) {
					int temp = Integer.parseInt(stack.pop());
					if (!stack.isEmpty() && stack.peek().equals("[")) {
						stack.pop();
						temp *= 3;
						
						if (!stack.isEmpty() && (stack.peek().equals("2") || stack.peek().equals("3"))) {
							temp += Integer.parseInt(stack.pop());
							stack.push(Integer.toString(temp));
						}else {
							stack.push(Integer.toString(temp));
						}
					}
					
				}else flag = false; //break;
			}
		}
		
		
		/* 출력 */
		int result = 0;
		// 스택에 남아있는 값 확인 - 스택에 괄호 잇으면 false
		if (flag && !stack.isEmpty()) {
			for (int j = 0; j <= stack.size(); j++) {
				if (!stack.isEmpty() && !(stack.peek().matches("[+-]?\\d*(\\.\\d+)?")) ) {
					flag = false;
					break;
				}
				result += Integer.parseInt(stack.pop());
			}
		}
		
		
		if (!flag) {
			System.out.println(0);
		}else {
			System.out.println(result);
		}
		
	}

}