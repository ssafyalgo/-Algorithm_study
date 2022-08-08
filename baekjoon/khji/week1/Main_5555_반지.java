package algo0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_5555_반지 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		
		String str = br.readLine();
		int length = str.length();
		int count = 0;
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<length; j++) {
				s = s+s.charAt(j);
			}
			if(s.contains(str)) {
				count++;
			}						
		}
		
		System.out.println(count);
		
	}
}
