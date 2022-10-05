package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* N x N 크기의 절벽지대에 활주로를 건설하려 한다.
*  - 각 셀의 숫자는 그 지형의 높이를 의미한다.
*  - 활주로를 가로 또는 세로 방향으로 건설할 수 있는 가능성을 확인하려 한다.
*
*  활주로는 높이가 동일한 구간에서 건설이 가능하다.
*  - 높이가 다른 구간의 경우, 경사로(직각삼각형)를 설치해야 활주로를 건설할 수 있다.
*
*  경사로
*  - 경사로는 길이가 X이고, 높이는 1이다. (! 높이는 항상 1이고, 길이는 2 이상 4 이하의 정수이다.)
*  - 경사로는 높이 차이가 1이고, 낮은 지형의 높이가 동일하게 경사로의 길이만큼 연속되는 곳에 설치할 수 있다.
*  ! 동일한 셀에 두 개 이상의 경사로를 겹쳐서 사용할 수 없다.
*  ! 경사로를 세워서 사용할 수 없다. */

// Q. 경사로의 길이 X와 절벽지대의 높이 정보가 주어질 때, 활주로를 건설할 수 있는 경우의 수를 계산하는 프로그램
public class Solution_4014_활주로건설 {

    static int N;                   // N x N 지형
    static int X;                   // 경사로 길이
    static int[][] map;             // 가로 배열
    static int[][] colMap;          // 세로 배열
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            /* 입력 */
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // 확인
            //printMap(map);

            /* 로직 */
            // ! 경사로를 건설하는 경우가 아님
            // 활주로를 건설하는 경우로 -> 활주로를 건설하려면 높이가 같아야 함 (다르면 경사로를 설치해야 가능)
            // !!!!!!!!!!!!!!!!! 경사로 겹치는 거 체크해야함 -> ableRunwayCheck 수정하기

            // 1. 가로, 세로 배열을 따로 저장
            colMap = new int[N][N];
            divideArray();
            // 2. 각 배열의 한 줄씩 활주로가 가능한 지 확인하기
            result = 0;
            ableRunwayCheck(map);
            //System.out.println("#21# 가로 확인: " + result);
            ableRunwayCheck(colMap);
            //System.out.println("#21# 세로 확인: " + result);
            // 3. 가능 횟수(경우의 수) 출력


            /* 출력 */
            System.out.println("#" + t + " " + result);
        }
    }

    // [@Method] 각 배열의 한 줄씩 활주로가 가능한 지 확인하기
    private static void ableRunwayCheck(int[][] maps) {
        // 현재 높이와 다음 높이가 같은 지 확인 (0, 0) <-> (0, 1)
        // -> 다르면 높이 차이가 1인지 확인
        // ->-> 높이 차이가 1인게 && X개 있는 지 확인 == 경사로 설치 가능
        boolean flag;
        boolean[][] visited = new boolean[N][N];
        
        for (int i = 0; i < N; i++) {
            flag = true;

            for (int j = 0; j < N-1; j++) {
                if (!flag) continue;
                if (maps[i][j]==maps[i][j+1]) continue;                 // 현재 높이와 == 다음 높이가 같다면 continue;
                if (Math.abs(maps[i][j] - maps[i][j+1])!=1) {           // 현재 높이와 다음 높이의 차가 1이 아니라면 continue;
                    flag = false;
                    continue;
                }

                // 현재 높이와 다음 높이의 차가 1이라면
                // -> 어느 쪽이 큰 지 확인 -> 다음 높이의 차인게 X개 있는 지 확인 (for. 경사로 설치 가능 확인)
                // i) 왼쪽이 큼 -> c+
                if (maps[i][j] > maps[i][j+1]) {
                	int[] temp = maps[i];
                    //System.out.println("#21# 다르고, 왼쪽이 큼: " + Arrays.toString(temp));
                    int height = maps[i][j+1];
                    int count = 1;
                    for (int x = j+2; x < temp.length; x++) {
                        if (height!=temp[x]) break;				// 높이가 다르면 continue;
                        if (visited[i][x]) break;				// 경사로를 설치한 곳이라면 continue;

                        if (height==temp[x]) {
                            count++;
                            if (count==X) break;
                        }
                    }

                    if (count!=X) {
                    	flag = false;
                    }
                    else {
                    	// 경사로 설치 체크(방문 체크)
                    	int index = j+1;
                    	for (int x = 0; x < X; x++) {
							visited[i][index++] = true;
						}
                    }
                    	
                }
                // ii) 오른쪽이 큼 -> r+
                else {
                    int[] temp = maps[i];
                    //System.out.println("#21# 다르고, 오른쪽이 큼: " + Arrays.toString(temp));
                    int height = maps[i][j];
                    int count = 1;
                    for (int x = j-1; x >= 0; x--) {
                        if (height!=temp[x]) break;				// 높이가 다르면 continue;
                        if (visited[i][x]) break;				// 경사로를 설치한 곳이라면 continue;

                        if (height==temp[x]) {
                            count++;
                            if (count==X) break;
                        }
                    }

                    if (count!=X) {
                    	flag = false;
                    }
                    else {
                    	// 경사로 설치 체크(방문 체크) 
                    	int index = j;
                    	for (int x = 0; x < X; x++) {
							visited[i][index--] = true;
						}
                    }
                }
            }

            //System.out.println("#21# 현재 i: " + i + " flag: " + flag + " result: " + result);
            if (flag)
                result++;
        }
    }

    // [@Method] 가로, 세로 배열 따로 저장
    // - 기존 배열(map)을 가로 배열로 사용
    private static void divideArray() {
        // (0, 0) ~ (N-1, 0)까지 0번째 줄
        // (0, 1) ~ (N-1, 1)까지 1번째 줄

        int index = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                colMap[i][j] = map[j][index];       // 0, 0 = 0, 0 -> 0, 1 = 1, 0 -> 0, 2 = 2, 0
            }
            index++;
        }
        /*System.out.println("#21# 세로 map 출력");
        printMap(colMap);*/
    }

    // [@Method] Map 출력
    private static void printMap(int[][] maps) {
        System.out.println("#21# Map 출력");
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(maps[i]));
        }
        System.out.println();
    }
}

/*
1
9 4
4 4 3 3 3 3 2 2 2
4 4 3 3 1 1 2 2 3
3 3 2 2 1 1 1 1 2
1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1
2 2 1 1 1 1 1 1 1
2 2 1 1 1 1 1 1 1
2 2 2 2 2 2 1 1 1
3 3 3 3 2 2 2 2 1
*/