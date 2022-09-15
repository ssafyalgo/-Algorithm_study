import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* N x N 크기 지도
 * 점심을 먹기 위해 최대한 빠른 시간 내에 아래 층으로 내려가야 한다.
 * - 방 안의 사람들 = P, 계단 입구 = S
 *
 * 이동 완료 시간 = 모든 사람들이 계단을 내려가 아래 층으로 이동을 완료한 시간
 * ! 이동 완료 시간에는 계단 입구까지 이동한 시간 + 계단을 내려가는 시간이 포함된다.
 * - 계단 입구까지 이동 시간: |PR - SR| + |PC - SC| (맨하튼)
 * - 계단을 내려가는 시간
 *   ! 계단 입구에 도착하면, 1분 후 아래칸으로 내려 갈 수 있다.
 *   > 계단 위에는 동시에 최대 3명까지만 올라가 있을 수 있다.
 *   > 이미 계단을 3명이 내려가고 있는 경우, 그 중 한 명이 완전히 내려갈 때까지 입구에서 대기해야 한다.
 *   > 계단마다 길이 K가 주어지며, 계단에 올라간 후 완전히 내려가는데 K분이 걸린다. */

// Q. 이동 완료 시간이 최소가 되는 경우를 찾고, 그 때의 소요시간을 출력하는 프로그램
//    - 사람은 1
//    - 2 이상은 계단의 입구를 나타내며, 그 값은 계단의 길이를 의미한다.
public class Solution_2383_점심식사시간 {

    static int N;
    static int[] match;

    static ArrayList<Person> persons;
    static ArrayList<Stair> staires;
    static class Person {
        int r, c;
        int selectStair;

        public Person(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "r=" + r +
                    ", c=" + c +
                    ", selectStair=" + selectStair +
                    '}';
        }
    }
    static class Stair {
        int r, c;
        int length;         // 계단 길이

        public Stair(int r, int c, int length) {
            this.r = r;
            this.c = c;
            this.length = length;
        }

        @Override
        public String toString() {
            return "Stair{" +
                    "r=" + r +
                    ", c=" + c +
                    ", length=" + length +
                    '}';
        }
    }

    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            /* 입력 */
            N = Integer.parseInt(br.readLine());

            persons = new ArrayList<>();
            staires = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int temp = Integer.parseInt(st.nextToken());

                    // i) 사람일 경우
                    if (temp==1) persons.add(new Person(i, j));
                    // ii) 계단일 경우
                    else if (temp >= 2) staires.add(new Stair(i, j, temp));
                }
            }
            // 확인
            //printPerson();
            //printStair();


            /* 로직 */
            // !! 중복순열을 사용하여 각 사람이 이용할 수 있는 계단의 모든 경우 고려
            //    - 가까운 계단을 내려가는 것보다 대기 했다가 내려가는 경우가 더 빠를수도 있음
            result = Integer.MAX_VALUE;
            perm(0);


            /* 출력 */
            System.out.println("#" + t + " " + result);
        }
    }

    // [@Method] 중복순열, 각 사람 별 이용할 수 있는 계단 모든 경우의 수 구하기
    private static void perm(int cnt) {
        // End 조건
        if (cnt == persons.size()) {
            /*System.out.println("#21# 중복순열");
            printPerson();*/
            downStair();
            return;
        }

        // 중복순열 - 계단 2개
        for (int i = 0; i < 2; i++) {
            persons.get(cnt).selectStair = i;       // 선택한 계단 index를 person.selectStair에 저장
            perm(cnt+1);
        }
    }

    // [@Method] 각 사람 별 계단을 내려가는데 걸린 시간 구하기
    private static void downStair() {
        PriorityQueue<Integer> pq0 = new PriorityQueue<>();     // 첫번째 계단 사용하는 사람
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();     // 두번째 계단 사용하는 사람

        for (int i = 0; i < persons.size(); i++) {
            int selectStair = persons.get(i).selectStair;
            //System.out.println("#21# 선택한 계단: " + persons.get(i));

            // i) 첫번째 계단을 이용 -> 첫번째 계단까지의 거리 PQ에 저장
            if (selectStair==0) pq0.add(getDistance(persons.get(i), staires.get(0)));

            // ii) 두번째 계단을 이용 -> 두번째 계단까지의 거리 PQ에 저장
            else pq1.add(getDistance(persons.get(i), staires.get(1)));
        }

        int personCnt = persons.size();                             // 남은 사람 명수
        int[] stair0 = new int[3];                                  // 첫번째 계단을 이용하는 사람들의 남은 이용시간
        int[] stair1 = new int[3];                                  // 두번재 계단을 ~

        int time = 0;
        while (true) {
            // End 조건 - 모든 사람이 계단을 다- 내려갈때까지
            if (personCnt == 0) {
                boolean flag = true;

                for (int i = 0; i < 3; i++) {
                    if (stair0[i]!=0) {
                        flag = false;
                        break;
                    }
                    if (stair1[i]!=0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) break;
            }

            // ! 계단은 최대 3명까지 동시 사용 가능
            for (int i = 0; i < 3; i++) {
                // i) 현재 첫번째 계단을 이용하는 사람이 없다면
                if (stair0[i]==0) {
                    if (!pq0.isEmpty()) {                           // if) 첫번째 계단을 사용할 사람이 있고
                        if (pq0.peek() <= time) {                   // if) 사용할 사람이 계단에 도착했다면
                            personCnt--;                            //     남은 사람 명수 -1 감소
                            stair0[i] = staires.get(0).length;      //     첫번째 계단의 이동시간 넣기
                            pq0.poll();
                        }
                    }
                }
                // ii) 현재 첫번째 계단을 사용하고 있다면
                else {
                    stair0[i]--;                                    // 첫번째 계단의 이동시간 -1 감소

                    if (stair0[i]==0) {                             // if) 첫번째 계단을 이용하는 사람이 없다면
                        if (!pq0.isEmpty()) {                       // if) 첫번째 계단을 사용할 사람이 있고
                            if (pq0.peek() <= time) {               // if) 사용할 사람이 계단에 도착했다면
                                personCnt--;                        //     - 남은 사람 명수 -1 감소
                                stair0[i] = staires.get(0).length;  //     - 이동시간 넣기
                                pq0.poll();
                            }
                        }
                    }
                }

                // i) 현재 두번째 계단을 이용하는 사람이 없다면
                if (stair1[i]==0) {
                    if (!pq1.isEmpty()) {                           // if) 두번째 계단을 이용하는 사람이 있고
                        if (pq1.peek() <= time) {                   // if) 사용할 사람이 계단에 도착했다면
                            personCnt--;                            //     - 남은 사람 명수 -1 감소
                            stair1[i] = staires.get(1).length;      //     - 이동시간 넣기
                            pq1.poll();
                        }
                    }
                }
                // ii) 현재 두번째 계단을 이용하는 사람이 있다면
                else {
                    stair1[i]--;                                    // 두번째 계단 이동시간 -1 감소

                    if (stair1[i]==0) {                             // if) 두번째 계단을 이용하고 있지 않다면
                        if (!pq1.isEmpty()) {                       // if) 두번째 계단을 이용할려는 사람이 있다면
                            if (pq1.peek() <= time) {               // if) 이용할 사람이 계단에 도착했다면
                                personCnt--;                        //     - 남은 사람 명수 -1 감소
                                stair1[i] = staires.get(1).length;  //     - 이동시간 넣기
                                pq1.poll();
                            }
                        }
                    }
                }

            } // for문 End-

            time++;
        } // while문 End-

        result = Math.min(result, time);
    }

    // [@Method] 계단까지의 거리 구하기
    //           계단 입구까지 이동 시간: |PR - SR| + |PC - SC| (맨하튼)
    private static Integer getDistance(Person person, Stair stair) {
        return Math.abs(person.r - stair.r) + Math.abs(person.c - stair.c);
    }

    // [@Method] 계단 정보 출력
    private static void printStair() {
        System.out.println("#21# 계단 정보 출력");
        for (Stair s: staires) {
            System.out.println(s);
        }
        System.out.println();
    }

    // [@Method] 사람 정보 출력
    private static void printPerson() {
        System.out.println("#21# 사람 정보 출력");
        for (Person p: persons) {
            System.out.println(p);
        }
        System.out.println();
    }
}