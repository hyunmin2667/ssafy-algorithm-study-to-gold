import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865_평범한배낭 {
    static int N;
    static int K;
    static int[][] products; // products[i][0]: 무게, products[i][1]: 가치
    static int[][] dp; // N * K 배열. dp[n][k]: n번째 물건까지만 사용할 때, 물건의 무게가 k이하가 될때 가치의 최댓값

    static int getDp() {
        dp = new int[N][K + 1];

        // dp[0][0] ~ dp[0][K]까지 사용

        for (int k = 1; k <= K; k++) {
            if (products[0][0] <= k) {
                dp[0][k] = products[0][1];
            }
        }

        // induction
        // dp[i][k] = max(dp[i - 1][k] + products[i][1] + dp[i-1][k-products[i][0]])

        for (int i = 1; i < N; i++) {
            for (int k = 1; k <= K; k++) {

                dp[i][k] = dp[i - 1][k];
                if (k - products[i][0] >= 0) {
                    dp[i][k] = Math.max(dp[i][k], products[i][1] + dp[i - 1][k - products[i][0]]);
                }

            }
        }

        return dp[N - 1][K];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        products = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            products[i] = new int[] { weight, value };

        }

        System.out.println(getDp());

    }

}