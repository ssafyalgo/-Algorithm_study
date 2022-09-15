/* 3 x 3 인 배열 A
*  ! 배열의 인덱스는 1부터 시작한다.
*  - 1초가 지날때마다 배열에 연산이 적용된다.
*
* R 연산: 행의 개수 >= 열의 개수인 경우 -> 배열 A의 모든 행에 대해서 정렬을 수행
* C 연산: 행의 개수  < 열의 개수인 경우 -> 배열 A의 모든 열에 대해서 정렬을 수행
*
* [ 정렬 방법 ]
* 한 행 또는 열에 있는 수를 정렬하려면, 각각의 수가 몇 번 나왔는 지 알아야 한다.
* 1) 그 다음, 수의 등장 횟수가 커지는 순으로
* 2) 그러한 것이 여러가지라면 수가 커지는 순으로 정렬한다.
* 3) 그 다음 배열 A에 정렬된 결과를 다시 넣는다.
*    ! 배열에 넣을 시, 수의 등장 횟수를 모두 넣으며, 순서는 수가 먼저이다.
* !! 결론: 수의 등장횟수 기준 오름차순 -> 중복되는게 있을 경우 수 기준 오름차순 -> [ 수, 등장횟수, 수, 등장횟수 ] 저장
*
* 정렬된 결과를 배열에 넣으면 행 또는 열의 크기가 달라질 수 있다.
* - R 연산을 적용할 경우 -> 가장 큰 행을 기준으로 모든 행의 크기가 변한다.
* - C 연산을 적용할 경우 -> 가장 큰 열을 기준으로 모든 열의 크기가 변한다.
* - 행 or 열의 크기가 커진 곳에는 0이 채워진다.
*   ! 수를 정렬할 때 0은 무시한다.
* ! 행 or 열의 크기가 100을 넘어가는 경우 처음 100개를 제외한 나머지는 버린다. */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

// Q. A[r][c]에 들어있는 값이 k가 되기 위한 최소 시간을 구하라.
//    ! 100초를 넘을 경우 -1을 출력한다.
public class Main_17140_이차원배열과연산 {

    static int R, C, K;                     // 배열[r][c]가 -> k가 되어야 함
    static int[][] A = new int[101][101];   // index가 1부터 시작하고, 100을 넘어가는 배열은 버린다.

    static int countX = 3, countY = 3;      // 초기 행, 열 개수 = 3
    static int time = 0;
    static class SortRule implements Comparable<SortRule> {
        int num;                    // 숫자
        int count;                  // 숫자의 등장횟수

        public SortRule(int num, int count) {
            this.num = num;
            this.count = count;
        }

        // 정렬 - count가 작은 게 앞으로, 같다면 num이 작은 게 앞으로 정렬
        //       수의 등장횟수 기준 오름차순 -> 중복되는게 있을 경우 수 기준 오름차순
        @Override
        public int compareTo(SortRule o) {
            if (this.count > o.count) {
                return 1;                       // 1 = 크다
            }
            else if (this.count == o.count) {
                return this.num - o.num;        // num 오름차순 으로 정렬
            }
            else {
                return -1;                      // -1 = 작다
            }
        }
    }

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);

        /* 입력 */
        R = scann.nextInt();
        C = scann.nextInt();
        K = scann.nextInt();

        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                A[i][j] = scann.nextInt();
            }
        }
        // 확인
        //printA();

        /* 로직 */
        // A[r][c]에 들어있는 값이 k가 되기 위한 최소 시간
        // 1) 1초가 지날때마다, R 연산(행의 개수 >= 열의 개수) or C 연산(행의 개수  < 열의 개수) 수행
        // 2) 수의 등장 횟수 계산
        // 3) 수의 등장횟수 기준 오름차순 -> 중복되는게 있을 경우 수 기준 오름차순 -> [ 수, 등장횟수, 수, 등장횟수 ] 으로 배열 A 재저장
        // 4) A[r][c] = k 확인 -> 1번 재귀

        // !! 주의
        // - 100초까지 인정
        // - 정렬 시 0은 무시
        // - 행 or 열의 크기가 100을 넘어가는 경우 처음 100개를 제외한 나머지는 버림
        time = sortExcutor();

        /* 출력 */
        System.out.println(time);
    }

    // [@Method] 1초마다 정렬 수행
    private static int sortExcutor() {
        for (int time = 0; time < 101; time++) {
            // End 조건 - A[r][c]가 k 일 경우 return;
            if (A[R][C]==K) {
                return time;
            }

            sortArray();
        }

        return -1;
    }

    // [@Method] 정렬
    //           R 연산(행의 개수 >= 열의 개수) or C 연산(행의 개수  < 열의 개수) 수행
    private static void sortArray() {
        // 수행할 연산 선택
        // i) R(X) 연산 - 모든 행에 대하여 정렬
        if (countX >= countY) {
            for (int i = 1; i < countX+1; i++) {
                sortX(i);
            }
        }
        // ii) C(Y) 연산 - 모든 열에 대하여 정렬
        else if (countX < countY) {
            for (int i = 1; i < countY+1; i++) {
                sortY(i);
            }
        }
    }

    // [@Method] R(X) 연산 - 모든 행에 대하여 정렬
    private static void sortX(int key) {
        PriorityQueue<SortRule> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();

        // i) 각 숫자별 등장횟수를 센 후 -> pq에 넣기
        for (int i = 1; i < countY+1; i++) {
            if (A[key][i]==0) continue;                                             // 0일 경우, 정렬 무시

            // map에 key가 존재할 경우 -> 해당 key값의 value값이 null이면 1, null이 아니라면 value+1 값을 넣어준다.
            map.compute(A[key][i], (num, count) -> count == null ? 1 : count+1);
        }
        map.forEach((k, v) -> pq.add(new SortRule(k, v)));                          // map에 저장되어 있는 key, value 값을 pq에 넣기

        // ii) 정렬 후 배열 A에 넣기 [수, 등장횟수]
        int i = 1;
        while (!pq.isEmpty()) {
            SortRule cur = pq.poll();

            A[key][i++] = cur.num;
            A[key][i++] = cur.count;
        }

        // iii) 업데이트
        //      C 연산을 적용할 경우 -> 가장 큰 열을 기준으로 모든 열의 크기가 변한다.
        countY = Math.max(countY, i);                                               // 가장 큰 열
        while (i < 100) {                                                           // 사용하지 않는 좌표는 0으로 설정
            A[key][i++] = 0;
            A[key][i++] = 0;
        }
    }

    // [@Method] C(Y) 연산 - 모든 열에 대하여 정렬
    private static void sortY(int key) {
        PriorityQueue<SortRule> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();

        // i) 각 숫자별 등장횟수를 센 후 -> pq에 넣기
        for (int i = 1; i < countX+1; i++) {
            if (A[i][key]==0) continue;                                             // 0일 경우, 정렬 무시

            // map에 key가 존재할 경우 -> 해당 key값의 value값이 null이면 1, null이 아니라면 value+1 값을 넣어준다.
            map.compute(A[i][key], (num, count) -> count == null ? 1 : count+1);
        }
        map.forEach((k, v) -> pq.add(new SortRule(k, v)));                          // map에 저장되어 있는 key, value 값을 pq에 넣기

        // ii) 정렬 후 배열 A에 넣기 [수, 등장횟수]
        int i = 1;
        while (!pq.isEmpty()) {
            SortRule cur = pq.poll();

            A[i++][key] = cur.num;
            A[i++][key] = cur.count;
        }

        // iii) 업데이트
        //      R 연산을 적용할 경우 -> 가장 큰 행을 기준으로 모든 행의 크기가 변한다.
        countX = Math.max(countX, i);                                               // 가장 큰 행
        while (i < 100) {                                                           // 사용하지 않는 좌표는 0으로 설정
            A[i++][key] = 0;
            A[i++][key] = 0;
        }
    }

    // [@Method] 배열 A 확인
    private static void printA() {
        System.out.println("#21# 배열 A 확인");
        for (int i = 1; i < countX +1; i++) {
            for (int j = 1; j < countY +1; j++) {
                if (A[i][j]!=0) {
                    System.out.print(A[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}