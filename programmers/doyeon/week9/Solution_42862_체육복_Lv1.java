package week9;

import java.util.Arrays;
import java.util.HashMap;

public class Solution_42862_체육복_Lv1 {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        boolean [] person = new boolean[n+1];
        HashMap<Integer,Integer> lostmap = new HashMap<>();
        Arrays.sort(lost);
        Arrays.sort(reserve);
        for(int i=0;i<lost.length;i++){
            lostmap.put(lost[i],1);
        }
        for(int i=1;i<n+1;i++){
            if(lostmap.get(i)==null)
                person[i]=true;
        }
        for(int i=0;i<reserve.length;i++){
            if(lostmap.get(reserve[i])!=null){  //빌려줄 수 없음(여벌의 체육복을 가진 학생이 도난도 당함)
                person[reserve[i]]=true;
                continue;
            }
            for(int j=0;j<lost.length;j++){
                if(!person[lost[j]]&&((reserve[i]-1==lost[j])||(reserve[i]+1==lost[j]))){
                    person[lost[j]]=true;
                    reserve[i]=-2;
                }
            }
        }

        for(int i=1;i<n+1;i++){
            if(person[i])   answer++;
        }
        return answer;
    }
}
