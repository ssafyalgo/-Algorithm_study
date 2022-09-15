package java0915;

/* 사다리 게임은 N개의 세로선과 M개의 가로선으로 이루어져 있다.
*  - 각각의 세로선마다 가로선을 놓을 수 있는 위치의 개수는 H
*  - 모든 세로선이 같은 위치를 가진다.
*
*  세로선과 점선(가로선을 놓을 수 있는 선)이 교차한느 점은 가로선을 놓을 수 있는 점이다.
*  - 가로선은 인접한 두 세로선을 연결해야 한다.
*  ! 단, 두 가로선이 연속하거나 서로 접하면 안된다. (입력으로 주어지는 가로선이 서로 연속하는 경우도 없다)
*
*  사다리 게임은 각각의 세로선마다 게임을 진행하고,
*  - 세로선의 가장 위부터 아래 방향으로 내려간다.
*  - 이때, 가로선을 만나면 가로선을 타고 옆 세로선으로 이동한 다음 이동한 세로선에서 아래 방향으로 이동한다.
*
*  사디리에 가로선을 추가하여, 사다리 게임을 조작하려고 한다.
*  - 이때, i번 세로선의 결과가 i번이 나와야 한다. */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Q. i번 세로선의 결과가 i번이 나오기 위해 추가해야 하는 가로선 개수의 최솟값을 구하는 프로그램
//    - 만약, 정답이 3보다 큰 값이거나 불가능한 경우 -1을 출력한다.
public class Main_15684_사다리조작_combi {

    static int N, M;            // 세로선의 개수 N, 가로선의 개수 M
    static int H;               // 세로선마다 가로선을 놓을 수 있는 위치의 개수 H

    static int[][] map;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /* 입력 */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 연결된 가로선 저장
        map = new int[H+1][N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a, b -> b번의 세로선과 b+1번의 세로선을 a번 점선 위치에서 연결
            map[a][b] = 1;                          // 1 = 오른쪽 세로선으로 이동
            map[a][b+1] = 2;                        // 2 = 왼쪽 세로선으로 이동
        }
        // 확인
        //printLinks();

        /* 로직 */
        // !! i번 세로선의 결과가 i번이 나오기 위해 추가해야 하는 가로선 개수의 최솟값
        // 최대 3개의 가로선 추가 가능 (! 문제에서 추가하는 가로선 개수가 3 이상이면 -1 출력 명시되어 있음)
        // 이때, i는 추가할 가로선의 개수 이다.
        result = -1;
        for (int i = 0; i <= 3; i++) {
            combi(1, 0, i);
        }

        /* 출력 */
        System.out.println(-1);
    }

    // [@Method] 조합 - 중복X, 순서X, 가로선 추가 경우의 수
    private static void combi(int start, int cnt, int addHorizontalLineCnt) {       // addHorizontalLineCnt = 추가할 가로선의 개수
        // End 조건
        if (cnt == addHorizontalLineCnt) {
            /*System.out.println("#21# 조합 결과 확인");
            printMap();*/
            if (arrivalCheck()) {
                System.out.println(addHorizontalLineCnt);
                System.exit(0);
            }
            return;
        }

        // 조합
        for (int i = start; i < H+1; i++) {
            for (int j = 1; j < N; j++) {
                // 가로선 두 개가 연속으로 놓여질 수 없기 때문에 가로선을 추가하기 전 연결된 가로선 확인
                if (map[i][j]==0 && map[i][j+1]==0) {
                    // 가로선 연결
                    map[i][j] = 1;
                    map[i][j+1] = 2;
                    combi(i, cnt+1, addHorizontalLineCnt);

                    // 연결했던 가로선 해제
                    map[i][j] = 0;
                    map[i][j+1] = 0;
                }
            }
        }
    }

    // [@Method] i번 세로줄이 i번에 도착하는지 체크
    private static boolean arrivalCheck() {
        for (int i = 1; i < N+1; i++) {
            int nr = 1;
            int nc = i;
            // [1][1], [1][2], [1][3] ... [1][5]

            // nr이 맨 아래줄 + 1에 도착한다면 End (맨 아랫줄도 사다리 이동 해야하니까 H+1)
            while (nr < H+1) {
                if (map[nr][nc]==1) nc++;           // if) 1의 경우, 오른쪽 세로선으로 이동
                else if (map[nr][nc]==2) nc--;      //     2의 경우, 왼쪽 세로선으로 이동

                nr++;                               // else) 아래로 이동
            }

            if (nc!=i) return false;                // 만약, 도착한 nc가 i번째 세로줄이 아니라면 return false;
        }

        return true;
    }


    // [@Method] 가로선 확인
    private static void printMap() {
        System.out.println("#21# 가로선 출력");
        for (int i = 0; i < H; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }
}