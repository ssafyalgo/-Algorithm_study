package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9372_상근이의여행_S4{
    static int N,M;
    static boolean [] visited;
    static int count;
    static List<ArrayList<Integer>> schedule;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T=Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            schedule=new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());
            visited=new boolean[N];
            count=0;

            for (int j = 0; j < N; j++) {
                schedule.add(new ArrayList<>());
            }
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int start=Integer.parseInt(st.nextToken())-1;
                int end=Integer.parseInt(st.nextToken())-1;
                schedule.get(start).add(end);
                schedule.get(end).add(start);
            }
            bfs(0);
            System.out.println(count-1);
        }

    }
    private static void bfs(int i) {
        Queue<Integer> q=new LinkedList<>();

        q.offer(i);
        visited[i]=true;

        while(!q.isEmpty()) {
            int cur=q.poll();
            count++;
            for (int k = 0; k < schedule.get(cur).size(); k++) {
                if(!visited[schedule.get(cur).get(k)]) {
                    q.offer(schedule.get(cur).get(k));
                    visited[schedule.get(cur).get(k)]=true;
                }
            }
        }
    }
}

