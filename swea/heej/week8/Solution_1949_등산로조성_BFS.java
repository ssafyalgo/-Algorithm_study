package java1007.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 등산로를 조성하려고 한다.
*  - N x N 크기의 부지
*  - 최대한 긴 등산로를 만들 계획이다.
*  - 등산로 부지에 적인 숫자는 지형의 높이를 나타낸다.
*
*  등산로를 만드는 규칙
*  1) 등산로는 가장 높은 봉우리에서 시작해야 한다.
*     - 높은 봉우리는 여러개일 수 있다. (최대 5개)
*  2) 등산로는 반드시 높은 지형에서 낮은 지형으로 가로 또는 세로 방향으로 연결이 되어야 한다.
*     - 즉, 높이가 같은 곳 혹은 낮은 지형이나, 대각선 방향의 연결은 불가능하다.
*  3) 긴 등산로를 만들기 위해 딱 한 곳을 정해서 최대 K 깊이만큼 지형을 깍는 공사를 할 수 있다.
*     K = 최대 공사 가능 깊이 */

// Q. 만들 수 있는 가장 긴 등산로를 찾아 그 길이를 출력하는 프로그램
public class Solution_1949_등산로조성_BFS {

    static int N;                   // N x N 크기
    static int K;                   // 최대 공사 가능 깊이 K
    static int[][] map;
    static int result;

    static Point[] highs;             // 가장 높은 봉우리들
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
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            int maxHigh = -1;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    // 가장 높은 봉우리 저장
                    maxHigh = Math.max(maxHigh, map[i][j]);
                }
            }
            // 확인
            //printMap();

            // 가장 높은 봉우리인 것들 저장
            highs = new Point[5];
            int index = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (maxHigh==map[i][j])
                        highs[index++] = new Point(i ,j);
                }
            }
            //printHighs();


            /* 로직 */
            // !!! K만큼 다 깍는게 아니라 -1씩 깍는데 최대가 K만큼 인 것
            result = Integer.MIN_VALUE;
            // 1. map 하나씩 K만큼씩 깍음
            // 2. 등산로 조성

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    for (int k = 1; k <= K; k++) {
                        if (map[i][j]-k < 0) continue;

                        // k만큼 깍기
                        map[i][j] = map[i][j] - k;

                        for (Point high: highs) {
                            if (high==null) continue;
                            // BFS - 등산로 조성
                            createHikingRoadBFS(high);
                        }

                        // 원복
                        map[i][j] = map[i][j] + k;
                    }

                }
            }

            /* 출력 */
            System.out.println("#" + t + " " + result);
        }

    }

    // [@Method] 등산로 조성
    private static void createHikingRoadBFS(Point locaion) {
        Queue<Point> queue = new LinkedList<>();
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // 시작점
        queue.offer(new Point(locaion.r, locaion.c));

        // BFS
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;

            for (int s = 0; s < size; s++) {
                Point cur = queue.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    if (!check(nr, nc)) continue;
                    if (map[nr][nc] >= map[cur.r][cur.c]) continue;         // 다음 map 높이가 >= 전 map 높이보다 크거나 같다면 continue;

                    queue.offer(new Point(nr, nc));
                }
            }
        }

        result = Math.max(result, distance);
    }

    // [@Method] 배열 범위 체크
    private static boolean check(int r, int c) {
        return 0<=r && r<N && 0<=c && c<N;
    }

    // [@Method] 가장 높은 봉우리 확인
    private static void printHighs() {
        System.out.println("#21# 가장 높은 봉우리 확인");
        for (Point h: highs) {
            System.out.println(h);
        }
        System.out.println();
    }


    // [@Method] Map 출력
    private static void printMap() {
        System.out.println("#21# Map 출력");
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }
}

/*
1
5 2
9 3 2 3 2
6 3 1 7 5
3 4 8 9 9
2 3 7 7 7
7 6 5 5 8
*/
