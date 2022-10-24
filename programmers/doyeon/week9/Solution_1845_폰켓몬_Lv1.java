package week9;

import java.util.HashMap;

public class Solution_1845_폰켓몬_Lv1 {
    public int solution(int[] nums) {
        int answer = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        int n=nums.length;

        for(int i=0;i<n;i++){
            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
        }

        int count=0;
        for(int key:map.keySet()){
            if(count==n/2)  break;
            map.put(key,map.get(key)-1);
            count++;
        }
        answer=count;
        return answer;
    }
}
