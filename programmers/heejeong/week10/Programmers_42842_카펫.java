package study_week_10;

public class Programmers_42842_카펫 {
    /* 카펫의 중앙에는 노란색, 테두리 1줄은 갈색으로 칠해져 있다.

   카펫의 노란색과 갈색으로 색칠된 격자의 개수가 주어질 때,
   카펫의 가로, 세로 크기를 순서대로 return
   ! 가로 길이 >= 세로 길이
   ! brown 범위: 8 <= brown <= 5000
   ! yellow 범위: 1 <= yellow <= 2,000,000*/

    class Solution {
        public int[] solution(int brown, int yellow) {
            //int[] answer = {};
            int[] answer = {0, 0};

            int xy = brown + yellow;
            int xPy = brown/2 +2;

            int temp =3 ;

            while(true){
                int x = xPy -temp;
                int y = temp;

                if(xy%y==0 && xy ==x*y){
                    answer[0]=x;
                    answer[1]=y;
                    break;
                }

                temp ++;
            }



            return answer;
        }
    }
}
