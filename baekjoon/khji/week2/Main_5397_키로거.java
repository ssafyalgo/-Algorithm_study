package algo0814;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_5397_키로거 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			char[] input = br.readLine().toCharArray();
			ArrayList<Character> pw = new ArrayList<>();
			int pointer = 0;
			for(int i=0; i<input.length; i++) {
				if(input[i] == '<') {
					if(pointer !=0) {
						pointer--;
					}
				}else if(input[i] == '>') {
					if(pointer < pw.size()) {
						pointer++;
					}
				}else if(input[i] == '-') {
					if(pointer!=0) {
						pointer--;
						pw.remove(pointer);						
					}
				}else {
					pw.add(pointer, input[i]);;
					pointer++;
				}
			}
			if(pointer <pw.size()) {
				pw.add(pointer, ' ');
			}
			
			for(char c : pw) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
}
