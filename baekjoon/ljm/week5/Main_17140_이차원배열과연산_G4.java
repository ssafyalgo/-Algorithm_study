package java09_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_17140_이차원배열과연산_G4 {
	
	static class Num implements Comparable<Num>{
		int num, cnt;

		public Num(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Num o) {
			int cntd = this.cnt - o.cnt;
			if(cntd==0) {
				return this.num-o.num;
			}
			return cntd;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken())-1;
		int c = Integer.parseInt(st.nextToken())-1;
		int k = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[100][100];
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Map<Integer, Integer> map = new TreeMap<>(); //숫자와 개수 저장할 변수
		PriorityQueue<Num> pq = new PriorityQueue<>(); //숫자와 개수를 정렬
		
		int ans = 101;
		//r최고값, c최고값, 시간
		int mr, mc;
		mr = mc = 3;
		int time = 0;
		
		
		while(true) {
			if(time>100) {
				ans = -1;
				break;
			}
			if(arr[r][c]==k) {
				ans = time;
				break;
			}
			
			if(mr>=mc) { //행이 더 크면
				for (int i = 0; i < mr; i++) {
					map.clear();
					for (int j = 0; j < mc; j++) {
						int num = arr[i][j];
						if(num==0) continue;
						//숫자와 개수 세기
						if(map.get(num)==null) {
							map.put(num, 1);
						} else {
							map.put(num, map.get(num)+1);
						}
					}
					//map에 저장된 숫자와 개수를 정렬
					for(int n:map.keySet()) {
						pq.offer(new Num(n, map.get(n)));
					}
					
					int csize = map.size();
					mc = Math.max(csize*2, mc);
					
					int j = 0;
					while(!pq.isEmpty()) { //정렬된 순으로 arr 변경
						arr[i][j++] = pq.peek().num;
						arr[i][j++] = pq.poll().cnt;
					}
					
					for (int j2 = csize*2; j2 <= mc; j2++) { //현재 행보다 최대 행이 작으면 차이나는 구간은 0값으로 
						arr[i][j2] = 0;
					}
				}
			} else {
				for (int j = 0; j < mc; j++) {
					map.clear();
					for (int i = 0; i < mr; i++) {
						int num = arr[i][j];
						if(num==0) continue;
						
						if(map.get(num)==null) {
							map.put(num, 1);
						} else {
							map.put(num, map.get(num)+1);
						}
					}
					for(int n:map.keySet()) {
						pq.offer(new Num(n, map.get(n)));
					}
					
					int rsize = map.size();
					mr = Math.max(rsize*2, mr);
					
					int i = 0;
					while(!pq.isEmpty()) {
						arr[i++][j] = pq.peek().num;
						arr[i++][j] = pq.poll().cnt;
					}
					
					for (int j2 = rsize*2; j2 <= mr; j2++) {
						arr[j2][j] = 0;
					}
				}
			}
			
			time++;
		}
		
		System.out.println(ans);
	}
	
}
