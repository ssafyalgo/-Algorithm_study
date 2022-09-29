package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_2383_미생물격리_SWTest {
    static int N;
    static int M;   //격리시간
    static int K;   //미생물 군집의 개수
    static class Kunzip implements Comparable<Kunzip> {
        int r, c;
        int microNum;
        int dir;
        int index;
        public Kunzip(int r, int c, int microNum, int dir, int index) {
            this.r = r;
            this.c = c;
            this.microNum = microNum;
            this.dir = dir;
            this.index = index;
        }
        @Override
        public int compareTo(Kunzip o) {
            if (this.r == o.r) {
                if(this.c==o.c)
                    return -Integer.compare(this.microNum,o.microNum);
                return Integer.compare(this.c, o.c);
            }
            return Integer.compare(this.r, o.r);
        }
    }
    static PriorityQueue<Kunzip> list;
    static PriorityQueue<Kunzip> tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T=Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T ; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());
            K=Integer.parseInt(st.nextToken());
            list = new PriorityQueue<>();
            tmp = new PriorityQueue<>();
            int index=1;
            int total=0;
            for (int i = 0; i < K; i++) {
                st=new StringTokenizer(br.readLine()," ");
                int r=Integer.parseInt(st.nextToken());
                int c=Integer.parseInt(st.nextToken());
                int n=Integer.parseInt(st.nextToken());
                int d=Integer.parseInt(st.nextToken())-1;
                list.offer(new Kunzip(r,c,n,d,index++));
            }

            isolation(0);

            for (Kunzip k:list) {
                if(k.index!=0)
                    total+=k.microNum;
            }
            sb.append("#").append(tc).append(" ").append(total).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void isolation(int t) {
        while (t != M) {
            while(!list.isEmpty()){
                Kunzip k = list.poll();
                if (k.index != 0) {
                    move(k);
                }
                tmp.offer(k);
            }

            Kunzip top = tmp.peek();
            int r = top.r;
            int c = top.c;

            while (!tmp.isEmpty()){
                Kunzip k = tmp.poll();
                if (k.index != 0 && k.index != top.index) {
                    if (k.r == r && k.c == c) {
                        top.microNum += k.microNum;
                        k.microNum = 0;
                        k.index = 0;
                    } else {
                        top = k;
                        r = top.r;
                        c = top.c;
                    }
                }
                list.offer(k);
            }
            t++;
        }
    }

    static int [] direction= new int[]{0, 1, 2, 3}; //상하좌우
    private static void move(Kunzip k) {
        int loc=0;
        switch (k.dir){
            case 0:
                k.r=k.r-1;
                loc=k.r;
                break;
            case 1:
                k.r=k.r+1;
                loc=k.r;
                break;
            case 2:
                k.c=k.c-1;
                loc=k.c;
                break;
            case 3:
                k.c=k.c+1;
                loc=k.c;
                break;

        }
        if(!check(loc)){
            k.microNum=k.microNum/2;
            if(k.microNum==0){
                k.index=0;
            }
            if(k.index!=0) {
                if (k.dir <= 1) {
                    k.dir = k.dir == 0 ? 1 : 0;
                } else if (k.dir <= 3) {
                    k.dir = k.dir == 2 ? 3 : 2;
                }
            }
        }
    }

    private static boolean check(int loc) {
        return loc>0&&loc<N-1;
    }
}
