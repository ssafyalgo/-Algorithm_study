import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 조합(combi)로 바이러스 정하고 
 * BFS로 바이러스 퍼트리기
 */
public class Main_17142_연구소3_G4 {
	static int[][] map;
	static int N, M, ans;
	static ArrayList<int[]> viruses;
	static int blanks, virescnt;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		blanks = 0;
		viruses = new ArrayList<>();
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num==2) {
					virescnt++;
					viruses.add(new int[] {i, j});
				}
				if(num==0) blanks++;
			}
		}
		
		ans = 999999;
		if(blanks>0) {
			combi(0, 0, new int[M][2]);
		} else {
			ans = 0;
		}
		
		System.out.println(ans==999999?-1:ans);
	}

	private static void combi(int start, int cnt, int[][] nums) {
		if(cnt==M) {
			ans = Math.min(BFS(nums), ans);
			return;
		}
		
		for (int i = start; i < virescnt; i++) {
			nums[cnt][0] = viruses.get(i)[0];
			nums[cnt][1] = viruses.get(i)[1];
			
			combi(i+1, cnt+1, nums);
		}
	}

	private static int BFS(int[][] nums) {
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		Queue<int[]> q = new ArrayDeque<>();
		
		boolean[][] v = new boolean[N][N];
		for (int i = 0; i < M; i++) {
			q.offer(new int[] {nums[i][0], nums[i][1]});
			v[nums[i][0]][nums[i][1]] = true;
		}
		int time = 0;
		int vcnt = blanks;

		while(!q.isEmpty()) {
			int qsize = q.size();
			
			for (int i = 0; i < qsize; i++) {
				int[] cur = q.poll();
				
				for (int j = 0; j < 4; j++) {
					int nr = cur[0]+dr[j];
					int nc = cur[1]+dc[j];
					
					if(nr<0||nr>=N||nc<0|nc>=N||v[nr][nc]||map[nr][nc]==1) continue;
					
					v[nr][nc] = true;
					q.offer(new int[] {nr, nc});
					
					if(map[nr][nc]==0) { //빈칸 개수 빼주기
						vcnt--;
					}
					
					if(vcnt==0) {
						return time+1;
					}
				}
			}
			
			time++;
		}
		
		return 999999;
	}

}
