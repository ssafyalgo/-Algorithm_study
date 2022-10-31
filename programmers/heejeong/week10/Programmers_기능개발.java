package study_week_10;

import java.util.*;

public class Programmers_기능개발 {

    public static void main(String[] args) {

        /* 각 기능은 진도가 100%일 때 서비스에 반영할 수 있다.
           - 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고
           - 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포된다.

           먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 배열 = progresses
           각 작업의 개발 속도가 적인 배열 = speeds

           Q. 각 배포마다 몇 개의 기능이 배포되는지 return */

        class Solution {
            //public int[] solution(int[] progresses, int[] speeds) {
            public ArrayList<Integer> solution(int[] progresses, int[] speeds) {
                //int[] answer = {};
                ArrayList<Integer> answer = new ArrayList();

                /* 로직 */
                // 1) 진행상황에 따라 완성도 진행
                // 2) 첫번째꺼가 완성됬는지 확인해서 완성된거 쭉쭉- 배포

                // 큐에 넣기
                //Queue<Integer> queue = new LinkedList();
                Queue<Map<Integer, Integer>> queue = new LinkedList();
                for (int i = 0; i < progresses.length; i++) {
                    Map<Integer, Integer> temp = new HashMap<>();
                    temp.put(i, progresses[i]);
                    queue.offer(temp);
                }
                //System.out.println("#21# Queue 확인: " + queue);


                while (!queue.isEmpty()) {
                    // i) 첫번째가 완료되었을 경우
                    Collection<Integer> tempFirst = queue.peek().values();
                    int first = 0;
                    for (Integer t: tempFirst) {
                        first = t;
                    }

                    if (first >= 100) {
                        int count = 0;
                        int size = queue.size();

                        for (int i = 0; i < size; i++) {
                            Collection<Integer> tempValue = queue.peek().values();
                            int temp = 0;
                            for (Integer va: tempValue) {
                                temp = va;
                            }

                            if (temp >= 100) {
                                count++;
                                queue.poll();
                            }
                            else {
                                System.out.println("#21# count: " + count);
                                System.out.println("#21# array: " + answer);
                                break;
                            }
                        }
                        answer.add(count++);
                    }


                    // ii) 첫번째가 완료되지 않았을 경우 -> 작업속도에 따라 진행, 큐에 넣기
                    int size = queue.size();
                    for (int i = 0; i < size; i++) {
                        Map<Integer, Integer> temp = queue.poll();
                        Collection<Integer> tempIndex = temp.keySet();
                        Collection<Integer> tempValue = temp.values();

                        int index = 0;
                        int value = 0;
                        for (Integer in: tempIndex) {
                            index = in;
                        }
                        for (Integer va: tempValue) {
                            value = va;
                        }

                        temp.clear();
                        temp.put(index, value+speeds[index]);

                        queue.offer(temp);
                    }
                }


                return answer;
            }
        }
    }
}
