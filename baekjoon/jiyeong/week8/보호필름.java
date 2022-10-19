import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int D,W,K,ans;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz ;
		int T = Integer.parseInt(br.readLine());

		for(int t=1;t<=T;t++) {
			stz = new StringTokenizer(br.readLine());
			D = Integer.parseInt(stz.nextToken());
			W = Integer.parseInt(stz.nextToken());
			K = Integer.parseInt(stz.nextToken());
			
			map = new int[D][W];
			for(int i=0;i<D;i++) {
				String str = br.readLine();
				for(int j=0;j<W;j++) {
					map[i][j] = str.charAt(j*2)-'0';
				}
			}
			ans = D;
			subset(0,new boolean[D],0);
			
			System.out.println("#"+t+" "+ans);
		}
	}
	private static void subset(int dep, boolean[] select, int cnt) {
		if(dep==D) {
			if(isPass(0,select,new char[D])) ans = Math.min(ans, cnt);
			return;
		}
		select[dep] = false;
		subset(dep+1, select, cnt);
		select[dep] = true;
		subset(dep+1, select, cnt+1);
	}
	
	private static boolean isPass(int dep, boolean[] select, char[] medicine) {
		if(dep==D) {
			return check(select, medicine);
		}
		
		if(select[dep]) {
			medicine[dep]='A';
			if(isPass(dep+1, select,medicine)) return true;
			medicine[dep]='B';
			if(isPass(dep+1, select,medicine)) return true;
		}else {
			if(isPass(dep+1, select,medicine)) return true;
		}
		return false;
	}
	private static boolean check(boolean[] select, char[] medicine) {
		for(int i=0;i<W;i++) {
			boolean flag = false;
			int cur = select[0]?medicine[0]-'A':map[0][i];
			int cnt = 1;
			for(int j=1;j<D;j++) {
				int type = select[j]?medicine[j]-'A':map[j][i];
				if(cur==type) { cnt++; }
				else {
					if(cnt>=K) {flag=true; break;}
					else {cur=type; cnt=1;}
				}
			}
			if(cnt>=K) flag=true;
			if(!flag) return false;
		}
		return true;
	}
}