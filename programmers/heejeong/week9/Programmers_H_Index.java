package study_week_9;

import java.util.*;

public class Programmers_H_Index {

/* H-Index는 과학자의 생산성과 영향력을 나타내는 지표
   - 어느 과학자의 H-Index 갓을 나타내는 값은 h를 구하고자 한다.

   * 구하는 방법
   과학자가 발표한 논문 n편 중,
   1) h번 이상 인용된 논문이 h편 이상이고
   2) 나머지 논문이 h번 이하로 인용되었다면
   h의 최댓값이 이 과학자의 H-Index 값이다.

   어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 주어질 때,
   이 과학자의 H-Index 값을 return */

    class Solution {
        public int solution(int[] citations) {
            int answer = 0;

            /* 로직 */
            Arrays.sort(citations);
            System.out.println("#21# 정렬 후: " + Arrays.toString(citations));

            // h번 이상 인용된 논문이 h편 이상
            for (int i = 0; i < citations.length; i++) {
                int h = citations.length - i;

                if (citations[i] >= h) {
                    answer = h;
                    break;
                }
            }


            return answer;
        }
    }
}
