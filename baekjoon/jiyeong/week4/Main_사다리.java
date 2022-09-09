import java.util.*;
import java.io.*;

public class Main_사다리 {

	static int N,M,H;
	static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		H = Integer.parseInt(stz.nextToken());
		map = new int[H][N-1];
		while(M-->0) {
			stz = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stz.nextToken())-1;
			int b = Integer.parseInt(stz.nextToken())-1;
			map[a][b] = 1;
		}
		
		for(int cnt =0; cnt<=3; cnt++) {
			if(dfs(0,cnt,0)) {
				System.out.println(cnt);
				return;
			}
		}
		System.out.println(-1);
		
	}

	private static boolean dfs(int cnt, int select, int v) {
		if(cnt==select) {
			return simmul();
		}
		
		//0번부터 N*(H-1)-1 번까지 돌면서 체크하기
		for(;v<(N-1)*H;v++) {
			int r = v/(N-1); 
			int c = v%(N-1);
			//현재칸이 1이면 다음칸 조사
			if(map[r][c]==1) continue;
			//현재칸의 오른쪽칸이나 왼쪽칸이 1이면 다음칸 조사
			if(c+1<N-1&&map[r][c+1]==1||c-1>=0&&map[r][c-1]==1) continue;
			//선택하기
			map[r][c] = 1;
			if(dfs(cnt+1, select, v+1)) return true;;
			map[r][c] = 0;
		}
		
		return false;
	}

	private static boolean simmul() {
		for(int c=0;c<N;c++) {
			int line = c;
			for(int r=0;r<H;r++) {
				if(line<N-1&&map[r][line]==1) line++;
				else if(0<=line-1&&map[r][line-1]==1) line--;
			}
			if(line!=c) return false;
		}
		return true;
	}

}
