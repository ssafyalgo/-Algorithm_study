package java0929.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* N인 길이의 컨베이어 벨트
 * - 길이가 2N인 벨트가 이 컨베이어 벨트를 위아래로 감싸며 돌고 있다.
 *
 * 벨트가 한 칸 회전하면 1번 ~ 2N-1번 칸은 다음 번호의 칸으로 이동하고, 2N번 칸은 1번 칸으로 이동한다.
 * - 1번 칸이 "올리는 위치"
 * - N번 칸이 "내리는 위치" 이다.
 *
 * 컨베이어 벨트에 박스 모양 로봇을 하나씩 올린다.
 * - 올리는 위치에만 올릴 수 있고, 언제든지 내리는 위치에 도달하면 즉시 내린다.
 * - 로봇은 벨트 위에서 스스로 이동할 수 있다.
 * - 로봇을 올리는 위치에 올리거나 && 이동하면 그 칸의 내구도가 즉시 1 감소한다.
 *
 * 컨베이터 벨트를 이용해 로봇들을 건너편으로 옮기려고 한다.
 * 1) 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
 * 2) 가장 먼저 벨트가 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동
 *    ! 이동하지 못한다면 가만히 있는다.
 *    - (이동 가능 조건) 이동하려는 칸에 로봇이 없고 && 그 칸의 내구도가 1 이상 남아 있어야 한다.
 * 3) 올리는 위치에 있는 칸의 내구도가 0이 아니라면 올리는 위치에 로봇을 올린다.
 * 4) 내구도가 0인 칸의 개수가 K개 이상이라면 종료 */

// Q. 종료되었을 때 몇 번째 단계가 진행 중이었는지 구하기
public class Main_20055_컨베이어벨트위의로봇 {

    static int N;					// 벨트의 길이 N, N번 칸 = 내리는 위치
    static int K;					// 종료 조건 -> 내구도가 0인 칸의 K 개수
    static int result = 0;

    static int[] conveyor;       // 컨베이너 벨트 내구도

    //static boolean[] robots;
    static Robot[] robots;
    static class Robot {
        boolean robot;
        boolean visited;

        public Robot(boolean robot, boolean visited) {
            super();
            this.robot = robot;
            this.visited = visited;
        }

        @Override
        public String toString() {
            return "Robot [robot=" + robot + ", visited=" + visited + "]";
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /* 입력 */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        conveyor = new int[2*N+1];
        conveyor[0] = 5;
        //robots = new boolean[2*N+1];
        robots = new Robot[2*N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < (2*N)+1; i++) {
            conveyor[i] = Integer.parseInt(st.nextToken());

            robots[i] = new Robot(false, false);
        }
        // 확인
        //printConveyor();
        //printRobots();

        /* 로직 */
        // !! 벨트 이동 시 로봇도 함께 이동
        // !! 내구도도 같이 이동

        while (!checkDurability()) {
            result++;
            // 1) 벨트 회전 (with 로봇, !! 내구도 감소)
            rotationBelt();

            // 2) 로봇 이동 = 가장 먼저 벨트에 올라간 로봇부터 -> 벨트가 회전하는 방향으로 한칸 이동
            //    - 어짜피, 로봇은 1부터 올라감
            moveRobot();

            // 3) 로봇 올리기 = 올리는 위치에 있는 칸의 내구도가 0이 아니라면 올리는 위치에 로봇을 올린다.
            setRobot();
        }

        /* 출력 */
        System.out.println(result);
    }


    // [@Method] 0번에 로봇 올리기
    private static void setRobot() {
        if (!(robots[1].robot) && conveyor[1]>0) {
            robots[1].robot = true;
            conveyor[1] -= 1;
        }

        /*System.out.println("#21# 3_로봇 올리기");
        printConveyor();
        printRobots();*/
    }

    // [@Method] 로봇 이동
    private static void moveRobot() {
        // !! 역순부터 로봇 이동 (1번에서 로봇이 올려져서 벨트가 이동함에 따라 뒤로 가기 때문)
        for (int i = robots.length-1; i > 0; i--) {
            if (!robots[i].robot)                       // 현재 칸에 로봇이 없다면 continue;
                continue;

            // 마지막 index의 경우 1번째꺼
            if (i==(robots.length-1)) {
                if (!robots[1].robot && conveyor[1]>0) {
                    robots[i].robot = false;
                    robots[1].robot = true;

                    conveyor[1] -= 1;
                }
                continue;
            }

            if (!robots[i+1].robot && conveyor[i+1]>0) {
                robots[i].robot = false;
                robots[i+1].robot = true;

                conveyor[i+1] -= 1;
            }

            robots[N].robot = false;                            // 로봇이 내리는 위치(N)일 경우 내림
        }

        /*System.out.println("#21# 2_로봇 이동");
        printConveyor();
        printRobots();*/
    }

    // [@Method] 로봇과 함께 한칸 벨트 회전
    private static void rotationBelt() {
        int[] temp = new int[conveyor.length];
        temp[0] = 5;

        temp[1] = conveyor[conveyor.length-1];			// 1번째 칸에 마지막 벨트 넣어두기
        for (int i = 2; i < conveyor.length; i++) {
            temp[i] = conveyor[i-1];
        }
        //conveyor = temp;
        conveyor = temp.clone();

        // 로봇도 함께 이동
        Robot[] tempRobot = new Robot[robots.length];
        tempRobot[1] = robots[robots.length-1];
        for (int i = 2; i < robots.length; i++) {
            tempRobot[i] = robots[i-1];
        }
        //robots = tempRobot;
        robots = tempRobot.clone();
        robots[N].robot = false; 							// N번째 벨트칸의 로봇은 내리기

        /*System.out.println("#21# 1_벨트 한칸 회전");
        printConveyor();
        printRobots();*/
    }

    // [@Method] End 조건 - 내구도 0인 칸 개수 확인
    private static boolean checkDurability() {
        int count = 0;

        for (int i = 1; i < conveyor.length; i++) {
            if (conveyor[i] <= 0)
                count++;
        }

        return count >= K ? true : false;
    }

    // [@Method] robots 배열 내 visited 초기화(false로)
    private static void initRobotVisited() {
        for (int i = 1; i < robots.length; i++) {
            robots[i].visited = false;
        }
    }

    // [@Method] 로봇 확인
    private static void printRobots() {
        System.out.println("#21# 로봇 확인");
        for (int i = 0; i < robots.length; i++) {
            System.out.println(robots[i]);
        }
        System.out.println();
    }

    // [@Method] 컨베이어 벨트 확인
    private static void printConveyor() {
        System.out.println("#21# 컨베이어 벨트 내구도 확인");
        System.out.println(Arrays.toString(conveyor));
        System.out.println();
    }

}