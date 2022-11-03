import java.util.*;
/*
brown+yellow가 카펫 크기 임

sqrt(카펫크기) 만큼 for문돌려서 
가능한 배열 모두 저장.

(가로+세로)*2-4가 brown이 되는 경우를 찾기 
*/


public class Solution_43165_타켓넘버_L2 {
	static int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        
        subset(0, 0, numbers.length, numbers, target);
        
        return answer;
    }
    
    
    public void subset(int cur, int tot, int end, int[] nums, int t){
        if(cur==end){
            if(tot==t) answer++;
            return;
        }
        
        subset(cur+1, tot+nums[cur], end, nums, t);
        subset(cur+1, tot-nums[cur], end, nums, t);
    }
}