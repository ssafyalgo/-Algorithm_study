import java.util.*;

public class Solution_42583_다리를지나는트럭_L2 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = bridge_length;
        int len = truck_weights.length;
        
        Queue<Integer> q = new ArrayDeque<>();
        int cnt = 0;
        boolean chk = false;
        
        int sum = 0;

        for(int t: truck_weights){
            while(true){
                answer++;
                
                if(q.isEmpty()) {
                    q.offer(t);
                    sum += t;
                    break;
                } else if (q.size()==bridge_length){
                    answer--;
                    sum-= q.poll();
                } else if(sum+t <= weight) { 
                    q.offer(t);
                    sum += t;
                    break; 
                } else {
                    q.offer(0);
                }
            }
        }
        
        return answer;
    }
}