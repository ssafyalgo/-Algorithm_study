package java08_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/* 에디터 S2 20
 * 커서가 존재, 맨앞, 맨 뒤, 임의의 곳에 위치할 수 있음.
 * L 은 왼쪽으로
 * D 는 오른쪽으로
 * B는 삭제 
 * 
 * P 새로운 문자를 왼쪽에 추가
 */
public class Main_1406_에디터_S2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String str = br.readLine();

		Stack<Character> stack = new Stack<>();
		Stack<Character> stack2 = new Stack<>();
		int size = str.length();

		for (int i = 0; i < size; i++) {
			stack.add(str.charAt(i));
		}

		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			switch (st.nextToken()) {
			case "L": // 왼쪽
				if (!stack.isEmpty())
					stack2.add(stack.pop());

				break;
			case "D": // 오른쪽
				if (!stack2.isEmpty())
					stack.add(stack2.pop());

				break;
			case "B": // 삭제
				if (!stack.isEmpty())
					stack.pop();

				break;
			default: // 추가
				stack.add(st.nextToken().charAt(0));
				break;
			}
		}

		for (char c : stack) {
			sb.append(c);
		}
		int size2 = stack2.size();
		for (int i = 0; i < size2; i++) {
			sb.append(stack2.pop());
		}

		System.out.println(sb);
	}

}
