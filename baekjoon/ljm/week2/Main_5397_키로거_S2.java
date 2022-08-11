package java08_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_5397_키로거_S2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		while (N-- > 0) {
			Stack<Character> stack = new Stack<>(); // 비밀번호가 저장될 변수.
			Stack<Character> tmpstack = new Stack<>(); // 방향에 따라서 따로 저장될 변수.

			String str = br.readLine();

			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				switch (c) {
				case '<':
					if (!stack.isEmpty()) {
						tmpstack.add(stack.pop());
					}
					break;
				case '>':
					if (!tmpstack.isEmpty())
						stack.add(tmpstack.pop());
					break;
				case '-':
					if (!stack.isEmpty()) {
						stack.pop();
					}
					break;

				default:
					stack.add(c);
				}
			}

			while (!tmpstack.isEmpty()) {
				stack.add(tmpstack.pop());
			}
			
			//String으로 만들어서 담으면 시간초과 -> sb로 바로 받으면 900ms
			for (char c : stack) {
				sb.append(c);
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
