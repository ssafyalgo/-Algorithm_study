import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	
	static class Node implements Comparable<Node>{
		int num,cnt;

		public Node(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			if(cnt==o.cnt) {
				return num-o.num;
			}else {
				return cnt-o.cnt;
			}
		}
	}
	
	static int[][] arr;
	static int R,C,ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz= new StringTokenizer(br.readLine());
		int r = Integer.parseInt(stz.nextToken())-1;
		int c = Integer.parseInt(stz.nextToken())-1;
		int k = Integer.parseInt(stz.nextToken());
		arr = new int[100][100];
		for(int i=0;i<3;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				arr[i][j]=Integer.parseInt(stz.nextToken());
			}
		}
		
		R =3; C=3; ans=0;
		while(ans<101&&arr[r][c]!=k) {
			ans++;
			if(R>=C) {
				row();
			}else {
				col();
			}
		}
		System.out.println(ans>100?-1:ans);
		
	}
	private static void col() {
		int max = Integer.MIN_VALUE;
		int[] cnt;
		for(int i=0;i<C;i++) {
			cnt = new int[200];
			for(int j=0;j<R;j++) {
				cnt[arr[j][i]]++;
			}
			//0으로초기화
			for(int j=0;j<R;j++) {
				arr[j][i]=0;
			}
			PriorityQueue<Node> pq = new PriorityQueue<>();
			for(int c=1;c<200;c++) {
				if(cnt[c]!=0) {
					pq.add(new Node(c,cnt[c]));
				}
			}
			//pq에서 꺼내서 넣기
			int idx =0;
			while(!pq.isEmpty()&&idx<100) {
				Node nd = pq.poll();
				arr[idx++][i] = nd.num;
				arr[idx++][i] = nd.cnt;
			}
			if(max<idx) {max = idx;}
		}
		R = max;
	}
	private static void row() {
		int max = Integer.MIN_VALUE;
		int[] cnt;
		for(int i=0;i<R;i++) {
			cnt = new int[200];
			for(int j=0;j<C;j++) {
				cnt[arr[i][j]]++;
			}
			//0으로초기화
			for(int j=0;j<C;j++) {
				arr[i][j]=0;
			}
			PriorityQueue<Node> pq = new PriorityQueue<>();
			for(int c=1;c<200;c++) {
				if(cnt[c]!=0) {
					pq.add(new Node(c,cnt[c]));
				}
			}
			int idx =0;
			while(!pq.isEmpty()&&idx<100) {
				Node nd = pq.poll();
				arr[i][idx++] = nd.num;
				arr[i][idx++] = nd.cnt;
			}
			if(max<idx) {max = idx;}
		}
		C = max;
	}

}