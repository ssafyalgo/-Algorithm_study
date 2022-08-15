package study;

import java.util.Scanner;

/* N개의 정수로 이루어진 수열 */
// Q. 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램
public class Main_1182_부분수열의합 {

    static int N;               // 정수의 개수
    static int S;               // 정수, 다 더한 값

    static int[] nums;          // N개의 정수로 이루어진 수열
    static int count;           // 합이 S인 부분수열 개수

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);

        /* 입력 */
        N = scann.nextInt();
        S = scann.nextInt();

        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = scann.nextInt();
        }

        /* 로직 */
        /* 부분수열
        *  하나의 수열의 집합이 있다면 그 중에서 부분의 수열
        *  ! 원래의 수열에서 순서가 다르면 부분수열이라고 할 수 없다. ex. {-7, -3, -2} -> {-3, -7, -2} (X)
        *  부분수열은 각 원소에 대해 원소를 포함한 형태와 포함하지 않은 형태 2가지 형태로 나뉜다.
        *  이에 따라 n개의 원소를 가진 수열에 대한 부분수열은 2^n개의 경우의 수가 나오고 !여기에 공집합도 포함되어 있다. */
        // 문제에서 "크기가 양수인 부분수열 중에서" -> 이 말의 뜻은 공집합은 포함하지 않는다는 의미
        subset(0, 0);

        /* 출력 */
        // 만약, S가 0일 경우 공집합도 포함되므로, -1 추가
        if (S==0) System.out.println(count-1);
        else System.out.println(count);
    }

    // [@Method] 부분집합
    private static void subset(int index, int sum) {
        // BC - End 조건
        if (index==N) {
            if (sum==S) count++;
            return;
        }

        // 부분수열을 구하는 방법 : 지금 위치의 원소를 선택하는 방법과 선택하지 않는 방법
        // 1) 현재 원소 사용
        subset(index+1, sum + nums[index]);
        // 2) 현재 원소 사용 X
        subset(index+1, sum);
    }
}
