import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 현재막을 안바꾸고 다음으로
 * ,현재막을 A로 바꾸고 다음으로
 * ,현재막을 B로 바꾸고 다음으로 
 */
public class Solution_2112_보호필름 {
	static int ans, D, W, K;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[D][W];
			
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = K+1;
			if(K==1) {
				ans = 0;
			} else {
				int[][] tmp = new int[D][W];
				for (int i = 0; i < D; i++) {
					System.arraycopy(map[i], 0, tmp[i], 0, W);
				}
				
				dfs(0, 0, tmp); //현재 깊이, 약품투여 횟수, 바뀐 필름 배열
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void dfs(int dep, int cnt, int[][] tmp) {
		if(cnt>=ans) return;
		
		if(cnt==K) { //약품투여는 K까지 가능 dep가 d까지 왔을 경우 K번 투여하면 됨.
			ans = ans>K?K:ans;
			return;
		}
		
		if(isOk(tmp)) {
			ans = ans>cnt?cnt:ans;
			return;
		}
		
		for (int i = dep; i < D; i++) {
			//현재 행 A약품 투여
			Arrays.fill(tmp[i], 0);
			dfs(i+1, cnt+1, tmp);
			
			//현재 행 B약품 투여 
			Arrays.fill(tmp[i], 1);
			dfs(i+1, cnt+1, tmp);
			
			for (int j = 0; j < W; j++) {
				tmp[i][j] = map[i][j];
			}
		}
	}

	private static boolean isOk(int[][] tmp) {
		int cur = 0;
		int cnt = 1;

		for (int i = 0; i < W; i++) {
			cur = tmp[0][i];
			cnt = 1;
			
			for (int j = 1; j < D; j++) {
				if(cur == tmp[j][i]) { //같으면 개수 증가
					cnt++;
				} else { //다르면 현재 값으로 바꾸고 cnt는 1로
					cur = tmp[j][i];
					cnt = 1;
					if(D-j<K) break;
				}
				
				if(cnt==K) break; //K만큼 되면 다음 열로 
			}
			
			if(cnt!=K) { //한 열을 다 확인했는데 cnt가 K보다 적을 경우 false
				return false;
			}
		}
		return true;
	}

}
