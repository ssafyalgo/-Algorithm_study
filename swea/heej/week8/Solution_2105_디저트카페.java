package java1012.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 디저트 카페 투어를 할 계획이다.
 *  - N x N 크기의 지역에 디저트 카페가 모여 있다. (4<= N <= 20)
 *  - 원 안의 숫자는 해당 디저트 카페에서 팔고 있는 디저트의 종류를 의미하고,
 *  - 카페들 사이에는 대각선 방향으로 움직일 수 있는 길들이 있다.
 *
 *  디저트 카페 투어는 어느 한 카페에서 출발하여
 *  대각선 방향으로 움직이고 사각형 모양을 그리며 출발한 카페로 돌아와야 한다.
 *  - 지역을 벗어나선 안된다.
 *  - 같은 종류의 디저트를 다시 먹는 것을 싫어한다. (1<= 디저트 종류 <= 100)
 *    ! 즉, 카페 투어 중 같은 숫자의 디저트를 팔고 있는 카페가 있으면 안된다.
 *    ! 하나의 카페에서 디저트를 먹는 것도 안된다. (map 한칸에서 사각형 돌기 불가능)
 *    ! 왔던 길을 다시 돌아가는 것 또한 안된다.
 *
 *  디저트를 최대한 많이 먹으려고 한다.
 *  임의의 한 카페에서 출발하여 대각선 방향으로 움직이고 서로 다른 디저트를 먹으면서 사각형 모양을 그리며 다시 출발점으로 돌아오는 경우, */
// Q. 디저트를 가장 많이 먹을 수 있는 경로를 찾고, 그 때의 디저트 수를 정답으로 출력하는 프로그램
//    - 디저트를 먹을 수 없는 경우 -1을 출력한다.
public class Solution_2105_디저트카페 {

    static int N;                                   // N x N 크기
    static int[][] map;
    static int result;

    static ArrayList<Integer> desertType;
    static boolean[][] visited;
    static int[] dr = {1, 1, -1, -1};                // 우하 대각선, 좌하 대각선, 좌상 대각선, 우상 대각선
    static int[] dc = {1, -1, -1, 1};
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
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // 확인
            //printMap();

            /* 로직 */
            result = Integer.MIN_VALUE;
            // 1. 사각형이 만들어질 수 있는 칸에서만 시작
            // 2. DFS 돌기 - 시작점 도착 시 끝내기 -> 방문 처리 + 디저트 종류 저장
            // 3. 디저트 종류 수 count

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!checkStart(i ,j)) continue;                    // 사각형을 만들 수 없는 지점 제외 continue;
                    //System.out.println("#21# 가능한 시작지점: " + i + ", " + j);

                    // 방문 체크 배열, 디저트 저장할 배열 선언
                    visited = new boolean[N][N];
                    desertType = new ArrayList<>();

                    // DFS
                    visited[i][j] = true;
                    desertType.add(map[i][j]);
                    goDesertTourDFS(new Point(i, j), new Point(i, j), 0, 0);       // 현재 시작 좌표, 시작점 좌표, cnt, 방향
                }
            }
            
            /* 출력 */
            System.out.println("#" + t + " " + (result==Integer.MIN_VALUE ? -1 : result));
        }

    }

    // [@Method] 디저트 투어 DFS
    private static void goDesertTourDFS(Point point, Point startPoint, int cnt, int dir) {

        // DFS - 이전 방향과 다른 방향으로 보내기
        for (int d = dir; d < 4; d++) {
            int nr = point.r + dr[d];
            int nc = point.c + dc[d];

            if (!check(nr, nc)) continue;
            //System.out.println("#21# 다음으로 갈 지점: " + nr + ", " + nc);

            // End 조건 - 출발지점 도착
            if (nr==startPoint.r && nc==startPoint.c && cnt>2) {
                result = Math.max(result, desertType.size());
                return;
            }

            if (visited[nr][nc]) continue;                      // 방문 체크
            if (desertType.contains(map[nr][nc])) continue;     // 디저트 종류 중복 체크

            // 갈 수 있는 경우 -> 방문처리 + 디저트 종류 저장 + 재귀
            visited[nr][nc] = true;
            desertType.add(map[nr][nc]);
            goDesertTourDFS(new Point(nr, nc), startPoint, cnt+1, d);

            // DFS 원복
            visited[nr][nc] = false;
            desertType.remove(Integer.valueOf(map[nr][nc]));
        }

    }

    // [@Method] 배열 범위 체크
    private static boolean check(int r, int c) {
        return 0<=r && r<N && 0<=c && c<N;
    }

    // [@Method] 출발지점 가능 체크
    private static boolean checkStart(int r, int c) {
        return 0<=r && r<N-2 && 0<c && c<N-1;
    }

    // [@Method] Map 출력
    private static void printMap() {
        System.out.println("#21# map 출력");
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }
}

/*
1
4
9 8 9 8
4 6 9 4
8 7 7 8
4 5 3 5
*/

