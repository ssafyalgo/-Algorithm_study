import java.util.*;


public class Solution_43162_네트워크_L3 {
    public int solution(int n, int[][] computers) {
        int answer = 1;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(computers[i][j]!=1) continue;
                
                computers[i][j] = ++answer;
                
                BFS(n, i, j, answer, computers);
           }   
        }
        // answer = answer==2?1:answer-1;
        return answer-1;
    }
    
    public void BFS(int n, int sr, int sc, int cnt, int[][] computers) {
        Queue<Integer> q = new ArrayDeque<>();
        
        q.offer(sc);

        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int i=0; i<n; i++) {
                if(computers[cur][i] != 1) continue;
                
                computers[cur][i] = cnt;
                q.offer(i);
            }
        }
    }
}