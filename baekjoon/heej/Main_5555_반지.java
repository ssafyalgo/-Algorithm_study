package java0809_study;

import java.util.Scanner;

public class Main_5555_반지 {

	static String str; 			// 찾고자하는 문자열
	static int N;				// 반지의 개수
	static int count;
	
	public static void main(String[] args) {
		
		Scanner scann = new Scanner(System.in);
		str = scann.next();
		N = scann.nextInt();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.setLength(0);					// StringBuilder 초기화
			String temp = scann.next(); 
			temp = sb.append(temp).append(temp).toString();
			
			//System.out.println(temp);
			if (temp.contains(str)) count++;
		}
		
		System.out.println(count);
	}

}