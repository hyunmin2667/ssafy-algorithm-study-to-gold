import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9461_파도반수열 {
    static int N;
    static long[] dp;

    static long getDp() {
        dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;

        for (int i = 6; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }

        return dp[N];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            sb.append(getDp());
            sb.append("\n");
        }

        System.out.println(sb);
    }

}
