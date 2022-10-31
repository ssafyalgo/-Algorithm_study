package study_week_11;

import java.util.*;

public class Programmers_게임맵최단거리 {

/* 상대팀 진영을 먼저 파괴하면 이기는 게임
   - 상대팀 진영에 최대한 빨리 도착하는 것이 유리하다.

   캐릭터는 4방으로 한 칸 이동할 수 있다.
   - 초기 캐릭터 위치: (1, 1) 좌측 상단
   - 상대팀 진영 위치: (n, m) 우측 하단
   - 검은색 = 벽 : 갈 수 없음
   - 흰색 = O : 갈 수 있음
   ! map 벗어날 수 없음

   Q. 상대 팀 진영에 도착하기 위하여 지나가야 하는
      칸의 개수의 최솟값 return
    ! 도착할 수 없을 시 -1 return */

    static int result;

    static int N, M;
    static class Point {
        int r, c;

        public Point (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    class Solution {

        public int solution(int[][] maps) {
            int answer = 0;

            N = maps.length;            // r
            M = maps[0].length;         // c
            //System.out.println("#21# NxM 크기: " + N + ", " + M);

            /* 로직 */
            // 최단거리 -> BFS 사용
            result = Integer.MAX_VALUE;
            int[][] visited = new int[N][M];
            answer = getDistanceBFS(maps, visited);

            return answer;
        }

        // [@Method] BFS
        public int getDistanceBFS(int[][] maps, int[][] visited) {
            Queue<Point> queue = new LinkedList();
            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            // 시작점
            queue.offer(new Point(0, 0));
            visited[0][0] = 1;

            // BFS
            while(!queue.isEmpty()) {
                Point cur = queue.poll();

                // End 조건
                if (cur.r==(N-1) && cur.c==(M-1)) {
                    return visited[cur.r][cur.c];
                }

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (!check(nr, nc)) continue;
                    if (visited[nr][nc]!=0) continue;
                    if (maps[nr][nc]==0) continue;

                    queue.offer(new Point(nr, nc));
                    visited[nr][nc] = visited[cur.r][cur.c] + 1;
                }
            }

            return -1;
        }

        // [@Method] 배열 범위 체크
        public boolean check(int r, int c) {
            return 0<=r && r<N && 0<=c && c<M;
        }
    }
}
