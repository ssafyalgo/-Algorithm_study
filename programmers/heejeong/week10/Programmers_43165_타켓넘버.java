package study_week_10;

public class Programmers_43165_타켓넘버 {

    static int targetNum;
    static int count;

    /* n개의 음이 아닌 정수들이 있다.
   - 이 정수들을 순서를 바꾸지 않고 적절히 더하거나(+) 빼서(-)
     타켓 넘버를 만들려고 한다.

   타겟 넘버를 만드는 방법의 수 return */

    class Solution {

        public int solution(int[] numbers, int target) {
            int answer = 0;
            count = 0;
            targetNum = target;

            /* 로직 */
            // 2가지 경우의 수 : +, -
            char[] operator = new char[numbers.length];

            // DFS
            //System.out.println("#21# targetNum: " + targetNum);
            getTargetNumberDFS(0, numbers, 0, 0);
            answer = count;

            return answer;
        }

        // [@Method] DFS
        public void getTargetNumberDFS(int cnt, int[] numbers, int sum, int index) {
            // End 조건
            if (cnt==numbers.length) {
                //System.out.println("#21# sum, targetNum: " + sum + ", " + targetNum);

                if (sum==targetNum)
                    count++;
                return;
            }

            // DFS
            for (int j = 0; j < 2; j++) {   // 0=+, 1=-
                if (j==0) {
                    sum += numbers[index];
                }
                else {
                    sum += -(numbers[index]);
                }

                getTargetNumberDFS(cnt+1, numbers, sum, index+1);

                // 원복
                if (j==0) {
                    sum -= numbers[index];
                }
                else {
                    sum -= -(numbers[index]);
                }
            }
        }
    }
}
