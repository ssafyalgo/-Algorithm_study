package java08_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*   반지 S5 10
 *   
 *   반지처럼 문자열의 처음과 끝이 연결되어 있다.
 *   -> 연결된 반지가 처음에 주어진 문자열이 포함되어있는 지 확인만 하면 되므로 (개수가 아니다.)
 *   -> 뒷 문장과 연결 시키기 위해 문장을 한번더 더해준다.
 */
public class Main_5555_반지_S5 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String str = br.readLine();
		
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			String ring = br.readLine();
			ring += ring;
			if(ring.contains(str)) cnt++;
		}
        
		System.out.println(cnt);
	}
}