import java.util.*;
public class Main_18352 {

    public static int n;
    public static int m;
    public static int k;    //최단 거리
    public static int start;    //시작 노드
    public static ArrayList<Integer>[] graph;
    public static int[] distance;   //거리 저장 배열
    public static Queue<Integer> q = new LinkedList<>();  //큐 생성

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); //노드 개수
        m = sc.nextInt(); //간선 개수
        k = sc.nextInt(); //최단 거리
        start = sc.nextInt(); //시작 노드

        distance = new int[n+1];

        graph = new ArrayList[n+1];

        for(int i=0;i<n+1;i++){
            graph[i] = new ArrayList<>();
        }

        //간선 정보 입력 받기
        for(int i=0;i<m;i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            graph[from].add(to);
        }

        q.add(start);     //시작 노드 큐에 넣기

        while(!q.isEmpty()){
            int v = q.poll(); //큐에서 꺼내기

            for(int i=0;i<graph[v].size();i++){
                int next = graph[v].get(i);
                if(distance[next] ==0 && start != next){
                    distance[next] = distance[v]+1;   //거리 계산
                    q.add(next);  //큐에 노드 넣기
                }
            }
        }
        boolean answer = false;
        for(int i=0;i<distance.length;i++){
            if(distance[i] ==k){
                answer = true;
                System.out.println(i);
            }
        }

        if(!answer){
            System.out.println(-1);
        }
    }
}