package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2644_촌수계산_S2 {

	static int N, M;
	static int a, b, cnt, ans;
	static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<>());
		}
		
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			tree.get(x).add(y);
			tree.get(y).add(x);
		}
		
		visited = new boolean[N+1];
		dfs(a);
		if (ans == 0) {
			System.out.println(-1);
		}else
			System.out.println(ans);
	}

	private static void dfs(int start) {
		if (start == b) {
			ans = cnt;
			return;
		}
		
		visited[start] = true;
		
		for(int value: tree.get(start)) {
			if (!visited[value]) {
				cnt++;
				dfs(value);
				cnt--;						//	졸라중요 dfs라 깊이 쭉 갈때 만약 아니면 돌아오면서 잘못갔던길 --해줘야함
			}
		}
	}

}
