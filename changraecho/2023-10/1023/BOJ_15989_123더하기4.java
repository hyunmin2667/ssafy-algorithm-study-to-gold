import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_15989_123더하기4 {
    static int N;
    static int[] dp;

    static long getAns(int N) { //
        if (N == 1) {
            return 1;
        } else if (N == 2) {
            return 2;
        } else if (N == 3) {
            return 3;
        }

        dp = new int[N + 1];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for (int i = 4; i <= N; i++) {
            // 합에서 3이 있는 경우
            dp[i] = dp[i - 3];

            // 합에서 3이 없는 경우: 1과 2만을 이용하기. 2의 개수만 정하면 된다
            dp[i] += (i / 2) + 1;
        }

        return dp[N];

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            sb.append(getAns(N));
            sb.append("\n");
        }

        System.out.println(sb);

    }

}