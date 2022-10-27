import java.util.*;

public class Solution_L1_체육복 {
	 public int solution(int n, int[] lost, int[] reserve) {
	        int answer = 0;
	        int[] stu = new int[n];
	        Arrays.fill(stu, 1);
	        
	        for(int i:lost)  stu[i-1]--;
	        for(int i:reserve) stu[i-1]++;
	    
	        for(int i=0; i<n; i++){
	            if(stu[i]>1){
	                if(i>0){
	                    if(stu[i-1] == 0){
	                        stu[i]--;
	                        stu[i-1]++;
	                        continue;
	                    }
	                } 
	                if(i<n-1){
	                    if(stu[i+1] == 0){
	                        stu[i]--;
	                        stu[i+1]++;
	                        continue;
	                    }
	                }
	            }
	        }
	        
	        for(int i:stu){
	            if(i>=1){
	                answer++;
	            }
	        }
	        // System.out.println(Arrays.toString(stu));
	        
	        return answer;
	    }
}