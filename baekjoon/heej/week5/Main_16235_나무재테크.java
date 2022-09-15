
/* N x N 크기의 땅
*  ! r, c는 1부터 시작한다.
*
*  땅의 양분을 조사하는 로봇 S2D2를 만들었다. 로봇은 모든 칸에 대한 양분을 조사한다.
*  - 가장 처음에 양분은 모든 칸에 5만큼 들어있다.
*
* [ 나무 재테크 ]
* 나무 재테크란, 작은 묘목을 구매해 어느정도 키운 후 팔아서 수익을 얻는 재테크이다.
* - M개의 나무를 구매해 땅에 심었다.
* ! 1x1 크기의 칸에 여러 개의 나무가 심어져 있을 수도 있다.
*
* 나무는 사계절을 보내며, 아래와 같은 과정을 반복한다.
* 1) 봄: 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
*        - 나무가 있는 1x1 칸에 있는 양분만 먹을 수 있다.
*        - 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
*        ! 만약, 땅에 양분이 부족하여 자신의 나이만틈 양분을 못먹은 나무는 즉시 죽는다.
* 2) 여름: 봄에 죽은 나무가 양분으로 변한다.
*         - 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. (소수점 아래 버림)
* 3) 가을: 나무가 번식한다.
*         - 번식하는 나무는 나이가 5의 배수여야 하며 && 인접한 8방 칸에 나이가 1인 나무가 생긴다.
*         ! 땅을 벗어나는 칸에는 나무가 생기지 않는다.
* 4) 겨울: 로봇 S2D2가 땅을 돌아다니며 땅에 양분을 추가한다.
*         - 각 칸의 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다. */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// Q. K년이 지난 후 땅에 살아있는 나무의 개수를 구하는 프로그램
public class Main_16235_나무재테크 {

    static int N, M, K;             // N x N 크기의 땅, M개의 나무 심기, K년이 지난 후
    static int[][] yangboon;        // 땅마다 추가되는 양분 배열
    static int[][] map;             // 땅 배열

    static ArrayList<Tree> trees;       // 나무
    static Deque<Integer> deadTrees = new LinkedList<>();    // 죽은 나무
    static class Tree implements Comparable<Tree> {
        int r, c;
        int age;                    // 나무의 나이
        boolean dead;               // 나무의 생존여부

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "r=" + r +
                    ", c=" + c +
                    ", age=" + age +
                    ", dead=" + dead +
                    '}';
        }

        // 어린 나이 순으로 정렬
        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /* 입력 */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        yangboon = new int[N][N];
        map = new int[N][N];
        // 매년마다 추가 될 양분 정보 받기 + 초기 땅의 양분 5로 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                // i) 양분
                yangboon[i][j] = Integer.parseInt(st.nextToken());
                // 2) 땅
                map[i][j] = 5;
            }
        }
        // 확인
        //printMorY(yangboon, "양분");
        //printMorY(map, "땅");

        // 초기 나무 입력받기 (r, c, 나무의 나이)
        trees = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());

            trees.add(new Tree(r, c, age));
        }

        /* 로직 */
        // [  봄 ] - 나이가 어린 나무부터 자신의 나이만큼 나무가 있는 칸에 양분을 먹고, 나이가 1 증가
        //         ! 자신의 나이만큼 양분을 먹지 못할 경우 즉사한다.
        // [ 여름 ] - (죽은 나무 나이 / 2)만큼 나무가 있던 칸에 양분으로 추가된다. (소수점 아래 버림)
        // [ 가을 ] - 나무의 나이가 5의 배수라면 → 8방 칸에 나이가 1인 나무 생성 (배열 벗어날 경우 생성 X)
        // [ 겨울 ] - 로봇이 A[r][c] 값만큼 땅에 양분을 추가한다. (입력으로 주어진다.)

        // 초기에 주어진 나무들을 어린나이 순으로 정렬
        Collections.sort(trees);
        //System.out.println("#21# 나무 " + trees);

        // K년이 지날때까지 사계절 반복
        while (K!=0) {
            spring();
            summer();
            fall();
            winter();
            K--;
        }

        /* 출력 */
        System.out.println(trees.size());
    }

    // [@Method] 겨울 - 로봇이 A[r][c] 값만큼 땅에 양분을 추가한다. (입력으로 주어진다.)
    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += yangboon[i][j];
            }
        }
    }

    // [@Method] 가을 - 나무의 나이가 5의 배수라면 → 8방 칸에 나이가 1인 나무 생성 (배열 벗어날 경우 생성 X)
    private static void fall() {
        int[] dr = {0, 1, 0, -1, -1, 1, -1, 1};
        int[] dc = {1, 0, -1, 0, 1, 1, -1, -1};

        // i) 나이가 1인 나무 생성 (newTrees 배열에 저장)
        ArrayList<Tree> newTrees = new ArrayList<>();       // 새로 생성될 나무들 배열
        for (int i = 0; i < trees.size(); i++) {
            Tree curTree = trees.get(i);
            if (curTree.dead) continue;                     // if) 죽은 나무라면 continue;

            if (curTree.age % 5 == 0) {                     // if) 나무의 나이가 5의 배수라면
                for (int d = 0; d < 8; d++) {
                    int nr = curTree.r + dr[d];
                    int nc = curTree.c + dc[d];
                    if (!check(nr, nc)) continue;

                    newTrees.add(new Tree(nr, nc, 1));  //     나이가 1인 나무 생성
                }
            }
        }

        // ii) 기존 나무 + 새로 생성된 나무 합치기
        for (Tree tree: trees) {
            if (tree.dead) continue;        // if) 죽은 나무라면 continue;
            newTrees.add(tree);             // 죽은 나무가 아니라면 newTrres에 현재 tree 넣기
        }
        trees = newTrees;
    }

    // [@Method] 배열 범위 확인
    private static boolean check(int r, int c) {
        return 0<=r && r<N && 0<=c && c<N;
    }

    // [@Method] 여름 - (죽은 나무 나이 / 2)만큼 나무가 있던 칸에 양분으로 추가된다. (소수점 아래 버림)
    private static void summer() {
        while (!deadTrees.isEmpty()) {
            int index = deadTrees.pollLast();           // 죽은 나무의 index 꺼내기
            Tree tree = trees.get(index);           // 죽은 나무 꺼내기

            int yangboon = tree.age / 2;
            map[tree.r][tree.c] += yangboon;    // (죽은 나무 나이 / 2)만큼 양분 추가

            tree.dead = true;                       // 죽은 나무 표시
        }
    }

    // [@Method] 봄
    //           나무 trees, 죽은 나무 deadTrees
    private static void spring() {
        for (int i = 0; i < trees.size(); i++) {
            Tree curTree = trees.get(i);

            if (map[curTree.r][curTree.c] < curTree.age) {      // if) 자신의 나이만큼 양분을 먹지 못할 경우 즉사
                deadTrees.add(i);                               //     현재 나무의 index를 죽은나무 Deque에 추가
                continue;
            }
            else {                                              // else) 땅의 양분을 먹고 나이 1 증가
                map[curTree.r][curTree.c] -= curTree.age;
                curTree.age += 1;
            }
        }
    }

    // [@Method] 땅 배열(A) 출력
    private static void printMorY(int[][] arr, String select) {
        System.out.println("#21# " + select + " 배열 출력");
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println();
    }
}