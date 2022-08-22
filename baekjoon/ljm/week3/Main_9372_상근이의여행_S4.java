package java08_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9372_상근이의여행_S4 {
	static int[] par;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			par = new int[N+1];
			for (int i = 0; i <= N; i++) {
				par[i] = i;
			}
			
			int ans = 0;
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(find(a)!=find(b)) { //두개의 정점의 부모가 서로 다르면  합친다. 
					union(a, b);
					ans++;
				}
				
			}

			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void union(int a, int b) { 
		a = find(a); // a의 조상 찾기
		b = find(b); // b의 조상 찾기
		
		if(a < b) par[b] = a; //b의조상이 더 크면 b에 a의 조상을 넣기. 루트를 가장 작은 수로 생각했을 때 
		else par[a] = b;
	}
	private static int find(int a) {
		if(a==par[a]) return a;
				
		return par[a] = find(par[a]); //find를 할 때마다 조상 업데이트.
	}

}
