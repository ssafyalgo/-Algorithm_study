import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* i번 세로선의 결과가 i번이 나오도록 조작 하려면, 추가해야 하는 가로선 개수의 최솟 값을.
 * 3개 이상을 필요로 하면 -1을, 
 * combi를 통해 연결할 사다리 조합 만들기.
 */
public class Main_15684_사다리조작_G3 {
	static int[][] ladder;
	static int linecnt;
	static int N,H;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		ladder = new int[H][N];
		
		int lnum = 1; //몇번째 연결 된 숫자인지 판단. 1,2 와 3,4가 연결 되었으면 구분해주기 위함 -> 1,1,2,2 형태로
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken());
			
			ladder[a][b] = lnum; //ex) 1과 2가 이어져있다.
			ladder[a][b-1] = lnum++; //
		}
		
		linecnt = 1000;
		
		backtrack(0, 0, 0, lnum);
		
		System.out.println(linecnt==1000?-1:linecnt);
	}
	
	private static void backtrack(int r, int c, int cnt, int lnum) {
		if(play()) {
			linecnt = cnt;
			return;
		}
		
		if(cnt==3 || linecnt<=cnt) { //현재 저장된 개수보다 많거나 3개되면 false
			return;
		}
		
		for (int i = r; i < H; i++) {
			if(i>r) {
				c = 0;
			}
			for (int j = c; j < N-1; j++) {
				if(ladder[i][j]>=1) continue; //현재 위치가 이미 연결 되어 있으면
				if(ladder[i][j+1]>=1) continue; //가로 연속으로 사다리를 놓을 수 없기 때문에 바로 다음에도 연결되어 있으면 continue
				
				ladder[i][j] = lnum; //ex) 1과 2가 이어져있다.
				ladder[i][j+1] = lnum; //
				backtrack(i, j, cnt+1, lnum+1);
				
				ladder[i][j] = 0;
				ladder[i][j+1] = 0;
			}
			
		}
	}
	private static boolean play() {
		
		for (int i = 0; i < N; i++) { //N 개 전부 탐색 해봐야 함.
			int nr = 0;
			int nc = i;
			int cur = 0;
			
			while(true) { //사다리 내려가기
				cur = ladder[nr][nc];
				if(cur>=1) { //연결되어 있으면 좌,우 탐색해서 좌,우로 가기
					if(nc>0 && ladder[nr][nc-1]==cur) {
						nc--;
					} else if(nc<N-1 && ladder[nr][nc+1]==cur) {
						nc++;
					}
				}
				nr++; //밑으로 내려가기
				
				if(nr==H) { //맨밑에 도착하면
					if(nc!=i) return false; //출발지와 도착지가 같지 않으면 무조건 false
					else break; //아니면 다음으로 
				}
			}
		}
		
		return true;
	}

}
