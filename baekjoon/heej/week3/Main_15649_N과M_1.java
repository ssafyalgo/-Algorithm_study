package study;

import java.util.Scanner;

/* Q. 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램
 *    - 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 */
public class Main_15649_N과M_1 {
	
	static int N, M;
	static int[] nums; 
	static boolean[] visited; 

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in); 
		
		/* 입력 */
		N = scann.nextInt(); 			// 1 ~ N까지의 자연수 중
		M = scann.nextInt(); 			// M개를 고른 수열
		
		/* 로직 */
		// 순열 nPr - n개 중 r개를 선택하여 나열한 것 [중복X, 순서O]
		nums = new int[M];
		visited = new boolean[N+1]; 
		perm(0);
	
	}

	// [@Method] 선택한 수열 출력
	private static void numsPrint() {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	// [@Method] 순열 nPr
	private static void perm(int cnt) {
		// End 조건 
		if (cnt==M) {
			numsPrint();
			return; 
		}
		
		// 순열 
		for (int i = 1; i < N+1; i++) {
			if (visited[i]) continue;
			
			visited[i] = true; 
			nums[cnt] = i;
			perm(cnt+1);
			
			visited[i] = false;
		}
	}

}
