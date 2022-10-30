import java.util.*;

public class Solution_1845_폰켓몬_L1 {
	class Solution {
	    public int solution(int[] nums) {
	        int answer = 0;
	        Set<Integer> set = new HashSet<>();
	        
	        for(int i:nums){
	            set.add(i);
	        }
	        
	        int size = set.size();
	        if(size>(nums.length/2)){
	            answer = nums.length/2;
	        } else {
	            answer = size;
	        }
	        
	        return answer;
	    }
	}
}