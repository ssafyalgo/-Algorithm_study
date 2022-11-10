import java.util.*;

class Solution {
    ArrayList<ArrayList<Integer>> adjlist;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        adjlist = new ArrayList<>();
        for(int i=0; i<=n; i++){
            adjlist.add(new ArrayList<>());
        }
        
        for(int i=0,s=edge.length; i<s; i++){
            adjlist.get(edge[i][0]).add(edge[i][1]);
            adjlist.get(edge[i][1]).add(edge[i][0]);
        }
        
        return BFS(n);
    }
    
    public int BFS(int n) {
        Queue<int[]> q = new ArrayDeque<>();
        
        int[] v = new int[n+1];
        // System.out.println(n);
        q.offer(new int[] {1, 0});
        v[1] = 1;
        int max = 0;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int i:adjlist.get(cur[0])) {
                if(v[i]>0) continue;
                
                int next = cur[1]+1;
                v[i] = next;//bfs를 돌면서 v에 거리를 저장.
                max = max<next?next:max; //가장 긴 거리 값을 저장.
                
                q.offer(new int[] {i, next});
            }
        }
        
        int cnt = 0;
        for(int i:v){ //체크된 거리 중에서 max값이랑 일치하는 노드 구하기.
            if(i==max) cnt++;
        }
        
        return cnt;
    }
}