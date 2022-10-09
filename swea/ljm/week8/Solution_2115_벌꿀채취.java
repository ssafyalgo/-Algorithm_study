import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 각각 일꾼은 가로로 연속적인 M개의 벌통선택
 * 
 * 최대 양은 C
 * C가 넘어가면 M개중 가장 높은거
 * C는 항상 모든 채취가능한 꿀보다 큼!
 * Combi로 채취할 라인 선택
 * 순열 형태로 라인 중에서 채취할 벌통 선택!
 * 
 * 담긴 벌꿀은 제곱 해서 계산
 */
public class Solution_2115_벌꿀채취 {
	static int ans, C, sum;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken()); //최대 가로길이
			C = Integer.parseInt(st.nextToken()); //최대 양
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
				}
			}
			ans = 0;
			combi(0, 0, 0, N, M, new int[2][2]);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void combi(int sr, int sc, int cnt, int N, int M, int[][] person) {
		if(cnt==2) {
			int tot = 0;
			for (int i = 0; i < 2; i++) {
				sum = 0;
				cal(0, 0, 0, M, person[i][0], person[i][1]);
				
				tot += sum;
			}
			
			ans = Math.max(tot, ans);
			
			return;
		}
		
		for (int i = sr; i < N; i++) {
			for (int j = sc; j <= N-M; j++) {
				person[cnt][0] = i;
				person[cnt][1] = j;
				combi(i, j+M, cnt+1, N, M, person);
			}
			
			sc = 0;
		}
		
	}

	private static void cal(int sum2, int honey, int cnt, int M, int r, int c) {
		if(cnt==M) {
			sum = sum<sum2?sum2:sum;
			return;
		}
		
		int cur = map[r][c];

		if(honey+cur <= C) { //이전 꿀 포함해서 채취하는 경우 
			cal(sum2+cur*cur, honey+cur, cnt+1, M, r, c+1);
		}
		
		cal(sum2, honey, cnt+1, M, r, c+1); //현재 꿀을 채취하지 않을경우
	}

}
