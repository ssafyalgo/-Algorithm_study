package algo0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_1406_에디터 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		Deque<Character> leftQueue = new ArrayDeque<>();
		Deque<Character> rightQueue = new ArrayDeque<>();
		for(int i=0; i<str.length(); i++) {
			leftQueue.offer(str.charAt(i));
		}
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			String input = br.readLine();
			if(input.equals("L")) {
				if(!leftQueue.isEmpty()) {
					rightQueue.offerFirst(leftQueue.pollLast());
				}
			}else if (input.equals("D")) {
				if(!rightQueue.isEmpty()) {
					leftQueue.offerLast(rightQueue.pollFirst());
				}
			}else if (input.equals("B")) {
				if(!leftQueue.isEmpty()) {
					leftQueue.removeLast();
				}
			}else {
				leftQueue.offerLast(input.charAt(2));
			}
		}
		
	
		while(!rightQueue.isEmpty()) 
			leftQueue.offerLast(rightQueue.pollFirst());
		
		while(!leftQueue.isEmpty())
			sb.append(leftQueue.pollFirst());
		
		System.out.println(sb);
		
	}
}
