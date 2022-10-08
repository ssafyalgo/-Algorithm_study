import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 등산로는 가장 높은 봉우리에서 시작 해야함.
 * 반드시 높은 지형에서 낮은 지형으로 가로 또는 세로
 * 긴 등산로를 만들기 위해 딱 한곳 정해서 최대 K만큼 지형을 깍는 공사를 할 수 있다.
 * !주의 1~K만큼 깍을 수 있음
 *
 * 1. 가장 높은 봉우리 위치 저장.
 * 2. 맵에서 K깎기
 * 3. q에 봉우리 넣고 BFS 시작
 * 
 * DFS로 갈때마다 깍을 수 있는지 체크하면 더 빠름!
 */
public class Solution_1949_등산로조성 {
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			int max = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
					max = max<num?num:max;
				}
			}
			
			ArrayList<int[]> bongs = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j]==max) {
						bongs.add(new int[] {i, j});
					}
				}
			}
			
			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int j2 = 1; j2 <= K; j2++) {
						map[i][j] -= j2;
						ans = Math.max(BFS(N, map, bongs), ans);
						map[i][j] += j2;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

	private static int BFS(int N, int[][] map, ArrayList<int[]> bongs) {
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		Queue<int[]> q = new ArrayDeque<>();
		for (int[] i: bongs) {
			q.offer(i);
		}
		
		int time = 0;
		while(!q.isEmpty()) {
			int qsize = q.size();
			
			for (int i = 0; i < qsize; i++) {
				int[] cur = q.poll();
				
				for (int j = 0; j < 4; j++) {
					int nr = cur[0]+dr[j];
					int nc = cur[1]+dc[j];
					
					if(nr<0||nr>=N||nc<0||nc>=N) continue;
					if(map[nr][nc]>=map[cur[0]][cur[1]]) continue;
					
					q.offer(new int[] {nr, nc});
				}
			}
			
			time++;
		}
		
		return time;
	}

}
