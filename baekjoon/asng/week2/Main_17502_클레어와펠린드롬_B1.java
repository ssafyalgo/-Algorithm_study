package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_17502_클레어와펠린드롬_B1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		String str = br.readLine();
		char[] arr = str.toCharArray();
		for (int i = 0; i < N; i++) {
			if (arr[i] == '?') {
				if (arr[N - i - 1] == '?') {
					arr[i] = 'a';
					arr[N - i - 1] = 'a';
				} else {
					arr[i] = arr[N - i - 1];
				}
			}
		}
		str = new String(arr);
		System.out.println(str);
	}

}
