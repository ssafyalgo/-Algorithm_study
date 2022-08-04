import java.io.*;
import java.util.*;

public class BOJ_5555_S5_반지 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int N = Integer.parseInt(br.readLine());
		int ans=0;
		while(N-->0) {
			String ring = br.readLine();
			for(int i=0; i<ring.length(); i++) {
				int j=0;
				for(;j<str.length(); j++) {
					if(ring.charAt((i+j)%ring.length())!=str.charAt(j)) {break;}
				}
				if(j==str.length()) {ans++;break;}
			}
		}
		System.out.println(ans);
	}
}
