package algo0812;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_키로거 {

	private static final String LinkedList = null;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		while(N-->0) {
			StringBuilder sb = new StringBuilder();
			char[] input = br.readLine().toCharArray();
			LinkedList<Character> left = new LinkedList<>();
			LinkedList<Character> right = new LinkedList<>();
			
			for(char c:input) {
				if('A'<=c&&c<='Z'||'a'<=c&&c<='z'||'0'<=c&&c<='9') {left.addLast(c);}
				else if(c=='<'&&!left.isEmpty()) {right.addFirst(left.pollLast());}
				else if(c=='>'&&!right.isEmpty()) {left.addLast(right.pollFirst());}
				else if(c=='-'&&!left.isEmpty()) {left.pollLast();}
			}
			for(char c:left) {
				sb.append(c);
			}
			for(char c: right) {
				sb.append(c);
			}
			System.out.println(sb);
		}
	}

}
