import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/* 22:35
 * 1. 택시에서 가장가까운 손님 먼저 태운다.
 * 행 - 열
 * 
 * 
 */
public class Main_19238_스타트택시_G2 {
	static int[][] map;
	static ArrayList<int[]> start;
	static ArrayList<int[]> end;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int fuel = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		Queue<taxi> q = new ArrayDeque<>();
		int sr = Integer.parseInt(st.nextToken())-1;
		int sc = Integer.parseInt(st.nextToken())-1;
		q.offer(new taxi(sr,sc));
		
		start = new ArrayList<>();
		end = new ArrayList<>();
		int pnums = -1; //손님 번호
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int pr = Integer.parseInt(st.nextToken())-1;
			int pc = Integer.parseInt(st.nextToken())-1;
			int er = Integer.parseInt(st.nextToken())-1;
			int ec = Integer.parseInt(st.nextToken())-1;
			
			if(sr==pr && sc==pc) { //시작점에 손님 있을 경우
				pnums = i;
			} else {
				start.add(new int[] {pr, pc});
			}
			end.add(new int[] {er, ec});
		}
		
		System.out.println(BFS(N, M, fuel, pnums, q));
	}
	
	private static int BFS(int N, int M, int fuel, int pnums, Queue<taxi> q) {
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, -1, 0, 1};
		int cnt = 0;
		PriorityQueue<person> pq = new PriorityQueue<>(); 
		boolean[][] v;
		
		while(true) { //태울 수 있는 손님이 없을 때 까지
			int dis = 0;
			v = new boolean[N][N];
			boolean pchk = false; //손님이 탑승해있는 지
			if(pnums!=-1) pchk = true;
			
			while(!q.isEmpty()) {
				int size = q.size();
				
				dis++;
				fuel--;
				if(fuel<0) return -1;
				
				loop: for (int i = 0; i < size; i++) {
					taxi cur = q.poll();
					
					for (int j = 0; j < 4; j++) {
						int nr = cur.r+dr[j];
						int nc = cur.c+dc[j];
						
						if(nr<0||nr>=N||nc<0||nc>=N||v[nr][nc]||map[nr][nc]==1) continue;
						
						v[nr][nc] = true;
						
						if(!pchk) {//손님 없는 상태에서 
							if((pnums=check(nr, nc, start))>=0) { //다음 지점이 손님위치일 경우
								pq.offer(new person(nr, nc, pnums));
							} else {
								q.offer(new taxi(nr, nc));
							}
						} else { 
							if(endcheck(pnums, nr, nc, end)) { //손님 태우고 목표지점에 도착했을 때
								fuel += dis*2; //구해진 거리*2
								cnt++;
								
								if(cnt==M) return fuel;
								
								end.remove(pnums);
								q.clear();
								q.offer(new taxi(nr, nc));
								pnums = -1;
								dis = 0;
								v = new boolean[N][N];
								pchk = false;
								
								if((pnums = check(nr, nc, start))>=0) { //도착 위치에 손님이 있을 경우
									pq.offer(new person(nr, nc, pnums));
								}
								
	 							break loop;
							} else {
								q.offer(new taxi(nr, nc));
							}
						}
					}
				}
				
				if(!pq.isEmpty()) { //손님이 있으면
					int r = pq.peek().r;
					int c = pq.peek().c;
					pnums = pq.peek().idx;
					
					start.remove(pnums);
					
					pq.clear(); //손님 택시 위치 초기화
					q.clear();
					
					v = new boolean[N][N];
					q.offer(new taxi(r, c)); 
					
					map[r][c] = 0;
					dis = 0;
					pchk = true;
				}
			}
			
			if(q.isEmpty()) {
				return -1;
			}
		}
		
	}

	private static boolean endcheck(int pnums, int nr, int nc, ArrayList<int[]> end2) {
		return nr==end2.get(pnums)[0] && nc==end2.get(pnums)[1];
	}

	private static int check(int nr, int nc, ArrayList<int[]> loc) {
		int size = loc.size();
		for (int i = 0; i < size; i++) {
			if(loc.get(i)[0]==nr && loc.get(i)[1]==nc) {
				return i;
			}
		}
		return -1;
	}

	static class taxi {
		int r, c;

		public taxi(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static class person implements Comparable<person>{
		int r, c, idx;

		public person(int r, int c, int idx) {
			super();
			this.r = r;
			this.c = c;
			this.idx = idx;
		}

		@Override
		public int compareTo(person o) {
			int rd = this.r-o.r;
			if(rd==0) {
				return this.c-o.c;
			}
			return rd;
		}
	}
}
