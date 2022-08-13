package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/* 키보드로 입력한 키는 알파벳 대문자, 소문자, 숫자, 백스페이스, 화살표 이다.
 *  백스페이스 = '-'          !! 이때, 커서의 바로 앞에 글자가 존재한다면, 그 글자를 지운다.
 *  화살표 입력 = '>' or '<'  !! 이때, 커서의 위치를 움직일 수 있다면, 왼쪽 또는 오른쪽으로 1만큼 움직인다.
 *  만약 커서의 위치가 줄의 마지막이 아니라면, 커스 및 커서 오른쪽에 있는 모든 문자는 오른쪽으로 한 칸 이동한다. */

// Q. 비밀번호 창에서 입력한 키가 주어졌을 때, 강산이의 비밀번호를 알아내는 프로그램
public class Main_5397_키로거 {

    static int T;                      // 테스트 케이스 개수

    static StringBuilder sb = new StringBuilder();
    static StringBuilder password = new StringBuilder();    // 도출된 비밀번호

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /* 입력 */
        T = Integer.parseInt(br.readLine());
        String pwd;
        for (int t = 0; t < T; t++) {
            sb.setLength(0);                // sbPwd 초기화
            pwd = br.readLine();

            /* 로직 */
            // !! 문자열의 크기가 1,000,000 임에 따라 배열로는 시간초과 발생 -> stack 2개 사용
            // 2개의 stack 중간 지점을 커서로 간주
            // Method
            // 1) 문자 입력 - 왼쪽 스택에 원소 삽입
            // 2) 백스페이스 - 왼쪽 스택에서 문자 삭제
            // 3) <- 왼쪽 - 왼쪽 스택에서 오른쪽 스택으로 원소 이동
            // 4) -> 오른쪽 - 오른쪽 스택에서 왼쪽 스택으로 원소 이동
            findPassword(pwd);

            /* 출력 */
            System.out.println(sb);
        }

    }

    // [@Method] 비밀번호 도출
    private static void findPassword(String pwd) {
        Stack<Character> stackLeft = new Stack<>();
        Stack<Character> stackRight = new Stack<>();

        for (int i = 0; i < pwd.length(); i++) {
            char temp = pwd.charAt(i);

            // <- 왼쪽 - 왼쪽 스택에서 오른쪽 스택으로 원소 이동
            if (temp=='<') {
                if (!stackLeft.isEmpty()) stackRight.push(stackLeft.pop());
            }
            // -> 오른쪽 - 오른쪽 스택에서 왼쪽 스택으로 원소 이동
            else if (temp=='>') {
                if(!stackRight.isEmpty()) stackLeft.push(stackRight.pop());
            }
            // 백스페이스 - 왼쪽 스택에서 문자 삭제
            else if (temp=='-') {
                if (!stackLeft.isEmpty()) stackLeft.pop();
            }
            // 문자 입력 - 왼쪽 스택에 원소 삽입
            else {
                stackLeft.push(temp);
            }
        }

        // 오른쪽 스택에 있는 문자 왼쪽 스택으로 넘기기
        while (!stackRight.isEmpty()) {
            stackLeft.push(stackRight.pop());
        }

        // 도출된 비밀번호 문자열로 합치기
        for (int i = 0; i < stackLeft.size(); i++) {
            sb.append(stackLeft.get(i));
        }
    }

}
