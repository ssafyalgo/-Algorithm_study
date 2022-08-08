import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1051 {
    static int N, M;
    static int [][] rect;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        rect=new int[N][M];

        for (int i = 0; i < N; i++) {
            String s =br.readLine();
            char [] ch=s.toCharArray();
            for (int j = 0; j < M; j++) {
                rect[i][j]=ch[j]-'0';
            }
        }

        int max=Integer.MIN_VALUE;
        int length=0;

        for (int i = 0; i < (N<M?N:M); i++) {
            for (int j = 0; j < N-i; j++) {
                for (int k = 0; k < M-i; k++) {
                    if(check(i,j,k)) {
                        length=i+1;
                    }
                }
            }
        }
        System.out.println(length*length);
    }

    private static boolean check(int i, int j,int k) {
        if((rect[j][k]==rect[j][k+i])&&(rect[j][k+i]==rect[j+i][k])&&(rect[j+i][k]==rect[j+i][k+i]))
            return true;
        return false;
    }
}
