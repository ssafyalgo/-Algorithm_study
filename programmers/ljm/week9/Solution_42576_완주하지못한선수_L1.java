import java.util.*;

public class Solution_42576_완주하지못한선수_L1 {
	 public String solution(String[] participant, String[] completion) {
	        String answer = "";
	        Map<String, Integer> map = new HashMap<>();
	        for(int i=0,s=participant.length; i<s; i++){
	            if(map.get(participant[i])==null){
	                map.put(participant[i], 1);
	            } else {
	                map.put(participant[i], map.get(participant[i])+1);
	            }
	        }
	        
	        // for(String key:map.keySet()){
	        //     System.out.println(key);
	        // }
	        
	        for(String str:completion){
	            if(map.containsKey(str)){
	                map.put(str, map.get(str)-1);
	            }
	        }
	        
	        // for(String key:map.keySet()){
	        //     System.out.println(key);
	        // }
	        
	        for(String key:map.keySet()){
	            if(map.get(key)==1){
	                answer = key;
	                break;
	            }
	        }
	        
	        return answer;
	    }
}