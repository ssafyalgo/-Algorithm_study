package java1007.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* N x N 정사각형 벌통
 * - 각 칸의 숫자는 벌통에 있는 꿀의 양을 나타낸다.
 * - 꿀의 양은 서로 다를 수 있다.
 * 
 * 다음과 같은 과정으로 벌꿀을  채취하여 최대한 많은 수익을 얻으려고 한다.
 * - 두명의 일꾼이 있다. 
 * 1) 각각의 일꾼은 가로로 연속되도록 M개의 벌통을 선택하고, 선택한 벌통에서 꿀을 채취할 수 있다.
 *    ! 단, 두명의 일꾼이 선택한 벌통은 서로 겹치면 안된다.
 * 2) 각각의 일꾼은 채취한 꿀을 용기에 담는다.
 *    ! 단, 서로 다른 벌통에서 채취한 꿀이 섞이면 안되므로, 하나의 벌통에서 채취한 꿀은 하나의 용기에 담아야 한다.
 *    - 하나의 벌통에서 꿀을 채취할 때, 벌통에 있는 모든 꿀을 한번에 채취해야 한다.
 *    - 두 일꾼이 채취할 수 있는 꿀의 최대 양은 C 이다.
 *    - 채취할 수 있는 최대 꿀의 양을 넘어갈 경우 벌통 중 일부를 선택하여 꿀을 채취해야 한다.
 * 3) 채취한 꿀은 시장에 팔리게 된다.
 *    - 각 용기에 있는 꿀의 양의 제곱만큼의 수익이 생긴다. */

// Q. 두 일꾼이 꿀을 채취하여 얻을 수 있는 수익의 합이 최대가 되는 경우를 찾고, 그 때의 최대 수익을 출력하는 프로그램 
public class Solution_2115_벌꿀채취 {
	
	static int N;				// 벌통들의 크기 N 
	static int M;				// 선택할 수 있는 벌통의 개수 M개
	static int C;				// 채취할 수 있는 꿀의 최대 양 C
	static int[][] map;
	static int[][] copyMap;
	static int result; 			// 두 일꾼이 꿀을 채취하여 얻을 수 있는 최대 수익

	static int max;
	static Point[] selectHoneys;
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point{" +
					"r=" + r +
					", c=" + c +
					'}';
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		int T = Integer.parseInt(br.readLine()); 
		for (int t = 1; t <= T; t++) {
			
			/* 입력 */
			st = new StringTokenizer(br.readLine()); 
			N = Integer.parseInt(st.nextToken()); 
			M = Integer.parseInt(st.nextToken()); 
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			copyMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					// copyMap 0으로 초기화
					copyMap[i][j] = 0;
				}
			}
			// 확인
			//printMap();


			/* 로직 */
			result = Integer.MIN_VALUE;
			max = -1;
			// 1. 두 명의 일꾼이 가로로 연속되는 M개의 벌통 선택 ex. M=2이면, 각 일꾼 당 가로로 연속되는 2개의 벌통 선택 = 총 4개 (조합 사용)
			selectHoneys = new Point[2];
			selectHoneyCombi(0, 0);
			// 2. 최대 양 C에 넘어가지 않도록 꿀 채취
			// 3. 수익 계산
			// 2-3번 부분조합 사용


			/* 출력 */
			System.out.println("#" + t + " " + result);
		}
		

	}

	// [@Method] 두 명의 일꾼이 가로로 연속되는 M개의 벌통 선택
	//           모든 M개의 벌통이 아닌 선택할 벌통의 기준점 선택
	private static void selectHoneyCombi(int cnt, int start) {
		// End 조건 - 두 일꾼이 M개의 벌통 선택
		if (cnt==2) {
			if (isAble()) {
				/*System.out.println("#21# 선택한 벌통");
				printMap(copyMap);*/

				// 꿀 채취 + 수익 계산하러 가기
				result = Math.max(result, getHoneyMoney());
			}
			return;
		}

		// 조합 - 순서 X, 중복 X
		for (int i = start; i < N*N; i++) {
			int r = i / N;
			int c = i % N;
			//System.out.println("#21# 선택한 좌표 - r: " + r + " c: " + c);

			copyMap[r][c] = map[r][c];
			selectHoneys[cnt] = new Point(r, c);
			selectHoneyCombi(cnt+1, i+M);

			// 원복
			copyMap[r][c] = 0;
		}
	}

	// [@Method] 최대 양 C에 넘어가지 않도록 꿀 채취 + 수익 계산
	// - selectHoneys : 선택한 벌통의 시작점
	private static int getHoneyMoney() {
		int money = 0;

		// 2명의 일꾼
		for (int i = 0; i < 2; i++) {
			max = -1;

			// 일꾼이 선택한 꿀통의 꿀을 배열에 저장 = workerHoney
			Point worker = selectHoneys[i];
			int[] workerHoney = new int[M];

			int r = worker.r;
			int c = worker.c;
			workerHoney[0] = map[r][c];
			for (int j = 1; j < M; j++) {
				c += 1;

				workerHoney[j] = map[r][c];
			}
			/*System.out.println("#21# 일꾼이 채취할 수 있는 벌꿀");
			System.out.println(Arrays.toString(workerHoney));*/

			setToMax(workerHoney);								// !max 변수 초기화 = 채취할 수 있는 꿀 중 max값으로 초기화 (2개의 수를 더해도 C값을 넘을 수 있으므로)
			//System.out.println("#21# max 초기화 확인: " + max);

			// 채취할 수 있는(C 이하의) 최대 꿀의 양 채취 + 수익계산
			getProfitSubset(workerHoney, 0, 0, 0);		// 결과 max 전역변수에 저장
			money += max;
			//System.out.println("#21# 최대 수익: " + money + " max: " + max);
		}

		return money;
	}

	// [@Mehtod] max 변수 채취할 수 있는 꿀 중 max값으로 초기화
	private static void setToMax(int[] workerHoney) {
		for (int honey: workerHoney) {
			max = Math.max(max, (honey*honey));
		}
	}

	// [@Method] 수익 게산
	private static void getProfitSubset(int[] workerHoney, int index, int sum, int profit) {
		// End 조건
		if (index==workerHoney.length) {
			if (sum <= C)
				max = Math.max(max, profit);
			return;
		}

		// 부분집합
		// i) 선택
		getProfitSubset(workerHoney, index+1, sum+workerHoney[index], profit+(workerHoney[index]*workerHoney[index]));

		// ii) 선택 X
		getProfitSubset(workerHoney, index+1, sum, profit);
	}


	// [@Method] 선택한 벌꿀 통을 기준으로 가로 M개 선택 가능한지 체크
	private static boolean isAble() {
		for (Point honey: selectHoneys) {
			if (!(honey.c + (M-1) < N)) {            // 가로 M개 선택 시 배열 범위 체크
				return false;
			}
		}
			
		return true;
	}

	// [@Method] Map 출력
	private static void printMap(int[][] m) {
		System.out.println("#21# Map 출력");
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(m[i]));
		}
		System.out.println();
	}

}


/*
1
4 2 13
6 1 9 7
9 8 5 8
3 4 5 3
8 2 6 7

1
4 3 13
6 1 9 7
9 8 5 8
3 4 5 3
8 2 6 7
*/
