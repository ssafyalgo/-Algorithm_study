import java.util.*;
import java.io.*;

public class Solution {
	
	static class Misang{
		int r,c,cnt,d,num;

		public Misang(int r, int c, int cnt, int d, int num) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.d = d;
			this.num = num;
		}
		
	}
	
	static int[] dr = {-1,1,0,0};// - 상 하 좌 우
	static int[] dc = {0,0,-1,1};// - 상 하 좌 우
	static int[] reversedic = {1,0,3,2};

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz;
		int TC = Integer.parseInt(br.readLine());
		for(int t=1;t<=TC;t++) {
			stz = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(stz.nextToken());
			int M = Integer.parseInt(stz.nextToken());
			int K = Integer.parseInt(stz.nextToken());
			Misang[] misangs = new Misang[K];
			for(int i=0;i<K;i++) {
				stz = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(stz.nextToken());
				int c = Integer.parseInt(stz.nextToken());
				int cnt = Integer.parseInt(stz.nextToken());
				int d = Integer.parseInt(stz.nextToken())-1;
				misangs[i] = new Misang(r,c,cnt,d,i);
			}
			
			//M시간 후 남아있는 미생물 수 구하기
			while(M-->0) {
				Misang[][] nextMap = new Misang[N][N];
				int[][] max= new int[N][N];
				for(int k=0;k<K;k++) {
					if(misangs[k]==null) continue;
					int nr = misangs[k].r+dr[misangs[k].d];
					int nc = misangs[k].c+dc[misangs[k].d];
					misangs[k].r = nr;
					misangs[k].c = nc;
					
					//약품속으로들감
					if(nr==0||nr==N-1||nc==0||nc==N-1) {
						misangs[k].d = reversedic[misangs[k].d];
						misangs[k].cnt = misangs[k].cnt/2;
						if(misangs[k].cnt==0) { misangs[k]=null; continue;}
					}
					
					//해당위치에 이미 누가 있음
					if(nextMap[nr][nc]!=null) {
						if(max[nr][nc]<misangs[k].cnt) {
							max[nr][nc] = misangs[k].cnt;
							misangs[k].cnt += nextMap[nr][nc].cnt;
							misangs[nextMap[nr][nc].num] = null;
							nextMap[nr][nc] = misangs[k];
						}else {
							nextMap[nr][nc].cnt += misangs[k].cnt;
							misangs[k]=null;
						}
					}else {
						nextMap[nr][nc]= misangs[k];
						max[nr][nc]= misangs[k].cnt;
					}
				}
			}
			int sum =0;
			for(int k=0;k<K;k++) {
				if(misangs[k]==null) continue;
				sum += misangs[k].cnt;
			}
			System.out.println("#"+t+" "+sum);
		}
	}
	
}
