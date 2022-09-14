import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 120
 * 
 * 가장 처음 양분은 모든 칸에 5만큼 !
 * 
 * 봄 = 자신의 나이만큼 양분을 먹고 나이가 1증가
 * 하나의 칸에 여러개의 나무가 있으면 나이가 어린 나무부터 양분을 먹는다.
 * 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 약분을 먹지 못하고 즉시 죽는다.
 * 
 * 여름 = 봄에 죽은 나무가 양분으로 변하게 된다. 각각 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가 됨.
 * 
 * 가을 = 나무가 번식한다. 번식하는 나무는 나이가 5배수 
 * 인접한 8개의 칸에 나무가 1생김.
 * 
 * 겨울에는 땅에 양분을 추가.
 * 
 * K년이 지난 후 상도의 땅에 살아있는 나무의 개수를 구하라.
 * 
 * dijkstra -> 매년 즉 pq.size 마다 while을 돌려서 가장 나이가 적은 나무부터 양분먹음
 * 죽게 되면 해당 좌표에 나이의/2 양분을 저장. -> 새로운 arr
 * 현재 나이가 5의 배수면 인접한 8칸에 나이가 1인 나무를 생성.
 */
public class Main_16235_나무재테크_G3 {
	static class tree implements Comparable<tree>{
		int r, c, age;

		public tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(tree o) {
			return this.age - o.age;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		PriorityQueue<tree> pq = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			pq.offer(new tree(r, c, z));
		}
		
		System.out.println(BFS(N, M, K, pq, arr));
	}

	private static int BFS(int N, int M, int K, PriorityQueue<tree> pq, int[][] arr) {
		int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
		
		int[][] tmp = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmp[i][j] = 5;
			}
		}
		
		ArrayList<tree> tree = new ArrayList<>();
		ArrayList<tree> deadtree = new ArrayList<>();
		int year = 0;
		
		while(!pq.isEmpty()) {
			int qsize = pq.size();
			
			for (int i = 0; i < qsize; i++) {
				tree cur = pq.poll();
				int r = cur.r;
				int c = cur.c;
				int age = cur.age;
				//봄
				if(tmp[r][c]>=age) {
					tmp[r][c]-=age;
					tree.add(new tree(r, c, age+1));
					//가을
					if((age+1)%5==0) {
						for (int j = 0; j < 8; j++) {
							int nr = r+dr[j];
							int nc = c+dc[j];
							if(nr<0||nr>=N||nc<0||nc>=N) continue;
							
							tree.add(new tree(nr, nc, 1));
						}
					}
				} else {
					//여름
					deadtree.add(new tree(r,c,age/2));
				}
			}
			//겨울
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					tmp[i][j] += arr[i][j];
				}
			}
			//양분 추가
			for (tree t:deadtree) {
				tmp[t.r][t.c] += t.age; //tmp에 양분 추가
			}
			//새로운 나무 
			for (tree t:tree) {
				pq.offer(t);
			}
			
			year++;
			if(year==K) return tree.size();
			
			tree.clear();
			deadtree.clear();
		}
		
		return 0;
	}

}
