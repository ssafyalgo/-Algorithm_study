package java08_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*   숫자 정사각형 S4 25
 * 
 *   NxM 크기의 직사각형 중, 정사각형 형태로 네 꼭짓점이 같은 수인 경우 가장 큰 크기의 정사각형 크기를 구하기. 
 *   -> 브루트 포스
 *   0,0~ N-1,M-1까지 구하기
 *   N과 M중 작은 수부터 0까지  -> while //->정사각형이 될 수 있는 가장 큰 수부터 탐색
 *   모든위치에서 정사각형 탐색  
 */
public class Main_1051_숫자정사각형_S4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j)-97;
			}
		}
		
		int max = 0; //가장 긴 변의 길이 저장
		N -= 1; //가장자리를 구할 필요 X->가장자리는 어차피 1이기 때문
		M -= 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int len = N-i<M-j?N-i:M-j; //1. 현재 위치로부터 정사각형이 될 수 있는 최대 길이 구하기.
				
				if(len<max) continue;
				
				while(len>max) { // 4. 현재 MAX보다 len의 길이가 크면 반복
					int cur = arr[i][j]; // 2. 각 꼭짓점 위치
					int down = arr[i+len][j];
					int right = arr[i][j+len];
					int diagonal = arr[i+len][j+len];
					
					if(cur==down&&cur==right&&cur==diagonal) { // 3. 정사각형인지 비교
						max = max<len?len:max;
					}
					len--; //5. 길이를 줄여 나감
				}
			}
		}
		
		max+=1; //좌표사이의 거리+1
		System.out.println((max)*(max));
	}
}