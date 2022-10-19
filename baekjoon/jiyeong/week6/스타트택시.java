import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Client{
		int r, c;
		int gr,gc;
		public Client(int r, int c, int gr, int gc) {
			super();
			this.r = r;
			this.c = c;
			this.gr = gr;
			this.gc = gc;
		}
	} 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int F = Integer.parseInt(stz.nextToken());
		
		int[][] map = new int[N+2][N+2];
		for(int[] ma : map) {
			Arrays.fill(ma, -1);
		}
		for(int i=1;i<=N;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				int t = Integer.parseInt(stz.nextToken());
				if(t==1) t= -1;
				map[i][j] = t;
			}
		}
		
		stz = new StringTokenizer(br.readLine());
		int tr = Integer.parseInt(stz.nextToken());
		int tc = Integer.parseInt(stz.nextToken());
		int[] taxi = new int[] {tr,tc};
		Client[] clients = new Client[M+1];
		for(int i=1;i<=M;i++) {
			stz = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(stz.nextToken());
			int c = Integer.parseInt(stz.nextToken());
			int gr = Integer.parseInt(stz.nextToken());
			int gc = Integer.parseInt(stz.nextToken());
			clients[i] = new Client(r,c,gr,gc);
			map[r][c]=i;
		}
		
		while(M-->0) {
			//가장 가까운 고객 찾기
			int[] clientLocation = new int[2];
			int clientDistance = findClient(map, taxi, clientLocation);
			if(clientDistance==-1) {System.out.println(-1); return;}
			Client client = clients[map[clientLocation[0]][clientLocation[1]]];
			//고객으로 이동하기
			F -= clientDistance;
			map[clientLocation[0]][clientLocation[1]] = 0;
			taxi = clientLocation;
			if(F<=0) {System.out.println(-1); return;}
			//고객에서 목적지로 가는 최단경로 찾기
			int[] goalLocation = new int[]{client.gr, client.gc};
			int goalDistance = findGoal(map, taxi, goalLocation);
			if(goalDistance==-1) {System.out.println(-1); return;}
			//목적지로 이동하기
			F -= goalDistance;
			taxi = goalLocation;
			//가는길에 연료 다썻는지 확인하기
			if(F<0) {System.out.println(-1); return;}
			//연료충전하기
			F += goalDistance*2;
		}
		System.out.println(F);
	}

	static int[] dr = {-1,0,0,1};
	static int[] dc = {0,-1,1,0};
	
	static public int findGoal(int[][] map, int[] taxi, int[] goal) {
		if(taxi[0]==goal[0]&&taxi[1]==goal[1]) {
			return 0; 
		}
		LinkedList<int[]> q = new LinkedList<>();
		boolean[][] visit = new boolean[22][22];
		visit[taxi[0]][taxi[1]]=true;
		q.add(taxi);
		int depth =0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			depth++;
			while(size-->0) {
				int[] cur = q.poll();
				
				for(int d=0;d<4;d++) {
					int nr = cur[0]+dr[d];
					int nc = cur[1]+dc[d];
					if(map[nr][nc]!=-1&&!visit[nr][nc]) {
						visit[nr][nc]=true;
						q.add(new int[] {nr,nc});
						if(nr==goal[0]&&nc==goal[1]) {
							return depth;
						}
					}
				}
			}
		}
		return -1;
	}
	
	static public int findClient(int[][] map, int[] taxi, int[] location) {
		if(map[taxi[0]][taxi[1]]!=0) { 
			location[0]=taxi[0];
			location[1]=taxi[1];
			return 0; 
		}
		LinkedList<int[]> q = new LinkedList<>();
		boolean[][] visit = new boolean[22][22];
		visit[taxi[0]][taxi[1]]=true;
		q.add(taxi);
		int depth =0;
		
		while(!q.isEmpty()) {
			PriorityQueue<int[]> pq = new PriorityQueue<>(
					(a,b)->{
				if(a[0]==b[0]) {
					return a[1]-b[1];
				}
				return a[0]-b[0];
			});
			int size = q.size();
			depth++;
			while(size-->0) {
				int[] cur = q.poll();
				
				for(int d=0;d<4;d++) {
					int nr = cur[0]+dr[d];
					int nc = cur[1]+dc[d];
					if(map[nr][nc]!=-1&&!visit[nr][nc]) {
						visit[nr][nc]=true;
						q.add(new int[] {nr,nc});
						if(map[nr][nc]!=0) {
							pq.add(new int[] {nr,nc});
						}
					}
				}
			}
			if(!pq.isEmpty()) {
				int[] temp = pq.poll();
				location[0] = temp[0];
				location[1] = temp[1];
				return depth;
			}
		}
		return -1;
	}
}