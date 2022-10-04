package java0929.study;
/* 정사각형 구역 안에 K개의 미생물 군집이 있다.
 *  - N x N 크기
 *
 *  구역 가장 바깥쪽 가장자리 부분에 위치한 셀들에는 특수한 약품이 칠해져 있다.
 *  - 군집 정보: 세로 위치, 가로 위치, 미생물 수, 이동방향
 *  - (0, 0~N-1), (0~N-1, 0) 가장자리는 약품이 칠해져 있음
 *
 *  1) 미생물 군집 정보가 주어진다.
 *     - 미생물 군집의 위치 + 군집 내 미생물 수 + 이동방향(4방 중 하나)
 *     ! 약품이 칠해준 부분에는 미생물이 배치되어 있지 않다.
 *  2) 각 군집들은 1시간마다 이동방향에 있는 다음 셀로 이동한다.
 *  3) 이동 후, 약품이 칠해진 셀에 도착하면 미생물의 절반이 죽고, 이동방향이 반대로 바뀐다.
 *     ! 미생물 수가 홀수인 경우, 살아남은 미생물 수 = 원래 미생물 수 / 2 (소수점 버림) 값으로 한다.
 *       만약, 군집에 미생물이 1마리 있는 경우 살아남은 미생물 수가 0이 되기 때문에, 군집이 사라진다.
 *  4) 이동 후 두 개 이상의 군집이 한 셀에 모이는 경우 군집들이 합쳐지게 된다.
 *     - 합쳐진 군집의 미생물 수 = 군집들의 미생물 수의 합
 *     - 이동방향 = 군집들 중 미생물 수가 가장 많은 군집의 이동방향이 된다. (! 합쳐지는 군집의 미생물 수가 같은 경우는 없다.) */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// Q. M 시간 동안 이 미생물 군집들을 격리하였다. M 시간 후 남아있는 미생물 수의 총합을 구하여라
public class Solution_2382_미생물격리 {

    static int N;                   // N 셀의 개수
    static int M;                   // M 격리 시간
    static int K;                   // 미생물 군집의 개수 K

    static int[] dr = {-1, 1, 0, 0};    // {상, 하, 좌, 우}
    static int[] dc = {0, 0, -1, 1};
    static int result;
    static int temp;                    // 미생물 개수가 1일 때 저장해둔 변수

    static ArrayList<Micro> micros;  // 미생물
    static class Micro implements Comparable<Micro> {
        int location;               // 군집의 위치 (2차원 배열을 1차원 배열로 나타냄)
        int r, c;
        int count;                  // 미생물 수
        int direction;              // 이동 방향 (상-1, 하-2, 좌-3, 우-4)

        public Micro(int location, int r, int c, int count, int direction) {
            this.location = location;
            this.r = r;
            this.c = c;
            this.count = count;
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "Micro{" +
                    "location=" + location +
                    ", r=" + r +
                    ", c=" + c +
                    ", count=" + count +
                    ", direction=" + direction +
                    '}';
        }

        @Override
        public int compareTo(Micro o) {
            if (this.location == o.location) {      // if) 군집이 같은 위치에 있다면
                return o.count - this.count;        //     미생물 수 기준 내림차순 정렬
            }
            return this.location - o.location;      // else) location 위치 순 기준으로 오름차순 정렬
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
            K = Integer.parseInt(st.nextToken());

            micros = new ArrayList<>();
            // 미생물 군집 정보 (세로위치, 가로위치, 미생물 수, 이동방향)
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                micros.add(new Micro(r*N+c, r, c, cnt, dir));      // 2차원 배열 -> 1차원 배열로 = A[i*c+j]
            }
            // 확인
            //printMicro();

            /* 로직 */
            // M 시간 후 남아있는 미생물 수의 총합
            // 1) 1시간 마다 이동방향에 있는 다음 셀로 이동
            // 2) 이동 후, 약품이 칠해진 곳이면 미생물 절반이 죽고, 이동방향이 반대로 바뀜
            //    ! 홀수인 경우, 살아남은 미생물 수 = 원래 미생물 수 / 2 (소수점 버림)
            // 3) 이동 후, 두 개 이상의 군집이 한 셀에 모이는 경우 군집들이 합쳐짐
            //    - 합쳐진 군집의 미생물 수 = 군집들의 미생물 수의 합
            //    - 이동방향 = 군집들 중 미생물 수가 가장 많은 군집의 이동방향이 된다
            for (int m = 0; m < M; m++) {
                // 1) 전 군집 이동방향 다음 셀로 이동 + 약품이 칠해진 cell 도착 시 미생물 수, 이동방향 변경
                moveMicro();

                // 2) 두 개 이상의 군집이 한 셀에 모이는 경우 군집들이 합쳐지고 + 이동뱡향 변경
                // i) 이동방향 변경 : micros 정렬, 같은 location에 있다면 -> 미생물 수 기준 내림차순으로 / 없으면 location 기준 오름차순
                Collections.sort(micros);
                /*System.out.println("#21# 정렬 후 군집 확인");
                printMicro();*/
                // ii) 미생물 수 병합
                result = mergeMicro();
            }

            /* 출력 */
            System.out.println("#" + t + " " + result);
        }


    }

    // [@Method] 군집이 한 셀에 모이는 경우 미생물 수 병합 + 이동방향 변경
    private static int mergeMicro() {
        int total = 0;

        for (int i = micros.size()-1; i > 0; i--) {
            Micro curMicro = micros.get(i);
            Micro nextMicro = micros.get(i-1);

            if (curMicro.location== nextMicro.location) {        // if) 같은 cell에 있다면
                nextMicro.count += curMicro.count;               //     다음 micro에 미생물 수를 병합하고,
                curMicro.count = 0;                              //     현재 micro에 미생물 수를 0으로 세팅 (나중에 지울려고, moveMicro에서 지움)
            }
            else {
                total += curMicro.count;                        // else) 같은 cell에 없다면 -> 미생물 개수 세기
            }
        }

        /*System.out.println("#21# merge 후 군집 확인");
        printMicro();*/

        return total + micros.get(0).count;
    }

    // [@Method] 미생물 군집 이동
    //           - 이동 방향 (상-1, 하-2, 좌-3, 우-4)
    private static void moveMicro() {  // 1-9
        for (int i = 0; i < micros.size(); i++) {
            Micro micro = micros.get(i);
            if (micro.count==0) {                               // if) 현재 군집의 미생물 수가 0이면 지우고 + 이동 skip
                //micros.remove(i);
                continue;
            }

            // 이동
            micro.r += dr[micro.direction-1];
            micro.c += dc[micro.direction-1];
            micro.location = micro.r * N + micro.c;

            // 약품이 발라져 있는 cell인 경우 -> 미생물 수, 이동방향 변경
            if (micro.r==0 || micro.r==N-1 || micro.c==0 || micro.c==N-1) {
                drugMicro(micro);
                //if (micro.count==0) micros.remove(i);
            }
        }

        /*System.out.println("#21# 군집 이동 확인");
        printMicro();*/
    }

    // [@Method] 약품이 발라진 cell에 도착한 미생물 군집
    private static void drugMicro(Micro micro) {
        //System.out.println("#21# 약물에 닿은 미생물 " + micro);
        micro.count /= 2;

        if (micro.direction==1 || micro.direction==3)
            micro.direction += 1;
        else
            micro.direction -= 1;
    }

    // [@Method] 미생물 군집 확인
    private static void printMicro() {
        System.out.println("#21# 미생물 군집 확인");
        for (Micro v: micros) {
            System.out.println(v);
        }
        System.out.println();
    }
}


/*

1
7 2 9
1 1 7 1
2 1 7 1
5 1 5 4
3 2 8 4
4 3 14 1
3 4 3 3
1 5 8 2
3 5 100 1
5 5 1 1

1
10 16 11
5 7 87 3
2 5 686 1
6 7 64 2
6 8 873 3
5 6 762 2
8 4 268 3
7 3 307 4
1 7 809 3
5 5 293 3
5 1 345 3
4 1 114 4

1
10 17 46
7 5 724 2
7 7 464 3
2 2 827 2
2 4 942 4
4 5 604 4
7 2 382 1
6 5 895 3
8 7 538 4
6 1 299 4
4 7 811 4
3 6 664 2
6 8 868 2
7 6 859 2
4 6 778 2
5 4 842 3
1 3 942 1
1 1 805 3
3 2 350 3
2 5 623 2
5 3 840 1
7 1 308 4
1 8 323 3
2 3 82 3
2 6 115 2
8 3 930 1
6 2 72 1
2 1 290 3
4 8 574 4
8 5 150 3
8 2 287 2
2 8 909 2
2 7 588 2
7 3 30 3
5 8 655 3
3 8 537 1
4 2 350 3
5 6 199 1
5 5 734 2
3 3 788 1
8 4 893 1
1 4 421 4
6 3 616 2
1 2 556 4
7 8 8 1
5 2 702 2
4 4 503 3
*/