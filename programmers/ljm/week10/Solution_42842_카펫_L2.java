import java.util.*;
/*
brown+yellow가 카펫 크기 임

sqrt(카펫크기) 만큼 for문돌려서 
가능한 배열 모두 저장.

(가로+세로)*2-4가 brown이 되는 경우를 찾기 
*/
public class Solution_42842_카펫_L2 {
	public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int size = brown+yellow;
        
        for(int i=3, s=(int)Math.sqrt(size); i<=s; i++) {
            if(size%i==0){
                int length = size/i;
                int width = i;
                if((length+width)*2-4 == brown) {
                    answer[0] = length;
                    answer[1] = width;
                    return answer;
                }
            }
        }
        
        return answer;
    }
}