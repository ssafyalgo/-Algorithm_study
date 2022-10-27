package week9;
import java.util.*;

public class Solution_42576_완주하지못한선수_Lv1 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();

        for(String s:participant){
            map.put(s, map.getOrDefault(s,0)+1);
        }
        for(String s:completion){
            map.put(s, map.get(s)-1);
        }

        for(String s: map.keySet()){
            if(map.get(s)!=0){
                answer=s;
            }
        }
        return answer;
    }
}
