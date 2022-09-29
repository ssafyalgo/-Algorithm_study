package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20055_컨베이어벨트위의로봇_G5 {
    static int N, K, cnt, step;
    static int [] dur;
    static int [] robot;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N= Integer.parseInt(st.nextToken());
        K= Integer.parseInt(st.nextToken());
        dur=new int[2*N];
        robot=new int[N];
        step=0;

        st = new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < 2*N; i++) {
            dur[i]=Integer.parseInt(st.nextToken());
        }

        while(check(dur)){
            //1. 벨트 한칸 회전
            int last=dur[2*N-1];
            for (int i = 2*N-1; i > 0; i--) {    //내구성
                dur[i]=dur[i-1];
            }
            dur[0]=last;
            for (int i = N-1; i >0 ; i--) { //로봇
                robot[i]=robot[i-1];
            }
            robot[0]=0;
            robot[N-1]=0;

            for (int i = N-1; i > 0; i--) {
                if(robot[i]==0&&robot[i-1]==1&&dur[i]>=1){
                    robot[i-1]=0;
                    robot[i]=1;
                    dur[i]--;
                }
            }
            if(dur[0]>0){
                dur[0]--;
                robot[0]=1;
            }
            step++;
        }
        System.out.println(step);
    }

    private static boolean check(int[] dur) {
        int cnt=0;
        for (int i = 0; i < 2*N; i++) {
            if(dur[i]==0)
                cnt++;
            if(cnt==K)  return false;
        }
        return true;
    }
}
