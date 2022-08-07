package algostudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node {										// 노드 클래스
	int x;
	int cost;

	Node(int x, int cost) {
		this.x = x;
		this.cost = cost;
	}
}

public class Main_18352_특정거리의도시찾기_S2_다익스트라시간초과 {

	static int N, M, K, X;							//	N:노드 수, M:간선 수, K:최소거리조건, X:출발노드
	static ArrayList<ArrayList<Node>> graph;		//	그래프
	static boolean[] visited;						// 	방문체크
	static int[] dist;								//	최소거리저장
	static boolean chk = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		graph = new ArrayList<ArrayList<Node>>();		//	그래프 객체 할당
		for (int i = 0; i <= N; i++) {					// 	그래프 초기화
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());	
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = 1;
			
			graph.get(a).add(new Node(b, cost));		//	a부터 b까지 cost는 항상 1
		}
		
		dist = new int[N + 1];					
		for (int i = 0; i <= N; i++) {					//	dist는 최소거리를 입력받아야 하므로 최대값으로 초기화
			dist[i] = Integer.MAX_VALUE;
		}
		dist[X] = 0;									//	시작노드는 자기 자신으로의 거리가 0 이므로 0 입력
		
		visited = new boolean[N + 1];
		
		Dijkstra();						
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <=N; i++) {
			if (dist[i] == K) {
				sb.append(i);
				sb.append("\n");
				chk = true;
			}
		}
		if (!chk) 
			System.out.println("-1");
	}
	
	private static void Dijkstra() {
		for (int i = 0; i < N; i++) {					//	노드의 수만큼 반복
			int nodeval = Integer.MAX_VALUE;			//	해당 노드가 가지고 있는 현재 비용
			int nodeidx = 0;							//	해당 노드의 인덱스
			
			for (int j = 1; j <= N; j++) {
				if (!visited[j] && dist[j] < nodeval) {	//	해당 노드를 방문하지 않았고, 현재 모든 거리비용 중 최솟값인 idx 찾는다.
					nodeval = dist[j];
					nodeidx = j;
				}
			}
			
			visited[nodeidx] = true;					//	최종 선택된 노드를 방문처리 한다.
			
			for (int j = 0; j < graph.get(nodeidx).size(); j++) {
				Node adjnode = graph.get(nodeidx).get(j);				//	인접 노드를 선택한다.
				if (dist[adjnode.x] > dist[nodeidx]+adjnode.cost) {		//	인접노드가 현재 가지는 최소 비용과 현재 선택된 노드로 거처가는 값을 비교하여 갱신한다.
					dist[adjnode.x] = dist[nodeidx]+adjnode.cost;
				}
			}
		}
	}
}
