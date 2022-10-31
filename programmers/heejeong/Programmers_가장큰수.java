package study_week_9;

import java.util.*;

public class Programmers_가장큰수 {

/* 0 또는 양의 정수가 주어졌을 때,
   정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요 */

    static class Number implements Comparable<Number> {
        int i1;

        public Number (int i1) {
            this.i1 = i1;
        }

        @Override
        public int compareTo(Number number) {
            String temp1 = Integer.toString(this.i1) + Integer.toString(number.i1); // 106  210
            String temp2 = Integer.toString(number.i1) + Integer.toString(this.i1); // 610  102
            // System.out.println("#21# temp 확인: " + temp1 + " " + temp2);

            if (Integer.parseInt(temp1) > Integer.parseInt(temp2)) {
                if (this.i1 > number.i1) {
                    return number.i1 - this.i1;
                }
                return this.i1 - number.i1;
            }

            if (this.i1 > number.i1) {
                return this.i1 - number.i1;
            }
            return number.i1 - this.i1;
        }
    }

    class Solution {

        public String solution(int[] numbers) {
            String answer = "";

            /* 로직 */
            // 1) 생성한 클래스로 배열 다시 저장
            // 1-1) 앞, 뒤로 문자열로 더해서 큰 값으로 정렬
            // 2) 가장 큰 수 출력

            Number[] nums = new Number[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                nums[i] = new Number(numbers[i]);
            }

            // 정렬
            Arrays.sort(nums);

            // !! 만약 테케가 [0, 0, 0, 0] 인 경우 -> 0 으로 정답이 출력되어야 함
            if (nums[0].i1==0) {
                answer = "0";
                return answer;
            }

            // 가장 큰 수 출력
            for (int i = 0; i < nums.length; i++) {
                answer += Integer.toString(nums[i].i1);
            }

            return answer;
        }
    }
}
