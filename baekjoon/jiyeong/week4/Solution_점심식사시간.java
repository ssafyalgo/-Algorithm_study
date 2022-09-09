import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_점심식사시간 {

	static class Node{
		int r,c;
		public Node(int r, int c){
			this.r =r;
			this.c =c;
		}
	}
	
	static class Stairs{
		int r,c,k;
		public Stairs(int r, int c, int k){
			this.r =r;
			this.c =c;
			this.k =k;
		}
	}
	
	static class Job{
		int index,time;
		public Job(int index, int time){
			this.index =index;
			this.time =time;
		}
	}
	
	static int N, min, P, S;
	static ArrayList<Node> persons;
	static ArrayList<Stairs> stairs;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for(int t=1;t<=TC;t++) {
			N = Integer.parseInt(br.readLine());
			min = Integer.MAX_VALUE;
			persons = new ArrayList<>();
			stairs = new ArrayList<>();
			for(int i=0;i<N;i++) {
				StringTokenizer stz = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					int temp = Integer.parseInt(stz.nextToken());
					if(temp==0) continue;
					if(temp==1) {
						persons.add(new Node(i,j));
					} else {
						stairs.add(new Stairs(i,j,temp+1));
					}
				}
			}
			P = persons.size();
			S = stairs.size();
			perm(0,new int[P]);
			System.out.println("#"+t+" "+min);
		}
	}
	private static void perm(int cnt, int[] select) {
		if(cnt==P) {
			int time = simul(select);
			if(time<min) { min = time;}
			return;
		}
		
		for(int i=0;i<stairs.size();i++) {
			select[cnt]=i;
			perm(cnt+1, select);
		}
	}
	private static int simul(int[] select) {
		//계단마다 대기큐와 작업큐를 만들기
		PriorityQueue<Job>[] rdq = new PriorityQueue[S];
		PriorityQueue<Job>[] jq = new PriorityQueue[S];
		for(int i=0;i<S; i++) {
			rdq[i] = new PriorityQueue<Job>((a,b)->{return a.time-b.time;});
			jq[i] = new PriorityQueue<Job>((a,b)->{return a.time-b.time;});
		}
		
		//대기큐 채우기
		for(int i=0;i<P;i++) {
			Node p = persons.get(i);
			Stairs s = stairs.get(select[i]);
			int mht = Math.abs(p.r-s.r)+Math.abs(p.c-s.c);
			rdq[select[i]].add(new Job(i,mht));
		}
		
		//초기화
		int max = Integer.MIN_VALUE;
		for(int i=0;i<S;i++) {
			if(rdq[i].isEmpty()) continue;
			Job init = rdq[i].poll();
			int time = init.time;
			jq[i].add(init);
			
			while(!rdq[i].isEmpty()) {
				if(jq[i].size()<3) {
					Job temp = rdq[i].poll();
					if(time>temp.time) {temp.time=time-1;}
					jq[i].add(temp);
				}else {
					Job done = jq[i].poll();
					time = done.time + stairs.get(i).k;
					while(!jq[i].isEmpty()&&jq[i].peek().time+stairs.get(i).k<=time) {jq[i].poll();}
				}
			}
			while(!jq[i].isEmpty()) {
				time = jq[i].poll().time + stairs.get(i).k;
				while(!jq[i].isEmpty()&&jq[i].peek().time+stairs.get(i).k<=time) {jq[i].poll();}
			}
			
			if(time>max) {max = time;}
		}
		
		return max;
	}

}
