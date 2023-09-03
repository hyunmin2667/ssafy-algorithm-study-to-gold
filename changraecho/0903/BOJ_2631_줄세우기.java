import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2631_줄세우기 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] children = new int[N];

        for (int i = 0; i < N; i++) {
            children[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
        }

        int lis = 1;

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (children[i] > children[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            lis = Math.max(lis, dp[i]);
        }

        System.out.println(N - lis);

    }

}
