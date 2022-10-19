import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static boolean[][] blue;
	static boolean[][] green;
	static int ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz;
		int N = Integer.parseInt(br.readLine());
		blue = new boolean[4][6];
		green = new boolean[6][4];
		ans =0;
		while(N-->0) {
			stz = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(stz.nextToken());
			int x = Integer.parseInt(stz.nextToken());
			int y = Integer.parseInt(stz.nextToken());
			
			playBlue(t,x);
			playGreen(t,y);
		}
		System.out.println(ans);
		int count =0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<6;j++) {
				if(blue[i][j]) count++;
				if(green[j][i]) count++;
			}
		}
		System.out.println(count);
	}

	private static void playGreen(int t, int y) {
		//t=1일 때 y열으로 1개
		//t=2일 때 y열,y+1열으로 2개
		//t=3일 때 y열으로 2개
		//블록 추가
		if(t==1) {
			int px=-1;
			while(px+1<6&&!green[px+1][y]) px++;
			green[px][y] = true;
		}
		else if(t==2) {
			int px=-1;
			while(px+1<6&&!green[px+1][y]&&!green[px+1][y+1]) px++;
			green[px][y] = true;
			green[px][y+1] = true;
		}
		else if(t==3) {
			int px=-1;
			while(px+2<6&&!green[px+1][y]&&!green[px+2][y]) px++;
			green[px][y] = true;
			green[px+1][y] = true;
		}
		
		//점수체크
		boolean[] flag = new boolean[6];
		for(int r=0;r<6;r++) {
			int chk = 0;
			for(int c=0;c<4;c++) {
				if(green[r][c]) chk++;
			}
			if(chk==4) {flag[r] = true; ans++;}
		}
		//당기기
		boolean[][] temp = new boolean[6][4];
		for(int r=5,nr=5;r>=0;r--) {
			if(!flag[r]) {
				for(int c=0;c<4;c++) {
					temp[nr][c] = green[r][c];
				}
				nr--;
			}
		}
		green = temp;
		
		//흰구간체크
		int cnt =0;
		for(int r=0;r<=1;r++) {
			boolean chk = false;
			for(int c=0;c<4;c++) {
				if(green[r][c]) chk=true;
			}
			if(chk) cnt++;
		}
		//당기기
		if(cnt>0) {
			temp = new boolean[6][4];
			for(int r=5-cnt,nr=5;r>=0;r--,nr--) {
				for(int c=0;c<4;c++) {
					temp[nr][c] = green[r][c];
				}
			}
			green = temp;
		}
	}

	private static void playBlue(int t, int x) {
		//t=1일 때 x행으로 1개
		//t=2일 때 x행으로 2개
		//t=3일 때 x행,x+1행으로 2개
		
		//블록 추가
		if(t==1) {
			int py=-1;
			while(py+1<6&&!blue[x][py+1]) py++;
			blue[x][py] = true;
		}
		else if(t==2) {
			int py=-1;
			while(py+2<6&&!blue[x][py+1]&&!blue[x][py+2]) py++;
			blue[x][py] = true;
			blue[x][py+1] = true;
		}
		else if(t==3) {
			int py=-1;
			while(py+1<6&&!blue[x][py+1]&&!blue[x+1][py+1]) py++;
			blue[x][py] = true;
			blue[x+1][py] = true;
		}
		
		//점수체크
		boolean[] flag = new boolean[6];
		for(int c=0;c<6;c++) {
			int chk = 0;
			for(int r=0;r<4;r++) {
				if(blue[r][c]) chk++;
			}
			if(chk==4) {flag[c] = true; ans++;}
		}
		//당기기
		boolean[][] temp = new boolean[4][6];
		for(int c=5,nc=5;c>=0;c--) {
			if(!flag[c]) {
				for(int r=0;r<4;r++) {
					temp[r][nc] = blue[r][c];
				}
				nc--;
			}
		}
		blue = temp;
		
		//흰구간체크
		int cnt =0;
		for(int c=0;c<=1;c++) {
			boolean chk = false;
			for(int r=0;r<4;r++) {
				if(blue[r][c]) chk=true;
			}
			if(chk) cnt++;
		}
		//당기기
		if(cnt>0) {
			temp = new boolean[4][6];
			for(int c=5-cnt,nc=5;c>=0;c--,nc--) {
				for(int r=0;r<4;r++) {
					temp[r][nc] = blue[r][c];
				}
			}
			blue = temp;
		}
	}

}
