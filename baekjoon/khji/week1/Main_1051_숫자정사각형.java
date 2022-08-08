package algo0805;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1051_숫자정사각형 {
	
	static int rectangle[][];
	public static void main(String[] args) throws Exception {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			rectangle = new int[N][M];
			for(int i=0; i<N; i++) {
				char c[] = br.readLine().toCharArray();
				for(int j=0; j<M; j++) {
					rectangle[i][j] = c[j] - '0';
				}
			}
			
			int ans = 0;
			if(N>M) {			
				OUTER:
				for(int k=M; k>0; k--) {
					for(int i=0; i<=N-k; i++) {
						for(int j=0; j<=M-k; j++) {
							if(k==1) {
								ans =1;
								break OUTER;
							}
							if(check(i,j,k)) {
								ans = k*k;
								break OUTER;
							}
						}
					}
				}
			}else {
				OUTER:
				for(int k=N; k>0; k--) {
					for(int i=0; i<=N-k; i++) {
						for(int j=0; j<=M-k; j++) {
							if(k==1) {
								ans =1;
								break OUTER;
							}
							if(check(i,j,k)) {
								ans = k*k;
								break OUTER;
							}
						}
					}
				}
			}
			
			System.out.println(ans);
			
	}

	private static boolean check(int i, int j, int k) {
		int num = rectangle[i][j];
		return num == rectangle[i][j+k-1] && num == rectangle[i+k-1][j] && num == rectangle[i+k-1][j+k-1];
	}
}
