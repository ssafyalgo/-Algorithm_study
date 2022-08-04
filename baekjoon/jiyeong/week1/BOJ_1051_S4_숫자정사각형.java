import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M;
	static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		int max = 1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				int size = squareSize(i,j);
				if(max<size) {max = size;}
			}
		}
		
		System.out.println(max*max);
	}
	
	static int squareSize(int r, int c) {
		int maxSize = 0;
		int size = 0;
		while(0<=r+size&&r+size<N&&0<=c+size&&c+size<M) {
			if(map[r][c]==map[r+size][c]&&
			map[r+size][c]==map[r][c+size]&&
			map[r][c+size]==map[r+size][c+size]) {
				maxSize = size;
			}
			size++;
		}
		return maxSize+1;
	}
}
