package study;

import java.util.Arrays;
import java.util.Scanner;

// Q. 자연수 N, M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램
/*    1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 *    고른 수열은 오름차순 */
public class Main_15650_N과M_2 {
	
	static int N;				// 1 ~ N까지 자연수
	static int M;				// 길이가 M인 수열
	static int[] nums;

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		/* 입력 */
		N = scann.nextInt();
		M = scann.nextInt();

		/* 로직 */
		// 중복 X, 순서 X = 조합 
		nums = new int[M];
		combi(0, 0);
		
		
		
	}

	// [@Method] 조합 nCr 
	private static void combi(int start, int cnt) {
		// End 조건
		if (cnt==M) {
			printNums();
			return;
		}
		
		// 조합 
		for (int i = start; i < N; i++) {
			nums[cnt] = i;
			combi(i+1, cnt+1);
		}
	}

	// [@Method] nums 조합으로 선택한 수열 출력
	private static void printNums() {
		for (int i = 0; i < nums.length; i++) {
			System.out.print((nums[i]+1) + " ");
		}
		System.out.println();
	}

}
