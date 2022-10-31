import java.util.*;
import java.util.Map.*;

/* 단 한 명의 선수를 제외하고 모든 선수가 마라톤을 완주하였을 때,
   완주하지 못한 선수의 이름을 return */

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        /* 로직 */
        HashMap<String, Integer> maraton = new HashMap<>();

        // 참여 선수 HashMap 저장 - value 해당 이름을 가진 선수의 명 수
        for (String key: participant) {
            maraton.put(key, maraton.getOrDefault(key, 0) + 1);
        }
        /*
        System.out.println("#21# hashMap 확인");
        System.out.println(maraton);
        */


        // 완주한 사람 기존 HaspMap에서 value 값 1 빼기 -> 0이 아니면 완주 못한 사람
        for (String key: completion) {
            maraton.put(key, maraton.get(key) - 1);
        }
        /*
        System.out.println("#21# 완주하지 못한 사람 누구야");
        System.out.println(maraton);
        */

        // 완주못한 선수 이름 판별
        // HashMap<String, Integer> maraton = new HashMap<>();
        for (Entry<String, Integer> entrySet: maraton.entrySet()) {
            if (entrySet.getValue()!=0) {
                answer = entrySet.getKey();
                break;
            }
        }


        return answer;
    }
}