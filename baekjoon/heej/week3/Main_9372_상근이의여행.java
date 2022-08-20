package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* N개국 여행 
 * 최대한 적은 종류의 비행기를 타고 국가들을 이동한다. 
 * 한 국가에서 다른 국가로 이동할 때 다른 국가를 거쳐 가도 (심지어 이미 방문한 국가라도) 된다. */
// Q. 상근이가 모든 국가를 여행하기 위해 타야 하는 비행기 종류의 최소 개수 
public class Main_9372_상근이의여행 {

	static int N;					// 국가의 수 
	static int M;					// 비행기의 종류
	
	static ArrayList<ArrayList<Integer>> airSchedule;
	static boolean[] visited; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine()); 
		for (int t = 1; t <= T; t++) {
			
			/* 입력 */
			st = new StringTokenizer(br.readLine()); 
			N = Integer.parseInt(st.nextToken()); 
			M = Integer.parseInt(st.nextToken()); 
			
			// 비행스케쥴, a와 b를 왕복하는 비행기 
			airSchedule = new ArrayList<>(); 
			for (int i = 0; i < N+1; i++) {
				airSchedule.add(new ArrayList<>());
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine()); 
				int x = Integer.parseInt(st.nextToken()); 
				int y = Integer.parseInt(st.nextToken()); 
				
				/* 양방향 */
				airSchedule.get(x).add(y);
				airSchedule.get(y).add(x);
			}
			// 확인 
			//airSchedulePrint();
			
			/* 로직 +  출력 */
			// 모든 국가 방문, 방문한 국가 다시 방문해도 OK 
			// 가장 적은 종류의 비행기 구하기
			// !! 최소 신장 트리 : 가장 적은 개수의 간선으로 모든 정점을 연결 -> 간선의 개수 = 정점의 개수 - 1
			System.out.println((N-1));
			
		}
		
	}


	// [@Method] 비행스케쥴 출력
	private static void airSchedulePrint() {
		for (ArrayList<Integer> air: airSchedule) {
			System.out.println(air);
		}
		System.out.println();
	}

}
