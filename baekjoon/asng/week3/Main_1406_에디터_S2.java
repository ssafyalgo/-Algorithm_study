package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_1406_에디터_S2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		
		int N = Integer.parseInt(br.readLine());
		Deque<Character> q1 = new ArrayDeque<>();
		Deque<Character> q2 = new ArrayDeque<>();
		for (int i = 0; i < str.length(); i++) {
			q1.offerLast(str.charAt(i));
		}
		
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			char x = str.charAt(0);
			switch(x) {
				case 'P':
					q1.offerLast(str.charAt(2));
					break;
				case 'L':
					if (!q1.isEmpty()) 
						q2.offerFirst(q1.pollLast());
					break;
				case 'D':
					if (!q2.isEmpty()) 
						q1.offerLast(q2.pollFirst());
					break;
				case 'B':
					if (!q1.isEmpty()) 
						q1.pollLast();
					break;
			}
		}
		
		// sb 안쓰면 시간초과남..
		while(!q1.isEmpty()) 
			sb.append(q1.pollFirst());
		while(!q2.isEmpty())
			sb.append(q2.pollFirst());
		
		System.out.println(sb);
	}

}
