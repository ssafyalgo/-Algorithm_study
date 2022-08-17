package algo0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_클레어와팰린드롬 {
	public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] str = br.readLine().toCharArray();
        for(int i=0; i<(N/2+1); i++){
            if(str[i]=='?'&&str[N-1-i]=='?'){str[i]=str[N-1-i]='a';}
            else if(str[i]=='?'){str[i]=str[N-1-i];}
            else if(str[N-1-i]=='?'){str[N-1-i]=str[i];}
        }
        System.out.println(str);
    }
}
