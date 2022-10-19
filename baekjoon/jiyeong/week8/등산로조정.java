import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N, K;
	static int[][] map;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz;
		int T = Integer.parseInt(br.readLine());
		for(int t =1;t<=T;t++) {
			stz = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stz.nextToken());
			K = Integer.parseInt(stz.nextToken());
			map = new int[N][N];
			int max = Integer.MIN_VALUE;
			for(int i=0;i<N;i++) {
				stz = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(stz.nextToken());
					if(max<map[i][j]) max = map[i][j];
				}
			}
			
			//등산로는 가장 높은 봉우리에서 시작
			int ans = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j]==max) {
						boolean[][] visit = new boolean[N][N];
						ans = Math.max(ans, DFS(i,j,map[i][j],0,1,visit));
					}
				}
			}
			
			System.out.println("#"+t+" "+ans);
		}
	}
	
	static int DFS(int r, int c, int h, int k, int len, boolean[][] visit) {
		visit[r][c]=true;
		int maxlen = len;
		//k를 사용하지 않기
		for(int d=0;d<4;d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(check(nr,nc)&&h>map[nr][nc]&&!visit[nr][nc]) {
				visit[nr][nc]=true;
				maxlen = Math.max(maxlen, DFS(nr,nc,map[nr][nc],k,len+1,visit));
				visit[nr][nc]=false;
			}
		}
		//k를 사용하지 않았다면 사용하기
		if(k==0) {
			for(int d=0;d<4;d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(check(nr,nc)&&h<=map[nr][nc]&&h>map[nr][nc]-K&&!visit[nr][nc]) {
					visit[nr][nc]=true;
					maxlen = Math.max(maxlen, DFS(nr,nc,h-1,1,len+1,visit));
					visit[nr][nc]=false;
				}
			}
		}
		
		return maxlen;
	}
	
	private static boolean check(int nr, int nc) {
		return (0<=nr&&nr<N&&0<=nc&&nc<N);
	}

}
