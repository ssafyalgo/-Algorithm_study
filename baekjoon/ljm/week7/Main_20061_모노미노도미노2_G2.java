import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 블록은 11 21 12 있음
 * 빨간색 구역에서 블록을 생성하면 파랑(오른쪽)끝 + 초록(아래) 끝
 */
public class Main_20061_모노미노도미노2_G2{
	static int[][] map;
	static int N, M, point;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[8][6];//green도 회전하면 blue랑 같음
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			goBlue(t, r, c);
			
			if(t==2) { 
				if(c!=1) {
					goGreen(t, (c+2)%4, r); //t=2(가로 블록)일 때 그린 구역에는 c가1인 상황을 제외하고 2->0 , 0->2로 바뀜.
				} else {
					goGreen(t, c, r);
				}
			} else {
				goGreen(t, 3-c, r); //t바꾸기 2=3, 3=2
			}
		}
		
		int bcnt = cntblock();
		
		System.out.println(point);
		System.out.println(bcnt);
	}

	private static int cntblock() { //남은 블록 수 
		int cnt = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 6; j++) {
				if(map[i][j]>=1) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void goBlue(int t, int r, int c) {
		move(t, r);
		
		int rcnt = remove(5, 0, 0);
		for (int i = 0; i < rcnt; i++) { //삭제하기
			go(0); //삭제할게 있으면 나머지 이동
		}
		
		int sc = softchk(0);
		for (int i = sc; i >0; i--) {
			remove2(5, 0);
			go(0);
		}
	}
	
	private static void goGreen(int t, int r, int c) {
		if(t==3) t=2;
		else if(t==2) t=3;
		int g = 4; 
        
		move(t, r+g); //맨 처음 블록 이동
		
		int rcnt = remove(5, 0, g);
		for (int i = 0; i < rcnt; i++) { //블록 이동 후 삭제할 블록 찾고 삭제하기
			go(g); //삭제할게 있으면 나머지 이동
		}
        
		int sc = softchk(g); //연한위치에 블록 있는 지 체크
		for (int i = sc; i >0; i--) { 
			remove2(5, g); //맨 끝 삭제
			go(g); //한칸 이동
		}
	}
	
	private static void remove2(int end, int g) { //무조건 삭제 
		for (int i = 5; i >=end; i--) {
			for (int j = g; j < g+4; j++) {
				map[j][i] = 0;
			}
		}
	}

	private static int softchk(int g) { //연한색 체크
		int cnt = 0;
		boolean chk = false;
		
		for (int i = 0; i <= 1; i++) {
			chk = false;
			for (int j = g; j < 4+g; j++) {
				if(map[j][i]>=1) {
					chk = true;
					break;
				}
			}
			if(chk) cnt++;
		}
		return cnt;
	}

	private static void go(int g) {
		for (int i = 4; i >=0; i--) {
			for (int j = g; j < 4+g; j++) {
				if(map[j][i]==3) {
					if(map[j][i+1]==0 && map[j+1][i+1]==0) {
						map[j][i+1] = 3;
						map[j+1][i+1] = 4;
						map[j][i] = 0;
						map[j+1][i] = 0;
					}
				} else if (map[j][i]==2) {
					if(map[j][i+1]==0) {
						map[j][i+1] = 2;
						map[j][i] = 0;
					}
				} else if (map[j][i]==1) {
					if(map[j][i+1]==0) {
						map[j][i+1] = 1;
						map[j][i] = 0;
					}
				}
			}
		}
	}

	private static int remove(int start, int end, int g) {
		boolean chk = false;
		int cnt = 0;
		for (int i = start; i >=end; i--) {
			chk = false;
			for (int j = g; j < g+4; j++) {
				if(map[j][i]==0) {
					chk = true;
					break;
				}
			}
			if(!chk) {
				for (int j = g; j < g+4; j++) {
					map[j][i] = 0;
				}
				cnt++;
			}
		}
		
		point += cnt;
		return cnt;
	}

	private static int move(int t, int r) {
		int nc = 0;
		boolean chk = false;
		
		for (int i = 1; i < 6; i++) {
			nc = i;
			if(map[r][nc]>=1) {
				map[r][nc-1] = t;
				if(t==2) map[r][nc-2] = t;
				if(t==3) map[r+1][nc-1] = 4;
				chk = true;
				break;
			}
			if(t==3) {
				if(map[r+1][nc]>=1) {
					map[r][nc-1] = t;
					map[r+1][nc-1] = 4;
					chk = true;
					break;
				}
			}
		}
		
		if(!chk) {
			map[r][5] = t;
			if(t==2) {
				map[r][4] = t;
			}else if(t==3) {
				map[r+1][5] = 4;
			}
		}
		
		return nc-1;
	}
}