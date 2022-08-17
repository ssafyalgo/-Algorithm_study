import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
//        InputReader in = new InputReader(new FileInputStream("input.txt"));
//        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("output.txt")));
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {
        //전역변수
        int N,r,c;
        public void solve(InputReader in, PrintWriter out) {
            int N = in.nextInt();
            r = in.nextInt();
            c = in.nextInt();

            out.println(Z(0,0,N));
        }

        private int Z(int startR, int startC, int exponent){
            if (exponent==0){ return 0;}

            //r c가 몇사분면에 있는지 확인하고 분기
            int mid = 1<<(exponent-1);
            //1사분면
            if (r < startR+mid && c < startC+mid){
                return Z(startR,startC,exponent-1);
            }
            //2사분면
            else if (r < startR+mid && c >= startC+mid){
                return Z(startR, startC+mid, exponent-1) + mid*mid;
            }
            //3사분면
            else if (r >= startR+mid && c < startC+mid){
                return Z(startR+mid, startC, exponent-1) + mid*mid*2;
            }
            //4사분면
            else {
                return Z(startR+mid, startC+mid, exponent-1) + mid*mid*3;
            }
        }
    }


    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
