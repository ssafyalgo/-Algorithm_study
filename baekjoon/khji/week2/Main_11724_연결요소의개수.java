package algo0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11724_연결요소의개수 {
	
	static int[][] map;
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		map = new int[N+1][N+1];
		visited = new int[N+1];
		
		for(int i=0; i<M; i++) {
			String[] str = br.readLine().split(" ");
			int r = Integer.parseInt(str[0]);
			int c = Integer.parseInt(str[1]);
			map[r][c] = 1;
			map[c][r] = 1;
		}
		//입력완료
		
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			if(visited[i]==0) {
				cnt++;
				dfs(i);
			}
		}
		System.out.println(cnt);
	}

	private static void dfs(int s) {
		
		visited[s] = 1;
		for(int i=1; i<map[s].length; i++) {
			if(map[s][i]==1 && visited[i]!=1) {								
				dfs(i);
			}
		}
	}
}
