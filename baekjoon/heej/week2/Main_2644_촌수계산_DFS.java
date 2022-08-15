package study;

/* 촌수계산법
*  부모 - 자식 사이를 1촌으로 정의하고 이로부터 사람들 간의 촌수를 계산한다.
*  ex. 나 - 아버지 = 1촌, 아버지 - 할아버지 = 1촌
*      나 - 할아버지 = 2촌
*      아버지 형제 - 할아버지 = 1촌
*      나 - 아버지 형제 = 3촌 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Q. 여러 사람들에 대한 부모 자식들 간의 관계가 주어졌을 때, 주어진 두 사람의 촌수를 계산하는 프로그램
public class Main_2644_촌수계산_DFS {

    static int N;               // 전체 사람의 수 N명
    static int X, Y;            // 촌수를 계산해야 하는 서로 다른 두 사람의 번호
    static int M;               // 부모 자식들 간의 관계의 개수 M개

    static int[][] map;         // 관계(간선) 배열
    static int[] visited;       // DFS - 방문체크

    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /* 입력 */
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        // 관계(간선) 배열
        map = new int[N+1][N+1];
        visited = new int[N+1];
        for (int i = 1; i < M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            /* 양방향 */
            map[x][y] = 1;
            map[y][x] = 1;
        }

        /* 로직 */
        // DFS 사용 - recursion
        visited[X] = 1;
        dfs(X, 0);

        /* 출력 */
        if (count==0) System.out.println("-1");
        else System.out.println(count);
    }

    // [@Method] DFS
    private static void dfs(int start, int cnt) {
        if (start==Y) {
            //System.out.println(Arrays.toString(visited));
            count = cnt;
            return;
        }

        for (int end = 1; end <= N; end++) {
            if (visited[end]==0 && map[start][end]==1) {
                visited[end] = 1;
                dfs(end, cnt+1);
            }
        }
    }
}
