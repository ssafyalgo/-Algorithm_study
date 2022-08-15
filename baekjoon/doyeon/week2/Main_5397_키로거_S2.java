package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_5397_키로거_S2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());

        for (int j = 0; j < N; j++) {
            String s=br.readLine();
            String [] key =s.split("");
            Stack<String> pre = new Stack<>();
            Stack<String> post = new Stack<>();
            StringBuffer sb=new StringBuffer();

            for(String in:key){
                switch (in){
                    case "<":
                        if(!pre.isEmpty())
                            post.push(pre.pop());
                        break;
                    case ">":
                        if(!post.isEmpty())
                            pre.push(post.pop());
                        break;
                    case "-":
                        if(!pre.isEmpty())
                            pre.pop();
                        break;
                    default:
                        pre.push(in);
                        break;
                }
            }
            while (!post.isEmpty()){
                pre.push(post.pop());
            }

            for (int i = 0; i < pre.size(); i++) {
                sb.append(pre.elementAt(i));
            }
            System.out.println(sb);
        }
    }
}