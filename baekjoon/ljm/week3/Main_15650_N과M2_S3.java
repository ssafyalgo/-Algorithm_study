package java08_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15650_N과M2_S3 {
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		combi(1, 0, N, M, new int[M]);
		
		System.out.println(sb);
	}

	private static void combi(int start, int cnt, int N, int M, int[] nums) {
		if(cnt==M) {
			for (int i = 0; i < M; i++) {
				sb.append(nums[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= N; i++) {
			nums[cnt] = i;
			combi(i+1, cnt+1, N, M, nums);
		}
	}

}
