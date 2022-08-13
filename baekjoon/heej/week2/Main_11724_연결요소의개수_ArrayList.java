package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// ? 연결 요소, 그룹을 어떻게 count할 것인지
/* 연결 요소: 그룹, 연결되어 있는 그룹의 개수
*          ex. A-B-C D-E -> 2개 (그룹이 2개) */

// Q. 방향 없는 그래프가 주어졌을 때, 연결요소(Connected Component)의 개수를 구하는 프로그램
public class Main_11724_연결요소의개수_ArrayList {

    static int N;						// 정점의 개수
    static int M;						// 간선의 개수

    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
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

        // map 입력
        // i) graph 초기화(?)
        for (int i = 0; i < N+1; i++) {
            map.add(new ArrayList<>());
        }
        // ii) 간선 정보 입력 - M개의 간선의 양 끝점 u, v
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            /* 양방향 */
            map.get(u).add(v);
            map.get(v).add(u);
        }
        // map 확인
        /*for (ArrayList<Integer> m: map) {
            System.out.println(m);
        }*/


        /* 로직 */
        count = 0;
        for (int i = 1; i < N+1; i++) {
            if (!visited[i]) {
                bfs(i);
                count++;
            }
        }


        /* 출력 */
        System.out.println(count);
    }

    // [@Method] BFS - Queue
    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        // 시작점
        visited[start] = true;
        queue.offer(start);

        // BFS
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 1; i < N+1; i++) {
                if (!visited[i] && map.get(cur).contains(i)) {
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
    }




}