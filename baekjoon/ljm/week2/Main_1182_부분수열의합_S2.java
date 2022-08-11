package java08_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1182_부분수열의합_S2 {
	static int[] nums;
	static int N, S, ans;
	static int count;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		ans = 0;
		if(S==0) ans--;
		subset(0, 0);
		
//		System.out.println(Arrays.toString(nums));
		System.out.println(ans);
	}
	
	private static void subset(int cnt, int sum) {
		if(cnt==N) {
			if(sum==S) {
				ans++;
			}
			return;
		}
		
		subset(cnt+1, sum+nums[cnt]);
		subset(cnt+1, sum);
	}
}
