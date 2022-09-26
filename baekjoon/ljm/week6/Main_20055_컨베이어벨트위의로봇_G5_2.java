import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 컨베이어 벨트의 위치마다 내구도 있음
 * 회전할 때 다음 위치에 로봇이 없으며 내구도가 1이상 있어야함
 * 내구도가 0인 칸의 개수가 K개 이상이면 과정을 종료
 * q에 로봇 담아서 해당 위치 -1
 * ArrayList를 통해서 뒤에값앞으로 넣고 맨뒤값 remove
 */
public class Main_20055_컨베이어벨트위의로봇_G5_2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> con = new ArrayList<>();
//		ArrayDeque<Integer> con = new ArrayDeque<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N*2; i++) {
			con.add(Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(simul(N, K, con));
	}

	private static int simul(int N, int K, ArrayList<Integer> con) {
		Queue<Integer> robot = new ArrayDeque<>();
		
		 //한칸 회전
		con.add(0, con.get(2*N-1));
		con.remove(2*N); 
		//로봇 올림
		robot.add(0);
		//내구도 깍기 
		con.set(0, con.get(0)-1); 
		
		int zerocnt = 0;
		if(con.get(0)==0) zerocnt++;
		if(zerocnt==K) return 1;
		
		int time = 1;
		boolean[] v = new boolean[2*N];
		
		
		while(true) {
			int qsize = robot.size();
			v = new boolean[2*N];
			int last = con.get(2*N-1);
			con.add(0, last); //한칸 회전
			con.remove(2*N);
			
			for (int i = 0; i < qsize; i++) {
				int cur = robot.poll()+1;//로봇도 함께 회전함
				if(cur==N-1) continue; //회전한 후 N-1번째 위치면 (0부터 시작했으므로) 바로 다음 로봇으로
				
				int d = con.get(cur+1)-1; //다음 위치로 갈 수 있기 때문에 내구도 -1
				
				if(v[cur+1] || d<0) { // 다음 위치가 0이거나 로봇이 있으면
					robot.add(cur); //현재 위치 저장
					v[cur] = true; //v체크
				} else { //다음 위치로 갈 수 있으면
					con.set(cur+1, d); //다음 위치에 -1 값 저장.
					if(d==0) {
						zerocnt++;
					}
					v[cur+1]=true; //2
					if(cur+1<N-1) { //다음 위치가 N-1 위치가 아닌경우 만 넣기
						robot.add(cur+1);
					}
				}
			}
			
			if(con.get(0)>=1) {
				if(con.get(0)==1) zerocnt++;
				robot.add(0); //0번째에 없으면 로봇 올림
				con.set(0, con.get(0)-1);
			}
			
			time++;
			if(zerocnt>=K) break;
		}
		
		return time;
	}

}
