package algo0812;

import java.util.Scanner;

public class Main_부분수열의합 {
	
	static int N,S;
	static int[] arr;
	static int count=0;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		S = s.nextInt();
		
		arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i]=s.nextInt();
		}
		
		subset(0,new boolean[N]);
		
		System.out.println(count);
	}
	private static void subset(int cnt, boolean[] visit) {
		if(cnt==N) {
			int sum =0;
			boolean flag = false;
			for(int i=0;i<N;i++) {
				if(visit[i]){sum += arr[i];flag =true;}
			}
			if(flag&&S==sum) {count++;}
			return;
		}
		visit[cnt]=true;
		subset(cnt+1,visit);
		visit[cnt]=false;
		subset(cnt+1,visit);
	}
	
	
	
	
}
