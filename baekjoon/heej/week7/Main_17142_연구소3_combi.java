package java1004.study;

/* 바이러스를 연구하던 연구소에 승원이가 침입했고, 바이러스를 유출하려 한다.
*  - 바이러스는 활성 상태와 비활성 상태가 있다.
*  - 처음 모든 바이러스는 비활성 상태이다.
*  - 활성 상태인 바이러스는 4방에 인접한 모든 빈 칸으로 동시에 복제되며, 1초가 걸린다.
*  - 활성 바이러스가 비활성 바이러스가 있는 칸으로 가면 비활성 바이러스가 활성으로 변한다.
*
*  승원이는 연구소의 바이러스 M개를 활성 상태로 변경하려 한다.
*  - 연구소 크기 N x N 정사각형
*  - 0=빈칸, 1=벽, 2=바이러스를 놓을 수 있는 칸 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// Q. 모든 빈 칸에 바이러스를 퍼뜨리는 최소 시간을 구하여라
//    - 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우 -1 출력
public class Main_17142_연구소3_combi {

    static int N;                           // N x N
    static int M;                           // 활성 바이러스 개수 (2 ~ 10)
    static int[][] map;
    static int result;

    static int[] dr = {-1, 1, 0, 0};        // 상 하 좌 우
    static int[] dc = {0, 0, -1, 1};
    static int emptyBoxCount = 0;           // 0인 칸의 개수
    static ArrayList<Virus> virusies;       // 비활성 바이러스 저장
    static int[][] activeMap;               // 활성 바이러스가 있는 Map
    static class Virus {
        int r, c;
        boolean active = false;

        public Virus(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Virus{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /* 입력 */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        virusies = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                // 바이러스 칸(2)인 경우 저장
                if (map[i][j]==2)
                    virusies.add(new Virus(i, j));

                // 0인 칸 개수 저장 (바이러스를 전파해야할 칸)
                if (map[i][j]==0)
                    emptyBoxCount++;
            }
        }
        // 확인
        //printMap(map);

        /* 로직 */
        // 0. map에 0이 없는 경우 0 출력 + 프로그램 끝내기
        if (emptyBoxCount==0) {
            System.out.println(0);
            System.exit(0);
        }

        // 1. map=2번 칸에 있는 바이러스를 M개 활성화 상태로 변경
        result = Integer.MAX_VALUE;
        activeMap = new int[N][N];
        activeVirusCombi(0, 0);
        // 2. 활성 바이러스 전파 (모든 활성 바이러스가 4방으로 동시에 전파, 1초 걸림)
        // 3. 최소 시간 출력

        
        /* 출력 */
        System.out.println(result==Integer.MAX_VALUE ? -1 : result);
    }

    // [@Method] M개의 바이러스를 활성화 상태로 변경
    private static void activeVirusCombi(int start, int cnt) {
        // End 조건
        if (cnt==M) {
            result = Math.min(result, spreadActiveVirusBFS(emptyBoxCount));      // 2. 활성 바이러스 전파
            return;
        }

        // Combi - n개 중 r개를 선택하여 나열 (중복 X, 순서 X)
        for (int i = start; i < virusies.size(); i++) {
            int r = virusies.get(i).r;
            int c = virusies.get(i).c;

            if (map[r][c]==2) {
                // 선택
                map[r][c] = 4;
                virusies.get(i).active = true;
                activeVirusCombi(i+1, cnt+1);                       // !!!!! 여기 start+1 으로 해서 시간초과 났었음

                // 복원
                map[r][c] = 2;
                virusies.get(i).active = false;
            }
        }
    }

    // [@Method] 활성 바이러스 전파 (모든 활성 바이러스가 4방으로 동시에 전파, 1초 걸림)
    private static int spreadActiveVirusBFS(int emptyBox) {
        copyMap();                                          // 활성 바이러스가 있는 map을 -> activeMap으로 복제
        /*System.out.println("#21# 활성 바이러스 activeMap");
        printMap(activeMap);*/

        Queue<Virus> queue = new LinkedList<>();

        // 시작점
        // 큐에 모든 활성 바이러스 넣기
        for (Virus v: virusies) {
            if (v.active)
                queue.offer(new Virus(v.r, v.c));
        }

        // BFS
        int time = 1;
        while (!queue.isEmpty()) {
            //System.out.println("#21# time: " + time);
            int size = queue.size();

            // End 조건 - (가지치기)
            // 1) result보다 현재 time이 더 크면 끝 (최소찾기)
            if (time >= result) {
                return Integer.MAX_VALUE;
            }

            for (int i = 0; i < size; i++) {
                Virus cur = queue.poll();

                // 4방
                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    if (!check(nr, nc)) continue;
                    if (activeMap[nr][nc]==1) continue;             // 벽이면 continue;
                    if (activeMap[nr][nc]==4) continue;             // 활성화 바이러스가 있는 칸이면 continue;

                    // activeMap=0 이거나 or activeMap=2 이면 활성 바이러스 전파
                    if (activeMap[nr][nc]==0 || activeMap[nr][nc]==2) {
                        if (activeMap[nr][nc]==0)                   // 0인 칸일 경우 emptyBox(count) 감소
                            emptyBox--;
                        activeMap[nr][nc] = 4;
                        queue.offer(new Virus(nr, nc));
                    }

                    // 2) 바이러스를 모두 전파하였다면 끝
                    if (emptyBox <= 0)
                        return time;
                }
                /*System.out.println("#21# 바이러스 전파 결과 - cur: " + cur);
                printMap(activeMap);*/
            }

            time++;
        }

        // 바이러스가 모두 전파되지 않았다면 MAX.VALUE return
        return Integer.MAX_VALUE;
    }

    // [@Method] map 복제 + 활성화 바이러스 저장
    private static void copyMap() {
        int index = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                activeMap[i][j] = map[i][j];
            }
        }
    }


    // [@Method] 바이러스 전파 확인
    private static boolean mapCheck(int[][] temp) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (temp[i][j]==0)
                    return false;
            }
        }

        return true;
    }

    // [@Method] 배열 범위 체크
    private static boolean check(int r, int c) {
        return 0<=r && r<N && 0<=c && c<N;
    }


    // [@Method] Map 출력
    private static void printMap(int[][] temp) {
        System.out.println("#21# Map 출력");
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(temp[i]));
        }
        System.out.println();
    }
}
