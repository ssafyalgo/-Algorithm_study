package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_1406_에디터_S2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M;
        String s= br.readLine();
        Deque<Character> front=new ArrayDeque<>();
        Deque<Character> back=new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            front.addLast(s.charAt(i));
        }

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "L":
                    if (front.isEmpty()) continue;
                    back.offerFirst(front.pollLast());
                    break;
                case "D":
                    if (back.isEmpty()) continue;
                    front.offerLast(back.pollFirst());
                    break;
                case "B":
                    if(front.isEmpty())   continue;
                    front.pollLast();
                    break;
                case "P":
                    char c = st.nextToken().charAt(0);
                    front.offerLast(c);
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!front.isEmpty()){
            sb.append(front.pollFirst());
        }
        while(!back.isEmpty()){
            sb.append(back.pollFirst());
        }
        System.out.println(sb.toString());
    }
}

