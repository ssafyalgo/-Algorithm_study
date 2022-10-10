package java1007.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 보호 필름을 제작하려고 한다.
*  - 보호 필름은 투명한 막을 D장 쌓아서 제작된다.
*  - 막은 == 동일한 크기를 가진 바(bar) 모양의 셀들이 가로 방향으로 W개 붙여서 만들어진다.
*  - 이렇게 제작된 필름은 두께 D, 가로 크기 W의 보호 필름이라고 한다.
*  ! 동일한 크기의 바(bar)를 붙여서 막이 된다.
*
*  각 셀(bar)들은 특성 A or 특성 B를 가지고 있다.
*  - 각 바(bar)는 특성 A or B를 가지고 있다.
*  - 특성 A는 = 0, 특성 B는 = 1로 표시된다.
*
*  보호 필름의 성능을 검사하기 위해 합격기준 K 값을 사용한다.
*  - 충격은 보호 필름 단면의 세로 방향으로 가해지므로, 세로 방향 셀들의 특성이 중요하다.
*  - 단면의 모든 세로방향에 대해서 동일한 특성의 셀들이 K개 이상 연속적으로 있는 경우에만 성능검사를 통과한다.
*    ! 아래에 있거나, 중간에 있거나 상관없음 연속적은 특성이 K개 이상 있으면 됨
*
*  성능검사를 통과하기 위해서 약품을 사용하여야 한다.
*  - 약품은 막 별로 투입할 수 있으며, 투입하는 막의 모든 셀들은 하나의 특정으로 변경된다. (막=가로, 셀=세로)
*    ex. 특정 막에 약품 A 투입 시, 막 내의 모든 셀들의 특성은 A로 변경 */

// Q. 성능 검사를 통과할 수 있는 최소 약품 투입 횟수를 구하여라.
//    - 약품을 투입하지 않고도 성능검사를 통과하는 경우 0 출력
public class Solution_2112_보호필름 {

    static int D;                   // 두께 (막)
    static int W;                   // 가로 크기 (셀 개수)
    static int K;                   // 합격기준 K
    static int[][] map;
    static int[][] originMap;
    static int result; 

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            /* 입력 */
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[D][W];
            originMap = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    // map 복제
                    originMap[i][j] = map[i][j];
                }
            }
            // 확인
            //printMap();


            /* 로직 */
            // 성능 검사를 통과할 수 있는 최소 약품 투입 횟수
            // 동일한 특성이 연속적으로 K개
            result = Integer.MAX_VALUE;
            // 1. 연속적인 특성을 다 가지고 있는 지 확인
            if (checkFilm()) {
                result = 0;
            }
            // 2. 특성이 없는 셀에 약물 투입 -> 해당 막의 특성이 다 바뀜
            // 바꿔보고 ->
            // 그냥 다 바꿔보기 - 부분조합 사용
            else {
                insertDrugSubset(0, 0);
            }

            /* 출력 */
            System.out.println("#" + t + " " + result);
        }

    }

    // [@Method] 약품 주입
    private static void insertDrugSubset(int cnt, int insertCount) {
        // End 조건
        if (cnt==D) {
            /*System.out.println("#21# 부분조합 실행 후");
            printMap();
            System.out.println("#21# 투입 횟수: " + insertCount);*/

            if (checkFilm()) {
                result = Math.min(result, insertCount);
            }
            return;
        }

        // 부분조합
        // i) A(0) 선택
        for (int i = 0; i < W; i++) {               // W = 열 c
            map[cnt][i] = 0;
        }
        insertDrugSubset(cnt+1, insertCount+1);

        // ii) B(1) 선택
        for (int i = 0; i < W; i++) {
            map[cnt][i] = 1;
        }
        insertDrugSubset(cnt+1, insertCount+1);

        // iii) 선택 안함
        for (int i = 0; i < W; i++) {               // 원복
            map[cnt][i] = originMap[cnt][i];
        }
        insertDrugSubset(cnt+1, insertCount);

    }

    // [@Method] 성능 검사 통과 체크
    private static boolean checkFilm() {
        for (int i = 0; i < W; i++) {           // W = 열 c
            int count = 1;
            int cur = map[0][i];                // 현재 특성 (기준)
            boolean flag = true;

            for (int j = 1; j < D; j++) {       // D = 행 r
                // 현재 특성과 다음 특성이 같다면 -> count 값 증가
                if (cur==map[j][i]) {
                    count++;
                }
                // 현재 특성과 다음 특성이 다르다면 -> 현재 특성 값 변경 + count 값 초기화
                else {
                    cur = map[j][i];
                    count = 1;
                }

                if (count==K) {
                    flag = true;
                    break;
                }
            }
            if (count!=K) {
                flag = false;
            }

            if (!flag)
                return false;
        }

        return true;
    }

    // [@Method] Map 출력
    private static void printMap() {
        System.out.println("#21# Map 출력");
        for (int i = 0; i < D; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}


/*
1
6 8 3
0 0 1 0 1 0 0 1
0 1 0 0 0 1 1 1
0 1 1 1 0 0 0 0
1 1 1 1 0 0 0 1
0 1 1 0 1 0 0 1
1 0 1 0 1 1 0 1
*/
