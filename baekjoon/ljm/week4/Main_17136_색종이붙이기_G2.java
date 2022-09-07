import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/* 
 * 색종이 붙이기 40
 * 
 * backtrack
 */
public class Main_17136_색종이붙이기_G2 {
	static int mincnt = 26;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] arr = new int[10][10];
		int total = 0;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i][j] = num;
				total += num;
			}
		}
		
		int[] papercnt = new int[6];
		for (int i = 1; i <= 5; i++) {
			papercnt[i] = 5;
		}
		
		DFS(0, 0, total, papercnt, arr);
		
		System.out.println(mincnt==26?-1:mincnt);
	}
	private static void DFS(int cur, int cnt, int total, int[] papercnt, int[][] arr) {
		if(cnt>mincnt) return;
		
		if(total==0) {
			mincnt = mincnt>cnt?cnt:mincnt;
			return;
		}
		
		for (int i = cur; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(arr[i][j]==1) {
					for (int k = 1; k <= 5; k++) {
						if(i+k>10 || j+k>10) break; //범위 체크
						if(papercnt[k]==0) continue;
						
						if(chkpaper(i, j, k, arr)) {
							papercnt[k]--;
							DFS(i, cnt+1, total-k*k, papercnt, attach(i, j, arr, k, 0));
							
							arr = attach(i, j, arr, k, 1);
							papercnt[k]++;
						} else { //k가 안되면 그 위에는 전부 안됨
							break;
						}
					}
					return; //1찾으면 되던 안되던 무조건 break -> 1을 남길 이유가 없기 때문에.
				}
			}
		}
	}
	
	private static int[][] attach(int r, int c, int[][] arr, int k, int s) {
		for (int i = r; i < r+k; i++) {
			for (int j = c; j < c+k; j++) {
				arr[i][j] = s;
			}
		}
		return arr;
	}
	
	private static boolean chkpaper(int r, int c, int k, int[][] arr) {
		for (int i = r; i < r+k; i++) {
			for (int j = c; j < c+k; j++) {
				if(arr[i][j]==0) {
					return false;
				}
			}
		}
		return true;
	}

}
