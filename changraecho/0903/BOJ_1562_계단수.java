import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1562_계단수 {
    static long[][][] dp;
    static int N;

    static void draw() {
        for (int i = 1; i <= N; i++) {
            System.out.println(Arrays.deepToString(dp[i]));
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long ans = 0;
        N = Integer.parseInt(br.readLine());
        dp = new long[N + 1][10][4]; // idx, num, status. idx는 1부터 N까지. num은 0부터 9까지.
        // status 0: 0과 9가 없다. 1: 0만 없다 2: 9만 없다 3: 둘다 가진 상태
        // 초기화
        for (int num = 0; num < 10; num++) {
            dp[N][num][3] = 1;
        }

        for (int idx = N - 1; idx >= 1; idx--) {
            for (int num = 0; num < 10; num++) {
                if (num == 0) {
                    dp[idx][num][0] = 0;
                    dp[idx][num][1] = 0;
                    dp[idx][num][2] = dp[idx + 1][1][2];
                    dp[idx][num][3] = dp[idx + 1][1][3];

                } else if (num == 1) {
                    dp[idx][num][0] = (dp[idx + 1][0][2] + dp[idx + 1][2][0]);
                    dp[idx][num][1] = (dp[idx + 1][0][3] + dp[idx + 1][2][1]);
                    dp[idx][num][2] = (dp[idx + 1][0][2] + dp[idx + 1][2][2]);
                    dp[idx][num][3] = (dp[idx + 1][0][3] + dp[idx + 1][2][3]);
                } else if (num == 8) {
                    dp[idx][num][0] = (dp[idx + 1][7][0] + dp[idx + 1][9][1]);
                    dp[idx][num][1] = (dp[idx + 1][7][1] + dp[idx + 1][9][1]);
                    dp[idx][num][2] = (dp[idx + 1][7][2] + dp[idx + 1][9][3]);
                    dp[idx][num][3] = (dp[idx + 1][7][3] + dp[idx + 1][9][3]);
                } else if (num == 9) {
                    dp[idx][num][0] = 0;
                    dp[idx][num][1] = dp[idx + 1][8][1];
                    dp[idx][num][2] = 0;
                    dp[idx][num][3] = dp[idx + 1][8][3];
                } else {
                    for (int j = 0; j < 4; j++) {
                        dp[idx][num][j] = (dp[idx + 1][num - 1][j] + dp[idx + 1][num + 1][j]);
                    }
                }

                dp[idx][num][0] %= 1000000000;
                dp[idx][num][1] %= 1000000000;
                dp[idx][num][2] %= 1000000000;
                dp[idx][num][3] %= 1000000000;
            }
        }

        for (int num = 1; num <= 8; num++) {
            ans += dp[1][num][0];

        }

        ans += dp[1][9][1];

        System.out.println(ans % 1000000000);

    }

}
