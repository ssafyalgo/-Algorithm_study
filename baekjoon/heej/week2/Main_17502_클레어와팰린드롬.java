package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 팰린드롬 문자열 : 앞에서부터 읽으나 뒤에서부터 읽으나 같은 문자열을 의미함
*  ex. "rotator", "racecar", "a" == 팰린드롬 문자열 */
// Q. 팰린드롬 문자열 복구 프로그램
public class Main_17502_클레어와팰린드롬 {

    static int N;

    static int asciiNum = 97;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        /* 입력 */
        N = Integer.parseInt(br.readLine());
        char[] strArr = (br.readLine()).toCharArray();

        /* 로직 */
        // 배열 i번째와 그와 대칭되는 N-i-1번째를 비교해서 문자열이 있는 쪽으로 맞춰주기
        // 둘다 문자열이 없다면 abc .. 순으로 넣어주기

        // ! 입력받은 문자열이 홀수 인 경우 가운데 문자열이 ? 인지 확인 후 처리 -> ? 이면 알파벳 넣어주기
        if (N%2==1) {
            if (strArr[N/2]=='?') {
                strArr[N/2] = (char) alpha();
            }
        }
        // 팰린드롬 문자열 맞추기
        palindrom(strArr);


        /* 출력 */
        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i]);
        }
        System.out.println(sb);
    }

    // [@Method] 팰린드롬 문자열 맞추기
    private static void palindrom(char[] strArr) {
        for (int i = 0; i < N/2; i++) {
            char left = strArr[i];
            char right = strArr[N-i-1];

            if (left=='?' && right=='?') {
                int temp = alpha();
                strArr[i] = (char) temp;
                strArr[N-i-1] = (char) temp;
            }
            else if (left=='?') {
                strArr[i] = strArr[N-i-1];
            }
            else if (right=='?') {
                strArr[N-i-1] = strArr[i];
            }
        }
        //System.out.println(Arrays.toString(strArr));
    }

    // [@Method] 둘 다 ? 일 경우 알파벳 넣기
    private static int alpha() {
        if (asciiNum > 122) {       // 122 = z
            asciiNum = 97;
            return asciiNum++;
        }
        else return asciiNum++;
    }


}
