import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2302_극장좌석 {
    static int N;
    static int M;
    static boolean[] fixed; // 고정석
    static int[] dp; // dp[i]: 1에서 i까지의 사람들을 자리에 배치할 경우의 수

    static int getDp() {
        dp = new int[N + 1];
        // N == 1인 경우
        if (N == 1) {
            return 1;
        }
        // N == 2인 경우
        else if (dp[N] == 2) {
            if (fixed[1] || fixed[2])
                return 1;
            return 2;
        }

        // N >= 3인 경우
        // i: 1부터 N까지 dp를 채운다

        // i = 1, 2인 경우 직접 작성
        dp[1] = 1;
        if (fixed[1] || fixed[2]) {
            dp[2] = 1;
        } else {
            dp[2] = 2;
        }

        // i는 3부터 N까지
        for (int i = 3; i <= N; i++) {
            // 만약 i가 고정석이라면
            if (fixed[i]) {
                dp[i] = dp[i - 1];
            }

            // i가 고정석이 아니고, i - 1이 고정석이라면
            else if (fixed[i - 1]) {
                dp[i] = dp[i - 2];
            }

            // i와 i - 1 모두 고정석이 아닐 때
            else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }

        }

        return dp[N];

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        fixed = new boolean[N + 1];

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            int fixedNum = Integer.parseInt(br.readLine());
            fixed[fixedNum] = true;
        }

        System.out.println(getDp());

    }

}