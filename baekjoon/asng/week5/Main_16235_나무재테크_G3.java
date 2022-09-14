package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_16235_나무재테크_G3 {

	static class Tree implements Comparable<Tree> {
		int x, y, age;

		public Tree(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}

	static int N, M, K;
	static int[][] A;
	static int[][] sangdo;
	static int[] dr = { 1, 0, -1, 0, 1, 1, -1, -1 };
	static int[] dc = { 0, 1, 0, -1, 1, -1, -1, 1 };
	static PriorityQueue<Tree> pq = new PriorityQueue<>();
	static ArrayList<Tree> temp = new ArrayList<>();
	static ArrayList<Tree> dietree = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		sangdo = new int[N][N];
		for (int[] a : sangdo) {
			Arrays.fill(a, 5);
		}

		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			pq.add(new Tree(x, y, z));
		}

		for (int i = 0; i < K; i++) {
			spring();
			summer();
			fall();
			winter();
		}
		
		System.out.println(pq.size());
	}

	private static void spring() {
		while (!pq.isEmpty()) {
			Tree t = pq.poll();
			int x = t.x;
			int y = t.y;
			int age = t.age;
			if (sangdo[x][y] >= age) {
				sangdo[x][y] -= age;
				temp.add(new Tree(x, y, age + 1));
			} else {
				dietree.add(t);
			}
		}

		for (Tree tree : temp) {
			pq.add(tree);
		}

		temp.clear();
	}

	private static void summer() {
		for (Tree tree : dietree) {
			int x = tree.x;
			int y = tree.y;
			int age = tree.age;

			sangdo[x][y] += (age / 2);
		}

		dietree.clear();
	}

	private static void fall() {
		while (!pq.isEmpty()) {
			Tree t = pq.poll();
			temp.add(t);

			int r = t.x;
			int c = t.y;
			int age = t.age;

			if (age % 5 == 0) {
				for (int d = 0; d < 8; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (nr < 0 || nc < 0 || nr >= N || nc >= N)
						continue;
					
					temp.add(new Tree(nr,nc,1));
				}
			}
		}
		
		for (Tree tree : temp) {
			pq.add(tree);
		}

		temp.clear();
	}

	private static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sangdo[i][j] += A[i][j];
			}
		}
		
	}

}