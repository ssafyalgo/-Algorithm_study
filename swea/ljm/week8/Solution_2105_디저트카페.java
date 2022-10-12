import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 반드시 대각선 이동만 가능하다.
 * 이전 가게 안되고, 같은 개수의 디저트를 파는 곳도 안된다.
 * 출발한 곳을 되돌아올 때 가장 많이 먹을 수 있는 디저트 수 구하라.
 * 
 * DFS를 통해서 다음 위치가 처음 위치면 값 저장
 * 
 * 디저트를 먹을 수 없으면 -1
 * 
 * 4각형은 어느 방향으로 시작 하던 상관 없이  반드시 4방향으로만 해결 가능함. 오위,왼위,왼아래,오아래(순서는 달라도 이 4개로 전부 표현 가능..)
 */
public class Solution_2105_디저트카페 {
	static int[][] map;
	static int[] dr = {-1, 1, 1, -1};
	static int[] dc = {1, 1, -1, -1};
	static int N, sr, sc, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sr = i;
					sc = j;
					boolean[] scnt = new boolean[101];
					DFS(i, j, 0, 0, scnt); //시작위치(r,c), 방문 횟수, 방문한 곳
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans==0?-1:ans).append("\n");
		}
		
		System.out.println(sb);
	}
	private static void DFS(int r, int c, int cnt, int d, boolean[] sameCnt) {
		if(r==sr&&c==sc&&d==3&&cnt>=3) { //갔다 왔다 2번만에 가능한 경우X
			ans = ans<cnt?cnt:ans;
			return;
		}
		
		for (int i = d; i < 4; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			
			if(nr<0||nr>=N||nc<0||nc>=N||sameCnt[map[nr][nc]]) continue;

			sameCnt[map[nr][nc]] = true;
			DFS(nr, nc, cnt+1, i, sameCnt);
			sameCnt[map[nr][nc]] = false;
		}
	}
	
}
