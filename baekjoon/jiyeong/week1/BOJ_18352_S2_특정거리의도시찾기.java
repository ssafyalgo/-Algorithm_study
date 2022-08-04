import java.io.*;
import java.util.*;

public class BOJ_18352_S2_특정거리의도시찾기 {

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		Task solver = new Task();
		solver.solve(in, out);
		out.close();
	}
	
    static class Task {
        //전역변수
        public void solve(InputReader in, PrintWriter out) {
        	int N = in.nextInt();//도시수
        	int M = in.nextInt();//도로수
        	int K = in.nextInt();//최소거리
        	int X = in.nextInt();//출발도시
        	
        	LinkedList<Integer>[] map = new LinkedList[N+1];
        	for(int i=0;i<=N;i++) {
        		map[i]=new LinkedList<>();
        	}
        	for(int i=0;i<M;i++) {
        		int a = in.nextInt();
        		int b = in.nextInt();
        		map[a].add(b);
        	}
        	
        	ArrayList<Integer> ans = new ArrayList<>();
        	int[] visited = new int[N+1];
        	Arrays.fill(visited, -1);
        	Queue<Integer> q = new LinkedList<>();
        	q.add(X);
        	visited[X] = 0;
        	while(!q.isEmpty()) {
        		int t = q.poll();
        		for(int m:map[t]) {
        			if(visited[m]==-1) {
        				visited[m]=visited[t]+1;
        				q.add(m);
        				if(visited[m]==K) {
        					ans.add(m);
        				}
        			}
        		}
        	}
        	if(ans.isEmpty()) {out.println("-1");}
        	Collections.sort(ans);
        	for(int a:ans) {
        		out.println(a);
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
