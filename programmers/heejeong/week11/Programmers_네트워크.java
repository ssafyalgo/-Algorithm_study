package study_week_11;

public class Programmers_네트워크 {
    /* 네트워크란 컨퓨터 상호 간 연결된 형태
   - A와 B가 직접적으로 연결되어 있고 & B와 C가 직접적으로 연결되어 있을 때
   - A와 C도 간접적으로 연결되어 정보 교환이 가능하다
   - 따라서 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있다.

   네트워크 개수를 return
   - 각 컴퓨터는 0부터 n-1인 정수로 표현
   - i번 컴퓨터와 j번 컴퓨터가 연결되어 있다면 computers[i][j] = 1로 표현 */

    static int N;
    static int answer;
    static boolean[] visited;
    static class Point {
        int r, c;

        public Point (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    class Solution {

        public int solution(int n, int[][] computers) {
            //int answer = 0;

            /* 입력 */
            // n : 컴퓨터 연결 개수
            // computers : 연결에 대한 정보
            N = n;


            /* 로직 */
            answer = 0;
            visited = new boolean[N];

            for (int i = 0; i < N; i++) {
                if (!visited[i]) {                      // 방문하지 않은 노드라면 DFS를 통해 연결된 노드 방문처리
                    connectionDFS(i, computers);
                    answer++;                           // 연결요소 1 증가
                }
            }

            return answer;
        }

        // [@Method] DFS - 연결된 노드 방문처리
        public void connectionDFS(int start, int[][] computers) {
            visited[start] = true;

            // DFS
            for (int i = 0; i < N; i++) {
                if (!visited[i] && computers[start][i]==1) {        // 방문하지 않았고 && 연결되어 있다면 -> 재귀, 방문처리
                    connectionDFS(i, computers);
                }
            }
        }

    }
}
