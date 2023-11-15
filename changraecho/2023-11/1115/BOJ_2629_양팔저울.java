import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2629_양팔저울 {
    static int N; // 추의 개수
    static int[] weights; // 추의 무게들
    static int M; // 구슬의 개수
    static int[] beadWeights; // 구슬들의 무게
    static boolean[][] dp;

    static void fillDp() {
        dp = new boolean[N + 1][30001];

        dp[1][15000] = true;
        dp[1][15000 - weights[1]] = true;
        dp[1][15000 + weights[1]] = true;

        for (int i = 2; i <= N; i++) {
            for (int s = 0; s <= 30000; s++) {
                if (dp[i - 1][s])
                    dp[i][s] = true;

                if (s - weights[i] >= 0 && dp[i - 1][s - weights[i]]) {
                    dp[i][s] = true;
                }

                if (s + weights[i] <= 30000 && dp[i - 1][s + weights[i]]) {
                    dp[i][s] = true;
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        weights = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        beadWeights = new int[M + 1];

        for (int i = 1; i <= M; i++) {
            beadWeights[i] = Integer.parseInt(st.nextToken());
        }

        fillDp();

        for (int i = 1; i <= M; i++) {

            if (15000 + beadWeights[i] <= 30000 && dp[N][15000 + beadWeights[i]]) {
                sb.append("Y");
            } else {
                sb.append("N");
            }
            sb.append(" ");
        }

        System.out.println(sb);

    }

}
