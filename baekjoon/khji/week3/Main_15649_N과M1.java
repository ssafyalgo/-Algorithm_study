import java.util.Scanner;

public class Main_15649_N과M1 {
	
	static int N;
	static int M;
	static int [] nums;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		nums = new int[M];
		perm(0,0);
	}

	private static void perm(int cnt, int flag) {
		
		if(cnt==M) {
			for(int i=0; i<M; i++) {
				System.out.print(nums[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0; i<N; i++) {
			if( (flag & 1<<i) != 0) continue;
			nums[cnt] = i+1;
			perm(cnt+1, flag | 1<<i);
			
		}
	}
}
