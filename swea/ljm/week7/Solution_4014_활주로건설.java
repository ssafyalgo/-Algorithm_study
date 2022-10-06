import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014_활주로건설 {
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
//		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= 1; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 0;
			findcol(N, X, map);
			System.out.println("#######");
			findrow(N, X, map);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void findrow(int N, int X, int[][] map) {
		for (int i = 0; i < N; i++) {
			int cnt = 0;
			int cur = map[0][i];
			for (int j = 0; j < N; j++) {
				if(Math.abs(cur-map[j][i])>=2) break;
				
				if(cur<map[j][i]) {
					if(cnt<X) break; 
					cnt=0;
				}
				
				if(cur>map[j][i]) {
					if(j+X>N) break;
					
					boolean chk = false;
					int tmp = map[j][i];
					
					for (int j2 = j; j2 < j+X; j2++) {
						if(tmp!=map[j2][i]) {
							chk = true;
							break;
						}
					}
					
					if(chk) break;
					
					j += (X-1);
					cnt = -1;
				}
				
				cur = map[j][i];
				cnt++;
				if(j==N-1) {
					System.out.println(i);
					ans++;
				}
			}
		}
	}

	private static void findcol(int N, int X, int[][] map) {
		for (int i = 0; i < N; i++) {
			int cnt = 1;
			int cur = map[i][0];
			for (int j = 1; j < N; j++) {
				if(Math.abs(cur-map[i][j])>=2) break; //차이가 2면 무조건 안됨!
				
				if(cur<map[i][j]) {
					if(cnt<X) break; //다음이 1높은데 길이가 안나오는 경우
					cnt = 0; //길이가 X와 같거나 큰 경우 경사로 놓을 수 있음. -> 다음으로 넘어가기 때문에 길이 초기화
				}
				
				if(cur>map[i][j]) { //다음이 더 낮은 경우 
					if(j+X>N) break; //남은 거리가 X보다 짧으면 무조건 안됨.
					
					boolean chk = false;
					int tmp = map[i][j];
					
					for (int j2 = j; j2 < j+X; j2++) { //X만큼 다음을 조사함.
						if(tmp!=map[i][j2]) { //X만큼 다음 높이 중에서 다른 높이가 있으면 무조건 안됨.
							chk = true;
							break;
						}
					}
					if(chk) break;
					
					j += (X-1); //다 통과되면 경사로 놓을 수 있음. 현재 위치와 길이 초기화.
					cnt = -1; //현재 위치까지 사용 됐기 때문에 -1로 초기화
				}
				
				cur = map[i][j];
				cnt++;
				if(j==N-1) { //j가 끝까지 왔으면 통과 됨.
					System.out.println(i);
					ans++;
				}
			}
		}
	}
}
