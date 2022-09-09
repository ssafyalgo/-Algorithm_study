import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_색종이붙이기 {

	static int min;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		min = 26;
		map = new int[10][10];
		for(int i=0;i<10;i++) {
			String str= br.readLine();
			for(int j=0;j<10;j++) {
				map[i][j] = str.charAt(2*j)-'0';
			}
		}
		dfs(0,new int[] {0,5,5,5,5,5},new boolean[10][10],0);
		
		System.out.println(min==26?-1:min);
	}

	private static void dfs(int num, int[] cnt, boolean[][] visit, int sum) {
		while(!(num>=100||map[num/10][num%10]==1&&!visit[num/10][num%10])) num++;
		int r = num/10, c= num%10;
		
		if(min<sum) {return;}
		if(num==100) { if(sum<min) {min = sum;} return;}
		
		for(int paper=1;paper<=5;paper++) {
			//paper크기의 종이를 붙일 수 있는가 확인
			boolean flag = true;
			for(int i=0;i<paper;i++) {
				if(r+paper-1>=10||c+i>=10||map[r+paper-1][c+i]==0||visit[r+paper-1][c+i]) {flag = false; break;}
				if(r+i>=10||c+paper-1>=10||map[r+i][c+paper-1]==0||visit[r+i][c+paper-1]) {flag = false; break;}
			}
			if(flag==false) break;
			//paper크기의 종이가 있는가?
			if(cnt[paper]==0) continue;
			//종이 붙이기
			for(int i=0;i<paper;i++) {
				for(int j=0;j<paper;j++) {
					visit[r+i][c+j] = true;
				}
			}
			cnt[paper]--;
			//dfs
			dfs(num+1, cnt, visit, sum+1);
			//종이 때기
			cnt[paper]++;
			for(int i=0;i<paper;i++) {
				for(int j=0;j<paper;j++) {
					visit[r+i][c+j] = false;
				}
			}
		}
	}

}