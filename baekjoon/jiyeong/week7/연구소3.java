import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static int N,M,Zero;
	static ArrayList<int[]> virus;
	static int ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		Zero = 0;
		map = new int[N][N];
		virus = new ArrayList<>();
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = str.charAt(j*2)-'0';
				if(map[i][j]==2) {
					virus.add(new int[] {i,j});
				}
				else if(map[i][j]==0) {
					Zero++;
				}
			}
		}
		
		//virus.size 중에 M 개를 선택하기
		ans = Integer.MAX_VALUE;
		combination(0, 0,new int[M]);
		System.out.println(ans==Integer.MAX_VALUE?-1:ans);
	}

	private static void combination(int idx, int cnt, int[] select) {
		if(cnt==M) {
			int time = simmul(select);
			if(ans>time) {ans=time;}
			return;
		}
		
		for(int i=idx;i<virus.size();i++) {
			select[cnt] = i;
			combination(i+1, cnt+1, select);
		}
	}

	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	private static int simmul(int[] select) {
		int time=0, zero=0;
		boolean[][] visit = new boolean[N][N];
		Queue<int[]> q = new ArrayDeque<>();
		for(int s:select) {
			int[] vrs= virus.get(s);
			visit[vrs[0]][vrs[1]]=true;
			q.add(new int[] {vrs[0],vrs[1],0});
		}
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				
				if(check(nr,nc)&&map[nr][nc]!=1&&!visit[nr][nc]) {
					visit[nr][nc]=true;
					q.add(new int[] {nr,nc,cur[2]+1});
					if(map[nr][nc]==0) zero++;
					if(map[nr][nc]==0&&cur[2]+1>time) time = cur[2]+1;
				}
			}
		}
		 
		return zero==Zero?time:Integer.MAX_VALUE;
	}

	private static boolean check(int nr, int nc) {
		return (0<=nr&&nr<N&&0<=nc&&nc<N);
	}

}
