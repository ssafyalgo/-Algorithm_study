package study_week_10;

import java.util.*;

public class Programmers_86491_최소직사각형 {

    static int[] width;
    static int[] height;

/* 다양한 모양과 크기의 명함들을 수납할 수 있는 지갑을 만들고자 한다.
   - 명함은 가로 또는 세로로 수납할 수 있다.

   모든 명함을 수납할 수 있는 가장 작은 지갑의 크기를 return 하라 */

    class Solution {

        public int solution(int[][] sizes) {
            int answer = 0;

            /* 로직 */
            // ! 여러개를 가로로 눕힐 수 있음
            // 두개의 수를 비교해서 큰 숫자를 width 배열에 넣기
            width = new int[sizes.length];
            height = new int[sizes.length];
            for (int i = 0; i < sizes.length; i++) {
                for (int j = 0; j < 2; j++) {
                    if (j==0)
                        width[i] = sizes[i][j];
                    else if (j==1)
                        height[i] = sizes[i][j];
                }
            }
            // 확인
            //System.out.println("#21# width 배열: " + Arrays.toString(width));
            //System.out.println("#21# height 배열: " + Arrays.toString(height));


            // width <-> height 교환
            for (int i = 0; i < sizes.length; i++) {
                if (width[i] < height[i]) {
                    int temp = width[i];
                    width[i] = height[i];
                    height[i] = temp;
                }
            }
            //System.out.println("#21#(교환) width 배열: " + Arrays.toString(width));
            //System.out.println("#21#(교환) height 배열: " + Arrays.toString(height));

            // 지갑 size 구하기
            int maxWidth = 0;
            int maxHeight = 0;
            for (int w: width) {
                maxWidth = Math.max(maxWidth, w);
            }
            for (int h: height) {
                maxHeight = Math.max(maxHeight, h);
            }
            answer = maxWidth * maxHeight;




        /*
        // 가로 배열, 세로 배열 별도로 저장
        width = new int[sizes.length];
        height = new int[sizes.length];
        for (int i = 0; i < sizes.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (j==0)
                    width[i] = sizes[i][j];
                else if (j==1)
                    height[i] = sizes[i][j];
            }
        }
        // 확인
        System.out.println("#21# width 배열: " + Arrays.toString(width));
        System.out.println("#21# height 배열: " + Arrays.toString(height));


        int maxWidth = 0;
        int maxHeight = 0;
        // 1) 세로일 때, 지갑의 크기
        for (int w: width) {
            maxWidth = Math.max(maxWidth, w);
        }
        for (int h: height) {
            maxHeight = Math.max(maxHeight, h);
        }
        answer = maxWidth * maxHeight;

        // 2) 가로로 눕혔을 때, 지갑의 크기
        // h -> w
        //swapHeightToWidth(sizes, maxHeight, height, width)
        for (int i = 0; i < sizes.length; i++) {
            if (maxHeight==height[i]) {         // 가장 높은 값 swap
                int temp = height[i];

                height[i] = width[i];
                width[i] = temp;
            }
        }
        */


            return answer;
        }
    }
}
