import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_7576_토마토 {
	
	static int zeroCnt = 0;
	static int[][] map;
	static int N,M;
	static int[] dy = {0,0,-1,1};
	static int[] dx = {-1,1,0,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		Deque<Point> dq = new ArrayDeque<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num==0) { // 안 익은 토마토의 개수를 세줌
					zeroCnt++;
				}else if(num==1) { // 익은 토마토면 queue에 넣어줌
					dq.offer(new Point(j,i));
				}
			}
		}//입력완료
		
		//안 익은 토마토가 없다면 0을 출력하고 끝냄
		if(zeroCnt==0) {
			System.out.println(0);
			return;
		}
		
		int result = 0;
		while(!dq.isEmpty()) {
			for(int i=0; i<dq.size(); i++) {
				Point cur = dq.poll();
				int y = cur.y;
				int x = cur.x;
				for(int d=0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if(check(ny,nx) && map[ny][nx]==0) {
						map[ny][nx] = map[y][x]+1;
						result = map[ny][nx];
						zeroCnt--;
						dq.offer(new Point(nx,ny));
					}
				}
			}
		}
		
		if(zeroCnt>0) {
			System.out.println(-1);
		}else {
			System.out.println(result-1);
		}
	}
	private static boolean check(int ny, int nx) {
		
		return 0 <= ny && N > ny && 0 <= nx && M > nx && map[ny][nx] != -1;
	}
}
