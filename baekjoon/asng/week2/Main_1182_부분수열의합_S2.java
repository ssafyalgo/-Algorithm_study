package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1182_부분수열의합_S2 {

	static int[] arr;
	static int[] nums;
	static int N, S, count;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		arr = new int[N];
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		visited = new boolean[N];
		subset(0);
		System.out.println(count);
	}

	private static void subset(int cnt) { 
		if (cnt == N) {	
			return;
		}
		
		// 원소 고를때마다 더하고 S랑 같은지 검사  
		// 0, 1, 2, 3, 4 다하고 cnt == N 이므로 return
		// cnt = 4상태로 리턴되서 미선택 하고 0 ,1 ,2 ,3 상태로 cnt+1 호출 -> 리턴
		//cnt = 3상태로 리턴되서 0,1,2,4 골라보고  검사하고 ...
		visited[cnt] = true;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				sum += arr[i];
			}
		}
		if (sum == S) {
			count++;
		}
		subset(cnt + 1);
		// 원소 미선택
		visited[cnt] = false;
		
		subset(cnt + 1);
	}

}
