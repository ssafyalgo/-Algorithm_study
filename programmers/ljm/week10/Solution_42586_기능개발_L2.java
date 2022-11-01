import java.util.*;

public class Solution_42586_기능개발_L2 {
	public int solution(int[][] sizes) {
        int answer = 0;
        int r, c;
        r = c = 0;
        for(int i=0,s=sizes.length; i<s; i++){
            if(sizes[i][0]<sizes[i][1]){
                int tmp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = tmp;
            }
            
            r = r<sizes[i][0]?sizes[i][0]:r;
            c = c<sizes[i][1]?sizes[i][1]:c;
        }
        
        
        return r*c;
    }
}