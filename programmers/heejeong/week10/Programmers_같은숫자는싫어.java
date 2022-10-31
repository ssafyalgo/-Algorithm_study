package study_week_10;

import java.util.*;

public class Programmers_같은숫자는싫어 {

    public static void main(String[] args) {

        /* 배열 arr에서 연속적으로 나타내는 숫자는 하나만 남기고 전부 제거
           ! 단, 제거된 후 남은 수들을 반환할 때는 배열 arr 원소들의 순서를 유지해야 한다. */

        class Solution {
            public int[] solution(int []arr) {
                int[] answer = {};

                /* 로직 */
                // 1) 큐에 넣기
                //    ! 같은 수면 안넣기
                Deque deque = new LinkedList();
                deque.offer(arr[0]);
                for (int i = 1; i < arr.length; i++) {
                    if (deque.peekLast().equals(arr[i])) continue;

                    //System.out.println("#21# 큐에 넣는 값: " + i + "번째 " +arr[i]);
                    deque.offer(arr[i]);
                }

                answer = new int[deque.size()];
                int index = 0;
                while (!deque.isEmpty()) {
                    answer[index++] = (Integer) deque.poll();
                }

                return answer;
            }
        }

    }
}
