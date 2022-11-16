import java.util.*;

public class Solution_1844_게임맵최단거리_L2 {
    public int solution(int[][] maps) {     
        return BFS(maps);
    }
    
    public int BFS(int[][] maps){
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int N = maps.length;
        int M = maps[0].length;
        
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];
        
        v[0][0] = true;
        q.offer(new int[] {0, 0, 1});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            // System.out.println(cur[0]+" "+cur[1]+" "+cur[2]);
            
            for(int i=0; i<4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                
                if(nr<0||nr>=N||nc<0||nc>=M||v[nr][nc]||maps[nr][nc]==0) continue;
                
                if(nr==N-1 && nc==M-1) {
                    
                    return cur[2]+1;
                }
                v[nr][nc] = true;
                q.offer(new int[] {nr, nc, cur[2]+1});
            }
        }

        return -1;
    }
}