package week9;
import java.util.*;
public class Solution_42747_HIndex_Lv2 {
    public int solution(int[] citations) {
        int answer = 0;
        int len=citations.length;
        Arrays.sort(citations);

        for(int i=0;i<len;i++){
            int h=citations[i];
            int lt=i;   //나머지 논문 h번 이하
            int gt=len-i;   //h번 이상
            if(lt<=h&&gt>=h)    answer=h;
            else{
                for(int j=h-1;j>=0;j--){
                    if(i>0?j>citations[i-1]:citations[i]>=j){
                        lt=i;
                        gt=len-i;
                        if(lt<=j&&gt>=j){
                            answer=j;
                            break;
                        }
                    }
                }
                break;
            }
        }
        return answer;
    }
}
