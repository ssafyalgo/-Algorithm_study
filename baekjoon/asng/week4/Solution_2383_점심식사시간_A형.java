package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_2383_점심식사시간_A형 {

	static class Person {
		int x, y, t, downtime;

		public Person(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Person(int x, int y, int t, int downtime) {
			super();
			this.x = x;
			this.y = y;
			this.t = t;
			this.downtime = downtime;
		}

		public void down() {
			this.downtime--;
		}

		public void walk() {
			this.t--;
		}

	}

	static int N, personcnt, ans;
	static int[][] map;
	static ArrayList<Integer> s1, s2;
	static ArrayList<Person> p;
	static ArrayList<int[]> stairs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];

			p = new ArrayList<>();
			stairs = new ArrayList<>();
			personcnt = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						p.add(new Person(i, j));
						personcnt++;
					} else if (map[i][j] > 1) {
						stairs.add(new int[] { i, j, map[i][j] });
					}
				}
			}

			ans = Integer.MAX_VALUE;
			s1 = new ArrayList<>();
			s2 = new ArrayList<>();
			for (int i = 0; i <= personcnt; i++) {
				Combi(0, 0, i);
			}

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);

	}

	private static void Combi(int cnt, int start, int num) {
		if (cnt == num) {
			s2.clear();
			fills2();

			// s1, s2를 사용해 p1, p2에 넣어준다
			ArrayList<Person> p1 = new ArrayList<>();
			ArrayList<Person> p2 = new ArrayList<>();
			for (int idx : s1) {
				Person person = p.get(idx);
				int len = Math.abs(person.x - stairs.get(0)[0]) + Math.abs(person.y - stairs.get(0)[1]);
				p1.add(new Person(person.x, person.y, len, stairs.get(0)[2]));
			}
			for (int idx : s2) {
				Person person = p.get(idx);
				int len = Math.abs(person.x - stairs.get(1)[0]) + Math.abs(person.y - stairs.get(1)[1]);
				p2.add(new Person(person.x, person.y, len, stairs.get(1)[2]));
			}

			int time = 0, s1cnt = 0, s2cnt = 0;

			while (!p1.isEmpty() || !p2.isEmpty()) {
				if (time-1 >= ans) {
					return;
				}
				
				time++;
				
				ArrayList<Person> temp1 = new ArrayList<>();
				ArrayList<Person> temp2 = new ArrayList<>();
				// 얕은복사
				for (Person ps : p1) {
					temp1.add(ps);
				}
				for (Person ps : p2) {
					temp2.add(ps);
				}

				for (Person ps : temp1) {
					if (ps.t <= -1) {
						if (ps.t != Integer.MIN_VALUE && s1cnt < 3) { // 1번 대기 했고, 계단에 3명이 있는게 아니면
							ps.t = Integer.MIN_VALUE; // 내려가고 있는중인 사람
							s1cnt++; // 계단 3자리 중 한자리 차지
							if (ps.downtime == 0) {
								s1cnt--; // 계단 탈출했으니 자리 내주기
								p1.remove(ps); // 탈출했으니 사람명단에서 뺴주기
							} else
								ps.down(); // 만약 계단을 끝까지 못내려갔으면 내려가기

						} else if (ps.t == Integer.MIN_VALUE) { // 내려가고 있는 중인 사람들은 내려갈수 있도록
							if (ps.downtime == 0) {
								s1cnt--;
								p1.remove(ps);
							} else {
								ps.down();
							}
						}
					} else {
						ps.walk();
					}
				}
				
				for (Person ps : temp1) {
					if (ps.downtime == 0) {
						s1cnt--; // 계단 탈출했으니 자리 내주기
						p1.remove(ps); // 탈출했으니 사람명단에서 뺴주기
					}
				}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				for (Person ps : temp2) {
					if (ps.t <= -1) {
						if (ps.t != Integer.MIN_VALUE && s2cnt < 3) {
							ps.t = Integer.MIN_VALUE;
							s2cnt++;
							if (ps.downtime == 0) {
								s2cnt--;
								p2.remove(ps);
							}else {
								ps.down();
							}

						} else if (ps.t == Integer.MIN_VALUE) {
							if (ps.downtime == 0) {
								s2cnt--;
								p2.remove(ps);
							} else {
								ps.down();
							}
						}
					} else {
						ps.walk();
					}
				}
				
				for (Person ps : temp2) {
					if (ps.downtime == 0) {
						s2cnt--; // 계단 탈출했으니 자리 내주기
						p2.remove(ps); // 탈출했으니 사람명단에서 뺴주기
					}
				}
			}
			
			ans = Math.min(ans, time);
			return;
		}

		for (int i = start; i < personcnt; i++) {
			s1.add(i);
			Combi(cnt + 1, i + 1, num);
			s1.remove(cnt);
		}
	}

	private static void fills2() {
		for (int i = 0; i < personcnt; i++) {
			boolean chk = true;
			for (int v : s1) {
				if (i == v) {
					chk = false;
					break;
				}
			}
			if (chk) {
				s2.add(i);
			}
		}
	}

}
