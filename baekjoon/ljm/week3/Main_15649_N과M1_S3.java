package java08_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15649_N과M1_S3 {
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		perm(0, N, M, new int[M], 0);
		
		System.out.println(sb);
	}

	private static void perm(int cnt, int N, int M, int[] nums, int bit) {
		if(cnt==M) {
			for (int i = 0; i < M; i++) {
				sb.append(nums[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if((bit & (1<<i))!=0) continue;
			
			nums[cnt] = i;
			perm(cnt+1, N, M, nums, bit | (1<<i));
		}
	}

}
