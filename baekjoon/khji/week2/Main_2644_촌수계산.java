package algo0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2644_촌수계산 {
	
	static int n;
	static int[][] arr;
	static int[] visited;
	static int result=-1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n+1][n+1];
		visited = new int[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int parents = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			arr[parents][child] = 1;
			arr[child][parents] = 1;
		}
		System.out.println(dfs(a,b,0));
	}
	
	private static int dfs(int a, int b, int cnt) {
		
		if(a == b) {
			return cnt;			
		}
		
		visited[a] = 1;
		
		for(int i=1; i<=n; i++) {
			if(arr[a][i]==1 && visited[i]!=1) {
				int result = dfs(i,b,cnt+1);
				if(result!=-1) {
					return result;
				}
			}
		}
		
		return -1;
		
	}
}
