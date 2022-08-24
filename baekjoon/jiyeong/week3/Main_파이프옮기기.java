package baekjoon;

import java.io.*;
import java.util.*;

public class Main_파이프옮기기 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer stz;
		int[][] map = new int[N][N];
		for(int i=0;i<N;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(stz.nextToken());
			}
		}
		
		long[][] dp1 = new long[N+1][N+1];//가로
		long[][] dp2 = new long[N+1][N+1];//세로
		long[][] dp3 = new long[N+1][N+1];//대각선
		
		dp1[0][1]=1;
		for(int r=0;r<N;r++) {
			for(int c=0;c<N;c++) {
				if(map[r][c]!=1) {
					if(0<=c-1) dp1[r][c] += dp1[r][c-1] + dp3[r][c-1];
					if(0<=r-1) dp2[r][c] += dp2[r-1][c] + dp3[r-1][c];
					if(0<=r-1&&0<=c-1&&map[r-1][c]==0&&map[r][c-1]==0) dp3[r][c] += dp1[r-1][c-1] + dp2[r-1][c-1]+ dp3[r-1][c-1];	
				}
			}
		}
		
		System.out.println(dp1[N-1][N-1]+dp2[N-1][N-1]+dp3[N-1][N-1]);
	}

}
