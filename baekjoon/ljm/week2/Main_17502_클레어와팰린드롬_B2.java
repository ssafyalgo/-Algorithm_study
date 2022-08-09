package java08_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_17502_클레어와팰린드롬_B2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] str = br.readLine().toCharArray();
		
		for (int i = 0; i < N/2+1; i++) {
			if(str[i]=='?'&&str[N-i-1]=='?') {
				str[i]='a';
				str[N-i-1]='a';
			} else if(str[i]=='?') {
				str[i]=str[N-i-1];
			} else if(str[N-i-1]=='?') {
				str[N-i-1] = str[i];
			}
		}
		
		System.out.println(String.valueOf(str));
	}

}
