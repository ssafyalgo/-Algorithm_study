package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_5397_키로거_S2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			String str = br.readLine();
			Deque<Character> s1 = new ArrayDeque<>();
			Deque<Character> s2 = new ArrayDeque<>();
			
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (c != '<' && c != '>' && c != '-') {
					s1.offerLast(c);
				} else if (c == '<' && !s1.isEmpty()) {
					s2.offerFirst(s1.pollLast());
				} else if (c == '>' && !s2.isEmpty()) {
					s1.offerLast(s2.pollFirst());
				} else if (c == '-' && !s1.isEmpty()) {	// 커서 위치에서 지워야 한다.
					s1.pollLast();
				}
			}
			
			while(!s2.isEmpty()) 
				s1.offerLast(s2.pollFirst());
			
			while(!s1.isEmpty())
				sb.append(s1.pollFirst());
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
