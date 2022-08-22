package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/* 이 편집기는 영어 소문자만을 기록할 수 있고, 최대 600,000글자까지 입력가능하다.
 * 
 *  '커서'는 아래와 같은 위치에 위치할 수 있다. 
 *   - 문장의 맨 앞(첫 번째 문자의 왼쪽)
 *   - 문장의 맨 뒤(마지막 문자의 오른쪽)
 *   - 문장 중간 임의의 곳 (모든 연속된 두 문자 사이) 
 *   
 *   편집기 지원 명령어 
 *   - L : 커서를 왼쪽으로 한 칸 이동 (문장의 맨 앞 일시 무시)
 *   - D : 커서를 오른쪽으로 한 칸 이동 (문장의 맨 뒤 일시 무시) 
 *   - B : 커서 왼쪽에 있는 문자 삭제 (문장의 맨 앞 일시 무시)
 *   - P $ : $ 라는 문자를 커서 왼쪽에 추가 */

// Q. 모든 명령어를 수행하고 난 뒤 편집기에 입력되어 있는 문자열을 구하는 프로그램 
//    ! 명령어가 수행되기 전에 커서는 문장의 맨 뒤에 위치
public class Main_1406_에디터 {
	
	static StringBuilder sb = new StringBuilder();
	
	static String initStr;		// 초기 문자열
	static int N;				// 초기 문자열 길이 N
	static int M;				// 명령어 개수 M개 
	
	static Deque<Character> dequeL;
	static Deque<Character> dequeR;
	
	static Order[] order;		// 명령어 
	static class Order {
		char command; 			// 명령어 종류
		char para;				// P 명령어일 경우 추가할 문자 
		
		public Order(char command, char para) {
			super();
			this.command = command;
			this.para = para;
		}

		@Override
		public String toString() {
			return "Order [command=" + command + ", para=" + para + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		/* 입력 */
		initStr = br.readLine();
		N = initStr.length();
		M = Integer.parseInt(br.readLine()); 
		
		order = new Order[M];
		for (int i = 0; i < M; i++) {
			char[] temp = br.readLine().toCharArray();
			
			if (temp[0]=='P') order[i] = new Order(temp[0], temp[2]);
			else order[i] = new Order(temp[0], ' ');
		}
		// 확인 - 입력받은 명령어
		//System.out.println(Arrays.toString(order));
		
		/* 로직 */
		// 1) 2개의 덱큐에 문자 넣기 - 데크 가운데를 커서라고 생각
		dequeL = new LinkedList<>();
		dequeR = new LinkedList<>();
		inputQueue();
		// 2) 사용자 명령어 실행 
		orderExcutor();
		
		/* 출력 */
		//printDeque();
		printResult();
		System.out.println(sb);
	}

	// [@Method] 결과 출력 - 2개의 데크 출력
	//           ! pop(), removeFirst()로 출력 시 반만 출력됨 (뒤에꺼 안꺼내옴)
	private static void printResult() {
		for (Character q: dequeL) {
			sb.append(q);
		}
		for (Character q: dequeR) {
			sb.append(q);
		}
	}

	// [@Method] 데크 Deque 에 초기 문자열 넣기 
	//           명령어가 수행되기 전에 커서는 문장의 맨 뒤에 위치
	private static void inputQueue() {
		
		for (int i = 0; i < N; i++) {			// N = 초기 문자열 길이 
			dequeL.add(initStr.charAt(i));
		}
		//printDeque();
	}


	// [@Method] 명령어 실행 - 4종류 / L, D, B, P (x)
	private static void orderExcutor() {
		for (int i = 0; i < order.length; i++) {
			if (order[i].command=='L') {
				cursorLeftMove();
			}
			else if (order[i].command=='D') {
				cursorRightMove();
			}
			else if (order[i].command=='B') {
				cursorLeftRemove();
			}
			else if (order[i].command=='P') {
				cursorLeftAdd(order[i].para);
			}
		}
		
	}

	// [@Method] 커서 왼쪽에 문자 추가
	private static void cursorLeftAdd(char str) {
		dequeL.add(str);
	}

	// [@Method] 커서 왼쪽 문자 삭제
	private static void cursorLeftRemove() {
		if (dequeL.isEmpty()) return;
		dequeL.removeLast();
	}

	// [@Method] 커서 오른쪽 이동
	private static void cursorRightMove() {
		if (dequeR.isEmpty()) return;
		dequeL.addLast(dequeR.pollFirst());			// !! Deque 방향 조심
	}

	// [@Method] 커서 왼쪽 이동
	private static void cursorLeftMove() {
		if (dequeL.isEmpty()) return;
		dequeR.addFirst(dequeL.pollLast());			// !! Deque 방향 조심
	}
	
	// [@Method] 데크 Deque 출력
	private static void printDeque() {
		System.out.println("# Left Deque 확인: " + dequeL);
		System.out.println("# Right Deque 확인: " + dequeR);
	}

}
