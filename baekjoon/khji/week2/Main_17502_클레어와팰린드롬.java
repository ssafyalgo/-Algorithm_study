package algo0814;

import java.util.Scanner;

public class Main_17502_클레어와팰린드롬 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N =sc.nextInt();
		sc.nextLine();
		char[] c = sc.nextLine().toCharArray();
		
		int i = 0;
		int j = c.length-1;
		
		if(i==j) {
			System.out.print('a');
		}else {
			while(i<=j) {
				if(c[i]=='?' && c[j]=='?') {
					c[i]='a'; c[j]='a';
				}
				else if(c[i]=='?') {
					c[i] = c[j];
				}
				else if(c[j]=='?') {
					c[j] = c[i];
				}			
				i++; j--;
			}
			for(char ch:c) System.out.print(ch);			
		}
	}
}