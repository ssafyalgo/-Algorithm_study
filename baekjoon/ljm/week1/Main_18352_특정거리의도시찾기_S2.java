package java08_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 특정 거리의 도시 찾기 S2 35
 * 
 * dijkstra를 통해서 각 위치별 최단 경로 저장
 * 오름 차순 출력
 */
public class Main_18352_특정거리의도시찾기_S2 {
	static ArrayList<ArrayList<Node>> graph;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //노드 수
        int M = Integer.parseInt(st.nextToken()); //간선 수 
        int K = Integer.parseInt(st.nextToken()); //목표 거리
        int X = Integer.parseInt(st.nextToken()); //출발 도시
        
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) { //노드(도시)수 만큼 세팅
			graph.add(new ArrayList<>());
		}
        
        for (int i = 0; i < M; i++) { //노드마다 간설 연결
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, 1));
		}
        
        Dijkstra(N, K, X);
        
        System.out.println(sb);
	}

	private static void Dijkstra(int N, int k, int x) {
		PriorityQueue<Node> pq = new PriorityQueue<>(); //그냥 Queue해도 됨. => 모든 거리 구하는 거라 최단거리가 필요가 없음.
		int[] dis = new int[N+1];
		boolean[] visited  = new boolean[N+1];
		Arrays.fill(dis, 99999999);
		dis[x] = 0;
		visited[x] = true;
		pq.offer(new Node(x, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			for(Node next:graph.get(cur.x)) {
				if(dis[next.x]<=dis[cur.x]+next.d) continue; //현재 저장된 거리보다 더 크면 넘어감
				
				dis[next.x] = dis[cur.x]+next.d;
				pq.offer(new Node(next.x, cur.d+1));
			}
		}
		boolean chk = false;
		for(int i=1; i<=N; i++) {
			if(dis[i]==k) {
				sb.append(i+"\n");
				chk = true;
			}
		}
		if(!chk) sb.append(-1);
		
		return;
	}
	
	static class Node implements Comparable<Node>{
		int x; //위치
		int d; //거리
		public Node(int x, int d) {
			super();
			this.x = x;
			this.d = d;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.d-o.d;
		}
	}
}
