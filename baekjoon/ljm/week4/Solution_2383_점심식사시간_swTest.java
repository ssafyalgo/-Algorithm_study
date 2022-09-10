package study_test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_2383_점심식사시간_swTest {
	
	static class person implements Comparable<person> {
		int idx, dis;//좌표, 거리

		public person(int idx, int dis) {
			super();
			this.idx = idx;
			this.dis = dis;
		}

		@Override
		public int compareTo(person o) {
			return this.dis-o.dis;
		}
	}
	
	static ArrayList<int[]> stairloc;
	static ArrayList<int[]> personloc;
	
	static int ans = 99999;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			stairloc = new ArrayList<>();
			personloc = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());
					if(n==1) {
						personloc.add(new int[] {i, j}); //사람 위치
 					} else if (n>1) {
 						stairloc.add(new int[] {i, j, n}); // 계단 위치
 					}
				}
			}
			
			int psize = personloc.size();
			
			ans = 99999;
			subset(0, new boolean[psize], psize);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void subset(int cnt, boolean[] st1, int psize) {
		if(cnt==psize) {
			PriorityQueue<person> s1 = new PriorityQueue<>();
			PriorityQueue<person> s2 = new PriorityQueue<>();
			for (int i = 0; i < psize; i++) {
				if(st1[i]) {
					System.out.print(i+" ");
					s1.offer(new person(i, 
							getDis(personloc.get(i)[0], personloc.get(i)[1], stairloc.get(0)[0], stairloc.get(0)[1])));
				}
				else {
					s2.offer(new person(i, 
						getDis(personloc.get(i)[0], personloc.get(i)[1], stairloc.get(1)[0], stairloc.get(1)[1])));
				}
			}
			System.out.println("");
			
			int time1 = 0;
			int time2 = 0;

			if(!s1.isEmpty()) {
				time1 = play(s1, stairloc.get(0)[2]);
				if(time1==0) return;
			}
			if(!s2.isEmpty()) {
				time2 = play(s2, stairloc.get(1)[2]);
				if(time2==0) return;
			}
			System.out.println("#####"+time1+" "+time2+" "+ans);
			System.out.println("");
			ans = Math.min(ans, Math.max(time1, time2));
			return; 
		}

		st1[cnt] = true;
		subset(cnt+1, st1, psize);
		st1[cnt] = false;
		subset(cnt+1, st1, psize);
	}

	private static int play(PriorityQueue<person> s, int K) {
		int[] sstate = new int[3];
		int time = s.peek().dis;
		int dis = 0;
		
		while(!s.isEmpty()) {
			//계단 내려가기
			for (int i = 0; i < 3; i++) {
				if(sstate[i]>=1) {
					sstate[i]--;
				}
			}
			//계단입구에서 내려갈 수 있는 지 
			for (int i = 0; i < 3; i++) {
				//0이고 다음 사람이 있고, 대기하고 있으면
				if(sstate[i]==0 && !s.isEmpty() && time >= s.peek().dis) {
					s.poll();
					if(time+K>=ans) return 0;
					sstate[i] = K; //k값 sstate에 저장.
				}
			}
			time = Math.max(++time, dis+1);
		}
		
		return time+K;
	}

	private static int getDis(int i, int j, int k, int l) {
		return Math.abs(i-k)+Math.abs(j-l);
	}

}