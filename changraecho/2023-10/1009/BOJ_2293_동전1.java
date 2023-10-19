import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2293_동전1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] prices = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            prices[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j <= K; j++) {
            dp[1][j] = j % prices[1] == 0 ? 1 : 0;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (prices[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    for (int k = 0; k <= j / prices[i]; k++) {
                        dp[i][j] += dp[i - 1][j - k * prices[i]];
                    }
                }
            }
        }

        System.out.println(dp[N][K]);

    }

}
