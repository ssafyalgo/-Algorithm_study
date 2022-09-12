import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Solution {
    static int N;
    static int [][] map;
    static class Pos{
        int r, c;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static class P{
        int num, time;
        boolean instair =false;
        public P(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
    static List<Pos> stairs;
    static List<Pos> person;
    static int [] order;
    static int mintime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T=Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T ; tc++) {
            N=Integer.parseInt(br.readLine());
            map=new int[N][N];
            stairs=new ArrayList<>();
            person=new ArrayList<>();
            int pNum=0;
            mintime=Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine()," ");
                for (int j = 0; j < N; j++) {
                    map[i][j]=Integer.parseInt(st.nextToken());
                    if(map[i][j]>1){    //계단 위치 저장
                        stairs.add(new Pos(i,j));
                    }
                    if(map[i][j]==1){
                        pNum++;
                        person.add(new Pos(i,j));
                    }
                }
            }
 
            order=new int[pNum];
 
            ncr(0);
 
            sb.append("#").append(tc).append(" ").append(mintime).append("\n");
        }
        System.out.println(sb.toString());
    }
 
    private static void ncr(int cnt) {
        if(cnt== order.length){
            lunch();
            return;
        }
        for (int i = 0; i < 2; i++) {
            order[cnt]=i;
            ncr(cnt+1);
        }
    }
 
    private static void lunch() {
        int spendtime=0;
        int pNum=person.size();
        List<P> select=new ArrayList<>();
 
        for (int i = 0; i < pNum; i++) {
            int r=person.get(i).r;
            int c=person.get(i).c;
            int dist = Math.abs(r-stairs.get(order[i]).r)+Math.abs(c-stairs.get(order[i]).c);
            select.add(new P(order[i], dist));
        }
 
        int [] exits = new int [2]; //현재 계단에 몇명이 있는지
        int size = select.size();
        while(true){
            spendtime++;
            if(spendtime>mintime)   return;
 
            for (int i = 0; i < size; i++) {
                if (select.get(i).time > 0 && select.get(i).instair) {    //계단으로 진입한 사람
                    select.get(i).time--;
                    if (select.get(i).time == 0) {
                        exits[select.get(i).num] -= 1;
                    }
                }
            }
            for (int i = 0; i < size; i++) {
                if(spendtime>=select.get(i).time +1&&!select.get(i).instair){  //시간 조건 만족하고 아직 계단에 가지 못했으면
                    if(exits[select.get(i).num]<3){ //계단에 사람이 3명미만이면
                        exits[select.get(i).num]+=1;    //계단으로 들어감
                        select.get(i).instair =true;
                        int stairIdx=select.get(i).num;    //선택한 계단 번호
                        select.get(i).time =map[stairs.get(stairIdx).r][stairs.get(stairIdx).c];
                    }
                }
            }
 
            boolean allSuccess = true;
            //모두 완료했는지 체크
            for (int i = 0; i < size; i++) {
                if(select.get(i).time !=0){   //아직 시간이 남았거나 계단에 있는 사람이 있으면
                    allSuccess=false;
                    break;
                }
            }
            if(allSuccess) break;
        }
        if(spendtime<mintime)   mintime=spendtime;
    }
}