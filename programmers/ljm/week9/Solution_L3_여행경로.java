import java.util.*;

public class Solution_L3_여행경로 {
    static int len;
    static String[] answer = {};
    static boolean chk;
    
    public String[] solution(String[][] tickets) {
        len  = tickets.length;
        
        Arrays.sort(tickets, (o1, o2)->{
            return o1[1].compareTo(o2[1]);
        });

        String[] strarr = new String[len+1];

        strarr[0] = "ICN";
        DFS(0, tickets, new boolean[len], strarr);
        
        return answer;
    }
    
    public void DFS(int dep, String[][] tickets, boolean[] v, String[] strarr) {
        if(dep==len){
            answer = strarr.clone();
            chk = true;
            return;
        }
        
        for(int i=0; i<len; i++){
            if(strarr[dep].equals(tickets[i][0]) && !v[i]){
                strarr[dep+1] = tickets[i][1];
                v[i] = true;
                
                DFS(dep+1, tickets, v, strarr);
                
                v[i] = false;
                if(chk) return;
            }
        }
    }
}