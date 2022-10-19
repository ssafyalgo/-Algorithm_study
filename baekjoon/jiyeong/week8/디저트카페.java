import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int N,ans;
	static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz;
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i=0;i<N;i++) {
				stz = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(stz.nextToken());
				}
			}
			
			ans = -1;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					for(int ts=0;ts<4;ts++) {
						boolean[] drtchk = new boolean[101];
						int[] start = {i,j};
						drtchk[map[i][j]]=true;
						DFS(start,start,0,drtchk, tristep[ts],0);
					}
				}
			}
			
			System.out.println("#"+t+" "+ans);
		}
	}
	
	static int[] dr = {-1,-1,1,1};
	static int[] dc = {-1,1,-1,1};
	static int[][] tristep = {{0,2,3,1},{0,1,2,3},{1,3,2,0},{2,3,1,0}};
	
	static void DFS(int[] cur, int[] start,int dep, boolean[] drtchk, int[] ts, int sum) {
		if(dep==4) return;
		boolean[] newChk = Arrays.copyOf(drtchk, drtchk.length);
		for(int i=1;i<N;i++) {
			int nr = cur[0]+ dr[ts[dep]]*i;
			int nc = cur[1] +dc[ts[dep]]*i;
			if(!check(nr,nc)) break;
			if(dep==3&&nr==start[0]&&nc==start[1]) { 
				ans = Math.max(ans, sum+i);	
			}
			if(newChk[map[nr][nc]]) break;
			
			newChk[map[nr][nc]]=true;
			DFS(new int[] {nr,nc},start,dep+1,newChk,ts,sum+i);
		}
	}
	private static boolean check(int nr, int nc) {
		return (0<=nr&&nr<N&&0<=nc&&nc<N);
	}
}