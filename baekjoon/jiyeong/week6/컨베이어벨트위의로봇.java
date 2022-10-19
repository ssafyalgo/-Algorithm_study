import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		//N길이 컨베이어벨트
		//2N길이 벨트 (1~2N까지의 칸)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());
		//i번칸 내구도 Ai
		//1번칸이 있는 위치 : 올리는 위치
		//N번칸이 있는 위치 : 내리는 위치
		stz = new StringTokenizer(br.readLine());
		int[] belt = new int[2*N];
		int zero = 0;
		for(int i=0;i<2*N;i++) {
			belt[i] = Integer.parseInt(stz.nextToken());
			if(belt[i]==0) zero++;
		}
		
		// 1번칸에만 박스로봇을 올릴수 있다.
		// 박스로봇이 N번칸에 오면 내린다.
		// 로봇이 올라가거나, 이동한칸 은 내구도가 감소함
		int[] robot = new int[N];
		
		//<한 단계>
		int ans = 0;
		// 내구도가 0인칸의 개수가 K이상이면 과정 종료
		while(zero<K) {
			ans++;
//			System.out.println(ans);
			//1. 벨트가 각 칸 위에 있는 로봇과 함께 한칸 회전한다.
			//벨트회전
			int lastBelt = belt[2*N-1];
			for(int i=2*N-1;i>0;i--) {
				belt[i] = belt[i-1];
			}
			belt[0] = lastBelt;
			//로봇회전
			for(int i=N-1;i>=1;i--) {
				robot[i]=robot[i-1];
			}
			robot[0]=0;
			if(robot[N-1]!=0) {robot[N-1]=0;}
//			System.out.println(Arrays.toString(belt));
//			System.out.println(Arrays.toString(robot));
			//2. 먼저올라간 로봇부터 회전방향으로 한칸이동 한다.이동불가시 이동x
			//(이동불가 : 이동하려는 칸에 로봇이 있음. 내구도가 0이하일시)
			for(int i=N-1;i>=1;i--) {
				if(robot[i-1]!=0&&robot[i]==0&&belt[i]>0) {
					belt[i]--;
					robot[i]=robot[i-1];
					robot[i-1]=0;
					if(belt[i]<=0) zero++;
				}
			}
			if(robot[N-1]!=0) robot[N-1]=0;
//			System.out.println(Arrays.toString(belt));
//			System.out.println(Arrays.toString(robot));
			//3. 올리는칸의 내구도가 0이 아니면 로봇올리기
			if(belt[0]>0) {
				robot[0]=1;
				belt[0]--;
				if(belt[0]<=0) zero++;
			}
//			System.out.println(Arrays.toString(belt));
//			System.out.println(Arrays.toString(robot));
		}
		//종료되었을때 몇 번째 단계가 진행중이었는지 구하기. 가장 처음 수행되는 단계는 1번째임.
		System.out.println(ans);
		
	}

}