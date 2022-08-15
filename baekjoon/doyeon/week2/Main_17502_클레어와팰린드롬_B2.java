package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_17502_클레어와팰린드롬_B2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());
        StringBuffer sb=new StringBuffer();
        String s = br.readLine();
        sb.append(s);
        if(s.length()%2!=0&&s.charAt(s.length()/2)=='?'){
            s=sb.replace(s.length()/2,s.length()/2+1,"a").toString();
        }

        int last=s.length()-1;
        for (int i = 0; i < s.length()/2; i++) {
            if (s.charAt(i)=='?'&&s.charAt(last-i)=='?'){
                sb.replace(i,i+1,"a");
                sb.replace(last-i,last-i+1,"a");
            }
            else if (s.charAt(i)=='?'){
                sb.replace(i,i+1,s.substring(last-i,last-i+1));
            }
            else if (s.charAt(last-i)=='?'){
                sb.replace(last-i,last-i+1,s.substring(i,i+1));
            }
        }
        System.out.println(sb.toString());
    }
}
