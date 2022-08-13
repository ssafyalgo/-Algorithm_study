package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// ? 연결 요소, 그룹을 어떻게 count할 것인지
/* 연결 요소: 그룹, 연결되어 있는 그룹의 개수
*          ex. A-B-C D-E -> 2개 (그룹이 2개) */

// Q. 방향 없는 그래프가 주어졌을 때, 연결요소(Connected Component)의 개수를 구하는 프로그램
public class Main_11724_연결요소의개수_DFS {

    static int N;						// 정점의 개수
    static int M;						// 간선의 개수

    static int[][] map;
    static boolean[] visited;			// 방문체크
    static int count;                   // 연결개수 count

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /* 입력 */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());       // 정점의 개수
        M = Integer.parseInt(st.nextToken());       // 간선의 개수

        visited = new boolean[N+1];
        map = new int[N+1][N+1];
        // map 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = 1;
            map[y][x] = 1;
        }
        // map 확인
        /*for (int i = 0; i < N+1; i++) {
            System.out.println(Arrays.toString(map[i]));
        }*/


        /* 로직 */
        count = 0;
        for (int i = 1; i < N+1; i++) {
            if (!visited[i]) {
                dfs(i);
                //bfs(i);
                count++;                // 방문한 적이 없는 노드라면 count++
            }
        }


        /* 출력 */
        System.out.println(count);
    }

    // [@Method] DFS - recursion (BC 없음)
    private static void dfs(int start) {
        visited[start] = true;

        // BFS
        for (int i = 1; i < N+1; i++) {
            if (!visited[i] && map[start][i]==1) {
                dfs(i);
            }
        }
    }


}