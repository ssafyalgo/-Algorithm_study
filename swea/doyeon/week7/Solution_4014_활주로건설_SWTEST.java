package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_4014_활주로건설_SWTEST {
	static int N, X, res;
	static int[][] map;
	static boolean [] build;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            N=Integer.parseInt(st.nextToken());
            X=Integer.parseInt(st.nextToken());
            map=new int[N][N];
            res=0;
            build=new boolean[N];
            
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine()," ");
                for (int j = 0; j < N; j++) {
                    map[i][j]=Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                if(garo(i)) {
                	res++;
                	System.out.println(i);
                }
            }
            for (int i = 0; i < N; i++) {
                if(searo(i)) {
                	res++;
                	System.out.println(i);
                }
            }
            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        System.out.println(sb.toString());
    }

	private static boolean searo(int i) {
		int idx=0;
        int gh=0, lh=0;
        List<Integer> idxs = new ArrayList<>();
        build=new boolean[N];

        for (int j = 1; j < N; j++) {
            if(map[j-1][i]!=map[j][i]) {
            	if(map[j-1][i]>map[j][i]) {	//뒤에 높이가 더 낮을 때, 뒤에 경사로를 설치해야 함 
            		if(map[j-1][i]-map[j][i]>1)	return false;	//차이가 2이상이면 설치못함
            			
            		int cnt=0;
            		for (int k = j; k < N; k++) {
            			if(map[j][i]!=map[k][i])	break;
            			else if(!build[k]&&map[j][i]==map[k][i]) {	
            				cnt++;
            				build[k]=true;
            				if(cnt==X)	break;
            			}
					}
            		if(cnt<X)	return false;
            	}
            	if(map[j-1][i]<map[j][i]) {	//앞에 높이가 더 낮을 때, 앞에 경사로를 설치해야 함 
            		if(map[j][i]-map[j-1][i]>1)	return false;	//차이가 2이상이면 설치못함
            		
            		int cnt=0;
            		for (int k = j-1; k >= 0; k--) {
            			if(map[j-1][i]!=map[k][i])	break;
            			else if(!build[k]&&map[j-1][i]==map[k][i]) {
            				cnt++;
            				build[k]=true;
            				if(cnt==X)	break;
            			}
					}
            		if(cnt<X)	return false;
            	}
            }
        }
        return true;
	}

	private static boolean garo(int i) {
        int idx=0;
        int gh=0, lh=0;
        List<Integer> idxs = new ArrayList<>();
        build=new boolean[N];

        for (int j = 1; j < N; j++) {
            if(map[i][j-1]!=map[i][j]) {
            	if(map[i][j-1]>map[i][j]) {	//뒤에 높이가 더 낮을 때, 뒤에 경사로를 설치해야 함 
            		if(map[i][j-1]-map[i][j]>1)	return false;	//차이가 2이상이면 설치못함
            		
            		int cnt=0;
            		for (int k = j; k < N; k++) {
            			if(map[i][j]!=map[i][k])	break;
            			else if(!build[k]&&map[i][j]==map[i][k]) {	
            				cnt++;
            				build[k]=true;
            				if(cnt==X)	break;
            			}
					}
            		if(cnt<X)	return false;
            	}
            	if(map[i][j-1]<map[i][j]) {	//앞에 높이가 더 낮을 때, 앞에 경사로를 설치해야 함 
            		if(map[i][j]-map[i][j-1]>1)	return false;	//차이가 2이상이면 설치못함
            		
            		int cnt=0;
            		for (int k = j-1; k >= 0; k--) {
            			if(map[i][j-1]!=map[i][k])	break;
            			else if(!build[k]&&map[i][j-1]==map[i][k]) {
            				cnt++;
            				build[k]=true;
            				if(cnt==X)	break;
            			}
					}
            		if(cnt<X)	return false;
            	}
            }
        }
        return true;
	}
}
