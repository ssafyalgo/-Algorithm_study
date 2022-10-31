package study_week_9;

import java.util.*;

/* N 마리의 폰켓몬 중 N/2마리 데려갈 수 있다.
   - 폰켓몬의 종류에 따라 번호를 붙여 구분한다. (같은 종류는 같은 번호를 가지고 있음)

   최대한 다양한 종류의 폰켓몬을 가지길 원하기 때문에,
   최대한 많은 종류의 폰켓몬을 포함해서 N/2마리를 선택하려 한다.

   N/2마리의 폰켓몬을 선택하는 방법 중,
   가장 많은 종류의 폰켓몬을 선택하는 방법을 찾아, 그때의 폰켓몬 종류 번호의 개수를 return */
/*

class Solution {

    static int N;               // nums 배열 길이

    public int solution(int[] nums) {
        int answer = 0;

        */
/* 입력초기 *//*

        N = nums.length;

        */
/* 로직 *//*

        // [3, 1, 2, 3] → [3, 1, 2] = 3
        // [3, 3, 3, 2, 2, 4] → [3, 2, 4] = 3
        // [3, 3, 3, 2, 2, 2] → [3, 2] = 2
        // ! 결국 (nums에서 중복값을 제외한 길이)가 N/2보다 작거나 같으면 해당 길이를 return 하고,
        //   아니면 N/2 길이 return;

        Set <Integer> numSet = new HashSet<Integer>();
        for (int i = 0; i < N; i++) {
            numSet.add(nums[i]);
        }
        // 확인
        */
/*
        System.out.println("#21# numSet 확인");
        System.out.println(numSet);
        *//*


        if (numSet.size() <= N/2) {
            answer = numSet.size();
        }
        else {
            answer = N/2;
        }


        */
/* 출력 *//*

        return answer;
    }

}*/
