import java.util.*;
import java.io.*;

public class Main {

	static int N, MAX_AGE=1100;
	static int[][] A,food;
	static TreeMap<Integer,Integer>[][] trees; //(age,count)
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());
		A = new int[N][N];
		for(int i=0;i<N;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				A[i][j] = Integer.parseInt(stz.nextToken());
			}
		}
		food = new int[N][N];
		for(int i=0;i<N;i++) {
			Arrays.fill(food[i], 5);
		}
		trees = new TreeMap[N][N];//TreeMap은 자동 오름차순 정렬
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				trees[i][j] = new TreeMap<>();
			}
		}
		while(M-->0) {
			stz = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(stz.nextToken())-1;
			int c = Integer.parseInt(stz.nextToken())-1;
			int age = Integer.parseInt(stz.nextToken());
			trees[r][c].put(age,1);
		}
		
		int year = 0;
		while(year<K) {
			//봄,여름
			springAndSummer();
			//가을
			fall();
			//겨울
			winter();
			year++;
		}
		
		int count = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				for(Integer age:trees[i][j].keySet()) {
					count += trees[i][j].get(age);
				}
			}
		}
		System.out.println(count);
	}

	private static void winter() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				food[i][j] += A[i][j];
			}
		}
	}

	static int[] dr = {-1,-1,-1,0,0,1,1,1};
	static int[] dc = {-1,0,1,-1,1,-1,0,1};
	private static void fall() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				for(Integer age:trees[i][j].keySet()) {
					if(age%5!=0) continue;
					//8방으로 아기나무생성
					for(int d=0;d<8;d++) {
						int nr = i+dr[d];
						int nc = j+dc[d];
						if(!(0<=nr&&nr<N&&0<=nc&&nc<N)) continue;
						if(trees[nr][nc].containsKey(1)) {
							trees[nr][nc].put(1,trees[nr][nc].get(1)+trees[i][j].get(age));
						}else {
							trees[nr][nc].put(1, trees[i][j].get(age));
						}
					}
				}
			}
		}
	}
	private static void springAndSummer() {
		TreeMap<Integer,Integer>[][] afterSummer = new TreeMap[N][N];//TreeMap은 자동 오름차순 정렬
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				afterSummer[i][j] = new TreeMap<>();
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int dead = 0;
				for(Integer age:trees[i][j].keySet()) {
					if(food[i][j]<age) {dead += (age/2)*trees[i][j].get(age); continue;}
					int maximum = food[i][j]/age;
					int want = trees[i][j].get(age);
					afterSummer[i][j].put(age+1, maximum>=want?want:maximum);
					food[i][j] -= maximum>=want?want*age:maximum*age;
					dead += maximum>=want?0:(want-maximum)*(age/2);
				}
				//갱신하기
				food[i][j]+=dead;
			}
		}
		trees = afterSummer;
	}
	
}