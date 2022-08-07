package algostudy;

import java.util.Scanner;
import java.util.Stack;

public class Main_2504_괄호의값_S1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str = sc.next();

		Stack<Character> s = new Stack<>();

		int sum = 0;
		int temp = 1;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				s.push(str.charAt(i));
				temp *= 2;
			} else if (str.charAt(i) == '[') {
				s.push(str.charAt(i));
				temp *= 3;
			} else if (str.charAt(i) == ')') {
				if (s.isEmpty() || s.peek() != '(') {
					sum = 0;
					break;
				} else if (str.charAt(i-1) == '(') { // 꼭 이전 글자랑 아구가 맞아야 더해줘야한다. 그렇지 않으면 중복해서 더하기 때문.
					sum += temp;
				}
				s.pop();
				temp /= 2;
			} else if (str.charAt(i) == ']') {
				if (s.isEmpty() || s.peek() != '[') {
					sum = 0;
					break;
				} else if (str.charAt(i-1) == '[') {
					sum += temp;
				}
				s.pop();
				temp /= 3;
			}
		}

		if (!s.isEmpty())
			sum = 0;

		System.out.println(sum);

		sc.close();
	}
}
