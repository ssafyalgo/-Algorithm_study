package algostudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_5555_반지_S5 {

	static int len = 10;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String s = br.readLine();		
		
		// 반지에 써져있는 문자열을 2개 연달아 붙여 contains 조사, 맞으면 cnt++;
		int n = Integer.parseInt(br.readLine());
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			String ring = br.readLine();
//			char[] rarr = new char[2 * len];
//			for (int j = 0; j < 2 * len; j++) {
//				int a = j%10;
//				rarr[j] = ring.charAt(a); // 문자열을 배열로 바꾸며 같은 문자열 2번 붙임
//			}
//			String ring2 = new String(rarr);	// 배열을 다시 String으로
			
//			=> 뻘짓 함 그래도 String과 arr를 왔다 갔다 하는 방법을 공부했다.
			
			String ring2 = ring+ring;	//	String은 덧셈 가능
			if (ring2.contains(s))
				cnt++;
		}
		// 답 출력
		System.out.println(cnt);
	}

}
