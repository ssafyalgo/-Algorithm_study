import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_5555 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String find=br.readLine();
        int N=Integer.parseInt(br.readLine());
        int cnt=0;

        for (int i = 0; i < N; i++) {
            String s=br.readLine();
            String ring=s.concat(s);
            if(ring.contains(find)) cnt++;
        }
        System.out.println(cnt);
    }
}
